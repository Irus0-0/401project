package kr.or.ddit.teampro.event_proposal.dao;

import java.util.List;

import kr.or.ddit.teampro.comm.dao.MyBatisDao;
import kr.or.ddit.teampro.event_proposal.vo.EventProposalVO;

public class EventProposalDaoImpl extends MyBatisDao implements EventProposalDao {
	
	private static EventProposalDao epDao;
	
	public static EventProposalDao getInstance() {
		
		if(epDao == null) {
			epDao = new EventProposalDaoImpl();
		}
		return epDao;
	}

	/**
	 * 1. 신청된 이벤트 조회 
	 */
	@Override
	public List<EventProposalVO> selectEventPro() {
		List<EventProposalVO> proList =selectList("event_proposal.selectEventPro");
		return proList;
	}
	
	/**
	 * 2. 이벤트 승인 결정
	 */
	@Override
	public int updateEventPro(EventProposalVO pvo) {
		int cnt = update("event_proposal.updateEventPro",pvo);
		return cnt;
	}
	/**
	 * 3. 이벤트 신청 결과 (proisok -> t,f 포함)
	 */
	@Override
	public List<EventProposalVO> selectEventProResult() {
		List<EventProposalVO> proList =selectList("event_proposal.selectEventProResult");
		return proList;
		
	}
	
	/**
	 * 4. 승인된 이벤트만 조회 (proisok -> t)
	 */
	@Override
	public List<EventProposalVO> selectEventProT(EventProposalVO pvo){
		List<EventProposalVO> proList = selectList("event_proposal.selectEventProT",pvo);
		return proList;
	}
	
	/**
	 * 5. 승인여부 포함하여 이벤트 검색
	 */
	@Override
	public List<EventProposalVO> searchEventPro(EventProposalVO pvo) {
	List<EventProposalVO> proList = selectList("event_proposal.searchEventPro");	
	return proList;
	
	}
	

	/**
	 * 이벤트 유무 체크
	 */
	@Override
	public boolean checkEventPro(String proIsok) {
		boolean isExist = false;
		int cnt = selectOne("event_proposal.checkEventPro",proIsok);
		if(cnt > 0) {
			isExist = true;
		}
		return isExist;
	}
	/**
	 * 관리자 유무 체크
	 */
	@Override
	public boolean checkEventProM(String masterId) {
		boolean isExist = false;
		int cnt = selectOne("event_proposal.checkEventProM",masterId);
		if(cnt > 0) {
			isExist = true;
		}
		return isExist;
	
		
	}


}
