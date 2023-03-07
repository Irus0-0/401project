package kr.or.ddit.teampro.report.dao;

import kr.or.ddit.teampro.report.vo.ReportVo;

import java.util.List;
import java.util.Map;

public interface ReportDao {
    // 파라미터  whoReport T = 유저가 신고 / F = 기업이 신고
    // 신고하는 기능 
    int insertReport(ReportVo reportVo, boolean whoReport);

    int checkReport(ReportVo reportVo, boolean whoReport);

    // 신고한 자료 수정하기
    int updateReport(ReportVo reportVo, boolean whoReport);

    // 신고 취소하기
    int deleteReport(int reportNum, boolean whoReport);

    // 모든 신고 조회하기(관리자가 로그로 사용)
    List<ReportVo> selectAllReport(boolean whoReport);

    // 선택한 유저나 기업이 신고한 모든 정보 확인하기
    List<ReportVo> selectAllReport(boolean whoReport, Map search);

    //------------------------------------------------------

    // 유저나 기업이 신고하거나 했었던 모든 자료를 조회
    List<ReportVo> selectAllReportResults(boolean whoReport);

    // 아직 처리 되지 않은 결과들을 조회
    List<ReportVo> selectAllReportResultsF(boolean whoReport);

    // 관리자가 신고 처리 결과 등록
    int insertReportResult(boolean whoReport, ReportVo reportVo);

    // 관리자가 신고 처리 결과 수정
    int updateReportResult(boolean whoReport, ReportVo reportVo);


}
