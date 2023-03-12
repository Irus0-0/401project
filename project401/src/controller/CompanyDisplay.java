package controller;

import kr.or.ddit.teampro.customer.service.CustomerService;
import kr.or.ddit.teampro.customer.vo.CustomerVO;

import java.util.Scanner;

public class CompanyDisplay {
    CustomerVO customerVO;
    private Scanner sc;
    public CompanyDisplay() {
        this.customerVO = CustomerService.getInstance().getVo();
        this.sc = new Scanner(System.in);
    }

    public void comMain() {
        System.out.println(customerVO.getCustomerId() +"님 환영합니다");
        while (true) {
            System.out.println("==============메뉴==============");
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
        }
    }
}
