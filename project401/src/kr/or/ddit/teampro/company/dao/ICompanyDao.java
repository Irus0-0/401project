package kr.or.ddit.teampro.company.dao;

import kr.or.ddit.teampro.company.vo.CompanyVO;

public interface ICompanyDao {
    public int insertCompany(CompanyVO uv);
    public int deleteCompany(String CompanyId);
    public int updateCompany(CompanyVO uv);
    public CompanyVO displayCompany(CompanyVO uv);
    public CompanyVO login(String CompanyId,String CompanyPw);
    public boolean isExist(String CompanyId);

}
