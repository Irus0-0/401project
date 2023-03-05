package kr.or.ddit.teampro.report.vo;

import java.util.Date;

public class ReportResultVo {
    private int reportNum; // 신고 번호
    private String masterId; // 신고를 처리한 관리자 ID
    private String result; // 신고 결과
    private Date startDate; // 제재 시작일
    private Date endDate; // 제재 종료일


    public int getReportNum() {
        return reportNum;
    }

    public void setReportNum(int reportNum) {
        this.reportNum = reportNum;
    }

    public String getMasterId() {
        return masterId;
    }

    public void setMasterId(String masterId) {
        this.masterId = masterId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
