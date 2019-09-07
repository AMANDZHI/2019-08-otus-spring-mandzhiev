package executor;

import dao.Question;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import service.ConsoleService;
import service.QuestionService;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

@AllArgsConstructor
@RequiredArgsConstructor
public class TestingAppImpl implements TestingApp {
    private final QuestionService service;
    private final ConsoleService consoleService;
    private int result;

    public void start() {
        String questionName = "Введите свою фамилию и имя";
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