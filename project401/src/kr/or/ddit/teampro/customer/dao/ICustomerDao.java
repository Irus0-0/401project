package kr.or.ddit.teampro.customer.dao;

import kr.or.ddit.teampro.customer.vo.CustomerVO;

public interface ICustomerDao {
    public int insertCustomer(CustomerVO uv); //고객등록
    public int deleteCustomer(CustomerVO uv); //고객삭제
    public int updateCustomer(CustomerVO uv); //미구현
    public CustomerVO displayCustomer(CustomerVO uv); //미구현
    public CustomerVO login(CustomerVO cv); //로그인 -> 객체 받아와서 함
     public boolean isExist(CustomerVO uv); //계정 확인

}
