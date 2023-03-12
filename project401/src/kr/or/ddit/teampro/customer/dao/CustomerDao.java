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

    //고객 등록
    @Override
    public int insertCustomer(CustomerVO uv) {
        SqlSession sqlSession = MyBatisUtil.getInstance();
        int result = 0;

        try {
            result = sqlSession.insert("customer.insert", uv);
            sqlSession.commit();
        } catch (Exception e) {
            //에러 터지면 롤백
            sqlSession.rollback();

        } finally {
            sqlSession.close();
        }
        return result;
    }
    //고객 삭제
    @Override
    public int deleteCustomer(CustomerVO uv) {
        SqlSession session = MyBatisUtil.getInstance();

        int result = session.delete("customer.delete", uv);

        //안되면 롤백
        if (!(result > 0)) session.rollback();

        session.commit();
        session.close();
        return result;
    }


    //미구현
    @Override
    public int updateCustomer(CustomerVO uv) {
       return 0;
    }

    //미구현
    @Override
    public CustomerVO displayCustomer(CustomerVO uv) {
        return null;
    }

    //계정확인여부
    @Override
    public boolean isExist(CustomerVO cv) {
        SqlSession sqlSession = MyBatisUtil.getInstance();
        boolean result =
                sqlSession.selectOne("customer.isExist", cv)!= null;

        sqlSession.commit();
        sqlSession.close();

        return result;
    }

    //로그인
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
