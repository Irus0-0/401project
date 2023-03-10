package kr.or.ddit.teampro.notice.service;

import java.util.List;

import kr.or.ddit.teampro.notice.vo.noticeVO;

public interface INoticeService {
	
	public int registNotice(noticeVO nv);
	public int modifyNotice(noticeVO nv);
	public int removeNotice(int noticeNum);
	public boolean checkNotice(String masterId);
	public List<noticeVO> selectAllNotice();
	public List<noticeVO> searchNotice(noticeVO nv);
	

}
