package kr.or.ddit.teampro.notice.dao;

import java.util.List;

import kr.or.ddit.teampro.comm.dao.MyBatisDao;
import kr.or.ddit.teampro.notice.vo.NoticeVO;

public class NoticeDaoImpl extends MyBatisDao implements NoticeDao {

	private static NoticeDao notDao;

	public static NoticeDao getInstance() {
		if (notDao == null) {
			notDao = new NoticeDaoImpl();
		}
		return notDao;
	}

	@Override
	public int insertNotice(NoticeVO nv) {
		int cnt = insert("notice.insertNotice", nv);
		return cnt;
	}

	@Override
	public int updateNotice(NoticeVO nv) {
		int cnt = update("notice.insertNotice", nv);
		return cnt;
	}

	@Override
	public int deleteNotice(int noticeNum) {
		int cnt = delete("notice.insertNotice", noticeNum);
		return cnt;
	}

	@Override
	public boolean checkNotice(String masterId) {
		boolean isExist = false;

		int cnt = selectOne("notice.checkNotice", masterId);

		if (cnt > 0) {
			isExist = true;
		}
		return isExist;
	}

	@Override
	public List<NoticeVO> selectAllNotice() {
		List<NoticeVO> notList = selectList("notice.selsectAllNotice");
		return notList;
	}

	@Override
	public List<NoticeVO> searchNotice(NoticeVO nv) {
		List<NoticeVO> notList = selectList("notice.selectAllNotice");
		return notList;
	}

}
