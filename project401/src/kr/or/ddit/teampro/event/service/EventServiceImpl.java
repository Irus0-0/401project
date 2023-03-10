package kr.or.ddit.teampro.event.service;

import java.util.List;

import kr.or.ddit.teampro.comm.dao.MyBatisDao;
import kr.or.ddit.teampro.event.dao.EventDao;
import kr.or.ddit.teampro.event.dao.EventDaoImpl;
import kr.or.ddit.teampro.event.vo.eventVO;

public class EventServiceImpl implements EventService {

	private EventDao eDao;
	private static EventService eveService;

	private EventServiceImpl() {
		eDao = EventDaoImpl.getInstance();
	}

	public static EventService getInstance() {
		if (eveService == null) {
			eveService = new EventServiceImpl();
		}
		return eveService;
	}

	@Override
	public int registEvent(eventVO ev) {

		int cnt = eDao.insertEvent(ev);
		return cnt;
	}

	@Override
	public int modifyEvent(eventVO ev) {
		return eDao.updateEvent(ev);
		
	}

	@Override
	public int removeEvent(int eventNum) {
		return eDao.deleteEvent(eventNum);
	
	}

	@Override
	public boolean checkEvent(String companyId) {
		return eDao.checkEvent(companyId);
	}

	@Override
	public List<eventVO> selectAllEvent() {
		return eDao.selectALLEvent();
	}

	@Override
	public List<eventVO> searchEvent(eventVO ev) {
		return eDao.searchEvent(ev);
	}

}
