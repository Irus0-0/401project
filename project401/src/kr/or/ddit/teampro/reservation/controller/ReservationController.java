package kr.or.ddit.teampro.reservation.controller;

import kr.or.ddit.teampro.reservation.service.ReservationService;
import kr.or.ddit.teampro.reservation.service.ReservationServiceImpl;
import kr.or.ddit.teampro.reservation.vo.ReservationVo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ReservationController {
    private ReservationService resService;
    private Scanner sc;
    static ReservationVo resVo = new ReservationVo();
    List<ReservationVo> reservationVoList;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public ReservationController() throws ParseException {
        resService = ReservationServiceImpl.getInstance();
        sc = new Scanner(System.in);

        // 테스트용
        resVo.setReservationNum("1");
        resVo.setCustomerId("김씨");
        resVo.setRoomNumber(101);
        resVo.setAccomName("참치호텔");
        resVo.setCompanyId("동원기업");
        resVo.setStartDate(sdf.parse("2023-03-03"));
        resVo.setEndDate(sdf.parse("2023-03-04"));
        resVo.setPepleNum(4);
    }


    public void displayRes() throws ParseException {
        int choiceNum;
        System.out.println("==============방 예약 ================");
        System.out.println("1. 예약하기 / 2.내 예약 확인하기 / 3. 뒤로가기");
        while (true) {
            choiceNum = Integer.parseInt(sc.nextLine());
            switch (choiceNum) {
                case 1:  // 예약하기
                    makeReservation();
                    break;
                case 2:  // 내 예약 확인하기
                    checkAllUseMyReservation();
                    break;
                case 3:  // 뒤로가기
                    return;
                default:
                    System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
            }
        }
    }

    //-------------------------------------------------------------------------------------

    /**
     * 예약 생성 과정을 출력
     */
    private void makeReservation() {
        // 로그인한 유저의 정보와 선택한 기업과 방 정보를 받아와서 객체에 저장 후 나머지 날짜를 입력받아 실행
        ReservationVo res = new ReservationVo();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("예약할 방 번호를 선택해주세요");
        System.out.print("입력> ");
        res.setRoomNumber(Integer.parseInt(sc.nextLine()));
        System.out.println("예약할 날짜를 정해주세요(ex:2023-03-04)");
        System.out.print("입력> ");
        while (true) {
            try {
                res.setStartDate(sdf.parse(sc.nextLine()));
                break;
            } catch (ParseException e) {
                System.out.println("날짜 형식에 맞지 않습니다, 다시 입력해주세요.");
            }
        }
        System.out.println("예약 종료 날짜를 정해주세요(ex:2023-03-04)");
        while (true) {
            try {
                System.out.print("입력> ");
                res.setEndDate(sdf.parse(sc.nextLine()));
                break;
            } catch (ParseException e) {
                System.out.println("날짜 형식에 맞지 않습니다, 다시 입력해주세요.");
            }
        }
        System.out.println("몇명의 고객이 이용하시나요?(숫자만 적어주세요)");
        System.out.print("입력> ");
        int count = Integer.parseInt(sc.nextLine());

        //todo 수정필요
        if (resService.registReservation(resVo) > 1) {
            System.out.println("예약이 완료되었습니다");
        } else {
            System.out.println("예약이 실패하였습니다, 다시 시도해 주세요.");
        }
    }

    //---------------------------------------------------------------------------------------------

    /**
     * 모든 예약 정보를 출력
     */
    private void checkAllMyReservation() throws ParseException { // 내 정보에서 사용해야 할듯
        List<ReservationVo> reservationVoList =
                resService.searchUserReservation(resVo.getCustomerId());
        if (reservationVoList.size() == 0) {
            System.out.println("예약이 없습니다.");
        } else {
            for (ReservationVo reservationVo : reservationVoList) {
                System.out.println("예약번호: " + reservationVo.getReservationNum());
                System.out.println("숙박시설명: " + reservationVo.getAccomName() +
                        "\t/\t방번호: " + reservationVo.getRoomNumber() + "호");
                System.out.println("예약일: " + sdf.format(reservationVo.getStartDate())
                        + " ~ " + sdf.format(reservationVo.getEndDate()));
                System.out.println("예약인원: " + reservationVo.getPepleNum() + "명");
                System.out.println("------------------------------------------");
            }
            while (true) {
                UDReservation();
            }
        }
    }
    // -----------------------------------------------------------------

    /**
     * 진행중 또는 아직 시행되지 않은 모든 예약정보 출력
     */
    private void checkAllUseMyReservation() throws ParseException { // 내 정보에서 사용해야 할듯
        reservationVoList = resService.searchUserUseReservation(resVo.getCustomerId());
        if (reservationVoList.size() == 0) {
            System.out.println("예약이 없습니다.");
        } else {
            for (ReservationVo reservationVo : reservationVoList) {
                System.out.println("예약번호: " + reservationVo.getReservationNum());
                System.out.println("숙박시설명: " + reservationVo.getAccomName() +
                        "\t/\t방번호: " + reservationVo.getRoomNumber() + "호");
                System.out.println("예약일: " + sdf.format(reservationVo.getStartDate())
                        + " ~ " + sdf.format(reservationVo.getEndDate()));
                System.out.println("예약인원: " + reservationVo.getPepleNum() + "명");
                System.out.println("------------------------------------------");
            }
            while (true) {
                UDReservation();
            }
        }
    }


    /**
     * 진행중 또는 시행되지 않은 예약정보를 확인 후 예약 수정 및 취소를 가능하게 하는 출력문
     */
    public void UDReservation() throws ParseException {
        int choiceNum;
        System.out.println("1.예약수정하기 / 2. 예약취소하기 / 3. 뒤로가기");
        System.out.print("입력> ");
        while (true) {
            choiceNum = Integer.parseInt(sc.nextLine());
            switch (choiceNum) {
                case 1:  // 예약수정하기
                    modifyMyReservation();
                    break;
                case 2:  // 예약 취소하기
                    deleteMyReservation();
                    break;
                case 3:  // 뒤로가기
                    return;
                default:
                    System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
            }
        }
    }

    /**
     * 예약 수정 하는 과정을 출력해줌
     */
    private void modifyMyReservation() {
        System.out.println("수정할 예약을 선택해주세요");
        System.out.print("입력> ");
        int choseResNum = Integer.parseInt(sc.nextLine());
        resVo = reservationVoList.get(choseResNum);
        System.out.println("방번호 변경"); // 그 숙박시설의 변경이 가능한 방을 보여준 뒤 선택
        System.out.println("예약날짜 변경"); // 예약날짜 또한 마찬가지
        System.out.println("인원 수 변경");



        // List 인덱스 넣고 나온 값으로 변경 시도
//        resService.modifyReservation();
    }

    //-------------------------------------------------------------

    /**
     * 예약 취소 출력
     */
    private void deleteMyReservation() {
        int choiceNum;
        System.out.println("예약을 취소 하시겠습니까?");
        System.out.println("1.예 / 2. 아니오");
        System.out.print("입력> ");
        choiceNum = Integer.parseInt(sc.nextLine());
        if (choiceNum == 1) {
            System.out.println("정말로 취소 하시겠습니까?");
            System.out.println("1.아니오/ 2. 예");
            System.out.print("입력> ");
            int choseNum = Integer.parseInt(sc.nextLine());
            if (choseNum == 2) {
//                if (resService.removeReservation(String resNum) >= 1) {
//                    System.out.println("예약이 취소 되었습니다.");
//                } else {
//                    System.out.println("알 수 없는 오류입니다, 다시 시도해주세요.");
//                }
            }
        } else {
            System.out.println("뒤로 돌아갑니다.");
        }

    }

    public static void main(String[] args) throws ParseException {

        new ReservationController().displayRes();
    }
}
