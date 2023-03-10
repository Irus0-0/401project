package kr.or.ddit.teampro.notice.dao;

import java.util.List;

import kr.or.ddit.teampro.comm.dao.MyBatisDao;
import kr.or.ddit.teampro.notice.vo.noticeVO;

public class NoticeDaoImpl extends MyBatisDao implements NoticeDao {

	private static NoticeDao notDao;

	public static NoticeDao getInstance() {
		if (notDao == null) {
			notDao = new NoticeDaoImpl();
		}
		return notDao;
	}

	@Override
	public int insertNotice(noticeVO nv) {
		int cnt = insert("notice.insertNotice", nv);
		return cnt;
	}

	@Override
	public int updateNotice(noticeVO nv) {
		int cnt = update("notice.updateNotice", nv);
		return cnt;
	}

	@Override
	public int deleteNotice(int noticeNum) {
		int cnt = delete("notice.deleteNotice", noticeNum);
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
	public List<noticeVO> selectAllNotice() {
		List<noticeVO> notList = selectList("notice.selectAllNotice");
		return notList;
	}

	@Override
	public List<noticeVO> searchNotice(noticeVO nv) {
		List<noticeVO> notList = selectList("notice.searchNotice",nv);
		return notList;
	}

}
