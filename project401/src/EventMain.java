import java.util.List;
import java.util.Scanner;

import kr.or.ddit.teampro.event.service.EventService;
import kr.or.ddit.teampro.event.service.EventServiceImpl;
import kr.or.ddit.teampro.event.vo.EventVO;
import kr.or.ddit.teampro.notice.vo.NoticeVO;

public class EventMain {

	private EventService eveService;
	private Scanner scan = new Scanner(System.in);

	public EventMain() {
		eveService = EventServiceImpl.getInstance();
	}

	public void displayMenu() {
		System.out.println();
		System.out.println("------------------------------------");
		System.out.println("======작업선택======");
		System.out.println("1. 이벤트 조회 ");
		System.out.println("2. 이벤트 검색 ");
		System.out.println("3. 이벤트 작성 ");
		System.out.println("4. 이벤트 수정 ");
		System.out.println("5. 이벤트 삭제 ");
		System.out.println("6. 작업 종료");
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
				displayAllEvent();
			case 2:
				searchEvent();
			case 3:
				insertEvent();
			case 4:
				modifyEvent();
			case 5:
				deleteEvent();
			case 6:
				System.out.println("------------------------------------");
				System.out.println("작업을 마칩니다.");
				System.out.println("------------------------------------");
			default:
				System.out.println("잘못 입력하셨습니다. ");
				System.out.println("다시 입력해주세요.");

			}
		} while (choice != 6);

	}

	private void displayAllEvent() {
		System.out.println();
		System.out.println("------------------------------------");
		System.out.println("이벤트번호\t기업ID\t제목\t내용\t이벤트작성일\t시작날짜\t종료날짜");
		System.out.println("------------------------------------");

		List<EventVO> eveList = eveService.selectAllEvent();

		if (eveList.size() == 0) {
			System.out.println("조회된 이벤트 목록이 없습니다.");
			System.out.println("다시 입력해주세요.");
		} else {
			for (EventVO ev : eveList) {
				System.out.println(
						ev.getEventNum() + "\t" + ev.getCompanyId() + ev.getEventTitle() + "\t" + ev.getEventContent()
								+ "\t" + ev.getEveCreDate() + "\t" + ev.getEveStartDate() + "\t" + ev.getEveEndDate());
				System.out.println("------------------------------------");
			}
		}
		System.out.println("이벤트 조회 종료");

	}

	private void searchEvent() {

		System.out.println();
		System.out.println("검색할 이벤트 정보를 입력하세요.");
		System.out.print("이벤트 번호 >> ");
		int eventNum = scan.nextInt();
		scan.nextLine();
		System.out.print("기업ID >> ");
		String companyId = scan.nextLine();
		System.out.print("이벤트 제목 >> ");
		String eventTitle = scan.nextLine();
		System.out.print("이벤트 내용 >> ");
		String eventContent = scan.nextLine();
		System.out.print("이벤트 작성일 >> ");
		String eveCreDate = scan.nextLine();
		System.out.print("이벤트 시작날짜 >> ");
		String eveStartDate = scan.nextLine();
		System.out.print("이벤트 종료날짜 >> ");
		String eveEndDate = scan.nextLine();

		EventVO ev = new EventVO();
		ev.setEventNum(eventNum);
		ev.setCompanyId(companyId);
		ev.setEventTitle(eventTitle);
		ev.setEventContent(eventContent);
		ev.setEveCreDate(eveCreDate);
		ev.setEveStartDate(eveStartDate);
		ev.setEveEndDate(eveEndDate);

		List<EventVO> eveList = eveService.searchEvent(ev);

		if (eveList.size() == 0) {
			System.out.println("조회된 이벤트가 없습니다.");
		} else {
			for (EventVO ev2 : eveList) {
				System.out.println();
				System.out.println(ev2.getEventNum() + "\t" + ev2.getCompanyId() + ev2.getEventTitle() + "\t"
						+ ev2.getEventContent() + "\t" + ev2.getEveCreDate() + "\t" + ev2.getEveStartDate() + "\t"
						+ ev2.getEveEndDate());
				System.out.println("------------------------------------");

			}
		}

		System.out.println("이벤트 검색 작업이 종료되었습니다.");

	}

	private void insertEvent() {
		boolean isExist = false;
		String companyId = "";

		do {
			System.out.println();
			System.out.print("기업ID를 입력하세요.");
			System.out.println("기업ID >> ");
			companyId = scan.nextLine();

			isExist = eveService.checkEvent(companyId);

			if (isExist) {
				System.out.println("Id가" + companyId + "인 기업ID를 찾을 수 없습니다.");
				System.out.println("이벤트는 기업만 작성할 수 있습니다.");
				System.out.println("다시 입력해주세요.");
			}

		} while (!isExist);

		System.out.print("이벤트 제목 >> ");
		String eventTitle = scan.next();
		System.out.print("이벤트 내용 >> ");
		String eventContent = scan.next();
		System.out.print("이벤트 시작날짜>> ");
		String eveStartDate = scan.next();
		System.out.print("이벤트 종료 날짜>> ");
		String eveEndDate = scan.next();

		EventVO ev = new EventVO();
		ev.setCompanyId(companyId);
		ev.setEventTitle(eventTitle);
		ev.setEventContent(eventContent);
		ev.setEveStartDate(eveStartDate);
		ev.setEveEndDate(eveEndDate);

		int cnt = eveService.registEvent(ev);

		if (cnt > 0) {
			System.out.println("이벤트가 성공적으로 등록되었습니다.");
		} else {
			System.out.println("이벤트 등록에 실패하였습니다.");
		}

	}

	private void modifyEvent() {
		boolean isExist = false;
		String companyId ="";

		do {
			System.out.println();
			System.out.println("기업ID를 입력하세요.");
			System.out.print("기업ID >> ");
			companyId = scan.nextLine();
			
			isExist = eveService.checkEvent(companyId);
			
			if(isExist) {
				System.out.println("Id가" + companyId + "인 기업ID를 찾을 수 없습니다.");
				System.out.println("이벤트는 기업만 작성할 수 있습니다.");
				System.out.println("다시 입력해주세요.");
			}
		
		} while(!isExist);
		
		System.out.print("이벤트 제목 >> ");
		String eventTitle = scan.next();
		System.out.print("이벤트 내용 >> ");
		String eventContent = scan.next();
		System.out.print("이벤트 시작날짜>> ");
		String eveStartDate = scan.next();
		System.out.print("이벤트 종료 날짜>> ");
		String eveEndDate = scan.next();
		
		EventVO ev = new EventVO();
		ev.setCompanyId(companyId);
		ev.setEventTitle(eventTitle);
		ev.setEventContent(eventContent);
		ev.setEveStartDate(eveStartDate);
		ev.setEveEndDate(eveEndDate);
		
		int cnt = eveService.registEvent(ev);

		if (cnt > 0) {
			System.out.println("이벤트가 성공적으로 수정되었습니다.");
		} else {
			System.out.println("이벤트 등록에 실패하였습니다.");
		}
		
	}

	private void deleteEvent() {
		System.out.println();
		searchEvent();
		System.out.println("삭제할 이벤트 번호를 입력하세요.");
		System.out.print("이벤트 번호 >> ");
		int eventNum = scan.nextInt();
		
		int cnt = eveService.removeEvent(eventNum);

		if (cnt > 0) {
			System.out.println(eventNum + "번 공지 삭제");
		} else {
			System.out.println(eventNum + "번 공지 삭제 실패");
		}
		

	}
	
	public static void main(String[] args) {
		EventMain eveObj = new EventMain();
		eveObj.start();
		
		EventVO ev = new EventVO();
		
		ev.setEventNum(1);
		ev.setCompanyId("태");
		ev.setEventTitle("이벤트");
		ev.setEventContent("이벤트다 이자식아");
		ev.setEveCreDate("2023-01-01");
		ev.setEveStartDate("2023-04-01");
		ev.setEveEndDate("2023-06-01");

		
	}

}
