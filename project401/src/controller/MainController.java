package controller;

import kr.or.ddit.teampro.company.coController.CompanyController;
import kr.or.ddit.teampro.customer.Customer;
import kr.or.ddit.teampro.customer.service.CustomerService;
import kr.or.ddit.teampro.customer.vo.CustomerVO;
import kr.or.ddit.teampro.master.Master;

import java.util.Scanner;

public class MainController {
    private Scanner sc;
    private static boolean whoIs;

    private Customer customer;
    private CompanyController company;
    private Master master;
    private CustomerService customerService;


    public MainController() {
        this.sc = new Scanner(System.in);
        this.customer = new Customer();
        this.company = new CompanyController();
        this.master = new Master();
    }

    public void mainDisplay() {
        while (true) {

            System.out.println("===========이짝워뗘===========");
            System.out.println("1. 로그인");
            System.out.println("2. 회원가입");
            System.out.println("3. 종료");
            System.out.println("==============================");
            System.out.print("입력> ");
            int choseNum = 0;
            try {
                choseNum = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("잘못입력하셨습니다, 다시 입력해주세요");
            }
            switch (choseNum) {
                case 1:
                    // 사용자 / 기업
                    System.out.println("로그인할 회원을 선택해 주세요");
                    System.out.println("1. 일반회원 / 2. 기업회원 / 3. 돌아가기");
                    System.out.print("입력> ");
                    choseNum = Integer.parseInt(sc.nextLine());
                    //로그인
                    if (choseNum == 1) {
                        // 일반회원 로그인
                        customer.login();
                        whoIs = true;
                        // 회원 화면 출력
                        userDisplay(customerService.getVo());
                    } else if (choseNum == 2) {
                        // 기업회원 로그인
                        company.login();
                        whoIs = false;
                        // 기업 화면 출력
                        companyDisplay();
                    } else if (choseNum == 3) {
                        // 돌아가기
                        break;
                    } else if (choseNum == 1212) {
                        // 관리자 로그인
                        master.login();
                        if (Master.getInstance() != null) {
                            // 관리자 화면 출력
                            adminDisplay();
                        }
                    } else {
                        // 잘못된 숫자 입력
                        System.out.println("잘못입력하셨습니다, 다시 입력해주세요");
                    }
                    break;
                case 2:
                    // 회원가입
                    System.out.println("1. 일반회원 가입 / 2. 기업회원 가입 / 3. 돌아가기");
                    System.out.print("입력> ");
                    choseNum = Integer.parseInt(sc.nextLine());

                    if (choseNum == 1) { // 일반 회원 가입
                        customer.insertCustomer();
                    } else if (choseNum == 2) { // 기업 회원 가입
                        company.insertCompany();
                    } else if (choseNum == 3) { // 돌아가기
                        return;
                    } else {  // 잘못된 숫자 입력
                        System.out.println("잘못입력하셨습니다, 다시 입력해주세요");
                    }
                    break;
                case 3:
                    // 종료
                    System.out.println("프로그램을 종료합니다.");
                    break;
                default:
                    System.out.println("잘못 입력하셨습니다 다시 입력해 주세요");
            }
        }

    }

    // 유저 로그인 후 화면
    public void userDisplay(CustomerVO vo) {
        new UserDisplay(vo);
    }

    // 기업 로그인 후 화면
    public void companyDisplay() {
    }

    // 관리자 로그인 후 화면
    public void adminDisplay() {
    }

    public static void main(String[] args) {
        new MainController().mainDisplay();
    }


}
