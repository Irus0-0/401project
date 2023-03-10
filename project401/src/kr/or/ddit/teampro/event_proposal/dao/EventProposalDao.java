package kr.or.ddit.teampro.event_proposal.dao;

import java.util.List;

import kr.or.ddit.teampro.event_proposal.vo.EventProposalVO;

public interface EventProposalDao {
	
	/**
	 * 이벤트 승인 결정
	 */
	public int updateEventPro(EventProposalVO pvo);
	
	
	/**
	 * 이벤트 검색
	 */
	public List<EventProposalVO> searchEventPro(EventProposalVO pvo);
	
	/***
	 * 이벤트 유무 체크 
	 */
	public boolean checkEventPro(String proIsok);
	
	/**
	 * 승인된 이벤트 조회
	 */
	public List<EventProposalVO> selectEventProT(EventProposalVO pvo);
	
	/**
	 * 모든 이벤트 불러오기 
	 */
	public List<EventProposalVO> selectEventPro();
	
	/**
	 * 이벤트 신청 결과 
	 * 승인 여부 T, F 포함
	 */
	public  List<EventProposalVO> selectEventProResult();
	
	/**
	 * 관리자 유무 체크
	 */
	public boolean checkEventProM (String masterId);
	
	

}
