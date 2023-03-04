package kr.or.ddit.teampro.company.dao;

import kr.or.ddit.teampro.company.util.MybatisUtil;
import kr.or.ddit.teampro.company.vo.CompanyVO;
import org.apache.ibatis.session.SqlSession;

public class CompanyDao implements ICompanyDao {
    private SqlSession sqlSession;

    //singleton 생성
    private static CompanyDao instance = null;
    private CompanyVO uv;

    private CompanyDao() {
    }

    public static CompanyDao getInstance() {
        if (instance == null) {
            instance = new CompanyDao();
        }
        return instance;
    }

    @Override
    public int insertCompany(CompanyVO uv) {
        SqlSession sqlSession = MybatisUtil.getInstance();
        int result = 0;

        try {
            result = sqlSession.insert("company.insert", uv);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return result;
    }

    @Override
    public int deleteCompany(String customerId) {
        SqlSession sqlSession = MybatisUtil.getInstance();
        int result = 0;

        try {
            result = sqlSession.delete("company.delete", customerId);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return result;
    }

    @Override
    public int updateCompany(CompanyVO uv) {
        return 0;
    }

    @Override
    public CompanyVO displayCompany(CompanyVO uv) {
        return null;
    }

    @Override
    public CompanyVO login(String companyId, String customerPw) {
        SqlSession sqlSession = MybatisUtil.getInstance();
        CompanyVO result = null;

        try {
            CompanyVO uv = new CompanyVO();
            uv.setCompanyPw(companyId);
            uv.setCompanyPw(customerPw);

            result = sqlSession.selectOne("company.login", uv);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return result;
    }

    @Override
    public boolean isExist(String companyId) {
        SqlSession sqlSession = MybatisUtil.getInstance();
        boolean result = false;

        try {
            CompanyVO uv = new CompanyVO();
            uv.setCompanyId(companyId);

            result = sqlSession.selectOne("company.isExist", uv)!= null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return result;
    }
}
