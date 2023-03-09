package kr.or.ddit.teampro.master;


import kr.or.ddit.teampro.master.service.IMasterService;
import kr.or.ddit.teampro.master.service.MasterService;
import kr.or.ddit.teampro.master.vo.MasterVO;

import java.util.Scanner;

public class Master {
    //singleton
    private static Master instance = null;

    public static Master getInstance() {
        if (instance == null) {
            instance = new Master();
        }
        return instance;
    }
    //스캐너 소환
    private Scanner scan = new Scanner(System.in);

    //service 소환
    private IMasterService masterService;
    public Master() {
        masterService = MasterService.getInstance();
    }

    /**
     * 메뉴를 출력하는 메소드`
     */
    public void displayMenu() {    // 뷰
        System.out.println();
        System.out.println("-----------------------");
        System.out.println("  === 작 업 선 택 ===");
        System.out.println("  1. 관리자등록");
        System.out.println("  2. 로그인");
        System.out.println("  3. 관리자 정보");
        System.out.println("  4. 로그아웃");
        System.out.println("  5. 계정삭제");
        System.out.println("  6. 작업 끝.");
        System.out.println("-----------------------");
        System.out.print("원하는 작업 선택 >> ");
    }

    /**
     * 프로그램 시작 메소드
     */
    public void start() {   // 컨트롤러
        int choice;
        try {
            while (true) {
                displayMenu(); //메뉴 출력
                choice = Integer.parseInt(scan.nextLine());; // 메뉴번호 입력받기

                switch (choice) {
                    case 1:  // 회원가입
                        insertMaster();
                        break;
                    case 2: // 로그인
                        login();
                        break;
                    case 3:  // 내 정보
                        displayMasterInfo();
                        break;
                    case 4: // 로그아웃
                        logout();
                        break;
                    case 5:  // 계정삭제
                        deleteId();
                        break;
                    case 6:  // 작업 끝
                        System.out.println("작업을 마칩니다.");
                        return;
                    default:
                        System.out.println("번호를 잘못 입력했습니다\n다시 입력하세요");
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("잘못 입력했습니다\n다시 입력하세요");
            start();
        }
    }

    //관리자 계정 삭제
    public void deleteId() {
        MasterVO mv = masterService.getVo();
        if (mv == null) {
            System.out.println("계정삭제가 불가능합니다\n로그인하세요");
            return;
        }
        System.out.print(mv.getMasterId()+" ");

        System.out.println("삭제하시겠습니까?");
        System.out.println("1.삭제 2.아니오");
        int choice = Integer.parseInt(scan.nextLine());

        switch (choice) {
            case 1:
                if (masterService.deleteMaster(mv) > 0) {
                    masterService.logout();
                    System.out.println("삭제 성공!");
                    System.out.println("강제로 로그아웃됩니다");
                } else System.out.println("삭제 실패했습니다");

                break;
            case 2:
                System.out.println("아니오");
                start();
                break;
            default:
                System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
                deleteId();
        }
    }

    //마스터 정보 출력
    public void displayMasterInfo() {
        MasterVO mv = masterService.getVo();
        if (mv == null) {
            System.out.println("정보조회가 불가능합니다\n로그인하세요");
            return;
        }

        System.out.println("관리자아이디>> " + mv.getMasterId());
        System.out.println("관리자이름>> " + mv.getName());
        System.out.println("관리자등급>> " + mv.getGrade());
    }

    //로그아웃
    public void logout() {
        MasterVO mv = masterService.getVo();
        if (mv == null) {
            System.out.println("로그인하세요");
            return;
        }
        System.out.println("로그아웃하시겠습니까");
        System.out.println("1.로그아웃 2.아니오");
        int choice = Integer.parseInt(scan.nextLine());

        switch (choice) {
            case 1:
                System.out.println("로그아웃 성공!");
                masterService.logout();
                break;
            case 2:
                System.out.println("아니오");
                start();
                break;
            default:
                System.out.println("번호를 잘못 입력했습니다\n다시 입력하세요");
                logout();
                break;
        }
    }

    //관리자 로그인
    public void login() {
        String masterId = "";
        String masterPw = "";

        System.out.println();
        System.out.println("로그인을 시작합니다");
        System.out.print("관리자 회원 ID >> ");
        masterId = scan.nextLine();

        System.out.print("관리자 비밀번호 >> ");
        masterPw = scan.nextLine();

        //관리자VO로 객체 형태로 담아서 로그인에 보내주기
        MasterVO mv = new MasterVO();
        mv.setMasterId(masterId.trim());
        mv.setMasterPw(masterPw.trim());

        MasterVO master = masterService.login(mv);

        //로그인 성공과 실패
        if (master != null) {
            MasterService.getInstance().setVo(master);
            System.out.println(masterId + "로그인 성공!");
        } else {
            System.out.println(masterId + "id 혹은 pw가 틀렸습니다");
        }
    }


    public void insertMaster() {
        MasterVO mvc = new MasterVO();
        mvc = masterService.getVo();
        if(mvc == null) {
            System.out.println("관리자 추가가 불가능합니다\n로그인하세요");
            return;
        }

        int gradeCd = mvc.getGrade();
        if (!(gradeCd>2)){
            System.out.println("관리자 등급이 낮습니다\n뒤로 돌아갑니다");
            return;
        }
        if (gradeCd > 2) {
            String masterId = "";

            System.out.println();
            System.out.println("새롭게 등록할 회원 정보를 입력하세요.");
            System.out.print("회원ID >> ");
            masterId = scan.next();

            MasterVO mv = new MasterVO();
            mv.setMasterId(masterId.trim());

            scan.nextLine();
            //회원아이디 중복검사
            if (masterService.isExist(mv) == true) {
                System.out.println(masterId + " 회원 ID 중복입니다.");
                return;
            }

            System.out.print("회원 비밀번호>> ");
            String masterPw = scan.nextLine();
            System.out.print("관리자이름>> ");
            String name = scan.nextLine();
            System.out.print("관리자등급>> ");
            int grade = Integer.parseInt(scan.nextLine().trim());


            scan.nextLine();  // 버퍼 비우기


            MasterVO mv1 = new MasterVO();

            mv1.setMasterId(masterId.trim());
            mv1.setMasterPw(masterPw.trim());
            mv1.setName(name.trim());
            mv1.setGrade(grade);

            if (masterService.insertMaster(mv1) > 0) {
                System.out.println(masterId + " 회원 추가 작업 성공!");
            } else {
                System.out.println(masterId + " 회원 추가 작업 실패!");
            }
        }
    }

    public static void main(String[] args) {

        Master MasterObj = new Master();
        MasterObj.start();
    }

}