package kr.or.ddit.teampro.reservation.controller;

import kr.or.ddit.teampro.reservation.vo.ReservationVo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ReservationController {
    public static void main(String[] args) throws ParseException {
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        ReservationVo resVo = new ReservationVo();
        resVo.setReservationNum("1");
        resVo.setCustomerId("노인");
        resVo.setRoomNumber(101);
        resVo.setAccomName("참치호텔");
        resVo.setCompanyId("동원기업");
        resVo.setStartDate(sdf.parse("2023-03-03"));
        resVo.setEndDate(sdf.parse("2023-03-04"));
        resVo.setPepleNum(4);

        System.out.println("==============방 예약 ================");
        System.out.println("1. 예약하기 / 2.예약수정하기 / 3. 예약취소하기 / 4.내 예약 확인하기");
        int choseNum = Integer.parseInt(sc.nextLine());
        switch (choseNum) {
            case 1:

        }
    }
}
