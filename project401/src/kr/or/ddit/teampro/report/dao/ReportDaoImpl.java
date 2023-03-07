package kr.or.ddit.teampro.report.dao;

import dao.MyBatisDao;
import kr.or.ddit.teampro.report.vo.ReportVo;
import kr.or.ddit.teampro.reservation.vo.ReservationVo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportDaoImpl extends MyBatisDao implements ReportDao {

    private static ReportDao reportDao;

    private ReportDaoImpl() {

    }

    public static ReportDao getInstance() {
        if (reportDao == null) {
            reportDao = new ReportDaoImpl();
        }

        return reportDao;
    }

    /**
     * 신고 기능
     *
     * @param reportVo  신고할 객체
     * @param whoReport T = 유저가 신고 / F = 기업이 신고
     * @return 0이면 실패 1이면 성공
     */
    @Override
    public int insertReport(ReportVo reportVo, boolean whoReport) {
        int cnt;
        if (whoReport) {
            // 유저가 신고
            cnt = insert("report.reportByUser", reportVo);
        } else {
            // 기업이 신고
            cnt = insert("report.reportByCo", reportVo);
        }
        return cnt;
    }

    @Override
    public int checkReport(ReportVo reportVo, boolean whoReport) {
        int cnt;
        if (whoReport) {
            // 유저가 신고
            cnt = selectOne("report.reportCheckByUser",reportVo);
        } else {
            // 기업이 신고
            cnt = selectOne("report.reportCheckByCo",reportVo);
        }
        return cnt;
    }

    /**
     * 신고 수정하기
     *
     * @param reportVo  신고할 객체
     * @param whoReport T = 유저가 신고 / F = 기업이 신고
     * @return 0이면 실패 1이면 성공
     */
    @Override
    public int updateReport(ReportVo reportVo, boolean whoReport) {
        int cnt;
        if (whoReport) {
            // 유저가 신고
            cnt = insert("report.updateReportByUser", reportVo);
        } else {
            // 기업이 신고
            cnt = insert("report.updateReportByCo", reportVo);
        }
        return cnt;
    }

    /**
     * 신고 삭제하기
     *
     * @param reportNum 삭제할 신고 번호
     * @param whoReport T = 유저가 신고 / F = 기업이 신고
     * @return 0이면 실패 1이면 성공
     */
    @Override
    public int deleteReport(int reportNum, boolean whoReport) {
        int cnt;
        if (whoReport) {
            // 유저가 신고
            cnt = insert("report.deleteReportByUser", reportNum);
        } else {
            // 기업이 신고
            cnt = insert("report.deleteReportByCo", reportNum);
        }
        return cnt;
    }

    /**
     * 모든 신고 조회하기
     *
     * @param whoReport T = 유저가 신고 / F = 기업이 신고
     * @return 0이면 실패 1이면 성공
     */
    @Override
    public List<ReportVo> selectAllReport(boolean whoReport) {
        List<ReportVo> ReportVoList;
        if (whoReport) {
            // 유저가 신고
            ReportVoList = selectList("report.selectAllReportByUser");
        } else {
            // 기업이 신고
            ReportVoList = selectList("report.selectAllReportByCo");
        }
        return ReportVoList;
    }


    /**
     * 선택한 일반,기업 유저가 신고한 모든 정보 가져오기
     *
     * @param whoReport T = 유저가 신고 / F = 기업이 신고
     * @param search    Map 타입으로 customerId, reservationNum 을 받아와야함
     * @return Map 에 입려된 사용자의 신고한 모든 신고 정보를 가져옴
     */
    @Override
    public List<ReportVo> selectAllReport(boolean whoReport, Map search) {
        List<ReportVo> ReportVoList;
        if (whoReport) {
            // 유저가 신고
            ReportVoList = selectList("report.selectAllReportByChoseUser", search);
        } else {
            // 기업이 신고
            ReportVoList = selectList("report.selectAllReportByChoseCo", search);
        }
        return ReportVoList;
    }

    /**
     * 모든 신고 결과를 조회함
     * @param whoReport T = 유저가 신고 / F = 기업이 신고
     * @return List에 사용자 또는 기업의 모든 신고 결과를 반환
     */
    @Override
    public List<ReportVo> selectAllReportResults(boolean whoReport) {
        List<ReportVo> ReportVoList;
        if (whoReport) {
            // 유저가 신고
            ReportVoList = selectList("report.selectAllReportResultByUser");
        } else {
            // 기업이 신고
            ReportVoList = selectList("report.selectAllReportResultByCo");
        }
        return ReportVoList;
    }

    /**
     * 처리되지 않은 신고 결과들을 조회
     * @param whoReport T = 유저가 신고 / F = 기업이 신고
     * @return List에 사용자 또는 기업의 처리되지 않은 모든 신고 결과를 반환
     */
    @Override
    public List<ReportVo> selectAllReportResultsF(boolean whoReport) {
        List<ReportVo> ReportVoList;
        if (whoReport) {
            // 유저가 신고
            ReportVoList = selectList("report.selectAllReportResultByUserF");
        } else {
            // 기업이 신고
            ReportVoList = selectList("report.selectAllReportResultByCoF");
        }
        return ReportVoList;
    }

    /**
     * 신고 결과 등록하기
     * @param reportVo  신고 결과를 담은 객체
     * @param whoReport T = 유저가 신고 / F = 기업이 신고
     * @return 0이면 실패 1이면 성공
     */
    @Override
    public int insertReportResult(boolean whoReport, ReportVo reportVo) {
        int cnt;
        if (whoReport) {
            // 유저가 신고
            cnt = insert("report.updateReportResultByCo", reportVo);
        } else {
            // 기업이 신고
            cnt = insert("report.updateReportResultByUser", reportVo);
        }
        return cnt;
    }

    /**
     * 신고 결과를 수정
     * @param reportVo  수정된 결과를 담은 객체
     * @param whoReport T = 유저가 신고 / F = 기업이 신고
     * @return 0이면 실패 1이면 성공
     */
    @Override
    public int updateReportResult(boolean whoReport, ReportVo reportVo) {
        int cnt;
        if (whoReport) {
            // 유저가 신고
            cnt = insert("report.updateReportResultByCo", reportVo);
        } else {
            // 기업이 신고
            cnt = insert("report.updateReportResultByUser", reportVo);
        }
        return cnt;
    }
}
