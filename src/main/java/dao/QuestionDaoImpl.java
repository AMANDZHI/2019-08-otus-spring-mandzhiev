package dao;

import lombok.RequiredArgsConstructor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@RequiredArgsConstructor
public class QuestionDaoImpl implements QuestionDao {
    private final String resourceLocation;

    public Map<Question, String> getAll() {
        Map<Question, String> map = new HashMap<Question, String>();
        try {
            InputStream inputStream = QuestionDaoImpl.class.getClassLoader().getResourceAsStream(resourceLocation);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            String line;
            while ((line = reader.readLine()) != null) {
                Scanner scanner = new Scanner(line);
                scanner.useDelimiter(",");
                int index = 0;
                String key = null;
                String value;
                while (scanner.hasNext()) {
                    if (index == 0) {
                         key = scanner.next();
                         index++;
                    } else {
                        value = scanner.next();
                        map.put(new Question(key), value);
                    }
                }
            }
            return map;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }
}