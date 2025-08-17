package com.example.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T extends Object> String toJson(final T userClass) throws JsonProcessingException {
        return objectMapper.writeValueAsString(userClass);
    }

    public static <T extends Class> Object getObject(final String jsonString, final T userObjectType) throws JsonProcessingException {
        return objectMapper.readValue(jsonString, userObjectType);
    }
}
