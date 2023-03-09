package com.placeteam.backend.helper;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

public class ServerUtils {
    private static final ObjectMapper objectMapper = createObjectMapper();
    private static ObjectMapper createObjectMapper() {
        JsonMapper.Builder builder = JsonMapper.builder();
        builder = builder.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS, true);
        builder = builder.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JsonMapper buildMapper = builder.build();
        return buildMapper;
    }
    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }
}
