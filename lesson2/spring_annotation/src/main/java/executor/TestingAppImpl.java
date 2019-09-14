package executor;

import dao.Question;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import service.IOServiceServiceImpl;
import service.LocaleService;
import service.QuestionService;

import java.util.List;
import java.util.Locale;

@Service
@AllArgsConstructor
public class TestingAppImpl implements TestingApp {
    private final QuestionService service;
    private final IOServiceServiceImpl ioServiceService;
    private int result;
    private final String QUESTION = "question";
    private final String FINAL_TEXT = "finalText";
    private final LocaleService localeService;
    private MessageSource messageSource;

    @Autowired
    public TestingAppImpl(QuestionService service, IOServiceServiceImpl ioServiceService, LocaleService localeService, MessageSource messageSource) {
        this.service = service;
        this.ioServiceService = ioServiceService;
        this.localeService = localeService;
        this.messageSource = messageSource;
    }

    public void start() {
        String questionName = messageSource.getMessage(QUESTION, null, localeService.getLocale());
        List<Question> questionsAndAnswers = service.getQuestionsAndAnswers();
        ioServiceService.printString(questionName);
        String name = ioServiceService.readString();
        for (Question q: questionsAndAnswers) {
            ioServiceService.printString(q.getText());
            String answer = ioServiceService.readString();
            if (answer.equals(q.getValidAnswer())) {
                result++;
            }
        }
        String validText = messageSource.getMessage(FINAL_TEXT, null, localeService.getLocale());
        System.out.println(name + validText + result);
    }
}