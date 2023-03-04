package kr.or.ddit.teampro.customer.service;

import customer.vo.CustomerVO;

public interface ICustomerService {
    public int insertUser(CustomerVO uv);
    public int deleteUser(String uv);
    public int updateUser(CustomerVO uv);
    public CustomerVO displayUser(CustomerVO uv);
    public CustomerVO login(String userId, String userPw);
    public CustomerVO logout(CustomerVO uv);
    public void setVo(CustomerVO uv);
    public CustomerVO getVo();
    public boolean isExist(String userId);

}
