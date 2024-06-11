package com.springframework.chapark.common;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Map;

public class ChaparkDAO {

	private SqlSession sqlSession;

	@Autowired
	public ChaparkDAO(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	// 단건 조회 (Map)
	public Map<String, Object> selectMap(Map<String, Object> paramMap, String mapperName, String sqlId) {
		String sql = mapperName + "." + sqlId;
		return sqlSession.selectOne(sql, paramMap);
	}

	// 단건 조회 (Object)
	public Object selectObject(Map<String, Object> paramMap, String mapperName, String sqlId) {
		String sql = mapperName + "." + sqlId;
		return sqlSession.selectOne(sql, paramMap);
	}

	// 다건 조회 (페이징 처리)
	// 추후 수정 예정
	public List<Map<String, Object>> selectPaging(Map<String, Object> paramMap) {
		return sqlSession.selectList("namespace.selectMultiplePaged", paramMap);
	}

	// 다건 조회 (List)
	// 추후 수정 예정
	public List<Map<String, Object>> selectList(Map<String, Object> paramMap) {
		return sqlSession.selectList("namespace.selectMultipleList", paramMap);
	}

	// 삽입
	public int insert(Map<String, Object> paramMap, String mapperName, String sqlId) {
		String sql = mapperName + "." + sqlId;
		return sqlSession.insert(sql, paramMap);
	}

	// 업데이트
	public int update(Map<String, Object> paramMap, String mapperName, String sqlId) {
		String sql = mapperName + "." + sqlId;
		return sqlSession.update(sql, paramMap);
	}

	// 삭제
	public int delete(Map<String, Object> paramMap, String mapperName, String sqlId) {
		String sql = mapperName + "." + sqlId;
		return sqlSession.delete(sql, paramMap);
	}
}