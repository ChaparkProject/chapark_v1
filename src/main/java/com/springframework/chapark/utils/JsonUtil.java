package com.springframework.chapark.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

	/**
	 * Map에서 JSON 문자열로 변환
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public static String mapToJson(Map<String, Object> map) throws Exception {
		return objectMapper.writeValueAsString(map);
	}
	
	/**
	 * Json 데이트포맷(날짜)
	 * @param date
	 * @return
	 */
	public static LocalDate jsonDateFormat (String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return LocalDate.parse(date, formatter);
	}
	
	/**
	 * Json 데이트포맷(날짜 + 시간)
	 * @param date
	 * @return
	 */
	public static LocalDate jsonDateFormatTime (String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return LocalDate.parse(date, formatter);
	}
}
