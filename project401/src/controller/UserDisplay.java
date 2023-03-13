package controller;

import kr.or.ddit.teampro.accommodations.accomController.AccommodationsController;
import kr.or.ddit.teampro.customer.Customer;
import kr.or.ddit.teampro.customer.service.CustomerService;
import kr.or.ddit.teampro.customer.service.ICustomerService;
import kr.or.ddit.teampro.customer.vo.CustomerVO;
import kr.or.ddit.teampro.event.EventMain;
import kr.or.ddit.teampro.notice.NoticeMain;
import kr.or.ddit.teampro.report.controller.ReportController;
import kr.or.ddit.teampro.reservation.controller.ReservationController;
import kr.or.ddit.teampro.room.rmController.RoomController;
import kr.or.ddit.teampro.room.rmVo.RoomVO;

import java.text.ParseException;
import java.util.Scanner;

public class UserDisplay {
    private CustomerVO customerVO;
    private Scanner sc;
    private ICustomerService customerService;
    private Customer customer;
    private ReservationController resController;
    private AccommodationsController accomController;
    private ReportController reportController;
    private RoomController roomController;
    private RoomVO roomVO;
    private EventMain eventMain;
    private NoticeMain noticeMain;

    public UserDisplay() throws ParseException {
        this.customerVO = CustomerService.getInstance().getVo();
        this.sc = new Scanner(System.in);
        this.customerService = CustomerService.getInstance();
        this.customer = new Customer();
        this.resController = new ReservationController();
        this.eventMain = new EventMain();
        this.noticeMain = new NoticeMain();
        this.accomController = new AccommodationsController();
        this.roomController = new RoomController();
        this.reportController = new ReportController();
    }

    public void userMain() throws ParseException {

        System.out.println(customerVO.getCustomerId() + "님 환영합니다.");
        boolean check = true;
        while (check) {
            System.out.println("==============메뉴==============");
            System.out.println("1. 숙소 예약하기");
            System.out.println("2. 예약 정보 확인하기");
            System.out.println("3. 공지사항 보기");
            System.out.println("4. 이벤트 확인하기");
            System.out.println("5. 내 정보");
            System.out.println("6. 로그아웃");
            System.out.println("================================");
            System.out.print("입력> ");
            int choseNum;
            choseNum = Integer.parseInt(sc.nextLine());
            switch (choseNum) {
                case 1:
                    // 예약 가능한 목록 출력
                    accomController.displayAllAccommodations();
                    break;
                case 2:
                    // 예약 정보 확인 및 수정 삭제 등등의 기능
                    resController.checkAllUseMyReservation();
                    break;
                case 3:
                    // 공지 사항
                    noticeMain.displayAllNotice();
                    break;
                case 4:
                    // 이벤트 확인
                    eventMain.displayAllEvent();
                    break;
                case 5:
                    // 내 정보 출력 및 여태까지의 예약 이력 확인하기
                    customer.displayCustomerInfo();
                    System.out.println("==================================");
                    System.out.println("1.종료된 예약 전체 보기 / 2. 신고이력 보기 / 3.뒤로가기");
                    System.out.print("입력> ");
                    choseNum = Integer.parseInt(sc.nextLine());
                    if (choseNum == 1) {
                        resController.checkAllCloseMyReservation();
                        System.out.println("==================================");
                        System.out.println("1. 신고하기 / 2. 돌아가기");
                        System.out.print("입력> ");
                        choseNum = Integer.parseInt(sc.nextLine());
                        switch (choseNum) {
                            case 1:
                                reportController.doReportDisplay(MainController.whoIs);
                                break;
                            case 2:
                                break;
                            default:
                                System.out.println("잘못입력하셨습니다");
                        }
                    } else if (choseNum == 2) {
                        reportController.displayReportList(MainController.whoIs);
                        break;
                    } else if (choseNum == 3) {
                        break;
                    } else {
                        System.out.println("잘못 입력하셨습니다 다시 입력해주세요");
                    }
                    break;
                case 6:
                    // 로그아웃
                    customerService.logout(customerVO);
                    check = false;
                default:
                    System.out.println("잘못 입력하셨습니다 다시 입력해주세요");
            }
        }

    }
}
