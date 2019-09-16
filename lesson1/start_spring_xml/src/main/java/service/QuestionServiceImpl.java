package service;

import dao.Question;
import dao.QuestionDao;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionDao questionDao;

    public List<Question> getQuestionsAndAnswers() {
       return questionDao.getAll();
    }
}