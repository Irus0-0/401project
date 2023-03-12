package kr.or.ddit.teampro.notice;

import java.util.List;
import java.util.Scanner;

import kr.or.ddit.teampro.master.service.MasterService;
import kr.or.ddit.teampro.notice.service.INoticeService;
import kr.or.ddit.teampro.notice.service.NoticeServiceImpl;
import kr.or.ddit.teampro.notice.vo.noticeVO;

public class NoticeMain {

    private INoticeService notService;
    private Scanner scan = new Scanner(System.in);

    public NoticeMain() {
        notService = NoticeServiceImpl.getInstance();
    }

    /**
     * 메뉴를 출력하는 메서드
     */

    public void displayMenu() {
        System.out.println();
//		System.out.println("------------------------------------");
        System.out.println("---------------작업선택----------------");
        System.out.println("1. 공지 조회 ");
        System.out.println("2. 공지 등록 ");
        System.out.println("3. 공지 수정 ");
        System.out.println("4. 공지 삭제 ");
        System.out.println("5. 공지 검색 ");
        System.out.println("6. 작업 끝 ");
        System.out.println("------------------------------------");
        System.out.println();
        System.out.print("원하는 작업 선택 >> ");
    }

    public void start() {
        int choice;

        do {
            displayMenu();
            choice = Integer.parseInt(scan.nextLine());

            switch (choice) {

                case 1: // 공지 출력
                    displayAllNotice();
                    break;
                case 2: // 공지 등록
                    insertNotice();
                    break;
                case 3: // 공지 수정
                    modifyNotice();
                    break;
                case 4: // 공지 삭제
                    deleteNotice();
                    break;
                case 5: // 공지 검색
                    searchNotice();
                    break;
                case 6:
                    System.out.println("작업을 마칩니다.");
                    break;
                default:
                    System.out.println("번호를 잘못 입력했습니다. 다시 입력해주세요.");

            }

        } while (choice != 6);

    }

    /**
     * 전체 공지를 출력하기 위한 메서드
     */

    public void displayAllNotice() {

        System.out.println();
        System.out.println("=========================검색 결과=========================");
        System.out.println("공지번호 \t관리자ID \t공지제목 \t공지내용 \t공지일자 \t공지등급");
        System.out.println("------------------------------------------------------------");
        List<noticeVO> notList = notService.selectAllNotice();

        if (notList.size() == 0) {
            System.out.println("조회된 공지가 없습니다.");
        } else {
            for (noticeVO nv : notList) {
                System.out.println(nv.getNoticeNum() + "\t" + nv.getMasterId() + "\t" + nv.getNotTitle() + "\t"
                        + nv.getNotContent() + "\t" + nv.getNotDate() + "\t" + nv.getNotGrade());
                System.out.println("-------------------------------------------------------");

            }
        }
    }

    /**
     * 공지를 등록하기 위한 메서드
     */
    public void insertNotice() {
        String masterId = "";

        masterId = MasterService.getInstance().getVo().getMasterId();

        System.out.print("공지 제목 >> ");
        String notTitle = scan.nextLine();

        System.out.print("공지 내용 >> ");
        String notContent = scan.nextLine();


        System.out.print("공지 등급 >> ");
        String notGrade = scan.nextLine();

        noticeVO nv = new noticeVO();

        nv.setMasterId(masterId);
        nv.setNotTitle(notTitle);
        nv.setNotContent(notContent);
        nv.setNotGrade(notGrade);

        int cnt = notService.registNotice(nv);

        if (cnt > 0) {
            System.out.println(masterId + "가 작성한 공지 등록 완료");
        } else {
            System.out.println(masterId + "가 작성한 공지 등록 실패");
        }
    }

    /**
     * 공지를 수정하기 위한 메서드
     */
    public void modifyNotice() {


        String masterId = "";
        masterId = MasterService.getInstance().getVo().getMasterId();

        System.out.println("수정할 공지글에 대한 정보를 입력하세요.");

        System.out.print("수정할 공지 번호 >> ");
        int noticeNum = Integer.parseInt(scan.nextLine());

        System.out.print("수정할 공지 제목을 입력하세요. >> ");
        String notTitle = scan.nextLine();

        System.out.print("수정할 공지 내용을 입력하세요. >> ");
        String notContent = scan.nextLine();

        System.out.print("수정할 공지 등급을 입력하세요.>> ");
        String notGrade = scan.nextLine();

        noticeVO nv = new noticeVO();
        nv.setNoticeNum(noticeNum);
        nv.setMasterId(masterId);
        nv.setNotTitle(notTitle);
        nv.setNotContent(notContent);
        nv.setNotGrade(notGrade);

        int cnt = notService.modifyNotice(nv);

        if (cnt > 0) {
            System.out.println("공지 수정을 성공적으로 완료되었습니다.");
        } else {
            System.out.println("공지 수정에 실패하였습니다.");
        }

    }

    /**
     * 공지를 삭제하기 위한 메서드
     */
    public void deleteNotice() {
        String masterId = "";
        masterId = MasterService.getInstance().getVo().getMasterId();

        System.out.println("삭제할 공지 번호를 선택하세요.");
        System.out.print("공지번호 >> ");
        int noticeNum = Integer.parseInt(scan.nextLine());

        int cnt = notService.removeNotice(noticeNum);

        if (cnt > 0) {
            System.out.println(noticeNum + "번 공지글의 삭제 작업이 완료되었습니다.");
        } else {
            System.out.println(noticeNum + "번 공지글의 삭제 작업에 실패하였습니다.");
        }

    }

    // 공지 검색 - 관리자 ID 필수 작성
    public void searchNotice() {

        System.out.println();
        System.out.println("글 정보를 입력하세요.");

        String masterId;
        masterId = MasterService.getInstance().getVo().getMasterId();

        System.out.print("공지 번호 (모르면 0을 적어주세요.) >> ");
        int noticeNum = Integer.parseInt(scan.nextLine());

        System.out.print("공지 제목 >> ");
        String notTitle = scan.nextLine();

        System.out.print("공지 내용 >> ");
        String notContent = scan.nextLine();

        System.out.print("공지 등급 >> ");
        String notGrade = scan.nextLine();

        noticeVO nv = new noticeVO();
        nv.setNoticeNum(noticeNum);
        nv.setMasterId(masterId);
        nv.setNotTitle(notTitle);
        nv.setNotContent(notContent);
        nv.setNotGrade(notGrade);


        System.out.println("------------------------------------");
        System.out.println("공지번호 \t관리자ID \t공지제목 \t공지내용 \t공지일자 \t공지등급");
        System.out.println("------------------------------------");

        List<noticeVO> notList = notService.searchNotice(nv);

        if (notList.size() == 0) {
            System.out.println("조회된 공지가 없습니다.");
        } else {
            for (noticeVO nv2 : notList) {
                System.out.println(nv2.getNoticeNum() + "\t" + nv2.getMasterId() + "\t" + nv2.getNotTitle() + "\t"
                        + nv2.getNotContent() + "\t" + nv2.getNotDate() + "\t" + nv2.getNotGrade());
                System.out.println("------------------------------------------");
            }
        }
        System.out.println("공지 검색 작업이 종료되었습니다.");
    }

    public static void main(String[] args) {
        NoticeMain notObj = new NoticeMain();
        notObj.start();


    }

}
