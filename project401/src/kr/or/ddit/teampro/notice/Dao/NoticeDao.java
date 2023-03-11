package kr.or.ddit.teampro.notice.dao;

import java.util.List;

import kr.or.ddit.teampro.notice.vo.noticeVO;

public interface NoticeDao {

	
	public int insertNotice(noticeVO nv);
	public int updateNotice(noticeVO nv);
	public int deleteNotice(int noticeNum);
	public boolean checkNotice(String masterId);
	public List<noticeVO> selectAllNotice();
	public List<noticeVO> searchNotice(noticeVO nv);
	
}
