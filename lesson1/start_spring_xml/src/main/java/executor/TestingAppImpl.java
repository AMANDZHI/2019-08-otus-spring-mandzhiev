package executor;

import dao.Question;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import service.IOService;
import service.QuestionService;

import java.util.List;

@AllArgsConstructor
@RequiredArgsConstructor
public class TestingAppImpl implements TestingApp {
    private final QuestionService service;
    private final IOService ioService;
    private int result;

    public void start() {
        String questionName = "Введите свою фамилию и имя";
        List<Question> questionsAndAnswers = service.getQuestionsAndAnswers();
        ioService.printString(questionName);
        String name = ioService.readString();
        for (Question q: questionsAndAnswers) {
            ioService.printString(q.getText());
            String answer = ioService.readString();
            if (answer.equals(q.getValidAnswer())) {
                result++;
            }
        }

        System.out.println(name + ", у вас правильных ответов: " + result);
    }
}