package console;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import service.QuestionService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Scanner;

@AllArgsConstructor
@RequiredArgsConstructor
public class ConsoleImpl implements Console {
    private final QuestionService service;
    private int result;

    public void start() throws IOException {
        Map<String, String> questionsAndAnswers = service.getQuestionsAndAnswers();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Scanner scanner = new Scanner(reader);
        for (Map.Entry<String, String> pair: questionsAndAnswers.entrySet()) {
            System.out.println(pair.getKey());
            String answer = scanner.next();
            if (answer.equals(pair.getValue())) {
                result++;
            }
        }

        System.out.println("Правильных ответов: " + result);
    }
}