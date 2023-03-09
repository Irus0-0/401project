package kr.or.ddit.teampro.customer.service;


import kr.or.ddit.teampro.customer.dao.CustomerDao;
import kr.or.ddit.teampro.customer.vo.CustomerVO;

import java.util.List;

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
    public int insertUser(CustomerVO cv) {
        return CustomerDao.getInstance().insertCustomer(cv);
    }

    @Override
    public int deleteUser(CustomerVO cv) {
        return CustomerDao.getInstance().deleteCustomer(cv);
    }


    @Override
    public int updateUser(CustomerVO cv) {
        return CustomerDao.getInstance().updateCustomer(cv);
    }

    @Override
    public List<CustomerVO> displayAll(CustomerVO cv) {
        return null;
    }


    @Override
    public CustomerVO login(CustomerVO cv) {
        return CustomerDao.getInstance().login(cv);
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
    public boolean isExist(CustomerVO cv) {
        return CustomerDao.getInstance().isExist(cv);
    }


}
