package kr.or.ddit.teampro.customer.dao;

import kr.or.ddit.teampro.customer.vo.CustomerVO;

public interface ICustomerDao {
    public int insertCustomer(CustomerVO uv);
    public int deleteCustomer(CustomerVO uv);
    public int updateCustomer(CustomerVO uv);
    public CustomerVO displayCustomer(CustomerVO uv);
    public CustomerVO login(CustomerVO cv);
    public boolean isExist(CustomerVO uv);

}
