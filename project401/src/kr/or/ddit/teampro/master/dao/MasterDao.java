package kr.or.ddit.teampro.master.dao;


import kr.or.ddit.teampro.master.util.MybatisUtil;
import kr.or.ddit.teampro.master.vo.MasterVO;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class MasterDao implements IMasterDao {
    //singleton
    private static MasterDao instance = null;
    private MasterVO mv;

    private MasterDao() {
    }

    public static MasterDao getInstance() {
        if (instance == null) {
            instance = new MasterDao();
        }
        return instance;
    }

    @Override
    public int insertMaster(MasterVO mv) {
        SqlSession sqlSession = MybatisUtil.getInstance();
        int result = 0;

        result = sqlSession.insert("master.insert", mv);
        sqlSession.commit();
        sqlSession.close();

        return result;
    }

    @Override
    public int updateMaster(MasterVO mv) {
        return 0;
    }

    @Override
    public int deleteMaster(MasterVO mv) {
        SqlSession sqlSession = MybatisUtil.getInstance();
        int result = 0;

        result = sqlSession.delete("master.delete", mv);
        sqlSession.commit();
        sqlSession.close();

        return 0;
    }

    @Override
    public MasterVO display(MasterVO mv) {
        return null;
    }

    @Override
    public List<MasterVO> displayAll() {
        SqlSession sqlSession = MybatisUtil.getInstance();
        List<MasterVO> result = null;

        result = sqlSession.selectList("master.displayAll", mv);
        sqlSession.commit();
        sqlSession.close();

        return result;
    }

    @Override
    public boolean isExist(MasterVO mv) {
        SqlSession sqlSession = MybatisUtil.getInstance();
        boolean result = false;

        result = sqlSession.selectOne("master.isExist", mv)!= null;
        sqlSession.commit();
        sqlSession.close();
        return false;
    }

    @Override
    public MasterVO login(MasterVO mv) {
        SqlSession sqlSession = MybatisUtil.getInstance();
        MasterVO result = null;

        result = sqlSession.selectOne("master.login", mv);
        sqlSession.commit();
        sqlSession.close();
        return result;
    }


}
