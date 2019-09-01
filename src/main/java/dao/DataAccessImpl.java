package dao;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.io.*;
import java.util.*;

@RequiredArgsConstructor
public class DataAccessImpl implements DataAccess {
    private final String resourceLocation;

    public Map<String, String> getAll() throws IOException {
        InputStream inputStream = ClassLoader.class.getResourceAsStream(resourceLocation);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        String line;
        Map<String, String> map = new HashMap<String, String>();
        while ((line = reader.readLine()) != null) {
            Scanner scanner = new Scanner(line);
            scanner.useDelimiter(",");
            int index = 0;
            String key = null;
            String value;
            while (scanner.hasNext()) {
                if (index == 0) {
                     key = scanner.next();
                     index++;
                } else {
                    value = scanner.next();
                    map.put(key, value);
                }
            }
        }
        return map;
    }
}
