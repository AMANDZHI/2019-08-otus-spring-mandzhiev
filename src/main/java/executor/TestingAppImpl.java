package executor;

import dao.Question;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import service.ConsoleService;
import service.QuestionService;

import java.util.Map;
import java.util.Scanner;

@AllArgsConstructor
@RequiredArgsConstructor
public class TestingAppImpl implements TestingApp {
    private final QuestionService service;
    private final ConsoleService consoleService;
    private int result;

    public void start() {
        Map<Question, String> questionsAndAnswers = service.getQuestionsAndAnswers();
        Scanner scanner = consoleService.gerConsole();
        System.out.println("Введите свою фамилию и имя");
        String name = scanner.next();
        for (Map.Entry<Question, String> pair: questionsAndAnswers.entrySet()) {
            System.out.println(pair.getKey().getText());
            String answer = scanner.next();
            if (answer.equals(pair.getValue())) {
                result++;
            }
        }

        System.out.println(name + ", у вас правильных ответов: " + result);
    }
}