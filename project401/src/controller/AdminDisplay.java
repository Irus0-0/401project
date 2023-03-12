package controller;

import kr.or.ddit.teampro.notice.NoticeMain;

import java.util.Scanner;

public class AdminDisplay {
    Scanner sc;
    NoticeMain noticeMain;

    public AdminDisplay() {
        sc = new Scanner(System.in);
        noticeMain = new NoticeMain();
    }

    public void adminDisplay() {
        while (true) {
            System.out.println("============관리자============");
            System.out.println("1. 공지사항");
            System.out.println("2. 신고처리");
            System.out.println("3. 관리자 생성");
            System.out.println("4. 로그아웃");
            System.out.println("===============================");
            System.out.print("입력> ");
            int chooseNum = Integer.parseInt(sc.nextLine());
            switch (chooseNum) {
                case 1:
                    // 공지사항
                    noticeDisplay();
                    break;
                case 2:
                    // 신고처리
                    break;
                case 3:
                    // 관라지 생성
                    break;
                case 4:
                    // 로그아웃

                    break;
                default:
            }

        }

    }

    private void noticeDisplay() {
        while (true) {
            System.out.println("=========================공지사항=========================");
            noticeMain.displayAllNotice();
            System.out.println("===========================================================================");
            System.out.println("1. 공지사항 등록 / 2. 공지사항 수정 / 3. 공지사항 삭제 / 4. 공지사항 검색 / 5. 뒤로가기");
            System.out.print("입력> ");
            int chooseNum = Integer.parseInt(sc.nextLine());
            switch (chooseNum) {
                case 1:
                    // 공지사항 등록
                    noticeMain.insertNotice();
                    break;
                case 2:
                    // 공지사항 수정
                    noticeMain.modifyNotice();
                    break;
                case 3:
                    noticeMain.deleteNotice();
                    // 공지사항 삭제
                    break;
                case 4:
                    // 공지사항 검색
                    noticeMain.searchNotice();
                    break;
                case 5:
                    //뒤로가기
                    return;
                default:
                    System.out.println("잘못 입력하셨습니다 다시 시도해주세요");

            }
        }
    }


}
