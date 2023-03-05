package kr.or.ddit.teampro.event_proposal.vo;

public class event_proposalVO {
	
	private int eventNum;
	private String masterId;
	private String proIsok;
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
	@Override
	public String toString() {
		return "event_proposalVO [eventNum=" + eventNum + ", masterId=" + masterId + ", proIsok=" + proIsok + "]";
	}
	
	
}
