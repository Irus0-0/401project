package kr.or.ddit.teampro.company.coDao;

import java.util.List;

import kr.or.ddit.teampro.company.coVo.CompanyVO;

public interface ICompanyDao {
	
	
	/** CompanyVO에 담겨진 자료를 DB를 Insert하기 위한 메서드
	 * @param coVo DB에 저장할 데이터를 가진 VO객체
	 * @return DB작업이 성공하면 1이상의 값이 반환되고, 실패하면 0이 반환된다.
	 */
	public int insertCompany(CompanyVO coVo);
	
	
	/**CompanyVO에 담겨진 자료를 DB를 update하기 위한 메서드
	 * @param coVo DB에 저장할 데이터를 가진 VO객체
	 * @return DB작업이 성공하면 1이상의 값이 반환되고, 실패하면 0이 반환된다.
	 */
	public int updateCompany(CompanyVO coVo);
	
	
	/**주어진 기업의 ID를 통해 해당 기업회원이 존재하는지 여부를 알아내기 위한 메서드
	 * @param companyId 존재여부 확인하기 위한 기업회원ID
	 * @return 해당회원이 존재하면 true, 존재하지 않으면 false 반환
	 */
	public boolean checkCompany(String companyId);
 	
	
	/**주어진 기업의 ID를 통해 해당 기업회원을 삭제하기 위한 메서드
	 * @param companyId 삭제할 기업회원ID
	 * @return 삭제작업 성공시 1, 실패시 0반환
	 */
	public int deleteCompany(String companyId);
	
	
	/**전체 기업회원정보를 조회하기 위한 메서드
	 * @return 리스트객체
	 */
	public List<CompanyVO> selectAllCompany();
	
	
	/**검색된 회원정보 조회하기 위한 메서드
	 * @param coVo 검색할 조건정보를 담은 VO객체
	 * @return 검색된 기업 회원정보를 담은 VO객체
	 */
	public List<CompanyVO> searchCompany(CompanyVO coVo);
}
