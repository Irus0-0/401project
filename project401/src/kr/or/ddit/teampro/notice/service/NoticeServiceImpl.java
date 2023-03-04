package kr.or.ddit.teampro.notice.service;

import java.util.List;

import kr.or.ddit.teampro.notice.Dao.NoticeDao;
import kr.or.ddit.teampro.notice.Dao.NoticeDaoImpl;
import kr.or.ddit.teampro.notice.vo.NoticeVO;

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

	@Override
	public int registNotice(NoticeVO nv) {
		int cnt = notDao.insertNotice(nv);
		
		return cnt;
	}

	@Override
	public int modifyNotice(NoticeVO nv) {
		return notDao.updateNotice(nv);
	}

	@Override
	public int removeNotice(String masterId) {
		return notDao.deleteNotice(masterId);
	}

	@Override
	public boolean checkNotice(String masterId) {
		return notDao.checkNotice(masterId);
	}

	@Override
	public List<NoticeVO> selectAllNotice() {
		return notDao.selectAllNotice();
	}

	@Override
	public List<NoticeVO> searchNotice(NoticeVO nv) {
		return notDao.searchNotice(nv);
	}


	
	

}
