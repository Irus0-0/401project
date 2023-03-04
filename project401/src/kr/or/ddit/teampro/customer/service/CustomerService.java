package kr.or.ddit.teampro.customer.service;

import customer.dao.CustomerDao;
import customer.vo.CustomerVO;

public class CustomerService implements ICustomerService{

    //singleton 설정
    private static ICustomerService instance = null;
    private static CustomerVO uv;

    private CustomerService() {
    }

    public static ICustomerService getInstance() {
        if (instance == null) {
            instance = new CustomerService();
        }
        return instance;
    }

    @Override
    public int insertUser(CustomerVO uv) {
        return CustomerDao.getInstance().insertCustomer(uv);
    }

    @Override
    public int deleteUser(String userId) {
        return CustomerDao.getInstance().deleteCustomer(userId);
    }

    @Override
    public int updateUser(CustomerVO uv) {
        return CustomerDao.getInstance().updateCustomer(uv);
    }

    @Override
    public CustomerVO displayUser(CustomerVO uv) {
        return null;
    }

    @Override
    public CustomerVO login(String userId, String userPwd) {
        return CustomerDao.getInstance().login(userId,userPwd);
    }

    @Override
    public CustomerVO logout(CustomerVO uv) {
        this.setVo(null);
        return getVo();
    }

    @Override
    public void setVo(CustomerVO uv) {
        this.uv = uv;
    }

    @Override
    public CustomerVO getVo() {
        return uv;
    }

    @Override
    public boolean isExist(String userId) {
        return CustomerDao.getInstance().isExist(userId);
    }


}
