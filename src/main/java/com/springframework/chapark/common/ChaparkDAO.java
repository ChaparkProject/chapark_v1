package com.springframework.chapark.common;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

//import javax.annotation.Resource;

@SuppressWarnings("unused")
@Repository
public class ChaparkDAO {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	

	private SqlSession sqlSession;

	@Autowired
	public ChaparkDAO(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	// 단건 조회 (Map)
	public Map selectMap(Map paramMap, String sqlId)  {
		return sqlSession.selectOne(sqlId, paramMap);
	}

	// 단건 조회 (Object)
	public Object selectObject(Map paramMap, String sqlId) {
		return sqlSession.selectOne(sqlId, paramMap);
	}

	// 다건 조회 (페이징 처리)
	// 추후 수정 예정
	public List<Map<String, Object>> selectPaging(Map<String, Object> paramMap) {
		return sqlSession.selectList("namespace.selectMultiplePaged", paramMap);
	}

	// 다건 조회 (List)
	// 추후 수정 예정
	public List selectList(Map paramMap, String sqlId) {
		return sqlSession.selectList(sqlId, paramMap);
	}

	// 삽입
	public int insert(Map paramMap, String sqlId) {
		logger.debug("Service - insert: sqlId={}, paramMap={}", sqlId, paramMap);
		return sqlSession.insert(sqlId, paramMap);
	}

	// 업데이트
	public int update(Map<String, Object> paramMap, String sqlId) {
		return sqlSession.update(sqlId, paramMap);
	}

	// 삭제
	public int delete(Map paramMap,String sqlId) {
		return sqlSession.delete(sqlId, paramMap);
	}
}
