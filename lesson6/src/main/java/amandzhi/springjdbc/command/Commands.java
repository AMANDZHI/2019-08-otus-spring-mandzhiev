package amandzhi.springjdbc.command;

import amandzhi.springjdbc.config.LocaleProps;
import amandzhi.springjdbc.io.IOService;
import amandzhi.springjdbc.model.Author;
import amandzhi.springjdbc.model.Book;
import amandzhi.springjdbc.model.Genre;
import amandzhi.springjdbc.service.AuthorService;
import amandzhi.springjdbc.service.BookService;
import amandzhi.springjdbc.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;

import java.util.Collection;

@ShellComponent
public class Commands {
    private String userName;
    private GenreService genreService;
    private AuthorService authorService;
    private BookService bookService;
    private IOService iOService;
    private LocaleProps localeProps;
    private MessageSource messageSource;
    private final String WELCOME = "welcome";
    private final String QUESTION_NAME_BOOK = "questionNameBook";
    private final String QUESTION_ID_BOOK = "questionIdBook";
    private final String QUESTION_NAME_AUTHOR = "questionAuthorBook";
    private final String QUESTION_NAME_GENRE = "questionGenreBook";
    private final String LOGIN_ENTER = "enterLogin";
    private final String SUCCESS = "success";
    private final String QUESTION_OLD_NAME_BOOK = "questionOldNameBook";
    private final String QUESTION_NEW_NAME_BOOK = "questionNewNameBook";
    private final String ERROR = "error";
    private final String ENTER_ID_BOOK = "enterIdBook";

    @Autowired
    public Commands(GenreService genreService, AuthorService authorService, BookService bookService, IOService iOService, LocaleProps localeProps, MessageSource messageSource) {
        this.genreService = genreService;
        this.authorService = authorService;
        this.bookService = bookService;
        this.iOService = iOService;
        this.localeProps = localeProps;
        this.messageSource = messageSource;
    }

    private Availability isLogin() {
        return userName == null ? Availability.unavailable(messageSource.getMessage(LOGIN_ENTER, null, localeProps.getLocale())) : Availability.available();
    }

    @ShellMethod(value = "Login command", key = {"l", "login"})
    public String login(@ShellOption(defaultValue = "amandzhi") String userName) {
        this.userName = userName;
        String welcome = messageSource.getMessage(WELCOME, null, localeProps.getLocale());
        return String.format(welcome + "%s", userName);
    }

    @ShellMethod(value = "add Book", key = {"addBook", "aB"})
    @ShellMethodAvailability(value = "isLogin")
    public void createBook() {
        iOService.printString(messageSource.getMessage(QUESTION_NAME_BOOK, null, localeProps.getLocale()));
        String bookTitle = iOService.readString();
        iOService.printString(messageSource.getMessage(QUESTION_NAME_AUTHOR, null, localeProps.getLocale()));
        String authorName = iOService.readString();
        iOService.printString(messageSource.getMessage(QUESTION_NAME_GENRE, null, localeProps.getLocale()));
        String genreName = iOService.readString();
        Author authorFind = authorService.findByName(authorName);
        Genre genreFind = genreService.findByName(genreName);
        Book book = new Book(null, bookTitle, authorFind, genreFind);
        bookService.insert(book);
        iOService.printString(messageSource.getMessage(SUCCESS, null, localeProps.getLocale()));
    }

    @ShellMethod(value = "find by title Book", key = {"findBook", "fB"})
    @ShellMethodAvailability(value = "isLogin")
    public void findBook() {
        iOService.printString(messageSource.getMessage(QUESTION_NAME_BOOK, null, localeProps.getLocale()));
        String bookTitle = iOService.readString();
        Book bookFind = bookService.findByName(bookTitle);
        iOService.printString(bookFind.getTitle());
    }

    @ShellMethod(value = "find by id Book", key = {"findByIdBook", "fidB"})
    @ShellMethodAvailability(value = "isLogin")
    public void findByIdBook() {
        iOService.printString(messageSource.getMessage(QUESTION_ID_BOOK, null, localeProps.getLocale()));
        String bookId = iOService.readString();
        Book bookFind = bookService.findById(Long.parseLong(bookId));
        iOService.printString(bookFind.getTitle());
    }

    @ShellMethod(value = "update Book", key = {"updateBook", "uB"})
    @ShellMethodAvailability(value = "isLogin")
    public void updateBook() {
        iOService.printString(messageSource.getMessage(QUESTION_OLD_NAME_BOOK, null, localeProps.getLocale()));
        String oldBookTitle = iOService.readString();
        Book bookFind = bookService.findByName(oldBookTitle);
        iOService.printString(messageSource.getMessage(QUESTION_NEW_NAME_BOOK, null, localeProps.getLocale()));
        String newBookTitle = iOService.readString();
        bookFind.setTitle(newBookTitle);
        bookService.update(bookFind);
        iOService.printString(messageSource.getMessage(SUCCESS, null, localeProps.getLocale()));
    }

    @ShellMethod(value = "delete by id Book", key = {"deleteBook", "dB"})
    @ShellMethodAvailability(value = "isLogin")
    public void deleteBook() {
        iOService.printString(messageSource.getMessage(ENTER_ID_BOOK, null, localeProps.getLocale()));
        String bookId = iOService.readString();
        boolean result = bookService.deleteById(Long.parseLong(bookId));
        if (result) {
            iOService.printString(messageSource.getMessage(SUCCESS, null, localeProps.getLocale()));
        } else {
            iOService.printString(messageSource.getMessage(ERROR, null, localeProps.getLocale()));
        }
    }

    @ShellMethod(value = "find all Books", key = {"findAll", "faB"})
    @ShellMethodAvailability(value = "isLogin")
    public void findAll() {
        Collection<Book> books = bookService.findAll();
        if (books.size() > 0) {
            for (Book book: books) {
                iOService.printString(book.getId().toString());
                iOService.printString(book.getTitle());
                iOService.printString(book.getAuthor().getName());
                iOService.printString(book.getGenre().getName());
            }
        } else {
            iOService.printString(messageSource.getMessage(ERROR, null, localeProps.getLocale()));
        }
    }
}