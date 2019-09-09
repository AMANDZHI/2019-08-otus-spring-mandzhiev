package executor;

import dao.Question;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import service.IOServiceServiceImpl;
import service.QuestionService;

import java.util.List;
import java.util.Locale;

@Service
@AllArgsConstructor
public class TestingAppImpl implements TestingApp {
    private final QuestionService service;
    private final IOServiceServiceImpl ioServiceService;
    private int result;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    public TestingAppImpl(QuestionService service, IOServiceServiceImpl ioServiceService) {
        this.service = service;
        this.ioServiceService = ioServiceService;
    }

    public void start() {
        String questionName = messageSource.getMessage("question.name", new String[]{}, new Locale("ru", "RU"));
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

        System.out.println(name + ", у вас правильных ответов: " + result);
    }
}