package kr.or.ddit.teampro.event_proposal.service;

import java.util.List;

import kr.or.ddit.teampro.event_proposal.vo.EventProposalVO;

public interface EventProposalService {
	
	
	//1. 신청된 이벤트 조회 (기업이 신청한 이벤트 조회)
	public List<EventProposalVO> selectAllEventPro();
	
	// 2. 이벤트 승인 결정
	public int modifyEventPro(EventProposalVO pvo);
		
	// 3. 이벤트 신청 결과 (proisok => t, f)
	public List<EventProposalVO> selectEventProResult(EventProposalVO pvo);
	
	
	// 4. 승인된 이벤트 조회 (proisok -> t)
	public List<EventProposalVO> selectEventProT(EventProposalVO pvo);

	// 5. 이벤트 검색
	public List<EventProposalVO> searchEventPro(EventProposalVO pvo);
	
	//이벤트 유무 체크
	public boolean checkEventPro(String proIsok);
}
