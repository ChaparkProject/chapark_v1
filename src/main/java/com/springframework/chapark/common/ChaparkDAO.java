package com.springframework.chapark.common;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

@Repository
public class ChaparkDAO {
	

	private SqlSession sqlSession;

	@Autowired
	public ChaparkDAO(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	// 단건 조회 (Map)
	public Map selectMap(commonMap paramMap, String sqlId) {
		return sqlSession.selectOne(sqlId, paramMap);
	}

	// 단건 조회 (Object)
	public Object selectObject(commonMap paramMap, String sqlId) {
		return sqlSession.selectOne(sqlId, paramMap);
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
