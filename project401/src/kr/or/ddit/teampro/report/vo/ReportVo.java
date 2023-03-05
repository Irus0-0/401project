package kr.or.ddit.teampro.report.vo;


/**
 * 기업을 신고하는 기능과 사용자를 신고하는 기능 둘다에서 사용
 */
public class ReportVo {
    private int reportNum; // 신고번호(기업신고, 사용자신고 둘다 사용)

    private String resNum; // 예약번호(외래키)

    private String reason; // 신고사유

    public int getReportNum() {
        return reportNum;
    }

    public void setReportNum(int reportNum) {
        this.reportNum = reportNum;
    }

    public String getResNum() {
        return resNum;
    }

    public void setResNum(String resNum) {
        this.resNum = resNum;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

}
