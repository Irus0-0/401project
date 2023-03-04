package kr.or.ddit.teampro.customer.dao;

import customer.vo.CustomerVO;

public interface ICustomerDao {
    public int insertCustomer(CustomerVO uv);
    public int deleteCustomer(String customerId);
    public int updateCustomer(CustomerVO uv);
    public CustomerVO displayCustomer(CustomerVO uv);
    public CustomerVO login(String customerId,String customerPw);
    public boolean isExist(String customerId);

}
