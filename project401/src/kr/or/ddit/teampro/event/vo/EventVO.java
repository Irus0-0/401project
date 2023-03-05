package kr.or.ddit.teampro.event.vo;

public class EventVO {
	private int eventNum;
	private String companyId;
	private String eventTitle;
	private String eventContent;
	private String eveCreDate;
	private String eveStartDate;
	private String eveEndDate;
	
	public int getEventNum() {
		return eventNum;
	}
	public void setEventNum(int eventNum) {
		this.eventNum = eventNum;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getEventTitle() {
		return eventTitle;
	}
	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}
	public String getEventContent() {
		return eventContent;
	}
	public void setEventContent(String eventContent) {
		this.eventContent = eventContent;
	}
	public String getEveCreDate() {
		return eveCreDate;
	}
	public void setEveCreDate(String eveCreDate) {
		this.eveCreDate = eveCreDate;
	}
	public String getEveStartDate() {
		return eveStartDate;
	}
	public void setEveStartDate(String eveStartDate) {
		this.eveStartDate = eveStartDate;
	}
	public String getEveEndDate() {
		return eveEndDate;
	}
	public void setEveEndDate(String eveEndDate) {
		this.eveEndDate = eveEndDate;
	}
	@Override
	public String toString() {
		return "EventVO [eventNum=" + eventNum + ", companyId=" + companyId + ", eventTitle=" + eventTitle
				+ ", eventContent=" + eventContent + ", eveCreDate=" + eveCreDate + ", eveStartDate=" + eveStartDate
				+ ", eveEndDate=" + eveEndDate + "]";
	}
	
	
	
	

}
