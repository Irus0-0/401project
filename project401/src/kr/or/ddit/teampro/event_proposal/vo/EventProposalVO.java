package kr.or.ddit.teampro.event_proposal.vo;

public class EventProposalVO {
	
	private int eventNum;
	private String masterId;
	private String proIsok;
	
	private String companyId;
	private String proTitle;
	private String proContent;
	private String proCreateDay;
	private String proStartDay;
	private String proEndDate;
	
	public int getEventNum() {
		return eventNum;
	}
	public void setEventNum(int eventNum) {
		this.eventNum = eventNum;
	}
	public String getMasterId() {
		return masterId;
	}
	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}
	public String getProIsok() {
		return proIsok;
	}
	public void setProIsok(String proIsok) {
		this.proIsok = proIsok;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getProTitle() {
		return proTitle;
	}
	public void setProTitle(String proTitle) {
		this.proTitle = proTitle;
	}
	public String getProContent() {
		return proContent;
	}
	public void setProContent(String proContent) {
		this.proContent = proContent;
	}
	public String getProCreateDay() {
		return proCreateDay;
	}
	public void setProCreateDay(String proCreateDay) {
		this.proCreateDay = proCreateDay;
	}
	public String getProStartDay() {
		return proStartDay;
	}
	public void setProStartDay(String proStartDay) {
		this.proStartDay = proStartDay;
	}
	public String getProEndDate() {
		return proEndDate;
	}
	public void setProEndDate(String proEndDate) {
		this.proEndDate = proEndDate;
	}
	@Override
	public String toString() {
		return "EventProposalVO [eventNum=" + eventNum + ", masterId=" + masterId + ", proIsok=" + proIsok
				+ ", companyId=" + companyId + ", proTitle=" + proTitle + ", proContent=" + proContent
				+ ", proCreateDay=" + proCreateDay + ", proStartDay=" + proStartDay + ", proEndDate=" + proEndDate
				+ "]";
	}
	
	
	
	
}
