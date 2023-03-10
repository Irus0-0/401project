package kr.or.ddit.teampro.event_proposal.service;

import java.util.List;

import kr.or.ddit.teampro.event_proposal.dao.EventProposalDao;
import kr.or.ddit.teampro.event_proposal.dao.EventProposalDaoImpl;
import kr.or.ddit.teampro.event_proposal.vo.EventProposalVO;

public class EventProposalServiceImpl implements EventProposalService{
	
	private EventProposalDao epDao;
	private static EventProposalService proService;
	
	private EventProposalServiceImpl() {
		epDao = EventProposalDaoImpl.getInstance();
	}
	public static EventProposalService getInstance() {
		if(proService == null) {
			proService = new EventProposalServiceImpl();
		}
		return proService;
	}
	/**
	 * 1. 신청된 이벤트 조회
	 */
	@Override
	public List<EventProposalVO> selectAllEventPro() {
		return epDao.selectEventPro();
	}
	
	/**
	 *2.  이벤트 승인 결정
	 */
	@Override
	public int modifyEventPro(EventProposalVO pvo) {
		return epDao.updateEventPro(pvo);
	}
	/**
	 * 3. 이벤트 신청 결과 (proisok => t, f)
	 */
	@Override
	public List<EventProposalVO> selectEventProResult(EventProposalVO pvo) {
		return epDao.selectEventProResult();
	}
	
	/**
	 * 4. 승인된 이벤트 조회 (proisok -> t)
	 */
	@Override
	public List<EventProposalVO> selectEventProT(EventProposalVO pvo) {
		return epDao.selectEventProT(pvo);
	}
	/**
	 * 5.이벤트 검색 
	 */
	@Override
	public List<EventProposalVO> searchEventPro(EventProposalVO pvo) {
		return epDao.searchEventPro(pvo);
	}
	
	/**
	 *  이벤트 유무 체크
	 */
	@Override
	public boolean checkEventPro(String proIsok) {
		return epDao.checkEventPro(proIsok);
	}
	/**
	 * 관리자 유무 체크
	 */
//	@Override
//	public boolean checkEventProM(String masterId) {
//		return epDao.checkEventPro(masterId);
//	}


}
