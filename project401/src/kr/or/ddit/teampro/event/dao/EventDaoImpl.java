package kr.or.ddit.teampro.event.dao;

import java.util.List;

import kr.or.ddit.teampro.comm.dao.MyBatisDao;
import kr.or.ddit.teampro.event.vo.EventVO;

public class EventDaoImpl extends MyBatisDao implements EventDao {

	private static EventDao eDao;
	
	public static EventDao getInstance() {
		
		if(eDao == null) {
			eDao = new EventDaoImpl();
		}
		return eDao;
	}
	
	@Override
	public int insertEvent(EventVO ev) {
		int cnt = insert("event.insertEvent",ev);
		return cnt;
	}

	@Override
	public int updateEvent(EventVO ev) {
		int cnt = update("event.updateEvent",ev);
		return cnt;
	}

	@Override
	public int deleteEvent(int eventNum) {
		int cnt = delete("event.deleteEvent",eventNum);
		return cnt;
	}
	
	@Override
	public boolean checkEvent(String companyId) {
		boolean isExist = false;
		
		int cnt = selectOne("event.checkEvent",companyId);
		
		if(cnt > 0) {
			isExist = true;
			
		}
		return isExist;
	}
	
	@Override
	public List<EventVO> searchEvent(EventVO ev) {
		List<EventVO> eveList = selectList("event.selectAllList");
		return eveList;
	}

	@Override
	public List<EventVO> selectALLEvent() {
		List<EventVO> eveList = selectList("event.selectAllList");
		return eveList;
	}



}
