package kr.or.ddit.teampro.room.rmVo;

public class RoomVO {
	private int roomNumber;				//객실 번호	--기본키
	private String accomName;			//숙박 시설명 --기본키,왜래키(accommodations테이블 참조)
	private String companyId;			//기업명	--기본키,외래키(company테이블 참조)
	private int grade;					//객실 등급
	private int price;					//숙박 비용
	private int appropriateNum;			//적정 인원수
	private String description;			//상세설명 --흡연가능여부 및 기타사항
	
	
	public int getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}
	public String getAccomName() {
		return accomName;
	}
	public void setAccomName(String accomName) {
		this.accomName = accomName;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getAppropriateNum() {
		return appropriateNum;
	}
	public void setAppropriateNum(int appropriateNum) {
		this.appropriateNum = appropriateNum;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	@Override
	public String toString() {
		return "\n객실번호:" + roomNumber + "\n숙박시설명: " + accomName + "\n객실 등급: "+ grade
				+ "\n숙박 비용(1박 기준): " + price + "\n적정인원: " + appropriateNum
				+ "\n상세설명:\n" + description+"\n---------------------";
	}
	
	
	
	
}
