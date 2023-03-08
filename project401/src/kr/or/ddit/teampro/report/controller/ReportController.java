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
        boolean whoIs = true;
        while (true) {
            System.out.println("신고하기");
            System.out.println("1. 신고하기 / 2. 신고이력보기 / 3. 뒤로가기");
            System.out.print("입력> ");
            choiceNum = Integer.parseInt(sc.nextLine());
            switch (choiceNum) {
                case 1:
                    // 신고하기
                    doReportDisplay(whoIs);
                    break;
                case 2:
                    // 신고이력 보기
                    displayReportList(whoIs);
                    break;
                case 3:
                    // 뒤로가기
                    return;
                default:
                    System.out.println("번호를 잘못 입력하셨습니다 다시 입력해주세요");
            }

        }
    }

    // 형재 로그인한 유저 정보 및 내 예약 확인 란의 예약 번호 까지 필요함
    public void doReportDisplay(boolean whoIs) {
        ReportVo reportVo = new ReportVo();

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
            return;

        }
    }

    // 로그인한 사용자의 신고리스트 보기
    public void displayReportList(boolean whoIs) {
        List<ReportVo> reportVoList;
        Map searchKey = new HashMap();
        searchKey.put("customerId", "김씨");
        searchKey.put("reservationNum", "1234");
        reportVoList = reportService.ReportInquire(whoIs, searchKey);
        System.out.println("==================================================");
        for (int i = 0; i < reportVoList.size(); i++) {
            System.out.println(i + 1 + "번 " + reportVoList.get(i).toString());
        }

        while (true) {
            // 상세보기
            detailReport(reportVoList, whoIs);
            return;
        }

    }

    public void detailReport(List<ReportVo> reportVoList, boolean whoIs) {
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
                // 자세히 볼 번호 검증
                boolean isOk = reportVoList.size() + 1 > choiceNum && choiceNum > 0;
                // 사이즈 만큼 입력 가능하고 그 외 숫자를 입력하면 다시 입력받게 해야함
                if (isOk) {
                    ReportVo reportVo = reportVoList.get(choiceNum - 1);
                    System.out.println(reportVo);
                    // 결과가 있으면 출력 없으면 신고가 처리 중 입니다 라는 메시지 출력

                    // 수정하기 , 삭제하기 기능이 표출
                    displayModify(reportVo, whoIs);
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

    }

    public void displayModify(ReportVo reportVo, boolean whoIs) {
        while (true) {
            System.out.println("1. 수정하기 / 2. 삭제하기 / 3. 돌아가기");
            System.out.print("입력> ");
            int choseNum = Integer.parseInt(sc.nextLine());
            switch (choseNum) {
                case 1:
                    //수정하기
                    System.out.println("수정할 내용을 입력해 주세요");
                    System.out.print("사유> ");
                    String reason = sc.nextLine();
                    reportVo.setReason(reason);
                    System.out.println("test: "+reportVo);
                    if (reportService.modifyReport(reportVo, whoIs) > 0) {
                        System.out.println("수정이 완료되었습니다");
                    } else {
                        System.out.println("수정이 실패했습니다 다시 시도해주세요");
                        return;
                    }
                    break;
                case 2:
                    // 삭제하기
                    System.out.println("정말로 삭제하시겠습니까? ");
                    System.out.println("1. 삭제한다. / 2. 돌아간다.");
                    choseNum = Integer.parseInt(sc.nextLine());
                    switch (choseNum) {
                        case 1:
                            System.out.println("정말로 삭제하시겠습니까?");
                            System.out.println("1. 돌아간다. / 2. 삭제한다.");
                            choseNum = Integer.parseInt(sc.nextLine());
                            switch (choseNum) {
                                case 2:
                                    if (reportService.cancelReport(reportVo.getReportNum(), whoIs) > 0) {
                                        System.out.println("삭제되었습니다");
                                    } else {
                                        System.out.println("실패하셨습니다 다시시도해주세요");
                                        return;
                                    }
                                    break;
                                default:
                                    return;
                            }
                        default:
                            return;
                    }
                case 3:
                    // 돌아가기
                    return;
                default:
                    System.out.println("잘못된 번호입니다 다시 입력해주세요");
                    break;
            }
        }
    }

    public static void main(String[] args) {
        new ReportController().displayReport();
    }
}
