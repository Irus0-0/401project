package kr.or.ddit.teampro.company.service;


import kr.or.ddit.teampro.company.dao.CompanyDao;
import kr.or.ddit.teampro.company.vo.CompanyVO;

public class CompanyService implements ICompanyService {

    //singleton 설정
    private static ICompanyService instance = null;
    private static CompanyVO uv;

    private CompanyService() {
    }

    public static ICompanyService getInstance() {
        if (instance == null) {
            instance = new CompanyService();
        }
        return instance;
    }

    @Override
    public int insertUser(CompanyVO uv) {
        return CompanyDao.getInstance().insertCompany(uv);
    }

    @Override
    public int deleteUser(String userId) {
        return CompanyDao.getInstance().deleteCompany(userId);
    }

    @Override
    public int updateUser(CompanyVO uv) {
        return CompanyDao.getInstance().updateCompany(uv);
    }

    @Override
    public CompanyVO displayUser(CompanyVO uv) {
        return null;
    }

    @Override
    public CompanyVO login(String userId, String userPwd) {
        return CompanyDao.getInstance().login(userId,userPwd);
    }

    @Override
    public CompanyVO logout(CompanyVO uv) {
        this.setVo(null);
        return getVo();
    }

    @Override
    public void setVo(CompanyVO uv) {
        this.uv = uv;
    }

    @Override
    public CompanyVO getVo() {
        return uv;
    }

    @Override
    public boolean isExist(String userId) {
        return CompanyDao.getInstance().isExist(userId);
    }


}
