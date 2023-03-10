package kr.or.ddit.teampro.comm.dao;

import java.util.Collections;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.teampro.util.MyBatisUtil;

public class MyBatisDao {

	public <T> T selectOne(String statement) {
		SqlSession sqlSession = MyBatisUtil.getInstance();
		T obj = null;

		try {
			obj = sqlSession.selectOne(statement);
		} catch (PersistenceException e) {
			throw new RuntimeException("마이바티스에서 데이터 조회 중 예외 발생!!", e);
		} finally {
			sqlSession.close();
		}
		return obj;
	}

	public <T> T selectOne(String statement, Object parameter) {
		SqlSession sqlSession = MyBatisUtil.getInstance();
		T obj = null;

		try {
			obj = sqlSession.selectOne(statement, parameter);
		} catch (PersistenceException e) {
			throw new RuntimeException("마이바티스에서 데이터 조회 중 예외 발생!!", e);
		} finally {
			sqlSession.close();
		}
		return obj;
	}

	public <T> List<T> selectList(String statement) {
		SqlSession sqlSession = MyBatisUtil.getInstance();

		List<T> list = Collections.EMPTY_LIST;

		try {

			list = sqlSession.selectList(statement);
		} catch (PersistenceException e) {
			throw new RuntimeException("마이바티스에서 데이터 조회 중 예외 발생!!", e);
		} finally {
			sqlSession.close();
		}
		return list;
	}

	public <T> List<T> selectList(String statement, Object parameter) {
		SqlSession sqlSession = MyBatisUtil.getInstance();

		List<T> list = Collections.EMPTY_LIST;

		try {
			list = sqlSession.selectList(statement, parameter);
		} catch (PersistenceException e) {
			throw new RuntimeException("마이바티스에서 데이터 조회 중 예외 발생!!", e);
		} finally {
			sqlSession.close();
		}
		return list;
	}

	public int insert(String statement, Object parameter) {
		SqlSession sqlSession = MyBatisUtil.getInstance();

		int cnt = 0;

		try {
			cnt = sqlSession.insert(statement, parameter);

			sqlSession.commit();

		} catch (PersistenceException e) {
			sqlSession.rollback();
			throw new RuntimeException("마이바티스에서 데이터 등록 중 예외발생!", e);

		} finally {
			sqlSession.close();
		}

		return cnt;
	}

	public int update(String statement, Object parameter) {
		SqlSession sqlSession = MyBatisUtil.getInstance();

		int cnt = 0;

		try {
			cnt = sqlSession.update(statement, parameter);

			sqlSession.commit();

		} catch (PersistenceException e) {
			sqlSession.rollback();
			throw new RuntimeException("마이바티스에서 데이터 수정 중 예외발생!", e);

		} finally {
			sqlSession.close();
		}

		return cnt;
	}

	public int delete(String statement, Object parameter) {
		SqlSession sqlSession = MyBatisUtil.getInstance();

		int cnt = 0;

		try {
			cnt = sqlSession.delete(statement, parameter);

			sqlSession.commit();

		} catch (PersistenceException e) {
			sqlSession.rollback();
			throw new RuntimeException("마이바티스에서 데이터 삭제 중 예외발생!", e);

		} finally {
			sqlSession.close();
		}

		return cnt;
	}
	
	

}
