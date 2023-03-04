package kr.or.ddit.teampro.customer.dao;

import customer.util.MybatisUtil;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;

import java.util.Collections;
import java.util.List;


public class MyBatisDao {
	
	
	public <T> T selectOne(String statement) {
		
		SqlSession sqlSession = MybatisUtil.getInstance(true);  // 세션 열기
		
		T obj = null;
		
		try {
			
			obj = sqlSession.selectOne(statement);
			
		} catch (PersistenceException e) {
			throw new RuntimeException("마이바티스에서 데이터 조회 중 예외 발생!!!", e);
		} finally {
			sqlSession.close();
		}
		
		return obj;
	}
	
	
	public <T> T selectOne(String statement, Object parameter) {
		
		SqlSession sqlSession = MybatisUtil.getInstance(true);  // 세션 열기
		
		T obj = null;
		
		try {
			
			obj = sqlSession.selectOne(statement, parameter);
			
		} catch (PersistenceException e) {
			throw new RuntimeException("마이바티스에서 데이터 조회 중 예외 발생!!!", e);
		} finally {
			sqlSession.close();
		}
		
		return obj;
	}
	
	
	public <T> List<T> selectList(String statement) {
		
		SqlSession sqlSession = MybatisUtil.getInstance();
		
		List<T> list = Collections.EMPTY_LIST;  // 사이즈가 0인 비어있는 리스트로 초기화 (null 로 초기화 하는 것과는 다름)
		
		try {
			
			list = sqlSession.selectList(statement);
			
		} catch (PersistenceException e) {
			throw new RuntimeException("마이바티스에서 목록 조회 중 예외 발생!!!", e);
		} finally {
			sqlSession.close();
		}
		
		return list;
	}
	
	
	public <T> List<T> selectList(String statement, Object parameter) {
		
		SqlSession sqlSession = MybatisUtil.getInstance();
		
		List<T> list = Collections.EMPTY_LIST;  // 사이즈가 0인 비어있는 리스트로 초기화 (null 로 초기화 하는 것과는 다름)
		
		try {
			
			list = sqlSession.selectList(statement, parameter);
			
		} catch (PersistenceException e) {
			throw new RuntimeException("마이바티스에서 목록 조회 중 예외 발생!!!", e);
		} finally {
			sqlSession.close();
		}
		
		return list;
	}
	
	
	public int insert(String statement, Object parameter) {
		
		SqlSession sqlSession = MybatisUtil.getInstance();
		
		int cnt = 0;
		
		try {
			
			cnt = sqlSession.insert(statement, parameter);
			
			sqlSession.commit();
			
		} catch (PersistenceException e) {
			sqlSession.rollback();
			throw new RuntimeException("마이바티스에서 데이터 등록 중 예외 발생!!!", e);
		} finally {
			sqlSession.close();
		}
		
		return cnt;
	}
	
	
	public int update(String statement, Object parameter) {
		
		SqlSession sqlSession = MybatisUtil.getInstance();
		
		int cnt = 0;
		
		try {
			
			cnt = sqlSession.update(statement, parameter);
			
			sqlSession.commit();
			
		} catch (PersistenceException e) {
			sqlSession.rollback();
			throw new RuntimeException("마이바티스에서 데이터 수정 중 예외 발생!!!", e);
		} finally {
			sqlSession.close();
		}
		
		return cnt;
	}
	
	
	public int delete(String statement, Object parameter) {
		
		SqlSession sqlSession = MybatisUtil.getInstance();
		
		int cnt = 0;
		
		try {
			
			cnt = sqlSession.delete(statement, parameter);
			
			sqlSession.commit();
			
		} catch (PersistenceException e) {
			sqlSession.rollback();
			throw new RuntimeException("마이바티스에서 데이터 삭제 중 예외 발생!!!", e);
		} finally {
			sqlSession.close();
		}
		
		return cnt;
	}
}








