package kr.or.ddit.teampro.notice.vo;


public class noticeVO {
	private int noticeNum;
	private String masterId;
	private String notTitle;
	private String notContent;
	private String notDate;
	private String notGrade;
	
	public int getNoticeNum() {
		return noticeNum;
	}
	public void setNoticeNum(int noticeNum) {
		this.noticeNum = noticeNum;
	}
	public String getMasterId() {
		return masterId;
	}
	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}
	public String getNotTitle() {
		return notTitle;
	}
	public void setNotTitle(String notTitle) {
		this.notTitle = notTitle;
	}
	public String getNotContent() {
		return notContent;
	}
	public void setNotContent(String notContent) {
		this.notContent = notContent;
	}
	public String getNotDate() {
		return notDate;
	}
	public void setNotDate(String notDate) {
		this.notDate = notDate;
	}
	public String getNotGrade() {
		return notGrade;
	}
	public void setNotGrade(String notGrade) {
		this.notGrade = notGrade;
	}
	@Override
	public String toString() {
		return "NoticeVO [noticeNum=" + noticeNum + ", masterId=" + masterId + ", notTitle=" + notTitle
				+ ", notContent=" + notContent + ", notDate=" + notDate + ", notGrade=" + notGrade + "]";
	}
	
	
	
	
}
