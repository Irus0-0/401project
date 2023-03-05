package kr.or.ddit.teampro.event.dao;

import java.util.List;

import kr.or.ddit.teampro.event.vo.EventVO;

public interface EventDao {
	
	public int insertEvent(EventVO ev);
	public int updateEvent(EventVO ev);
	public int deleteEvent(int eventNum);
	public List<EventVO> searchEvent(EventVO ev);
	public List<EventVO> selectALLEvent();
	public boolean checkEvent(String companyId);
	

}
