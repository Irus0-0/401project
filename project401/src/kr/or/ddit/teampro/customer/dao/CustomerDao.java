package kr.or.ddit.teampro.customer.dao;

import customer.util.MybatisUtil;
import customer.vo.CustomerVO;
import org.apache.ibatis.session.SqlSession;


public class CustomerDao implements ICustomerDao {
    private SqlSession sqlSession;

    //singleton 생성
    private static CustomerDao instance = null;
    private CustomerVO uv;

    private CustomerDao() {
    }

    public static CustomerDao getInstance() {
        if (instance == null) {
            instance = new CustomerDao();
        }
        return instance;
    }

    @Override
    public int insertCustomer(CustomerVO uv) {
        SqlSession sqlSession = MybatisUtil.getInstance();
        int result = 0;

        try {
            result = sqlSession.insert("customer.insert", uv);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return result;
    }

    @Override
    public int deleteCustomer(String customerId) {
        SqlSession sqlSession = MybatisUtil.getInstance();
        int result = 0;

        try {
            result = sqlSession.delete("customer.delete", customerId);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return result;
    }

    @Override
    public int updateCustomer(CustomerVO uv) {
        return 0;
    }

    @Override
    public CustomerVO displayCustomer(CustomerVO uv) {
        return null;
    }

    @Override
    public CustomerVO login(String customerId, String customerPw) {
        SqlSession sqlSession = MybatisUtil.getInstance();
        CustomerVO result = null;

        try {
            CustomerVO uv = new CustomerVO();
            uv.setCustomerId(customerId);
            uv.setCustomerPw(customerPw);

            result = sqlSession.selectOne("customer.login", uv);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return result;
    }

    @Override
    public boolean isExist(String customerId) {
        SqlSession sqlSession = MybatisUtil.getInstance();
        boolean result = false;

        try {
            CustomerVO uv = new CustomerVO();
            uv.setCustomerId(customerId);

            result = sqlSession.selectOne("customer.isExist", uv)!= null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return result;
    }
}
