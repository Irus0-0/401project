package kr.or.ddit.teampro.company.coDao;

import java.util.List;

import kr.or.ddit.teampro.company.coVo.CompanyVO;
import org.apache.ibatis.session.SqlSession;
import util.MyBatisUtil;

public class CompanyDaoImpl extends MyBatisDao implements ICompanyDao{
	
	private static ICompanyDao comDao;
	
	private CompanyDaoImpl() {
		
	}
	
	public static ICompanyDao getInstance() {
		if(comDao==null) {
			comDao=new CompanyDaoImpl();
		}
		return comDao;
	}
	

	@Override
	public int insertCompany(CompanyVO coVo) {
		int cnt=insert("company.insertCompany",coVo);
		return cnt;
	}

	@Override
	public int updateCompany(CompanyVO coVo) {
		int cnt=update("company.updateCompany", coVo);
		return cnt;
	}

	@Override
	public boolean checkCompany(String company_Id) {
		boolean isExist = false;
		
		int cnt = selectOne("company.checkCompany",company_Id);
				
		if(cnt>0) {
			isExist = true;
		}
		return isExist;
	}

	
	@Override
	public int deleteCompany(String company_Id) {
		int cnt=delete("company.deleteCompany", company_Id);
		return cnt;
	}

	
	@Override
	public List<CompanyVO> selectAllCompany() {
		List<CompanyVO> companyList = selectList("company.selectAllCompany");
		return companyList;
	}
	

	@Override
	public List<CompanyVO> searchCompany(CompanyVO coVo) {
		List<CompanyVO> companyList =selectList("company.searchCompany", coVo);
		return companyList;
	}


	public int makeCompany(CompanyVO uv) {
		SqlSession sqlSession = MyBatisUtil.getInstance();
		int result = 0;

		try {
			result = sqlSession.insert("company.insert", uv);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return result;
	}

	public CompanyVO login(String companyId, String customerPw) {
		SqlSession sqlSession = MyBatisUtil.getInstance();
		CompanyVO result = null;

		try {
			CompanyVO uv = new CompanyVO();
			uv.setCompanyId(companyId);
			uv.setCompanyPw(customerPw);

			result = sqlSession.selectOne("company.login", uv);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}

		return result;
	}

}
