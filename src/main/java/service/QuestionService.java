package service;

import java.io.IOException;
import java.util.Map;

public interface QuestionService {
    Map<String, String> getQuestionsAndAnswers() throws IOException;
}
