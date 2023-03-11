package kr.or.ddit.teampro.reservation.vo;

import java.util.Date;

public class ReservationVo {
    private String reservationNum; // 예약번호
    private String customerId; // 고객ID
    private int roomNumber; // 방번호
    private String accomName; // 숙박시설명
    private String companyId; // 기업ID
    private Date startDate; // 예약 날짜
    private Date endDate; // 예약 종료날짜
    private int peopleNum; // 예약인원수

    // Getter Setter

    public String getReservationNum() {
        return reservationNum;
    }

    public void setReservationNum(String reservationNum) {
        this.reservationNum = reservationNum;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getPeopleNum() {
        return peopleNum;
    }

    public void setPeopleNum(int peopleNum) {
        this.peopleNum = peopleNum;
    }

    @Override
    public String toString() {
        return "ReservationVo{" +
                "reservationNum='" + reservationNum + '\'' +
                ", customerId='" + customerId + '\'' +
                ", roomNumber=" + roomNumber +
                ", accomName='" + accomName + '\'' +
                ", companyId='" + companyId + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", peopleNum=" + peopleNum +
                '}';
    }
}
