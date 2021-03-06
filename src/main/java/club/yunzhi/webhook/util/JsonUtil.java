package club.yunzhi.webhook.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.regex.Pattern;

/**
 * εεΊεε
 */
@Slf4j
public class JsonUtil {

  private static final Pattern JSON_MAPPING_EXCEPTION_TEMPLATE =
          Pattern.compile("^Missing required creator property \'(?<property>.*)\'");

  public static final ObjectMapper MAPPER = new ObjectMapper();
  public static final ObjectMapper CAMEL_MAPPER = new ObjectMapper();

  private JsonUtil() throws IllegalAccessException {
    throw new IllegalAccessException();
  }

  static {
    MAPPER.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
    MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    MAPPER.registerModule(new JavaTimeModule());
    CAMEL_MAPPER.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CAMEL_CASE);
    CAMEL_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    CAMEL_MAPPER.registerModule(new JavaTimeModule());
  }

  public static <T> T deserializeFromJson(String jsonString, Class<T> clazz) throws IOException {
    if (jsonString == null) {
      return null;
    }
    return MAPPER.readValue(jsonString, clazz);
  }

  public static <T> T deserializeFromJson(String jsonString, TypeReference<T> typeReference, boolean enableCamelCase) throws IOException {
    ObjectMapper mapper = MAPPER;
    if (enableCamelCase) {
      mapper = CAMEL_MAPPER;
    }
    if (jsonString == null) {
      return null;
    }
    return mapper.readValue(jsonString, typeReference);
  }

  public static String serializeToJson(Object object) {
    return serializeToJson(object, false);
  }

  public static String serializeToJson(Object object, boolean enableCamelCase) {
    ObjectMapper mapper = MAPPER;
    if (enableCamelCase) {
      mapper = CAMEL_MAPPER;
    }
    try {
      if (object == null) {
        return "";
      }
      return mapper.writeValueAsString(object);
    } catch (IOException e) {
      log.info("catch exception when serializeToJson, object:{}, e:{}", object, e);
      throw new IllegalArgumentException("Can't serialize object to json.");
    }
  }

  public static JsonNode serializeToJsonNode(Object object) {
    return serializeToJsonNode(object, false);
  }

  public static JsonNode serializeToJsonNode(Object object, boolean enableCamelCase) {
    try {
      ObjectMapper mapper = MAPPER;
      if (enableCamelCase) {
        mapper = CAMEL_MAPPER;
      }
      if (object == null) {
        return createObjectNode();
      }
      return mapper.readTree(serializeToJson(object, enableCamelCase));
    } catch (IOException e) {
      log.info("catch exception when serializeToJsonNode, object:{}, e:{}", object, e);
      throw new IllegalArgumentException("Can't serialize object to json node.");
    }
  }

  public static ObjectNode createObjectNode() {
    return createObjectNode(false);
  }

  public static ObjectNode createObjectNode(boolean enableCamelCase) {
    ObjectMapper mapper = MAPPER;
    if (enableCamelCase) {
      mapper = CAMEL_MAPPER;
    }
    return mapper.createObjectNode();
  }
}
