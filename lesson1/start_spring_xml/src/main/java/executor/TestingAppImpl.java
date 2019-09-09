package executor;

import dao.Question;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import service.IOServiceImpl;
import service.QuestionService;

import java.util.List;

@AllArgsConstructor
@RequiredArgsConstructor
public class TestingAppImpl implements TestingApp {
    private final QuestionService service;
    private final IOServiceImpl IOServiceImpl;
    private int result;

    public void start() {
        String questionName = "Введите свою фамилию и имя";
        List<Question> questionsAndAnswers = service.getQuestionsAndAnswers();
        IOServiceImpl.printString(questionName);
        String name = IOServiceImpl.readString();
        for (Question q: questionsAndAnswers) {
            IOServiceImpl.printString(q.getText());
            String answer = IOServiceImpl.readString();
            if (answer.equals(q.getValidAnswer())) {
                result++;
            }
        }

        System.out.println(name + ", у вас правильных ответов: " + result);
    }
}