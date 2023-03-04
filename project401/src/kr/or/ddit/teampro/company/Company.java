package kr.or.ddit.teampro.company;

import kr.or.ddit.teampro.company.service.CompanyService;
import kr.or.ddit.teampro.company.service.ICompanyService;
import kr.or.ddit.teampro.company.vo.CompanyVO;

import java.util.Scanner;

public class Company {

    private ICompanyService companyService;

    private Scanner scan = new Scanner(System.in);

    public Company() {
        companyService = CompanyService.getInstance();
    }

    /**
     * 메뉴를 출력하는 메소드`
     */
    public void displayMenu() {    // 뷰
        System.out.println();
        System.out.println("-----------------------");
        System.out.println("  === 작 업 선 택 ===");
        System.out.println("  1. 회원가입");
        System.out.println("  2. 로그인");
        System.out.println("  3. 내 정보");
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
        do {
            displayMenu(); //메뉴 출력
            choice = scan.nextInt(); // 메뉴번호 입력받기
            switch (choice) {
                case 1:  // 회원가입
                    insertUser();
                    break;
                case 2: // 로그인
                    login();
                    break;
                case 3:  // 내 정보
                    displayUserInfo();
                    break;
                case 4: // 로그아웃
                    logout();
                    break;
                case 5:  // 계정삭제
                    deleteId();
                    break;
                case 6:  // 작업 끝
                    System.out.println("작업을 마칩니다.");
                    break;
                default:
                    System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
            }
        } while (choice != 6);
    }

    private void deleteId() {
        CompanyVO uv = companyService.getVo();

        System.out.println("삭제하시겠습니까?");
        System.out.println("1.삭제 2.아니오");
        int choice = scan.nextInt();
        switch (choice) {
            case 1:
                int cnt = 0;

                cnt = companyService.deleteUser(uv.getCompanyId());
                if (cnt > 0) {
                    System.out.println("삭제 성공!");
                    CompanyService.getInstance().logout(uv);
                    System.out.println("강제로 로그아웃됩니다");
                } else System.out.println("삭제 실패");

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

    private void displayUserInfo() {
        CompanyVO user = CompanyService.getInstance().getVo();

        System.out.println("아이디>> " + user.getCompanyId());
        System.out.println("기업주명>> " + user.getOwnerName());
        System.out.println("기업이름>> " + user.getName());
        System.out.println("사업자번호>> " + user.getBizNo());
        System.out.println("전화번호>> " + user.getPhoneNum());
        System.out.println("사업장주소>> " + user.getAddr());
        System.out.println("규모>> " + user.getScale());
        System.out.println("제제횟수>> "+user.getSanctionsCount());
    }

    private void logout() {
        CompanyVO uv = CompanyService.getInstance().getVo();
        System.out.println("로그아웃하시겠습니까");
        System.out.println("1.로그아웃 2.아니오");
        int choice = scan.nextInt();
        switch (choice) {
            case 1:
                System.out.println("로그아웃 성공!");
                companyService.logout(uv);
                break;
            case 2:
                System.out.println("아니오");
                start();
                break;
            default:
                System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
                logout();
                break;
        }
    }

    public void login() {

        String memId = "";
        String memPw = "";

        System.out.println();
        System.out.println("로그인을 시작합니다");
        System.out.print("회원ID >> ");
        memId = scan.next();

        scan.nextLine();

        System.out.print("회원 비밀번호 >> ");
        memPw = scan.nextLine();

        CompanyVO company = companyService.login(memId.trim(), memPw.trim());

        if (company != null) {
            CompanyService.getInstance().setVo(company);
            System.out.println(memId + " 회원 로그인 성공!");
        } else {
            System.out.println(memId + " id 혹은 pw가 틀렸습니다");
        }
    }


    public void insertUser() {
        String userId = "";

        System.out.println();
        System.out.println("새롭게 등록할 회원 정보를 입력하세요.");
        System.out.print("회원ID >> ");
        userId = scan.next();

        scan.nextLine();
        //회원아이디 중복검사
        if (CompanyService.getInstance().isExist(userId.trim())==true) {
            System.out.println(userId + " 회원 ID 중복입니다.");
            return;
        }

        System.out.print("회원 비밀번호>> ");
        String userPw = scan.nextLine();
        System.out.print("기업이름>> ");
        String companyName = scan.next();
        System.out.print("기업주명>> ");
        String ownerName = scan.next();
        System.out.print("사업자번호>> ");
        String bizNo = scan.next();
        System.out.print("사업장주소>> ");
        String addr = scan.next();
        System.out.print("전화번호>> ");
        String phoneNum = scan.next();
        System.out.println("사업장규모>> ");
        String scale = scan.next();


        scan.nextLine();  // 버퍼 비우기


        CompanyVO mv = new CompanyVO();

        mv.setCompanyId(userId.trim());
        mv.setCompanyPw(userPw.trim());
        mv.setBizNo(bizNo.trim());
        mv.setOwnerName(companyName.trim());
        mv.setPhoneNum(phoneNum.trim());
        mv.setName(companyName.trim());
        mv.setAddr(addr.trim());
        mv.setScale(scale.trim());
        mv.setSanctionsCount(0);

        int cnt = CompanyService.getInstance().insertUser(mv);

        if (cnt > 0) {
            System.out.println(userId + " 회원 추가 작업 성공!");

        } else {
            System.out.println(userId + " 회원 추가 작업 실패!");
        }
    }

    public static void main(String[] args) {

        Company companyObj = new Company();
        companyObj.start();
    }

}
