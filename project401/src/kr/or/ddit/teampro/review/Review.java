package review;

import review.service.IReviewService;
import review.service.ReviewService;
import review.vo.ReviewVO;

import java.util.List;
import java.util.Scanner;

public class Review {
    private IReviewService reviewService;
    private Scanner scanner;

    public Review() {
        reviewService = ReviewService.getInstance();
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
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    addReview();
                    break;
                case 2:
                    showAllReviews();
                    break;
                case 3:
                    updateReview();
                    break;
                case 4:
                    deleteReview();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }

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
        rv.setReservationNum("1");

        int result = reviewService.updateReview(rv);

    }

    private void showAllReviews() {
        System.out.println("모든 후기 보기");

        for (ReviewVO rv :reviewService.getReviewList()){
            System.out.printf("%8s\t%4d\t%s\n%s",
                    rv.getReservationNum(),rv.getReviewNum()
                    ,rv.getStarPoint(),rv.getContent());
        }


    }

    private void addReview() {

        System.out.println("리뷰를 작성하시겠습니까?");
        System.out.print("별점(1~5)>> ");
        int starPoint = Integer.parseInt(scanner.next());
        System.out.print("리뷰 내용(50자 이내)>> ");
        String content = scanner.next();

        ReviewVO rv = new ReviewVO();
        rv.setStarPoint(starPoint);
        rv.setContent(content);
        rv.setReservationNum("1");

        int result = reviewService.addReview(rv);
        if (result > 0) {
            System.out.println("성공");
        } else {
            System.out.println("실패");
        }
    }

    public static void main(String[] args) {
        new Review().start();
    }
}
