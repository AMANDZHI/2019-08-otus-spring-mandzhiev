package dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class QuestionDaoImpl implements QuestionDao {
    private String resourceLocation;

    public QuestionDaoImpl(@Value("${name.file}") String resourceLocation) {
        this.resourceLocation = resourceLocation;
    }

    public List<Question> getAll() {
        List<Question> questions = new ArrayList<Question>();
        try {
            InputStream inputStream = QuestionDaoImpl.class.getClassLoader().getResourceAsStream(resourceLocation);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            String line;
            while ((line = reader.readLine()) != null) {
                Scanner scanner = new Scanner(line);
                scanner.useDelimiter(",");
                int index = 0;
                String questionText = null;
                String answerValid;
                while (scanner.hasNext()) {
                    if (index == 0) {
                         questionText = scanner.next();
                         index++;
                    } else {
                        answerValid = scanner.next();
                        questions.add(new Question(questionText, answerValid));
                    }
                }
            }
            return questions;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return questions;
    }
}