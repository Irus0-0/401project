package kr.or.ddit.teampro.report.dao;

import dao.MyBatisDao;
import kr.or.ddit.teampro.report.vo.ReportVo;
import kr.or.ddit.teampro.reservation.vo.ReservationVo;

import java.util.List;

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
     * @param reportVo 신고할 객체
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

    /**
     * 신고 수정하기
     * @param reportVo 신고할 객체
     * @param whoReport T = 유저가 신고 / F = 기업이 신고
     * @return 0이면 실패 1이면 성공
     */
    @Override
    public int modifyReport(ReportVo reportVo, boolean whoReport) {
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
     * 각 사용자들의 모든 신고 조회하기
     * @param whoReport T = 유저가 신고 / F = 기업이 신고
     * @return 0이면 실패 1이면 성공
     */
    @Override
    public List<ReportVo> selectAllReport(boolean whoReport) {
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

    @Override
    public List<ReportVo> selectAllReport(boolean whoReport, ReservationVo reservationVo) {
        return null;
    }

    @Override
    public List<ReportVo> selectAllReportResults(boolean whoReport) {
        return null;
    }

    @Override
    public List<ReportVo> selectAllReportResultsF(boolean whoReport) {
        return null;
    }

    @Override
    public int insertReportResult(boolean whoReport, ReportVo reportVo) {
        return 0;
    }

    @Override
    public int modifyReportResult(boolean whoReport, ReportVo reportVo) {
        return 0;
    }
}
