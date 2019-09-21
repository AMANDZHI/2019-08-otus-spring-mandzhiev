package amandzhi.springBoot.service;

import amandzhi.springBoot.dao.Question;
import amandzhi.springBoot.dao.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    private final QuestionDao questionDao;

    @Autowired
    public QuestionServiceImpl(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    public List<Question> getQuestionsAndAnswers() {
       return questionDao.getAll();
    }
}