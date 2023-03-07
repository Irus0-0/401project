package kr.or.ddit.teampro.report.controller;

import kr.or.ddit.teampro.report.service.ReportService;
import kr.or.ddit.teampro.report.service.ReportServiceImpl;
import kr.or.ddit.teampro.report.vo.ReportVo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ReportController {
    private ReportService reportService;
    private Scanner sc;

    public ReportController() {
        reportService = ReportServiceImpl.getInstance();
        sc = new Scanner(System.in);
    }


    public void displayReport() {
        int choiceNum;
        while (true) {
            System.out.println("신고하기");
            System.out.println("1. 신고하기 / 2. 신고이력보기 / 3. 뒤로가기");
            choiceNum = Integer.parseInt(sc.nextLine());
            switch (choiceNum) {
                case 1:
                    // 신고하기
                    doReportDisplay();
                    break;
                case 2:
                    // 신고이력 보기
                    displayReportList();
                    break;
                case 3:
                    // 뒤로가기
                    break;
                default:
                    System.out.println("번호를 잘못 입력하셨습니다 다시 입력해주세요");
            }

        }
    }

    // 형재 로그인한 유저 정보 및 내 예약 확인 란의 예약 번호 까지 필요함
    public void doReportDisplay() {
        ReportVo reportVo = new ReportVo();
        boolean whoIs = true;
        while (true) {
            reportVo.setReportNum(3); // 신고번호  // 임시

            reportVo.setResNum("1234"); // 예약번호  // 임시
            System.out.println("신고 사유를 입력해주세요");
            System.out.print("입력> ");
            reportVo.setReason(sc.nextLine()); // 신고사유
            if (reportService.checkReport(reportVo, whoIs) <= 0) {
                if (reportService.doReport(reportVo, whoIs) >= 1) {
                    System.out.println("신고가 완료되었습니다.");
                    return;
                }
            }
            System.out.println("신고가 실패했습니다 다시 시도해주세요.");

        }
    }

    public void displayReportList() {
        List<ReportVo> reportVoList;
        Map searchKey = new HashMap();
        searchKey.put("customerId", "김씨");
        searchKey.put("reservationNum", "1234");
        boolean whoIs = true;
        reportVoList = reportService.ReportInquire(whoIs, searchKey);
        System.out.println("==================================================");
        for (int i = 0; i < reportVoList.size(); i++) {
            System.out.println(i + 1 + "번 " + reportVoList.get(i).toString());
        }

        while (true) {
            detailReport(reportVoList);
        }




       /* while (true) {
            choiceNum = Integer.parseInt(sc.nextLine());
            switch (choiceNum) {
                case 1:
                    // 신고 수정하기
                    displayModify();
                    break;
                case 2:
                    // 신고 삭제하기
                    displayReportList();
                    break;
                case 3:
                    // 뒤로가기
                    return;
                default:
                    System.out.println("번호를 잘못 입력하셨습니다 다시 입력해주세요");
            }

        }*/
    }

    public void detailReport(List<ReportVo> reportVoList) {
        System.out.println("==================================================");
        System.out.println("1.신고 자세히 보기 / 2. 뒤로가기");
        System.out.print("입력> ");
        int choiceNum;
        choiceNum = Integer.parseInt(sc.nextLine());
        switch (choiceNum) {
            case 1:
                System.out.println("자세히 볼 신고를 선택해 주세요");
                System.out.print("입력> ");
                choiceNum = Integer.parseInt(sc.nextLine());
                boolean isOk = reportVoList.size() + 1 > choiceNum && choiceNum > 0;
                // 사이즈 만큼 입력 가능하고 그 외 숫자를 입력하면 다시 입력받게 해야함
                if (isOk) {
                    System.out.println(reportVoList.get(choiceNum - 1));
                    // 수정하기 , 삭제하기 기능이 표출
                } else {
                    System.out.println("잘못된 번호입니다 다시 입력해 주세요");
                }
                break;
            case 2:
                return;
            default:
                System.out.println("잘못된 번호입니다 다시 입력해주세요");
                break;
        }

    }

    public void displayModify() {

    }

    public static void main(String[] args) {
        new ReportController().displayReport();
    }
}
