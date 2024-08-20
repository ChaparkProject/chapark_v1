package com.springframework.chapark.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

/**
 * Json 형변환 공통 유틸리티
 */
public class JsonUtil {

	private static final ObjectMapper objectMapper = new ObjectMapper();
	private static final TypeReference<Map<String, Object>> typeReference = new TypeReference<Map<String, Object>>() {
	};

	/**
	 * Json에서 Map으로 형변환
	 * 
	 * @param jsonString
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> JsonToMap(String jsonString) throws Exception {
		return objectMapper.readValue(jsonString, typeReference);
	}

	// Map에서 JSON 문자열로 변환
	public static String mapToJson(Map<String, Object> map) throws Exception {
		return objectMapper.writeValueAsString(map);
	}
}
