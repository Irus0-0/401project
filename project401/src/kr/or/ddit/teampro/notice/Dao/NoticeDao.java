																	package kr.or.ddit.teampro.notice.Dao;

import java.util.List;

import kr.or.ddit.teampro.notice.vo.NoticeVO;

public interface NoticeDao {

	
	public int insertNotice(NoticeVO nv);
	public int updateNotice(NoticeVO nv);
	public int deleteNotice(String masterId);
	public boolean checkNotice(String masterId);
	public List<NoticeVO> selectAllNotice();
	public List<NoticeVO> searchNotice(NoticeVO nv);
	
}
