package service.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import util.serializers_deserializers.LocalDateDeserializer;
import util.serializers_deserializers.LocalDateSerializer;
import util.serializers_deserializers.LocalDateTimeDeserializer;
import util.serializers_deserializers.LocalDateTimeSerializer;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Slf4j
public class ObjectLoader {
    private static Gson gson;
    static {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer());
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer());
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateSerializer());
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateDeserializer());
        gson = gsonBuilder.setPrettyPrinting().create();
    }
    public static <T> T load(final InputStream inputStream, final Class<T> clazz) {
        try {
            if (inputStream != null) {
                final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                return gson.fromJson(reader, clazz);
            }
        }
        catch (final Exception e) {
            log.info("Exception occurred --> {}", e.getMessage());
            return null;
        }
        return null;
    }

    public static Gson getGson() {
        return gson;
    }
}
