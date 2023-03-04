package kr.or.ddit.teampro.notice.service;

import java.util.List;

import kr.or.ddit.teampro.notice.vo.NoticeVO;

public interface INoticeService {
	
	public int registNotice(NoticeVO nv);
	public int modifyNotice(NoticeVO nv);
	public int removeNotice(String masterId);
	public boolean checkNotice(String masterId);
	public List<NoticeVO> selectAllNotice();
	public List<NoticeVO> searchNotice(NoticeVO nv);
	

}
