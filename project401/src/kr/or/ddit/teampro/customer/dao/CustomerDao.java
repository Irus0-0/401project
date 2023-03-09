package kr.or.ddit.teampro.customer.dao;


import kr.or.ddit.teampro.customer.vo.CustomerVO;
import org.apache.ibatis.session.SqlSession;
import util.MyBatisUtil;

public class CustomerDao implements ICustomerDao {

    //singleton 생성
    private static CustomerDao instance = null;

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
        SqlSession sqlSession = MyBatisUtil.getInstance();
        int result = 0;

        try {
            result = sqlSession.insert("customer.insert", uv);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return result;
    }

    @Override
    public int deleteCustomer(CustomerVO uv) {
        SqlSession session = MyBatisUtil.getInstance();

        int result = session.delete("customer.delete", uv);

        if (!(result > 0)) session.rollback();

        session.commit();
        session.close();
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
    public boolean isExist(CustomerVO cv) {
        SqlSession sqlSession = MyBatisUtil.getInstance();
        boolean result =
                sqlSession.selectOne("customer.isExist", cv)!= null;

        sqlSession.commit();
        sqlSession.close();

        return result;
    }

    @Override
    public CustomerVO login(CustomerVO cv) {
        SqlSession sqlSession = MyBatisUtil.getInstance();
        CustomerVO result =
                sqlSession.selectOne("customer.login", cv);

        sqlSession.commit();
        sqlSession.close();

        return result;
    }
}
