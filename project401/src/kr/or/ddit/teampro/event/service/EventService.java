package kr.or.ddit.teampro.event.service;

import java.util.List;

import kr.or.ddit.teampro.event.vo.EventVO;

public interface EventService {

	public int registEvent(EventVO ev);

	public int modifyEvent(EventVO ev);

	public int removeEvent(int eventNum);

	public boolean checkEvent(String companyId);

	public List<EventVO> selectAllEvent();

	public List<EventVO> searchEvent(EventVO ev);

}
