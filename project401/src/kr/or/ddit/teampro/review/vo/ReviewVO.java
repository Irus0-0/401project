package kr.or.ddit.teampro.review.vo;

public class ReviewVO {
    private String reservationNum;
    private String content;
    private int starPoint;
    private int reviewNum;

    public int getReviewNum() {
        return reviewNum;
    }

    public void setReviewNum(int reviewNum) {
        this.reviewNum = reviewNum;
    }

    public String getReservationNum() {
        return reservationNum;
    }

    public void setReservationNum(String reservationNum) {
        this.reservationNum = reservationNum;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getStarPoint() {
        return starPoint;
    }

    public void setStarPoint(int starPoint) {
        this.starPoint = starPoint;
    }
}
