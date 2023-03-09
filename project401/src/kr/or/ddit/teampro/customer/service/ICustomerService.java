package kr.or.ddit.teampro.customer.service;

import kr.or.ddit.teampro.customer.vo.CustomerVO;

import java.util.List;

public interface ICustomerService {
    public int insertUser(CustomerVO cv);
    public int deleteUser(CustomerVO cv);
    public int updateUser(CustomerVO cv);
    public List<CustomerVO> displayAll(CustomerVO cv);
    public CustomerVO login(CustomerVO cv);
    public CustomerVO logout(CustomerVO cv);
    public void setVo(CustomerVO cv);
    public CustomerVO getVo();
    public boolean isExist(CustomerVO cv);

}
