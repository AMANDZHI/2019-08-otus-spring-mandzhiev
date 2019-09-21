package amandzhi.springBoot.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import amandzhi.springBoot.util.LocaleProps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class QuestionDaoImpl implements QuestionDao {
    private final LocaleProps localeProps;

    @Autowired
    public QuestionDaoImpl(LocaleProps localeProps) {
        this.localeProps = localeProps;
    }

    public List<Question> getAll() {
        List<Question> questions = new ArrayList<Question>();
        try {
            InputStream inputStream = QuestionDaoImpl.class.getClassLoader().getResourceAsStream(localeProps.getNameFile());
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