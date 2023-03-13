package kr.or.ddit.teampro.review;


import kr.or.ddit.teampro.review.service.IReviewService;
import kr.or.ddit.teampro.review.service.ReviewService;
import kr.or.ddit.teampro.review.vo.ReviewVO;

import java.util.Scanner;

public class Review {

    //서비스 불러옴
    private IReviewService reviewService;
    private Scanner scanner;

    //싱글톤 처리
    private static Review instance;

    public static Review getInstance() {
        if (instance == null) {
            instance = new Review();
        }
        return instance;
    }

    //서비스 인스턴스
    public Review() {
        reviewService = ReviewService.getInstance();
        scanner = new Scanner(System.in);
    }

    public void start() {
        scanner = new Scanner(System.in);
        while (true) {
            System.out.println("-------------------------");
            System.out.println("1. 후기 작성");
            System.out.println("2. 작성한 모든 후기 보기");
            System.out.println("3. 후기 수정");
            System.out.println("4. 후기 삭제");
            System.out.println("5. 종료");
            System.out.println("-------------------------");
            System.out.print("번호>> ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    //후기 작성할 때 로그인한 고객 객체 저장한 연결고리
                    //미구현이니까, 메인 컨트롤러에서 처리 바람
                    addReview();
                    break;
                case 2:
                    //모든 후기 내용 보기
                    showAllReviews();
                    break;
                case 3:
                    //후기 수정
                    updateReview();
                    break;
                case 4:
                    //후기 삭제
                    deleteReview();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("잘못된 입력입니다");
                    break;
            }
        }
    }

    //후기 삭제시 로그인 x 일때 구현할거면 불러와서 앞부분에서 하면 될거라고 생각함
    private void deleteReview() {
        System.out.println("리뷰를 삭제하시겠습니까?");
        System.out.print("후기 번호>> ");
        int reviewNum = Integer.parseInt(scanner.next());

        ReviewVO review = new ReviewVO();
        review.setReviewNum(reviewNum);

        int result = reviewService.deleteReview(review);
        if (result > 0) {
            System.out.println("성공");
        } else {
            System.out.println("실패");
        }
    }

    //로그인 했을 때만 받아오게 설정하면 되는거라, 굳이 구현 안해도 될듯?
    //후기 수정
    private void updateReview() {
        System.out.println("리뷰를 수정하시겠습니까?");
        System.out.print("후기 번호>> ");
        int reviewNum = Integer.parseInt(scanner.next());
        System.out.print("별점(1~5)>> ");
        int starPoint = Integer.parseInt(scanner.next());
        System.out.print("리뷰 내용(50자 이내)>> ");
        String content = scanner.next();

        ReviewVO rv = new ReviewVO();
        rv.setReviewNum(reviewNum);
        rv.setStarPoint(starPoint);
        rv.setContent(content);
        //후기수정 이 부분은 메인에서 처리해줘야된다고 생각해서 더 이상 진행하지 않았음
        //메인에서 로그인 객체 저장된 녀석을 받아서 써야된다고 생각했음
        rv.setReservationNum("1");

        int result = reviewService.updateReview(rv);

    }

    //모든 후기 불러오기
    private void showAllReviews() {
        System.out.println("모든 후기 보기");

        for (ReviewVO rv : reviewService.getReviewList()) {
            System.out.printf("%8s\t%4d\t%s\n%s\n",
                    rv.getReservationNum(), rv.getReviewNum()
                    , rv.getStarPoint(), rv.getContent());
        }


    }

    //후기 작성
    public void addReview() {
        int starPoint = 0;
        ReviewVO rv = new ReviewVO();
        System.out.println("리뷰를 작성할 예약을 선택해주세요(예약번호 입력)");
        System.out.print("입력> ");
        rv.setReservationNum(scanner.nextLine());

        System.out.print("별점(1~5)>> ");
        starPoint = Integer.parseInt(scanner.nextLine());

        System.out.print("리뷰 내용(50자 이내)>> ");
        String content = scanner.nextLine();
        rv.setContent(content);
        rv.setStarPoint(starPoint);

        int result = reviewService.addReview(rv);
        if (result > 0) {
            System.out.println("후기작성이 완료되었습니다");
        } else {
            System.out.println("후기작성이 완료되지 않았습니다");
        }
    }


    public static void main(String[] args) {
        new Review().start();
    }
}
