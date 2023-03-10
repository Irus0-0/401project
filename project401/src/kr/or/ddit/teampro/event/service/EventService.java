package kr.or.ddit.teampro.event.service;

import java.util.List;

import kr.or.ddit.teampro.event.vo.eventVO;

public interface EventService {

	public int registEvent(eventVO ev);

	public int modifyEvent(eventVO ev);

	public int removeEvent(int eventNum);

	public boolean checkEvent(String companyId);

	public List<eventVO> selectAllEvent();

	public List<eventVO> searchEvent(eventVO ev);

}
