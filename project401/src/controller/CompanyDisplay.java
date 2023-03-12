package controller;

import kr.or.ddit.teampro.accommodations.accomController.AccommodationsController;
import kr.or.ddit.teampro.company.coController.CompanyController;
import kr.or.ddit.teampro.company.coService.CompanyService;
import kr.or.ddit.teampro.company.coVo.CompanyVO;
import kr.or.ddit.teampro.customer.service.CustomerService;
import kr.or.ddit.teampro.customer.vo.CustomerVO;
import kr.or.ddit.teampro.event.EventMain;
import kr.or.ddit.teampro.notice.NoticeMain;
import kr.or.ddit.teampro.report.controller.ReportController;
import kr.or.ddit.teampro.reservation.controller.ReservationController;
import kr.or.ddit.teampro.room.rmController.RoomController;

import java.util.Scanner;

public class CompanyDisplay {
    CompanyVO companyVO;
    private Scanner sc;
    private EventMain eventMain;
    private NoticeMain noticeMain;
    private AccommodationsController accomController;
    private RoomController roomController;
    private CompanyController coController;
    private ReportController reportController;
    private ReservationController resController;

    public CompanyDisplay() {
        this.companyVO = CompanyService.getInstance().getVo();
        this.sc = new Scanner(System.in);
        this.eventMain = new EventMain();
        this.noticeMain = new NoticeMain();
        this.accomController = new AccommodationsController();
        this.roomController = new RoomController();
        this.coController = new CompanyController();
        this.reportController = new ReportController();
        this.resController = new ReservationController();
    }

    public void comMain() {
        System.out.println(companyVO.getCompanyId() + "님 환영합니다");
        while (true) {
            System.out.println("==============메뉴==============");
            System.out.println("1. 숙박시설 관리");
            System.out.println("2. 공지사항 보기");
            System.out.println("3. 이벤트 보기");
            System.out.println("4. 이벤트 작성");
            System.out.println("5. 내 정보");
            System.out.println("6. 로그아웃");
            System.out.println("================================");
            System.out.print("입력> ");
            int choseNum;
            choseNum = Integer.parseInt(sc.nextLine());
            switch (choseNum) {
                case 1:
                    // 숙박시설 관리
                    accomController.showMyAccom();
                    displayAccom();
                    break;
                case 2:
                    // 공지사항 보기 O
                    noticeMain.displayAllNotice();
                    break;
                case 3:
                    // 이벤트 보기 O
                    eventMain.displayAllEvent();
                    break;
                case 4:
                    // 이벤트 작성 O
                    eventMain.insertEvent();
                    break;
                case 5:
                    // 내 정보
                    showCoProfile();
                    break;
                case 6:
                    // 로그아웃
                    coController.logout();
                    return;
                default:
                    break;
            }
        }
    }

    public void displayAccom() {
        System.out.println("=========숙박시설 관리=========");
        System.out.println("1. 숙박시설 등록");
        System.out.println("2. 등록된 숙박시설 보기");
        System.out.println("3. 숙박시설정보 수정");
        System.out.println("4. 숙박시설정보 삭제");
        System.out.println("5. 숙박시설 객실 관리");
        System.out.println("6. 뒤로가기");
        System.out.println("===============================");
        System.out.print("입력> ");
        int choseNum;
        choseNum = Integer.parseInt(sc.nextLine());
        switch (choseNum) {
            case 1:
                //시설 등록
                accomController.insertAccommodations();
                break;
            case 2:
                // 등록된 숙박시설 보기
                accomController.showMyAccom();
                break;
            case 3:
                // 시설 정보 수정
                accomController.updateAccommodations();
                break;
            case 4:
                // 시설정보 삭제
                accomController.deleteAccommodations();
                break;
            case 5:
                // 방 관리
                roomController();
                break;
            case 6:
                // 뒤로가기
                break;
            default:
                break;
        }
    }

    public void roomController() {
        while (true) {
            // 내 소유 숙박시설 표시
            System.out.println("1. 객실 등록");
            System.out.println("2. 객실 정보 확인하기");
            System.out.println("2. 객실정보 수정");
            System.out.println("3. 객실정보 삭제");
            System.out.println("4. 뒤로가기");
            System.out.println("===============================");
            System.out.print("입력> ");
            int choseNum;
            choseNum = Integer.parseInt(sc.nextLine());
            switch (choseNum) {
                case 1:
                    // 객실 등록
                    roomController.insertRoom();
                    break;
                case 2:
                    // 객실 정보 확인하기 (숙박시설 명을 입력받아 방들의 정보를 불러와서 확인이 가능하게 해줌)
                    roomController.displayChooseRoom();
                    break;
                case 3:
                    // 객실 수정
                    roomController.updateRoom();
                    break;
                case 4:
                    // 객실 삭제
                    roomController.deleteRoom();
                    break;
                case 5:
                    // 뒤로가기
                    return;
                default:
                    break;
            }
        }
    }

    public void showCoProfile() {
            // 내 정보 표출
            coController.displayCompanyInfo();

        while (true) {
            System.out.println("1. 고객 이용 내역 및 신고 확인하기 / 2. 뒤로가기");
            // 이용내역 확인안에 신고하기 기능 넣어야 함

            System.out.println("===============================");
            System.out.print("입력> ");
            int choseNum;
            choseNum = Integer.parseInt(sc.nextLine());
            switch (choseNum) {
                case 1:
                    //전체 예약 이용 내역
                    resController.searchCoAllReservation();
                    reportController.displayReport(); // 신고기능
                    break;
                case 2:
                    // 뒤로가기
                    return;
                default:
                    System.out.println("잘못 입력하셨습니다 다시 시도해주세요");
            }
        }
    }
}
