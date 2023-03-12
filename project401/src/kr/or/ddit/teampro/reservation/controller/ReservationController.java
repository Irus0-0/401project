package kr.or.ddit.teampro.reservation.controller;

import controller.MainController;
import kr.or.ddit.teampro.accommodations.accomController.AccommodationsController;
import kr.or.ddit.teampro.customer.service.CustomerService;
import kr.or.ddit.teampro.customer.vo.CustomerVO;
import kr.or.ddit.teampro.report.controller.ReportController;
import kr.or.ddit.teampro.reservation.service.ReservationService;
import kr.or.ddit.teampro.reservation.service.ReservationServiceImpl;
import kr.or.ddit.teampro.reservation.vo.ReservationVo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class ReservationController {
    private ReservationService resService;
    private Scanner sc;
    static ReservationVo resVo = new ReservationVo();
    List<ReservationVo> reservationVoList;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    private CustomerVO customerVO;

    public ReservationController(){
        resService = ReservationServiceImpl.getInstance();
        sc = new Scanner(System.in);
        this.customerVO = CustomerService.getInstance().getVo();
    }


    public void displayRes() throws ParseException {
        int choiceNum;
        while (true) {
            System.out.println("==============방 예약 ================");
            System.out.println("1. 예약하기 / 2. 예약 확인하기 / 3. 이력 확인하기/ 4. 뒤로가기");
            System.out.print("입력> ");
            choiceNum = Integer.parseInt(sc.nextLine());
            switch (choiceNum) {
                case 1:  // 예약하기
                    makeReservation();
                    break;
                case 2:  // 내 예약 확인하기
                    checkAllUseMyReservation();
                    break;
                case 3:
                    // 예약이력 확인하기
                    checkAllMyReservation();
                    break;

                case 4:  // 뒤로가기
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
    public void makeReservation() {
        // 로그인한 유저의 정보와 선택한 기업과 방 정보를 받아와서 객체에 저장 후 나머지 날짜를 입력받아 실행
        ReservationVo res = new ReservationVo();
        res.setCompanyId(AccommodationsController.companyVO.getCompanyId());
        res.setAccomName(AccommodationsController.companyVO.getName());

        System.out.println("test " + customerVO.getCustomerId());
        res.setCustomerId(customerVO.getCustomerId());
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
        res.setPeopleNum(count);

        //todo 수정필요
        if (resService.registReservation(res) > 0) {
            System.out.println("예약이 완료되었습니다");
        } else {
            System.out.println("예약이 실패하였습니다, 다시 시도해 주세요.");
        }
    }

    //---------------------------------------------------------------------------------------------

    /**
     * 모든 예약 정보를 출력
     */
    public void checkAllMyReservation() throws ParseException { // 내 정보에서 사용해야 할듯
        List<ReservationVo> reservationVoList =
                resService.searchUserReservation(customerVO.getCustomerId());
        if (reservationVoList.size() == 0) {
            System.out.println("예약이 없습니다.");
        } else {
            System.out.println("=============== 예약 이력 ===========");
            for (ReservationVo reservationVo : reservationVoList) {
                System.out.println("예약번호: " + reservationVo.getReservationNum());
                System.out.println("숙박시설명: " + reservationVo.getAccomName() +
                        "\t/\t방번호: " + reservationVo.getRoomNumber() + "호");
                System.out.println("예약일: " + sdf.format(reservationVo.getStartDate())
                        + " ~ " + sdf.format(reservationVo.getEndDate()));
                System.out.println("예약인원: " + reservationVo.getPeopleNum() + "명");
                System.out.println("------------------------------------------");
            }
        }
    }

    // -----------------------------------------------------------------

    /**
     * 진행중 또는 아직 시행되지 않은 모든 예약정보 출력
     */
    public void checkAllUseMyReservation() throws ParseException { // 내 정보에서 사용해야 할듯
        reservationVoList = resService.searchUserUseReservation(customerVO.getCustomerId());
        if (reservationVoList.size() == 0) {
            System.out.println("예약이 없습니다.");
        } else {
            for (int i = 0; i < reservationVoList.size(); i++) {
                System.out.println(i + 1 + "번---------------------------------------------------------");
                System.out.println("예약번호: " + reservationVoList.get(i).getReservationNum());
                System.out.println("숙박시설명: " + reservationVoList.get(i).getAccomName() +
                        "\t/\t방번호: " + reservationVoList.get(i).getRoomNumber() + "호");
                System.out.println("예약일: " + sdf.format(reservationVoList.get(i).getStartDate())
                        + " ~ " + sdf.format(reservationVoList.get(i).getEndDate()));
                System.out.println("예약인원: " + reservationVoList.get(i).getPeopleNum() + "명");
//                System.out.println("-----------------------------------------------------------");
            }
            detailReservation(reservationVoList);
        }
    }

    /**
     * 종료된 모든 예약정보 출력
     */
    public void checkAllCloseMyReservation() throws ParseException { // 내 정보에서 사용해야 할듯
        reservationVoList = resService.searchCloseReservation(customerVO.getCustomerId());
        if (reservationVoList.size() == 0) {
            System.out.println("종료된 예약이 없습니다.");
        } else {
            for (int i = 0; i < reservationVoList.size(); i++) {
//                System.out.println(i + 1 + "번---------------------------------------------------------");
                System.out.println("-----------------------------------------------------------");
                System.out.println("예약번호: " + reservationVoList.get(i).getReservationNum());
                System.out.println("숙박시설명: " + reservationVoList.get(i).getAccomName() +
                        "\t/\t방번호: " + reservationVoList.get(i).getRoomNumber() + "호");
                System.out.println("예약일: " + sdf.format(reservationVoList.get(i).getStartDate())
                        + " ~ " + sdf.format(reservationVoList.get(i).getEndDate()));
                System.out.println("예약인원: " + reservationVoList.get(i).getPeopleNum() + "명");
            }
                // 신고 기능 처리 필요
//                new ReportController().doReportDisplay(MainController.whoIs);
        }
    }


    private void detailReservation(List<ReservationVo> reservationVoList) throws ParseException {
        boolean isOk;
        System.out.println("==================================================");
        System.out.println("1. 예약수정하기 /  2. 예약취소하기  / 3. 뒤로가기");
        System.out.print("입력> ");
        int choiceNum;
        choiceNum = Integer.parseInt(sc.nextLine());
        switch (choiceNum) {
            case 1:
                System.out.println("수정할 예약을 선택해 주세요");
                System.out.print("입력> ");
                choiceNum = Integer.parseInt(sc.nextLine());
                isOk = reservationVoList.size() + 1 > choiceNum && choiceNum > 0;
                if (isOk) {
                    ReservationVo reservationVo = reservationVoList.get(choiceNum - 1);

                    System.out.println(reservationVo.toString());
                    // 수정 과정 진행하기

                    // 수정하기 , 삭제하기 기능이 표출
                    modifyMyReservation(reservationVo);
                } else {
                    System.out.println("잘못된 번호입니다 다시 입력해 주세요");
                }
                break;
            case 2:
                System.out.println("삭제할 예약을 선택해주세요.");
                System.out.print("입력> ");
                choiceNum = Integer.parseInt(sc.nextLine());

                isOk = reservationVoList.size() + 1 > choiceNum && choiceNum > 0;
                if (isOk) {
                    ReservationVo reservationVo = reservationVoList.get(choiceNum - 1);

                    System.out.println(reservationVo.toString());

                    // 삭제 하기
                    deleteMyReservation(reservationVo.getReservationNum());

                } else {
                    System.out.println("잘못된 번호입니다 다시 입력해 주세요");
                }
                break;
            case 3:
                // 돌아가기
                return;
            default:
                System.out.println("잘못된 번호입니다 다시 입력해주세요");
                break;
        }

    }


    /**
     * 진행중 또는 시행되지 않은 예약정보를 확인 후 예약 수정 및 취소를 가능하게 하는 출력문
     */
//    public void UDReservation(ReservationVo reservationVo) throws ParseException {
//        int choiceNum;
//        while (true) {
//            System.out.println("1.예약수정하기 / 2. 예약취소하기 / 3. 뒤로가기");
//            System.out.print("입력> ");
//            choiceNum = Integer.parseInt(sc.nextLine());
//            switch (choiceNum) {
//                case 1:  // 예약수정하기
//                    modifyMyReservation();
//                    break;
//                case 2:  // 예약 취소하기
//                    deleteMyReservation();
//                    break;
//                case 3:  // 뒤로가기
//                    return;
//                default:
//                    System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
//            }
//        }
//    }

    /**
     * 예약 수정 하는 과정을 출력해줌
     */
    private void modifyMyReservation(ReservationVo reservationVo) {
        // 해당 숙소의 교체 가능한 방 리스트를 보여줘야함
        //TODO 교체 가능한 숙소 정보 필요
        System.out.println("교체하실 방 번호를 선택해주세요"); // 그 숙박시설의 변경이 가능한 방을 보여준 뒤 선택
//        reservationVo.setRoomNumber();
        System.out.println("변경하실 예약날짜를 선택해주세요"); // 예약날짜 또한 마찬가지
//        reservationVo.setStartDate();
//        reservationVo.setEndDate();
        System.out.println("인원 수 변경");
//        reservationVo.setPeopleNum();

        // List 인덱스 넣고 나온 값으로 변경 시도
        resService.modifyReservation(reservationVo);
    }

    //-------------------------------------------------------------

    /**
     * 예약 취소 출력
     */
    private void deleteMyReservation(String reservationNum) {
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
                if (resService.removeReservation(reservationNum) >= 1) {
                    System.out.println("예약이 취소 되었습니다.");
                } else {
                    System.out.println("알 수 없는 오류입니다, 다시 시도해주세요.");
                }
            }
        } else {
            System.out.println("뒤로 돌아갑니다.");
        }

    }

    public static void main(String[] args) throws ParseException {

        new ReservationController().displayRes();
    }
}
