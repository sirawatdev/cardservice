package com.hackathon.cardservice.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import java.io.IOException;

public class Util {
    private static ObjectMapper obMap = new ObjectMapper();

    public static <T> T readValue(String jsonValue, Class<T> tClass) throws IOException {
        return obMap.readValue(jsonValue, tClass);
    }
}
