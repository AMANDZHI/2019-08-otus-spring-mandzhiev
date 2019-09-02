package service;

import dao.Question;

import java.io.IOException;
import java.util.Map;

public interface QuestionService {
    Map<Question, String> getQuestionsAndAnswers();
}
