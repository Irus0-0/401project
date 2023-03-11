package controller;

import kr.or.ddit.teampro.customer.vo.CustomerVO;

import java.util.Scanner;

public class UserDisplay {
    private CustomerVO customerVO;
    private Scanner sc;

    public UserDisplay(CustomerVO vo) {
        this.customerVO = vo;
        this.sc = new Scanner(System.in);
    }

    public void userMain() {
        System.out.println(customerVO.getCustomerId() + "님 환영합니다.");
        System.out.println("==============메뉴==============");

    }
}
