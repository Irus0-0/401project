package kr.or.ddit.teampro.event.dao;

import java.util.List;

import kr.or.ddit.teampro.event.vo.eventVO;

public interface EventDao {
	
	public int insertEvent(eventVO ev);
	public int updateEvent(eventVO ev);
	public int deleteEvent(int eventNum);
	public List<eventVO> searchEvent(eventVO ev);
	public List<eventVO> selectALLEvent();
	public boolean checkEvent(String companyId);
	

}
