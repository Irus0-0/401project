package kr.or.ddit.teampro.accommodations.accomVo;

import java.text.SimpleDateFormat;
import java.util.Date;

import kr.or.ddit.teampro.company.coVo.CompanyVO;

public class AccommodationsVO {
	private String accomName;		//숙박시설명 --기본키
	private String companyId;		//숙박시설이 속한 기업의 기업ID --기본키, 외래키(company 테이블참조)
	private String accomAddr;		//숙박시설 주소
	private String phoneNum;		//숙박시설 전화번호
	private String type;			//숙박시설 분류-호텔,모텔,펜션,리조트
	private Date setUpDate;			//숙박시설 설립날짜
	private int starPoint;			//숙박시설 별점
	private int useCount;			//숙박시설 이용횟수
	private String description;		//숙박시설 상세설명(서비스가능언어, 아동및반려견 출입가능여부, 기타사항)
	
	
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
	public String getAccomAddr() {
		return accomAddr;
	}
	public void setAccomAddr(String accomAddr) {
		this.accomAddr = accomAddr;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getSetUpDate() {
		return setUpDate;
	}
	public void setSetUpDate(Date setUpDate) {
		this.setUpDate = setUpDate;
	}
	public int getStarPoint() {
		return starPoint;
	}
	public void setStarPoint(int starPoint) {
		this.starPoint = starPoint;
	}
	public int getUseCount() {
		return useCount;
	}
	public void setUseCount(int useCount) {
		this.useCount = useCount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	
	@Override
	public String toString() {
		return "\n숙박시설명: " + accomName + "\n시설주소: " + accomAddr
				+ "\n시설 전화번호: " + phoneNum + "\n시설 타입: " + type + "\n시설 설립일자: " + formatter.format(setUpDate) + "\n별점: "+ starPoint
				+ "\n누적 이용횟수: " + useCount + "\n상세설명\n"  + description+"\n-------------------------\n";
		
	}
	
	
	

	
}
