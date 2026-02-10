package org.example.floatnovel.utility;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

    public class JsonUtil {

        private static final ObjectMapper MAPPER = new ObjectMapper();

        // 对象 → JSON 字符串
        public static String toJson(Object obj) {
            try {
                return MAPPER.writeValueAsString(obj);
            } catch (Exception e) {
                throw new RuntimeException("JSON序列化失败", e);
            }
        }

        // JSON → 对象
        public static <T> T fromJson(String json, Class<T> clazz) {
            try {
                return MAPPER.readValue(json, clazz);
            } catch (Exception e) {
                throw new RuntimeException("JSON反序列化失败", e);
            }
        }

        // JSON → List / Map / 泛型对象（非常重要）
        public static <T> T fromJson(String json, TypeReference<T> type) {
            try {
                return MAPPER.readValue(json, type);
            } catch (Exception e) {
                throw new RuntimeException("JSON反序列化失败", e);
            }
        }
    }



