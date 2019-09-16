package service;

import dao.Question;

import java.util.List;

public interface QuestionService {
    List<Question> getQuestionsAndAnswers();
}