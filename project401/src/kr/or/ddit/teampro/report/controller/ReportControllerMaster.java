package kr.or.ddit.teampro.report.controller;

import kr.or.ddit.teampro.report.service.ReportService;
import kr.or.ddit.teampro.report.service.ReportServiceImpl;
import kr.or.ddit.teampro.report.vo.ReportVo;

import java.util.List;
import java.util.Scanner;

public class ReportControllerMaster {

    private ReportService reportService;
    private Scanner sc;

    public ReportControllerMaster() {
        reportService = ReportServiceImpl.getInstance();
        sc = new Scanner(System.in);
    }


    public void displayReport4Master() {
        int choiceNum;
        boolean whoIs = true;
        while (true) {

            System.out.println("1. 모든 신고 확인하기 /" +
                    " 2.처리되지 않은 결과 조회하기 / " +
                    "3.선택한 유저의 신고 정보 확인하기 / 4.뒤로가기");
            System.out.print("입력> ");
            choiceNum = Integer.parseInt(sc.nextLine());
            switch (choiceNum) {
                case 1:
                    // 모든 신고 정보 확인
                    displayAllReportResult(whoIs);
                    break;
                case 2:
                    // 처리하지 않은 신고 확인하기
                    break;
                case 3:
                    // 선택한 유저의 신고정보 확인하기
                    break;
                case 4:
                    // 뒤로가기
                    return;

                default:
                    System.out.println("번호를 잘못 입력하셨습니다 다시 입력해주세요");
            }
        }
    }

    // 모든 신고 조회하기(결과도 같이 보이게 해야겠음)
    public void displayAllReportResult(boolean whoIs) {
        // 모든 신고 정보 가져오기
        List<ReportVo> reportVoList = reportService.ReportInquire(whoIs);
        for (ReportVo reportVo : reportVoList) {
            System.out.println(reportVo.toString());
        }

        // 모든 신고 결과 조회하기
        reportService.ReportResultsFInquire(whoIs);
    }

    public static void main(String[] args) {
        new ReportControllerMaster().displayReport4Master();
    }
}
