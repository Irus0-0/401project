package kr.or.ddit.teampro.accommodations.accomDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.teampro.accommodations.accomVo.AccommodationsVO;
import kr.or.ddit.teampro.company.coDao.MyBatisDao;
import kr.or.ddit.teampro.company.coVo.CompanyVO;


public class AccommodationsDaoImpl extends MyBatisDao implements IAccommodationsDao{
	
	private static IAccommodationsDao AccomDao;
	
	private AccommodationsDaoImpl () {
		
	}
	
	public static IAccommodationsDao getInstance() {
		if(AccomDao==null) {
			AccomDao=new AccommodationsDaoImpl();
		}
		return AccomDao;
	}
	
	

	@Override
	public int insertAccom(AccommodationsVO AccomVo) {
		int cnt = insert("Accom.insertAccom",AccomVo);
		return cnt;
	}

	@Override
	public int updateAccom(AccommodationsVO AccomVo) {
		int cnt=update("Accom.updateAccom",AccomVo);
		return cnt;
	}

	@Override
	public boolean checkAccom(String accomName,String companyId) {
		Map<String, String> checkMap = new HashMap<>();
	    checkMap.put("accomName", accomName);
	    checkMap.put("companyId", companyId);
		boolean isExist = false;
		
		int cnt = selectOne("Accom.checkAccom",checkMap);
				
		if(cnt>0) {
			isExist = true;
		}
		return isExist;
		
		
		
	}

	@Override
	public int deleteAccom(String accomName, String companyId) {
		Map<String, String> deleteMap = new HashMap<>();
	    deleteMap.put("accomName", accomName);
	    deleteMap.put("companyId", companyId);
	    
		int cnt=delete("Accom.deleteAccom",deleteMap);
		return cnt;
	}

	@Override
	public List<AccommodationsVO> selectAllAccom() {
		List<AccommodationsVO> AccomList = selectList("Accom.selectAllAccom");
		return AccomList;
	}

	@Override
	public List<AccommodationsVO> searchAccom(AccommodationsVO AccomVo) {
		List<AccommodationsVO> AccomList=selectList("Accom.searchAccom",AccomVo);
		return AccomList;
	}
	
	
	@Override
	public List<AccommodationsVO> searchAccomJoin(String companyId) {
		List<AccommodationsVO> AccomList=selectList("Accom.searchAccomJoin",companyId);
		return AccomList;
	}
	
}
