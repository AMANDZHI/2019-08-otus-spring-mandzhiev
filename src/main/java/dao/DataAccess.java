package dao;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

public interface DataAccess {
    Map<String, String> getAll() throws IOException;
}
