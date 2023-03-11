package kr.or.ddit.teampro.room.rmDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.teampro.company.coDao.MyBatisDao;
import kr.or.ddit.teampro.room.rmVo.RoomVO;

public class RoomDaoImpl extends MyBatisDao implements IRoomDao {

	private static IRoomDao rmDao;

	private RoomDaoImpl() {

	}

	public static IRoomDao getInstance() {
		if (rmDao == null) {
			rmDao = new RoomDaoImpl();
		}
		return rmDao;
	}

	@Override
	public int insertRoom(RoomVO rmVo) {
		int cnt = insert("room.insertRoom", rmVo);
		return cnt;
	}

	@Override
	public int updateRoom(RoomVO rmVo) {
		int cnt = update("room.updateRoom", rmVo);
		return cnt;
	}

	@Override
	public boolean checkRoom(int roomNumber, String accomName, String companyId) {
		Map<String, Object> checkMap = new HashMap<>();
		checkMap.put("roomNumber", roomNumber);
		checkMap.put("accomName", accomName);
		checkMap.put("companyId", companyId);
		boolean isExist = false;

		int cnt = selectOne("room.checkRoom", checkMap);

		if (cnt > 0) {
			isExist = true;
		}
		return isExist;

	}

	@Override
	public int deleteRoom(int roomNumber, String accomName, String companyId) {
		Map<String, Object> deleteMap = new HashMap<>();
		deleteMap.put("roomNumber", roomNumber);
		deleteMap.put("accomName", accomName);
		deleteMap.put("companyId", companyId);
		int cnt = delete("room.deleteRoom", deleteMap);
		return cnt;
	}

	@Override
	public List<RoomVO> selectAllRoom() {
		List<RoomVO> roomList = selectList("room.selectAllRoom");
		return roomList;
	}

	@Override
	public List<RoomVO> searchRoom(RoomVO rmVo) {
		List<RoomVO> roomList = selectList("room.searchRoom", rmVo);
		return roomList;
	}
	
	@Override
	public List<RoomVO> searchRoomJoin(String accomName) {
		List<RoomVO> roomList = selectList("room.searchRoomJoin", accomName);
		return roomList;
	}
	
	@Override
	public List<RoomVO> searchRoomGrade(int minGrade,int maxGrade) {
		Map<String, Object> searchGradeMap = new HashMap<>();
		searchGradeMap.put("minGrade", minGrade);
		searchGradeMap.put("maxGrade", maxGrade);
		List<RoomVO> roomList = selectList("room.searchRoomGrade", searchGradeMap);
		return roomList;
	}
	
	@Override
	public List<RoomVO> searchRoomPrice(int minPrice,int maxPrice) {
		Map<String, Object> searchPriceMap = new HashMap<>();
		searchPriceMap.put("minPrice", minPrice);
		searchPriceMap.put("maxPrice", maxPrice);
		List<RoomVO> roomList = selectList("room.searchRoomPrice", searchPriceMap);
		return roomList;
	}
	
	@Override
	public List<RoomVO> searchRoomAppro(int minAppro,int maxAppro) {
		Map<String, Object> searchApproMap = new HashMap<>();
		searchApproMap.put("minAppro", minAppro);
		searchApproMap.put("maxAppro", maxAppro);
		List<RoomVO> roomList = selectList("room.searchRoomAppro", searchApproMap);
		return roomList;
	}

}
