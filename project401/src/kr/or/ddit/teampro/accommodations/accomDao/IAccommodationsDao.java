package kr.or.ddit.teampro.accommodations.accomDao;

import java.util.List;
import kr.or.ddit.teampro.accommodations.accomVo.AccommodationsVO;
import kr.or.ddit.teampro.company.coVo.CompanyVO;


public interface IAccommodationsDao {
	
	/** RoomVO에 담겨진 자료를 DB를 Insert하기 위한 메서드
	 * @param rmVo DB에 저장할 데이터를 가진 VO객체
	 * @return DB작업이 성공하면 1이상의 값이 반환되고, 실패하면 0이 반환된다.
	 */
	public int insertAccom(AccommodationsVO AccomVo);
	
	
	/**RoomVO에 담겨진 자료를 DB를 update하기 위한 메서드
	 * @param rmVo DB에 저장할 데이터를 가진 VO객체
	 * @return DB작업이 성공하면 1이상의 값이 반환되고, 실패하면 0이 반환된다.
	 */
	public int updateAccom(AccommodationsVO AccomVo);
	
	
	
	/**주어진 정보를 통해 해당 숙박시설 존재 여부를 알아내는 메서드
	 * @param accomName
	 * @param companyId
	 * @return 해당 객실이 존재하면 true, 존재하지 않으면 false 반환
	 */
	public boolean checkAccom(String accomName,String companyId);
 	
	
	
	/**주어진 숙박시설명,기업아이디를 통해 해당 숙박시설을 삭제하는 메서드
	 * @param accomName 삭제할 숙박시설명
	 * @param companyId 숙박시설이 속한 기업ID
	 * @return 삭제작업 성공시 1, 실패시 0반환
	 */
	public int deleteAccom(String accomName,String companyId);
	
	
	
	/**전체 숙박시설정보를 조회하기 위한 메서드
	 * @return 리스트객체
	 */
	public List<AccommodationsVO> selectAllAccom();
	
	
	
	/**검색된 숙박시설정보 조회하기 위한 메서드
	 * @param AccomVo 검색할 조건정보를 담은 VO객체
	 * @return 검색된 숙박시설정보를 담은 VO객체
	 */
	public List<AccommodationsVO> searchAccom(AccommodationsVO AccomVo);

	
	
	/**Company Controller에서 기업정보 검색 후 해당 기업이 가진 숙박시설 목록을 보는 메서드
	 * @param companyId 검색된 기업 아이디-아이디를 통해 조인쿼리로 해당 기업에 속한 숙박시설을 조회 
	 * @return 해당 기업이 가진 숙박시설 정보를 담은 VO객체
	 */
	public List<AccommodationsVO> searchAccomJoin(String companyId);
}
