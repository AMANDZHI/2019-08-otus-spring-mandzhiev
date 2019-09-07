package executor;

import dao.Question;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import service.ConsoleService;
import service.QuestionService;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

@Service
@AllArgsConstructor
public class TestingAppImpl implements TestingApp {
    private final QuestionService service;
    private final ConsoleService consoleService;
    private int result;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    public TestingAppImpl(QuestionService service, ConsoleService consoleService) {
        this.service = service;
        this.consoleService = consoleService;
    }

    public void start() {
        String questionName = messageSource.getMessage("question.name", new String[]{}, new Locale("ru", "RU"));
        List<Question> questionsAndAnswers = service.getQuestionsAndAnswers();
        consoleService.printConsole(questionName);
        String name = consoleService.readConsole();
        for (Question q: questionsAndAnswers) {
            consoleService.printConsole(q.getText());
            String answer = consoleService.readConsole();
            if (answer.equals(q.getValidAnswer())) {
                result++;
            }
        }

        System.out.println(name + ", у вас правильных ответов: " + result);
    }
}