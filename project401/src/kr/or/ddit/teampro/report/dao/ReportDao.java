package kr.or.ddit.teampro.report.dao;

import kr.or.ddit.teampro.report.vo.ReportVo;
import kr.or.ddit.teampro.reservation.vo.ReservationVo;

import java.util.List;

public interface ReportDao {
    // 파라미터  whoReport T = 유저가 신고 / F = 기업이 신고
    // 신고하는 기능 
    int insertReport(ReportVo reportVo, Boolean whoReport);

    // 신고한 자료 수정하기
    int modifyReportByUser(ReportVo reportVo, Boolean whoReport);

    // 신고 취소하기
    int deleteReport(int reportNum, Boolean whoReport);
    
    // 모든 신고 조회하기(관리자가 로그로 사용)
    List<ReportVo> selectAllReport(Boolean whoReport);
    
    // 유저나 기업이 신고한 모든 정보 확인하기
    List<ReportVo> selectAllReport(Boolean whoReport, ReservationVo reservationVo);

    //------------------------------------------------------

    // 유저나 기업이 신고하거나 했었던 모든 자료를 조회
    List<ReportVo> selectAllReportResults(Boolean whoReport);

    // 아직 처리 되지 않은 결과들을 조회
    List<ReportVo> selectAllReportResultsF(Boolean whoReport);







}
