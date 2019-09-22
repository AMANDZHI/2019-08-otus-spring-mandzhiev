package amandzhi.springBoot.service;

import amandzhi.springBoot.dao.Question;

import java.util.List;

public interface QuestionService {
    List<Question> getQuestionsAndAnswers();
}