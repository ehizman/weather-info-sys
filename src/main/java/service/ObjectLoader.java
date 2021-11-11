package service;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

@Slf4j
public class ObjectLoader {
    public static <T> T load(final InputStream inputStream, final Class<T> clazz) {
        try {
            if (inputStream != null) {
                final Gson gson = new Gson();
                final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                return gson.fromJson(reader, clazz);
            }
        }
        catch (final Exception e) {
            log.info("Exception occurred --> {}", e.getMessage());
        }
        return null;
    }
}
