package dao;

import java.io.IOException;
import java.util.Map;

public interface QuestionDao {
    Map<Question, String> getAll();
}
