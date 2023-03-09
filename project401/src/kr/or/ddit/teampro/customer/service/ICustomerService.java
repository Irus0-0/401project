package kr.or.ddit.teampro.customer.service;

import kr.or.ddit.teampro.customer.vo.CustomerVO;

import java.util.List;

public interface ICustomerService {
    public int insertUser(CustomerVO cv); //계정등록
    public int deleteUser(CustomerVO cv); //계정삭제
    public int updateUser(CustomerVO cv); //미구현
    public List<CustomerVO> displayAll(CustomerVO cv); //미구현
    public CustomerVO login(CustomerVO cv); //로그인
    public CustomerVO logout(CustomerVO cv); //로그아웃
    public void setVo(CustomerVO cv); //로그인 계정 service에서 저장용
    public CustomerVO getVo(); //로그인 계정 service에 저장한 거 불러오기용
    public boolean isExist(CustomerVO cv); //계정확인

}
