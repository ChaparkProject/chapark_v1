package com.springframework.chapark.common;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

public class chaparkService {
	private final ChaparkDAO chaparkDAO;

	@Autowired
	public chaparkService(ChaparkDAO chaparkDAO) {
		this.chaparkDAO = chaparkDAO;
	}

	// 단건 조회 (Map)
	public Map<String, Object> selectMap(Map<String, Object> paramMap, String mapperName, String sqlId) {
		return chaparkDAO.selectMap(paramMap, mapperName, sqlId);
	}

	// 단건 조회 (Object)
	public Object selectObject(Map<String, Object> paramMap, String mapperName, String sqlId) {
		return chaparkDAO.selectObject(paramMap, mapperName, sqlId);
	}

	// 다건 조회 (페이징 처리)
	// 추후 수정 예정
	public List<Map<String, Object>> selectPaging(Map<String, Object> paramMap) {
		// 여기에 페이징 처리 로직 추가
		return chaparkDAO.selectPaging(paramMap);
	}

	// 다건 조회 (List)
	// 추후 수정 예정
	public List<Map<String, Object>> selectList(Map<String, Object> paramMap) {
		// 여기에 다건 조회 로직 추가
		return chaparkDAO.selectList(paramMap);
	}

	// 삽입
	public int insert(Map<String, Object> paramMap, String mapperName, String sqlId) {
		return chaparkDAO.insert(paramMap, mapperName, sqlId);
	}

	// 업데이트
	public int update(Map<String, Object> paramMap, String mapperName, String sqlId) {
		return chaparkDAO.update(paramMap, mapperName, sqlId);
	}

	// 삭제
	public int delete(Map<String, Object> paramMap, String mapperName, String sqlId) {
		return chaparkDAO.delete(paramMap, mapperName, sqlId);
	}
}
