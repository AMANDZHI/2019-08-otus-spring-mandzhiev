package service;

import dao.QuestionDao;
import dao.Question;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.Map;

@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionDao questionDao;

    public Map<Question, String> getQuestionsAndAnswers() {
       return questionDao.getAll();
    }
}
