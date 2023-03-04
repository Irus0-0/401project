package kr.or.ddit.teampro.customer;

import customer.service.CustomerService;
import customer.service.ICustomerService;
import customer.vo.CustomerVO;

import java.util.Scanner;

public class Customer {

    private ICustomerService customerService;

    private Scanner scan = new Scanner(System.in);

    public Customer() {
        customerService = CustomerService.getInstance();
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
        CustomerVO uv = customerService.getVo();

        System.out.println("삭제하시겠습니까?");
        System.out.println("1.삭제 2.아니오");
        int choice = scan.nextInt();
        switch (choice) {
            case 1:
                int cnt = 0;

                cnt = customerService.deleteUser(uv.getCustomerId());
                if (cnt > 0) {
                    System.out.println("삭제 성공!");
                    CustomerService.getInstance().logout(uv);
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
        CustomerVO user = CustomerService.getInstance().getVo();

        System.out.println("아이디>> " + user.getCustomerId());
        System.out.println("이름>> " + user.getName());
        System.out.println("생일>> " + user.getBirthdate().substring(0,10));
        System.out.println("성별>> " + user.getGender());
        System.out.println("전화번호>> " + user.getPhoneNum());
    }

    private void logout() {
        CustomerVO uv = CustomerService.getInstance().getVo();
        System.out.println("로그아웃하시겠습니까");
        System.out.println("1.로그아웃 2.아니오");
        int choice = scan.nextInt();
        switch (choice) {
            case 1:
                System.out.println("로그아웃 성공!");
                customerService.logout(uv);
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

        CustomerVO customer = customerService.login(memId.trim(), memPw.trim());

        if (customer != null) {
            CustomerService.getInstance().setVo(customer);
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
        if (CustomerService.getInstance().isExist(userId.trim())==true) {
            System.out.println(userId + " 회원 ID 중복입니다.");
            return;
        }

        System.out.print("회원 비밀번호>> ");
        String userPw = scan.nextLine();
        System.out.print("회원 이름>> ");
        String userName = scan.next();
        System.out.print("회원 전화번호>> ");
        String userPh = scan.next();
        System.out.print("성별>> ");
        String userGender = scan.next();
        System.out.print("\t생년월일\n(예시 yyyy-MM-dd)>> ");
        String userBirthDate = scan.next();
        System.out.print("주소>> ");
        String userAddr = scan.next();

        scan.nextLine();  // 버퍼 비우기


        CustomerVO mv = new CustomerVO();

        mv.setCustomerId(userId.trim());
        mv.setCustomerPw(userPw.trim());
        mv.setName(userName.trim());
        mv.setPhoneNum(userPh.trim());
        mv.setGender(userGender.trim());
        mv.setBirthdate(userBirthDate.trim());
        mv.setAddr(userAddr.trim());

        int cnt = CustomerService.getInstance().insertUser(mv);

        if (cnt > 0) {
            System.out.println(userId + " 회원 추가 작업 성공!");

        } else {
            System.out.println(userId + " 회원 추가 작업 실패!");
        }
    }

    public static void main(String[] args) {

        Customer customerObj = new Customer();
        customerObj.start();
    }

}
