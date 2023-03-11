package kr.or.ddit.teampro.notice.service;

import java.util.List;

import kr.or.ddit.teampro.notice.dao.NoticeDao;
import kr.or.ddit.teampro.notice.dao.NoticeDaoImpl;
import kr.or.ddit.teampro.notice.vo.noticeVO;

public class NoticeServiceImpl implements INoticeService{
	
	private NoticeDao notDao; 
	private static INoticeService notService;
	
	private NoticeServiceImpl() {
		notDao = NoticeDaoImpl.getInstance();
	}
	
	public static INoticeService getInstance() {
		if(notService == null) {
			notService = new NoticeServiceImpl();
		}
		return notService;
	}

	/*
	 * 등록
	 */
	@Override
	public int registNotice(noticeVO nv) {
		int cnt = notDao.insertNotice(nv);
		
		return cnt;
	}

	/*
	 * 수정
	 */
	
	@Override
	public int modifyNotice(noticeVO nv) {
		return notDao.updateNotice(nv);
	}

	/*
	 * 제거
	 */
	
	@Override
	public int removeNotice(int noticeNum) {
		return notDao.deleteNotice(noticeNum);
	}

	/*
	 *체크
	 */
	
	@Override
	public boolean checkNotice(String masterId) {
		return notDao.checkNotice(masterId);
	}

	/*
	 * 전체출력
	 */
	@Override
	public List<noticeVO> selectAllNotice() {
		return notDao.selectAllNotice();
	}

	/*
	 * 검색
	 */
	@Override
	public List<noticeVO> searchNotice(noticeVO nv) {
		return notDao.searchNotice(nv);
	}


	
	

}
