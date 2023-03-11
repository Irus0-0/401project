package kr.or.ddit.teampro.room.rmService;

import java.util.List;

import kr.or.ddit.teampro.room.rmDao.IRoomDao;
import kr.or.ddit.teampro.room.rmDao.RoomDaoImpl;
import kr.or.ddit.teampro.room.rmVo.RoomVO;



public class RoomService {
	
	private IRoomDao rmDao;
	
	private static RoomService rmService;
	
	
	private RoomService() {
		rmDao=RoomDaoImpl.getInstance();
	}
	
	public static RoomService getInstance() {
		if (rmService==null) {
			rmService=new RoomService();
		}
		
		return rmService;
	}
	
	
	
	/** RoomVO에 담겨진 자료를 DB를 Insert하기 위한 메서드
	 * @param rmVo DB에 저장할 데이터를 가진 VO객체
	 * @return DB작업이 성공하면 1이상의 값이 반환되고, 실패하면 0이 반환된다.
	 */
	public int registRoom(RoomVO rmVo) {
		return rmDao.insertRoom(rmVo);
	}
	
	
	/**RoomVO에 담겨진 자료를 DB를 update하기 위한 메서드
	 * @param rmVo DB에 저장할 데이터를 가진 VO객체
	 * @return DB작업이 성공하면 1이상의 값이 반환되고, 실패하면 0이 반환된다.
	 */
    public int modifyRoom(RoomVO rmVo) {
    	return rmDao.updateRoom(rmVo);
    }
    
    
    /**주어진 객실 번호를 통해 해당 객실이 존재하는지 여부를 알아내기 위한 메서드
   	 * @param roomNumber 존재 여부 확인위한 객실번호
   	 * @param accomName 존재 여부 확인위한 객실번호가 속한 숙박시설 이름
   	 * @param companyId 존재 여부 확인위한 객실번호가 속한 숙박시설의 담당 기업ID
   	 * @return 해당 객실이 존재하면 true, 존재하지 않으면 false 반환
   	 */
    public boolean checkRoom(int roomNumber,String accomName,String companyId) {
    	return rmDao.checkRoom(roomNumber,accomName,companyId);
    }
    
    
    /**주어진 객실 번호를 통해 해당 객실을 삭제하기 위한 메서드
	 * @param roomNumber 삭제할 객실번호
	 * @param accomName 삭제할 객실번호가 속한 숙박시설 이름
	 * @param companyId 기업ID
	 * @return 삭제작업 성공시 1, 실패시 0반환
	 */
    public int removeRoom(int roomNumber,String accomName,String companyId) {
		return rmDao.deleteRoom(roomNumber,accomName,companyId);
    }
    
    
    /**전체 객실정보를 조회하기 위한 메서드
	 * @return 리스트객체
	 */
    public List<RoomVO> selectAllRoom() {
		return rmDao.selectAllRoom();
	}
    
    
    /**검색된 객실정보 조회하기 위한 메서드
	 * @param rmVo 검색할 조건정보를 담은 VO객체
	 * @return 검색된 객실정보를 담은 VO객체
	 */
    public List<RoomVO> searchRoom(RoomVO rmVo){
    	return rmDao.searchRoom(rmVo);
    }
    
    
    /**accommodations controller에서 숙박시설 검색 후 해당 숙박시설에 속한 객실정보 조회하기 위한 메서드
	 * @param accomName 숙박시설 이름
	 * @return 해당 숙박시설에 속한 객실정보 담은 VO객체
	 */
    public List<RoomVO> searchRoomJoin(String accomName){
    	return rmDao.searchRoomJoin(accomName);
    }

    
    /**사용자가 입력한 등급 범위에 맞는 객실을 조회하기 위한 메서드
	 * @param minGrade 사용자가 입력한 최소등급
	 * @param maxGrade 사용자가 입력한 최대등급
	 * @return 조건에 맞는 객실 정보를 담은 VO객체
	 */
	public List<RoomVO> searchRoomGrade(int minGrade,int maxGrade ) {
		return rmDao.searchRoomGrade(minGrade,maxGrade);
	}
	
	
	/**사용자가 입력한 가격 범위에 맞는 객실을 조회하기 위한 메서드
	 * @param minPrice 사용자가 입력한 최소가격
	 * @param maxPrice 사용자가 입력한 최대가격
	 * @return 조건에 맞는 객실 정보를 담은 VO객체
	 */
	public List<RoomVO> searchRoomPrice(int minPrice,int maxPrice ) {
		return rmDao.searchRoomPrice(minPrice,maxPrice);
	}
	
	
	/**사용자가 입력한 적정인원 범위에 맞는 객실을 조회하기 위한 메서드
	 * @param minAppro 사용자가 입력한 최소 적정인원
	 * @param maxAppro 사용자가 입력한 최대 적정인원
	 * @return 조건에 맞는 객실 정보를 담은 VO객체
	 */
	public List<RoomVO> searchRoomAppro(int minAppro,int maxAppro ) {
		return rmDao.searchRoomAppro(minAppro,maxAppro);
	}

	
    
}
