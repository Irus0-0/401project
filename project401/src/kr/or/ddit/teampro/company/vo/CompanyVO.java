package kr.or.ddit.teampro.company.vo;

public class CompanyVO {
    private String companyId;
    private String companyPw;
    private String name;
    private String bizNo;
    private String ownerName;
    private String phoneNum;
    private String addr;
    private String scale;
    private int sanctionsCount;

    public CompanyVO() {
    }

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
}
