package kr.or.ddit.teampro.report.controller;

import kr.or.ddit.teampro.report.service.ReportService;
import kr.or.ddit.teampro.report.service.ReportServiceImpl;
import kr.or.ddit.teampro.report.vo.ReportResultVo;
import kr.or.ddit.teampro.report.vo.ReportVo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        int choiceNum = 0;
        while (true) {
            System.out.println("===================신고===================");
            System.out.println("1. 유저 -> 기업 신고 확인하기");
            System.out.println("2. 기업 -> 유저 신고 확인하기");
            System.out.println("3.처리되지 않은 결과 조회하기");
            System.out.println("4.뒤로가기");
            System.out.println("-------------------------------------");
            System.out.print("입력> ");
            try {
                choiceNum = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
            }
            switch (choiceNum) {
                case 1:
                    // 모든 신고 정보 확인
                    displayAllReportResult(true);
                    break;
                case 2:
                    // 모든 신고 정보 확인
                    displayAllReportResult(false);
                    break;
                case 3:
                    // 처리하지 않은 신고 확인하기
                    displayNeedProcess();
                    break;
                case 4:
                    // 뒤로가기
                    return;
                default:
                    System.out.println("번호를 잘못 입력하셨습니다 다시 입력해주세요");
            }
        }
    }

    // 처리가 필요한 신고 출력
    private void displayNeedProcess() {
        System.out.println("=========================================================");
        System.out.println("1. 유저 신고 확인하기 / 2. 기업신고 확인하기 / 3. 뒤로가기");
        System.out.print("입력> ");
        int choseNum = Integer.parseInt(sc.nextLine());
        switch (choseNum) {
            case 1:
                System.out.println("처리가 필요한 유저들의 신고내역을 출력합니다");
                List<ReportVo> reportVoListUser = reportService.ReportResultsFInquire(true);
                if (reportVoListUser.size() > 0) {
                    for (int i = 0; i < reportVoListUser.size(); i++) {
                        System.out.println(i + 1 + "번-----------------------------------");
                        System.out.print("신고번호: " + reportVoListUser.get(i).getReportNum());
                        System.out.println(" / 예약번호: " + reportVoListUser.get(i).getResNum());
                        System.out.println("신고 사유: " + reportVoListUser.get(i).getReason());
                    }
                    // 처리하는 기능 넣어야함
                    processReport(reportVoListUser, true);
                } else {
                    System.out.println("처리할 신고가 없습니다");
                }

                break;

            case 2:
                System.out.println("처리가 필요한 기업들의 신고내역을 출력합니다");
                List<ReportVo> reportVoListCo = reportService.ReportResultsFInquire(false);
                if (reportVoListCo.size() > 0) {
                    for (int i = 0; i < reportVoListCo.size(); i++) {
                        System.out.println(i + 1 + "번-----------------------------------");
                        System.out.print("신고번호: " + reportVoListCo.get(i).getReportNum());
                        System.out.println(" / 예약번호: " + reportVoListCo.get(i).getResNum());
                        System.out.println("신고 사유: " + reportVoListCo.get(i).getReason());
                    }
                    // 처리 기능 필요
                    processReport(reportVoListCo, false);
                } else {
                    System.out.println("처리할 신고가 없습니다");
                }

                break;


        }

    }

    public void processReport(List<ReportVo> reportVoList, boolean whoIs) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("==================================================");
        System.out.println("1.신고 처리하기 / 2. 뒤로가기");
        System.out.print("입력> ");
        int choiceNum;
        choiceNum = Integer.parseInt(sc.nextLine());
        switch (choiceNum) {
            case 1:
                System.out.println("처리할 신고를 선택해 주세요");
                System.out.print("입력> ");
                choiceNum = Integer.parseInt(sc.nextLine());
                boolean isOk = reportVoList.size() + 1 > choiceNum && choiceNum > 0;
                // 사이즈 만큼 입력 가능하고 그 외 숫자를 입력하면 다시 입력받게 해야함
                if (isOk) {
                    ReportVo reportVo = reportVoList.get(choiceNum - 1);
                    ReportResultVo reportResultVo = new ReportResultVo();
                    reportResultVo.setReportNum(reportVo.getReportNum());
                    reportResultVo.setMasterId("test");
                    System.out.println("처리내용을 입력해주세요");
                    System.out.print("입력> ");
                    reportResultVo.setResult(sc.nextLine());
                    System.out.println("제재를 시작할 날짜를 입력해주세요(예: 2023-03-09)");
                    System.out.print("입력> ");
                    try {
                        reportResultVo.setStartDate(sdf.parse(sc.nextLine()));
                        System.out.println("제재를 종료할 날짜를 입력해주세요(예: 2023-03-09)");
                        System.out.print("입력> ");
                        reportResultVo.setEndDate(sdf.parse(sc.nextLine()));
                    } catch (ParseException e) {
                    }
                    if (reportService.registerReportResult(whoIs, reportResultVo) > 0) {
                        System.out.println("처리되었습니다");
                    } else {
                        System.out.println("다시 시도해 주세요");
                    }
                } else {
                    System.out.println("잘못된 번호입니다 다시 입력해 주세요");
                }
                break;
            case 2:
                // 돌아가기
                return;
            default:
                System.out.println("잘못된 번호입니다 다시 입력해주세요");
                break;
        }
        // 관리자가 미처리된 신고내역 확이
    }

    // 모든 신고 조회하기(결과도 같이 보이게 해야겠음)
    public void displayAllReportResult(boolean whoIs) {
        // 모든 신고 정보 가져오기
        List<ReportVo> reportVoList = reportService.ReportInquire(whoIs);
        if (reportVoList.size() == 0) {
            System.out.println("신고가 없습니다");
        } else {
            System.out.println("===========신고 정보==============");
            for (ReportVo reportVo : reportVoList) {
                System.out.print("신고번호: " + reportVo.getReportNum() + "번 / ");
                System.out.println("예약번호:  " + reportVo.getResNum() + "번 ");
                System.out.println("신고사유:  " + reportVo.getReason());
                System.out.println("---------------------------------");
            }
        }
    }

    public static void main(String[] args) {
        new ReportControllerMaster().displayReport4Master();
    }
}

