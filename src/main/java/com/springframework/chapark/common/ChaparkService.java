package com.springframework.chapark.common;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChaparkService {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private final ChaparkDAO chaparkDAO;

	@Autowired
	public ChaparkService(ChaparkDAO chaparkDAO) {
		this.chaparkDAO = chaparkDAO;
	}

	// 단건 조회 (Map)
	public Map selectMap(String sqlId, Map paramMap) throws Exception {
		return chaparkDAO.selectMap(paramMap, sqlId);
	}

	// 단건 조회 (Object)
	public Object selectObject(String sqlId,Map paramMap) {
		return chaparkDAO.selectObject(paramMap, sqlId);
	}

	// 다건 조회 (페이징 처리)
	// 추후 수정 예정
	public List<Map<String, Object>> selectPaging(Map<String, Object> paramMap) {
		// 여기에 페이징 처리 로직 추가
		return chaparkDAO.selectPaging(paramMap);
	}

	// 다건 조회 (List)
	// 추후 수정 예정
	public List selectList(String sqlId,Map paramMap) {
		// 여기에 다건 조회 로직 추가
		return chaparkDAO.selectList(paramMap, sqlId);
	}

	// 삽입
	public int insert(String sqlId, Map paramMap) {
		logger.debug("Service - insert: sqlId={}, paramMap={}", sqlId, paramMap);
		return chaparkDAO.insert(paramMap, sqlId);
	}

	// 업데이트
	public int update( String sqlId, Map paramMap) {
		return chaparkDAO.update(paramMap, sqlId);
	}

	// 삭제
	public int delete(String sqlId, Map paramMap) {
		return chaparkDAO.delete(paramMap, sqlId);
	}
}
