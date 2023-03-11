package kr.or.ddit.teampro.customer.service;


import kr.or.ddit.teampro.customer.dao.CustomerDao;
import kr.or.ddit.teampro.customer.vo.CustomerVO;

import java.util.List;

public class CustomerService implements ICustomerService{
    private static CustomerVO uv; //로그인한 객체 저장용

    //singleton 설정
    private static ICustomerService instance = null;

    private CustomerService() {
    }

    public static ICustomerService getInstance() {
        if (instance == null) {
            instance = new CustomerService();
        }
        return instance;
    }

    //계정등록 -> dao에서 처리
    @Override
    public int insertUser(CustomerVO cv) {
        return CustomerDao.getInstance().insertCustomer(cv);
    }

    //계정삭제 -> dao에서 처리
    @Override
    public int deleteUser(CustomerVO cv) {
        return CustomerDao.getInstance().deleteCustomer(cv);
    }


    //미구현
    @Override
    public int updateUser(CustomerVO cv) {
        return CustomerDao.getInstance().updateCustomer(cv);
    }

    //미구현
    @Override
    public List<CustomerVO> displayAll(CustomerVO cv) {
        return null;
    }


    //로그인 객체 dao에서 받아옴
    @Override
    public CustomerVO login(CustomerVO cv) {
        return CustomerDao.getInstance().login(cv);
    }

    //로그인한 객체 서비스에서만 처리하고, 로그아웃시 null 값으로 저장
    @Override
    public CustomerVO logout(CustomerVO uv) {
        this.setVo(null);
        return getVo();
    }

    //로그인한 객체를 로그인 할 때, Service 객체에서 저장
    @Override
    public void setVo(CustomerVO uv) {
        this.uv = uv;
    }

    //저장한 객체 불러오기
    @Override
    public CustomerVO getVo() {
        return uv;
    }

    //계정확인용
    @Override
    public boolean isExist(CustomerVO cv) {
        return CustomerDao.getInstance().isExist(cv);
    }


}
