package kr.or.ddit.teampro.event.dao;

import java.util.List;

import kr.or.ddit.teampro.comm.dao.MyBatisDao;
import kr.or.ddit.teampro.event.vo.eventVO;

public class EventDaoImpl extends MyBatisDao implements EventDao {

	private static EventDao eDao;
	
	public static EventDao getInstance() {
		
		if(eDao == null) {
			eDao = new EventDaoImpl();
		}
		return eDao;
	}
	// 이벤트 작성
	@Override
	public int insertEvent(eventVO ev) {
		int cnt = insert("event.insertEvent",ev);
		return cnt;
	}
	// 이벤트 수정
	@Override
	public int updateEvent(eventVO ev) {
		int cnt = update("event.updateEvent",ev);
		return cnt;
	}
	// 이벤트 삭제
	@Override
	public int deleteEvent(int eventNum) {
		int cnt = delete("event.deleteEvent",eventNum);
		return cnt;
	}
	// 이벤트 유무 체크
	@Override
	public boolean checkEvent(String companyId) {
		boolean isExist = false;
		
		int cnt = selectOne("event.checkEvent",companyId);
		
		if(cnt > 0) {
			isExist = true;
			
		}
		return isExist;
	}
	// 이벤트 검색
	@Override
	public List<eventVO> searchEvent(eventVO ev) {
		List<eventVO> eveList = selectList("event.searchEvent",ev);
		return eveList;
	}
	//이벤트 조회 
	@Override
	public List<eventVO> selectALLEvent() {
		List<eventVO> eveList = selectList("event.selectAllEvent");
		return eveList;
	}
	


}
