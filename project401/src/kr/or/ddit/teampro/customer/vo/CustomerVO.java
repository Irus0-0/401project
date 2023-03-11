package kr.or.ddit.teampro.customer.vo;

public class CustomerVO {
    private String customerId;
    private String customerPw;
    private String name;
    private String phoneNum;
    private String Gender;
    private String birthdate;
    private String addr;
    private String grade;
    private String sanctionsCount;

    public CustomerVO() {
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerPw() {
        return customerPw;
    }

    public void setCustomerPw(String customerPw) {
        this.customerPw = customerPw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getSanctionsCount() {
        return sanctionsCount;
    }

    public void setSanctionsCount(String sanctionsCount) {
        this.sanctionsCount = sanctionsCount;
    }
}
