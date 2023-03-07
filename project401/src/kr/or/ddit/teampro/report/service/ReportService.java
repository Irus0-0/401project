package kr.or.ddit.teampro.report.service;

import kr.or.ddit.teampro.report.vo.ReportVo;

import java.util.List;
import java.util.Map;

public interface ReportService {

    // 파라미터  whoReport T = 유저가 신고 / F = 기업이 신고
    // 신고하는 기능
    int doReport(ReportVo reportVo, boolean whoReport);

    int checkReport(ReportVo reportVo, boolean whoReport);

    // 신고한 자료 수정하기
    int modifyReport(ReportVo reportVo, boolean whoReport);

    // 신고 취소하기
    int cancelReport(int reportNum, boolean whoReport);

    // 모든 신고 조회하기(관리자가 로그로 사용)
    List<ReportVo> ReportInquire(boolean whoReport);

    // 선택한 유저나 기업이 신고한 모든 정보 확인하기
    List<ReportVo> ReportInquire(boolean whoReport, Map search);

    // 유저나 기업이 신고하거나 했었던 모든 자료를 조회
    List<ReportVo> ReportResultsInquire(boolean whoReport);

    // 아직 처리 되지 않은 결과들을 조회
    List<ReportVo> ReportResultsFInquire(boolean whoReport);

    // 관리자가 신고 처리 결과 등록
    int registerReportResult(boolean whoReport, ReportVo reportVo);

    // 관리자가 신고 처리 결과 수정
    int modifyReportResult(boolean whoReport, ReportVo reportVo);

}
