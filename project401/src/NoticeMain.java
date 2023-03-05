import java.util.List;
import java.util.Scanner;

import kr.or.ddit.teampro.notice.service.INoticeService;
import kr.or.ddit.teampro.notice.service.NoticeServiceImpl;
import kr.or.ddit.teampro.notice.vo.NoticeVO;

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
		System.out.println("------------------------------------");
		System.out.println("======작업선택======");
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
			choice = scan.nextInt();

			switch (choice) {

			case 1:
				displayAllNotice();
			case 2:
				insertNotice();
			case 3:
				modifyNotice();
			case 4:
				deleteNotice();
			case 5:
				searchNotice();
			case 6:
				System.out.println("작업을 마칩니다.");
			default:
				System.out.println("번호를 잘못 입력했습니다. 다시 입력해주세요.");

			}

		} while (choice != 6);

	}

	private void displayAllNotice() {

		System.out.println();
		System.out.println("------------------------------------");
		System.out.println("공지번호\t관리자ID\t공지제목\t공지내용\t공지일자\t공지등급");
		System.out.println("------------------------------------");
		List<NoticeVO> notList = notService.selectAllNotice();

		if (notList.size() == 0) {
			System.out.println("조회된 공지가 없습니다.");
		} else {
			for (NoticeVO nv : notList) {
				System.out.println(nv.getNoticeNum() + "\t" + nv.getMasterId() + "\t" + nv.getNotTitle() + "\t"
						+ nv.getNotContent() + "\t" + nv.getNotDate() + "\t" + nv.getNotGrade());
				System.out.println("------------------------------------");

			}
		}
		System.out.println("출력작업 끝");

	}

	/**
	 * 공지를 등록하기 위한 메서드
	 */
	private void insertNotice() {
		boolean isExist = false;
		String masterId = "";

		do {
			System.out.println();
			System.out.println("관리자ID를 입력하세요");
			System.out.print("관리자ID >> ");
			masterId = scan.next();

			isExist = notService.checkNotice(masterId);

			if (isExist) {
				System.out.println("ID가 " + masterId + " 인 관리자가 존재하지 않습니다.");
				System.out.println("공지는 관리자만 작성할 수 있습니다. ");
				System.out.println("다시 입력해 주세요");

			}
		} while (!isExist);

		System.out.print("공지 제목 >> ");
		String notTitle = scan.next();

		System.out.print("공지 내용 >> ");
		String notContent = scan.next();

		scan.nextLine(); // 버퍼 비우기

		System.out.print("공지 등급 >> ");
		String notGrade = scan.nextLine();

		NoticeVO nv = new NoticeVO();
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
	private void modifyNotice() {
		boolean isExist = false;
		String masterId = "";

		do {
			System.out.println();
			System.out.println("수정할 공지정보를 입력하세요.");
			System.out.println("관리자ID >> ");
			masterId = scan.nextLine();

			isExist = notService.checkNotice(masterId);

			if (!isExist) {
				System.out.println("관리자Id" + masterId + "가 작성한 공지가 존재하지 않습니다. ");
				System.out.println("다시 입력해주세요.");
			}

		} while (!isExist);

		System.out.print("공지 제목 >> ");
		String notTitle = scan.next();

		System.out.print("공지 내용 >> ");
		String notContent = scan.next();

		scan.nextLine(); // 버퍼 비우기

		System.out.print("공지 등급>> ");
		String notGrade = scan.nextLine();
		
		NoticeVO nv = new NoticeVO();
		nv.setMasterId(masterId);
		nv.setNotTitle(notTitle);
		nv.setNotContent(notContent);
		nv.setNotGrade(notGrade);

		int cnt = notService.registNotice(nv);

		if (cnt > 0) {
			System.out.println("공지 수정을 성공적으로 완료하였습니다.");
		} else {
			System.out.println("공지 수정에 실패하였습니다.");
		}

	}

	/**
	 * 공지를 삭제하기 위한 메서드
	 */
	private void deleteNotice() {
		System.out.println();
		searchNotice();
		System.out.println("삭제할 공지 번호를 선택하세요.");
		System.out.print("공지번호 >> ");
		int noticeNum = scan.nextInt();

		int cnt = notService.removeNotice(noticeNum);

		if (cnt > 0) {
			System.out.println(noticeNum + "번 공지 삭제");
		} else {
			System.out.println(noticeNum + "번 공지 삭제 실패");
		}

	}

	/**
	 * 공지를 검색하기 위한 메서드
	 */
	private void searchNotice() {

		/*
		 * 제목과 내용은 입력한 값이 포함만 되어도 검색 되도록 한다. 입력을 하지 않을 자료는 엔터키로 다음 입력으로 넘긴다.
		 */

		scan.nextLine();
		System.out.println();
		System.out.println("검색할 관리자ID를 입력하세요.");
		System.out.print("관리자ID >> ");
		String masterId = scan.nextLine().trim();

		System.out.print("공지 제목 >> ");
		String notTitle = scan.nextLine().trim();

		System.out.print("공지 내용 >> ");
		String notContent = scan.nextLine().trim();

		System.out.print("공지 등급 >> ");
		String notGrade = scan.nextLine().trim();

		NoticeVO nv = new NoticeVO();
		nv.setMasterId(masterId);
		nv.setNotTitle(notTitle);
		nv.setNotContent(notContent);
		nv.setNotGrade(notGrade);

		List<NoticeVO> notList = notService.searchNotice(nv);
		if (notList.size() == 0) {
			System.out.println("조회된 공지가 없습니다.");
		} else {
			for (NoticeVO nv2 : notList) {
				System.out.println(nv.getNoticeNum() + "\t" + nv.getMasterId() + "\t" + nv.getNotTitle() + "\t"
						+ nv.getNotContent() + "\t" + nv.getNotDate() + "\t" + nv.getNotGrade());
				System.out.println("------------------------------------------");

			}
		}
		System.out.println("공지 검색 작업이 종료되었습니다.");

	}

	public static void main(String[] args) {
		NoticeMain notObj = new NoticeMain();
		notObj.start();
		
		NoticeVO nv = new NoticeVO();
		
		nv.setNoticeNum(1);
		nv.setMasterId("1111");
		nv.setNotTitle("안녕");
		nv.setNotContent("안녕하세요");
		nv.setNotDate("2023-11-11");
		nv.setNotGrade("3");

		
	}
	
}
