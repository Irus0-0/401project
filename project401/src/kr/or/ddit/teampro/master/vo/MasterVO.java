package kr.or.ddit.teampro.master.vo;

public class MasterVO {
    private String masterId;
    private String masterPw;
    private String name;
    private int grade;

    public String getMasterId() {
        return masterId;
    }

    public void setMasterId(String masterId) {
        this.masterId = masterId;
    }

    public String getMasterPw() {
        return masterPw;
    }

    public void setMasterPw(String masterPw) {
        this.masterPw = masterPw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}