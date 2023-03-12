package kr.or.ddit.teampro.customer;


import kr.or.ddit.teampro.customer.service.CustomerService;
import kr.or.ddit.teampro.customer.service.ICustomerService;
import kr.or.ddit.teampro.customer.vo.CustomerVO;

import java.util.Scanner;

public class Customer {
    //singleton
    private static Customer instance = null;

    public static Customer getInstance() {
        if (instance == null) {
            instance = new Customer();
        }
        return instance;
    }

    //스캐너 미리 만들어두기
    private Scanner scan = new Scanner(System.in);

    //customerService 소환
    private ICustomerService customerService;

    public Customer() {
        customerService = CustomerService.getInstance();
    }

    //뷰
    public void displayMenu() {
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
        try {
            while (true) {
                displayMenu(); //메뉴 출력
                choice = Integer.parseInt(scan.nextLine());

                ; // 메뉴번호 입력받기
                switch (choice) {
                    case 1:  // 회원가입
                        insertCustomer();
                        break;
                    case 2: // 로그인
                        login();
                        break;
                    case 3:  // 내 정보
                        displayCustomerInfo();
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
                        System.out.println("번호를 잘못 입력했습니다." +
                                " 다시입력하세요");
                        break;
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("번호가 아닙니다. 다시 입력하세요");
            start();
        }

    }

    private void deleteId() {
        //로그인 객체 받아오기
        CustomerVO cv = customerService.getVo();
        if(cv==null) {
            System.out.println("삭제할 수 없습니다");
            System.out.println("뒤로 돌아갑니다");
            return;
        }

        System.out.println("삭제하시겠습니까?");
        System.out.println("1.삭제 2.아니오");
        System.out.print("번호입력>> ");
        int choice = Integer.parseInt(scan.nextLine());
        switch (choice) {
            case 1:
                int cnt = 0;

                cnt = customerService.deleteUser(cv);
                if (cnt > 0) {
                    System.out.println("삭제 성공!");
                    CustomerService.getInstance().logout(cv);
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

    public void displayCustomerInfo() {
        CustomerVO customer = CustomerService.getInstance().getVo();
        if (customer == null) {
            System.out.println("정보를 출력할 수 없습니다");
            System.out.println("뒤로 돌아갑니다");
            start();
        }
        System.out.println("============내 정보============");
        System.out.println("아이디>> " + customer.getCustomerId());
        System.out.println("이름>> " + customer.getName());
        System.out.println("생일>> " + customer.getBirthdate().substring(0, 10));
        System.out.println("성별>> " + customer.getGender());
        System.out.println("전화번호>> " + customer.getPhoneNum());
        System.out.println("주소>> "+customer.getAddr());
    }

    private void logout() {
        CustomerVO uv = CustomerService.getInstance().getVo();
        if(uv == null) {
            System.out.println("로그아웃할 수 없습니다");
            System.out.println("뒤로 돌아갑니다");
            return;
        }
        System.out.println("로그아웃 하시겠습니까?");
        System.out.println("1.로그아웃 2.아니오");
        System.out.print("번호입력>> ");
        int choice = Integer.parseInt(scan.nextLine());
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
        if(customerService.getVo()!=null) {
            System.out.println("로그인 불가");
        }
        CustomerVO cv = new CustomerVO();

        System.out.println("=======로그인을 시작합니다=======");
        System.out.print("회원ID >> ");
        String customerId = scan.nextLine();

        System.out.print("회원 비밀번호 >> ");
        String memPw = scan.nextLine();

        cv.setCustomerId(customerId.trim());
        cv.setCustomerPw(memPw.trim());

        CustomerVO customer = customerService.login(cv);

        if (customer != null) {
            CustomerService.getInstance().setVo(customer);
        } else {
            System.out.println(" id 혹은 pw가 틀렸습니다");
        }
    }


    public void insertCustomer() {
        CustomerVO ctv = new CustomerVO();

        String customerId = "";
        String customerPw = "";

        System.out.println();
        System.out.println("회원가입을 시작합니다");
        while (true) {
            System.out.print("회원ID >> ");
            customerId = scan.nextLine();

            ctv.setCustomerId(customerId);
            //회원아이디 중복검사
            if (CustomerService.getInstance().isExist(ctv) == true) {
                System.out.println(ctv.getCustomerId() + " 회원 ID 중복입니다.");
                System.out.println("다시 입력하세요");
                continue;
            }else break;
        }

        while (true) {

            System.out.print("회원 비밀번호 1차 입력>> ");
            String customerPw1 = scan.nextLine();
            System.out.print("회원 비밀번호 2차 입력>> ");
            String customerPw2 = scan.nextLine();

            if (customerPw1.equals(customerPw2)) {
                customerPw = customerPw1;
                break;
            }
            else System.out.println("다시 입력하세요");
        }

        System.out.print("회원 이름>> ");
        String customerName = scan.nextLine();
        System.out.print("회원 전화번호(010-1234-1234)>> ");
        String customerPh = scan.nextLine();
        System.out.print("성별(남자, 여자)>> ");
        String customerGender = scan.nextLine();
        System.out.print("생년월일(yyyy-MM-dd)>> ");
        String customerBirthDate = scan.nextLine();
        System.out.print("주소>> ");
        String customerAddr = scan.nextLine();

        CustomerVO cv = new CustomerVO();


        cv.setCustomerId(customerId.trim());
        cv.setCustomerPw(customerPw.trim());
        cv.setName(customerName.trim());
        cv.setPhoneNum(customerPh.trim());
        cv.setGender(customerGender.trim());
        cv.setBirthdate(customerBirthDate.trim());
        cv.setAddr(customerAddr.trim());

        if (customerService.insertUser(cv) > 0) {
            System.out.println(customerId + " 회원 추가 작업 성공!");

        } else {
            System.out.println(customerId + " 회원 추가 작업 실패!");
        }
    }

    public static void main(String[] args) {

        Customer customerObj = new Customer();
        customerObj.start();
    }
}
