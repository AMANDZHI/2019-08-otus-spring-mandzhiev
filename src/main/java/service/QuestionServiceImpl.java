package service;

import dao.DataAccess;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.Map;

@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final DataAccess dataAccess;

    public Map<String, String> getQuestionsAndAnswers() throws IOException {
       return dataAccess.getAll();
    }
}
