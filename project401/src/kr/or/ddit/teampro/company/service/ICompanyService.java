package kr.or.ddit.teampro.company.service;

import kr.or.ddit.teampro.company.vo.CompanyVO;

public interface ICompanyService {
    public int insertUser(CompanyVO uv);
    public int deleteUser(String uv);
    public int updateUser(CompanyVO uv);
    public CompanyVO displayUser(CompanyVO uv);
    public CompanyVO login(String userId, String userPw);
    public CompanyVO logout(CompanyVO uv);
    public void setVo(CompanyVO uv);
    public CompanyVO getVo();
    public boolean isExist(String userId);

}
