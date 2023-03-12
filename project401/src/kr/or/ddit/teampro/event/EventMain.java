package kr.or.ddit.teampro.event;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import kr.or.ddit.teampro.company.coService.CompanyService;
import kr.or.ddit.teampro.event.service.EventService;
import kr.or.ddit.teampro.event.service.EventServiceImpl;
import kr.or.ddit.teampro.event.vo.eventVO;

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
                    displayAllEvent(); //1. 이벤트 조회
                    break;
                case 2:
                    searchEvent(); // 2. 이벤트 검색
                    break;
                case 3:
                    insertEvent(); // 3. 이벤트 작성
                    break;
                case 4:
                    modifyEvent(); // 4. 이벤트 수정
                    break;
                case 5:
                    deleteEvent(); // 5. 이벤트 삭제
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
        } while (choice != 6);

    }


    //1. 이벤트 조회 - 순서대로 조회 불가능..
    public void displayAllEvent() {
        System.out.println();
        System.out.println("------------------------------------");
        System.out.println("이벤트번호\t기업ID\t제목\t내용\t이벤트작성일\t시작날짜\t종료날짜");
        System.out.println("------------------------------------");

        List<eventVO> eveList = eveService.selectAllEvent();

        if (eveList.size() == 0) {
            System.out.println("조회된 이벤트 목록이 없습니다.");
        } else {
            for (eventVO ev : eveList) {
                System.out.println(
                        ev.getEventNum() + "\t" + ev.getCompanyId() + ev.getEventTitle() + "\t" + ev.getEventContent()
                                + "\t" + ev.getEveCreDate() + "\t" + ev.getEveStartDate() + "\t" + ev.getEveEndDate());
                System.out.println("------------------------------------");
            }
        }
    }

    // 2. 이벤트 검색 - 날짜 검색 시 다 0000-00-00로 입력해야 조회 가능, 00(월)로 조회 시 조회 불가
    private void searchEvent() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        scan.nextLine();

        System.out.println();
        System.out.println("검색할 이벤트 정보를 입력하세요.");

        System.out.print("기업ID >> ");
        String companyId = scan.nextLine().trim();

        System.out.print("이벤트 제목 >> ");
        String eventTitle = scan.nextLine().trim();

        System.out.print("이벤트 내용 >> ");
        String eventContent = scan.nextLine().trim();

        System.out.print("이벤트 시작날짜 >> ");
        String eveStartDate = scan.nextLine().trim();

        System.out.print("이벤트 종료날짜 >> ");
        String eveEndDate = scan.nextLine().trim();

        eventVO ev = new eventVO();
        //ev.setEventNum(eventNum);
        ev.setCompanyId(companyId);
        ev.setEventTitle(eventTitle);
        ev.setEventContent(eventContent);
        ev.setEveStartDate(eveStartDate);
        ev.setEveEndDate(eveEndDate);

        List<eventVO> eveList = eveService.searchEvent(ev);

        if (eveList.size() == 0) {
            System.out.println("조회된 이벤트가 없습니다.");
        } else {
            System.out.println();
            System.out.println("------------------------------------");
            System.out.println("이벤트번호\t기업ID\t제목\t내용\t이벤트작성일\t시작날짜\t종료날짜");
            System.out.println("------------------------------------");
            for (eventVO ev2 : eveList) {
                try {
                    System.out.println(ev2.getEventNum() + "\t" + ev2.getCompanyId() + ev2.getEventTitle() + "\t"
                            + ev2.getEventContent() + "\t" + ev2.getEveCreDate() + "\t" + ev2.getEveStartDate() + "\t"
                            + ev2.getEveEndDate());
                }catch (Exception e) {

                }
                System.out.println("------------------------------------");

            }
        }

        System.out.println("이벤트 검색 작업이 종료되었습니다.");

    }

    // 3. 이벤트 작성 이벤트 번호 시퀀스 적용 x
    public void insertEvent() {

        String companyId = "";

        companyId = CompanyService.getInstance().getVo().getCompanyId();

        System.out.print("이벤트 제목 >> ");
        String eventTitle = scan.nextLine();
        System.out.print("이벤트 내용 >> ");
        String eventContent = scan.nextLine();

        System.out.print("이벤트 시작날짜(ex: 2023-03-12)>> ");
        String eveStartDate = scan.nextLine();

        System.out.print("이벤트 종료 날짜(ex: 2023-03-12)>> ");
        String eveEndDate = scan.nextLine();

        eventVO ev = new eventVO();
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


    // 4. 이벤트 수정
    private void modifyEvent() {
        boolean isExist = false;
        String companyId = "";

        scan.nextLine();

        do {
            System.out.println();
            System.out.println("기업ID를 입력하세요.");
            System.out.print("기업ID >> ");
            companyId = scan.nextLine();

            isExist = eveService.checkEvent(companyId);

            if (!isExist) {
                System.out.println("Id가" + companyId + "인 기업ID를 찾을 수 없습니다.");
                System.out.println("이벤트는 기업만 작성할 수 있습니다.");
                System.out.println("다시 입력해주세요.");
            }

        } while (!isExist);

        System.out.print("수정할 이벤트 글의 번호 >> ");
        int eventNum = Integer.parseInt(scan.next());

        scan.nextLine();

        System.out.print("이벤트 제목을 수정하세요. >> ");
        String eventTitle = scan.nextLine();
        System.out.print("이벤트 내용을 수정하세요. >> ");
        String eventContent = scan.nextLine();
        System.out.print("이벤트 시작날짜를 수정하세요.>> ");
        String eveStartDate = scan.nextLine();
        System.out.print("이벤트 종료날짜를 수정하세요.>> ");
        String eveEndDate = scan.nextLine();

        eventVO ev = new eventVO();
        ev.setEventNum(eventNum);
        ev.setCompanyId(companyId);
        ev.setEventTitle(eventTitle);
        ev.setEventContent(eventContent);
        ev.setEveStartDate(eveStartDate);
        ev.setEveEndDate(eveEndDate);

        int cnt = eveService.modifyEvent(ev);

        if (cnt > 0) {
            System.out.println("이벤트가 성공적으로 수정되었습니다.");
        } else {
            System.out.println("이벤트 등록에 실패하였습니다.");
        }

    }
    // 5. 이벤트 삭제

    private void deleteEvent() {

        boolean isExist = false;
        String companyId = "";

        scan.nextLine();

        do {
            System.out.println();
            System.out.println("기업ID를 입력하세요.");
            System.out.print("기업ID >> ");

            companyId = scan.nextLine();

            isExist = eveService.checkEvent(companyId);

            if (!isExist) {
                System.out.println("Id가 " + companyId + "인 기업ID를 찾을 수 없습니다.");
                System.out.println("이벤트는 기업만 삭제할 수 있습니다.");
                System.out.println("다시 입력해주세요.");
            }

        } while (!isExist);

        System.out.println("삭제할 이벤트 번호를 입력하세요.");
        System.out.print("이벤트 번호 >> ");
        int eventNum = scan.nextInt();

        int cnt = eveService.removeEvent(eventNum);

        if (cnt > 0) {
            System.out.println(eventNum + "번 공지 삭제");
        } else {
            System.out.println(eventNum + "번 공지 삭제 실패\n 다시 입력해주세요");
        }

    }

    public static void main(String[] args) {
        EventMain eveObj = new EventMain();
        eveObj.start();

//		eventVO ev = new eventVO();
//		
//		ev.setEventNum(1);
//		ev.setCompanyId("태");
//		ev.setEventTitle("이벤트");
//		ev.setEventContent("이벤트다 이자식아");
//		ev.setEveCreDate("2023-01-01");
//		ev.setEveStartDate("2023-04-01");
//		ev.setEveEndDate("2023-06-01");

    }

}
