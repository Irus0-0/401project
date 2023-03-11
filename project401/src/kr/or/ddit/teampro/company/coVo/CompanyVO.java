package kr.or.ddit.teampro.company.coVo;

public class CompanyVO {
    private String companyId; 			//기업 아이디--기본키
    private String companyPw;			//기업 패스워드
    private String name;				//기업명
    private String bizNo;				//사업자 등록번호
    private String ownerName;			//CEO
    private String phoneNum;			//기업 전화번호
    private String addr;				//기업 주소지
    private String scale;				//기업 규모
    private int sanctionsCount;			//기업이 제재당한 횟수
    
    
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getCompanyPw() {
		return companyPw;
	}
	public void setCompanyPw(String companyPw) {
		this.companyPw = companyPw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBizNo() {
		return bizNo;
	}
	public void setBizNo(String bizNo) {
		this.bizNo = bizNo;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getScale() {
		return scale;
	}
	public void setScale(String scale) {
		this.scale = scale;
	}
	public int getSanctionsCount() {
		return sanctionsCount;
	}
	public void setSanctionsCount(int sanctionsCount) {
		this.sanctionsCount = sanctionsCount;
	}
	
	
	@Override
	public String toString() {
		return "CompanyVO [companyId=" + companyId + ", companyPw=" + companyPw + ", name=" + name + ", bizNo=" + bizNo
				+ ", ownerName=" + ownerName + ", phoneNum=" + phoneNum + ", addr=" + addr + ", scale=" + scale
				+ ", sanctionsCount=" + sanctionsCount + "]";
	}
    
    
	
	
    
}
