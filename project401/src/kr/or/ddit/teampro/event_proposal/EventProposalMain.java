package kr.or.ddit.teampro.event_proposal;

import java.util.List;
import java.util.Scanner;

import kr.or.ddit.teampro.event_proposal.service.EventProposalService;
import kr.or.ddit.teampro.event_proposal.service.EventProposalServiceImpl;
import kr.or.ddit.teampro.event_proposal.vo.EventProposalVO;

public class EventProposalMain {

	private EventProposalService proService;
	private Scanner scan = new Scanner(System.in);

	public EventProposalMain() {
		proService = EventProposalServiceImpl.getInstance();
	}

	public void displayMenu() {

		System.out.println();
		System.out.println("------------------------------------");
		System.out.println("----------------작업선택--------------");
		System.out.println("1. 신청된 이벤트 조회 ");
		System.out.println("2. 이벤트 승인 결정");
		System.out.println("3. 이벤트 신청 결과");
		System.out.println("4. 승인된 이벤트 조회");
		System.out.println("5. 이벤트 검색");
		System.out.println("5. 작업 종료");
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
			case 1:
				selectAllEventPro(); // 1. 신청된 이벤트 조회
				break;
			case 2:
				modifyEventPro(); // 2. 이벤트 승인 결정
				break;
			case 3:
				selectEventProResult(); // 3. 이벤트 신청 결과
				break;
			case 4:
				selectEventProT(); // 4. 승인된 이벤트 조회
				break;
			case 5:
				searchEventPro(); // 5. 이벤트 검색
				break;
			case 6:
				System.out.println("------------------------------------");
				System.out.println("작업을 마칩니다.");
				System.out.println("------------------------------------");
				break;
			default:
				System.out.println("잘못 입력하셨습니다. ");
				System.out.println("다시 입력해주세요.");

			}
		} while (choice != 5);
	}

	
	// 1. 신청된 이벤트 조회
	private void selectAllEventPro() {

		scan.nextLine();

		System.out.println();
		System.out.println("------------------------------------");
		System.out.println("이벤트번호\t기업ID\t이벤트제목\t이벤트내용\t작성일\t이벤트시작날짜\t이벤트종료날짜");
		System.out.println("------------------------------------");

		List<EventProposalVO> pvoList = proService.selectAllEventPro();

		if (pvoList.size() == 0) {
			System.out.println("신청된 이벤트 목록이 없습니다.");
			System.out.println("다시 입력해 주세요.");
		} else {
			for (EventProposalVO pvo : pvoList) {
				System.out.println(pvo.getEventNum() + "\t" +pvo.getCompanyId()+ "\t" +pvo.getProTitle()+"\t" +pvo.getProContent() + pvo.getProStartDay() + "\t" +pvo.getProEndDate()+"\t");
				System.out.println("------------------------------------");
			}
		}
		System.out.println("이벤트 조회 종료");
	}

	
	// 2. 이벤트 승인 결정(승인하기)
	private void modifyEventPro() {

		System.out.print("관리자 번호를 입력하세요.");
		String masterId = scan.nextLine();
		
		 scan.nextLine();
		
		System.out.print("승인여부를 결정할 이벤트 번호를 입력하세요. >> ");
		int eventNum = Integer.parseInt(scan.next());

		scan.nextLine();
		
		// 승인여부는 대문자 T나 F로
		 
		System.out.print("이벤트 승인여부 (T,F)>> ");
		String proIsok = scan.nextLine();

		EventProposalVO ev = new EventProposalVO();
		ev.setProIsok(proIsok);
		ev.setMasterId(masterId);

		int cnt = proService.modifyEventPro(ev);

		if (cnt > 0) {
			System.out.println("정상적으로 처리되었습니다.");
		} else {
			System.out.println("정상적으로 처리되지 못하였습니다. \n다시 입력해주세요.");
		}
	}
	
	
	// 3. 이벤트 신청 결과 (T,F 모두 출력)
	private void selectEventProResult() {
		

		System.out.println();
		System.out.println("------------------------------------");
		System.out.println("이벤트번호\t기업ID\t이벤트제목\t이벤트내용\t작성일\t관리자ID\t승인여부");
		System.out.println("------------------------------------");

		List<EventProposalVO> pvoList = proService.selectAllEventPro();
		
		System.out.println(pvoList);
		
		if (pvoList.size() == 0) {
			System.out.println("신청된 이벤트 목록이 없습니다.");
			System.out.println("다시 입력해 주세요.");
		} else {
			for (EventProposalVO pvo : pvoList) {
				System.out.println(pvo.getEventNum() + "\t" +pvo.getCompanyId() + "\t" +pvo.getProTitle() +"\t" + pvo.getProContent() + "\t" + pvo.getProCreateDay()  + "\t" + pvo.getMasterId() + "\t"+ pvo.getProIsok() + "\t");
				System.out.println("------------------------------------");
			}
		}
		System.out.println("이벤트 조회 종료");

	}

	
	//  4. 승인된 이벤트 조회
	private void selectEventProT() {

		System.out.print("관리자 ID >> ");
		String masterId = scan.nextLine();
		
		scan.nextLine();
		
		System.out.print("승인여부 >> ");
		String proIsok = scan.nextLine();

		EventProposalVO pvo = new EventProposalVO();
		pvo.setMasterId(masterId);
		pvo.setProIsok(proIsok);

		List<EventProposalVO> pvoList = proService.selectEventProT(pvo);

		if (pvoList.size() == 0) {
			System.out.println("조회된 이벤트가 없습니다.");
		} else {
			System.out.println();
			System.out.println("------------------------------------");
			System.out.println("이벤트번호\t관리자ID\t승인여부");
			System.out.println("------------------------------------");
			for (EventProposalVO pvo2 : pvoList) {
				System.out.println(pvo2.getEventNum() + "\t" + pvo2.getMasterId() + pvo2.getProIsok() + "\t");
				System.out.println("------------------------------------");

			}
			System.out.println("이벤트 검색 작업이 종료되었습니다.");
		}

	}

	// 5. 이벤트 검색
	private void searchEventPro() {

		System.out.println();
		System.out.println("검색할 이벤트 정보를 입력하세요.");
		System.out.print("이벤트 번호 >> ");
		// 이벤트 번호를 0으로 입력해야 다음으로 넘어감.

		int eventNum = Integer.parseInt(scan.nextLine());

		scan.nextLine();

		System.out.print("관리자ID >> ");
		String masterId = scan.nextLine().trim();

		System.out.print("이벤트 승인여부 >> ");
		String proIsok = scan.nextLine().trim();

		EventProposalVO pvo = new EventProposalVO();
		pvo.setEventNum(eventNum);
		pvo.setMasterId(masterId);
		pvo.setProIsok(proIsok);

		List<EventProposalVO> proList = proService.searchEventPro(pvo);

		if (proList.size() == 0) {
			System.out.println("조회된 이벤트가 없습니다.");
			
		} else {
			System.out.println();
			System.out.println("------------------------------------");
			System.out.println("이벤트번호\t관리자ID\t승인여부");
			System.out.println("------------------------------------");
			
			for (EventProposalVO pvo2 : proList) {
				System.out.println(pvo2.getEventNum() + "\t" + pvo2.getMasterId() + pvo2.getProIsok() + "\t");
				System.out.println("------------------------------------");

			}
			System.out.println("이벤트 검색 작업이 종료되었습니다.");
		}

	}

	public static void main(String[] args) {
		EventProposalMain proObj = new EventProposalMain();
		proObj.start();
	}

}
