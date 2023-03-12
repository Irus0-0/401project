package kr.or.ddit.teampro.company.coController;

import java.util.List;
import java.util.Scanner;

import kr.or.ddit.teampro.accommodations.accomService.AccommodationsService;
import kr.or.ddit.teampro.accommodations.accomVo.AccommodationsVO;
import kr.or.ddit.teampro.company.coService.CompanyService;
import kr.or.ddit.teampro.company.coVo.CompanyVO;

public class CompanyController {
	private CompanyService coService;
	private AccommodationsService accomService;

	private Scanner scan = new Scanner(System.in);

	public CompanyController() {
		coService = CompanyService.getInstance();
		accomService = AccommodationsService.getInstance();
	}

	/**
	 * 
	 */
	public void displayMenu() {
		System.out.println();
		System.out.println("----------------------");
		System.out.println("  === 작 업 선 택 ===");
		System.out.println("  1. 기업 등록");
		System.out.println("  2. 기업정보 삭제");
		System.out.println("  3. 기업정보 수정");
		System.out.println("  4. 전체 기업 출력");
		System.out.println("  5. 기업 검색");
		System.out.println("  6. 작업 끝.");
		System.out.println("----------------------");
		System.out.print("원하는 작업 선택 >> ");
	}

	public void start() {
		int choice;
		do {
			displayMenu();
			choice = Integer.parseInt(scan.nextLine());
			switch (choice) {
			case 1: insertCompany(); break;
			case 2: deleteCompany(); break;
			case 3: updateCompany(); break;
			case 4: displayAllCompany(); break;
			case 5: searchCompany(); break;
			case 6: System.out.println("작업을 마칩니다."); break;
			default: System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
			}
		} while (choice != 6);
	}

	/**
	 * 기업정보 등록 메서드
	 */
	public void insertCompany() {
		boolean isExist;
		String companyId = "";
		do {
			System.out.println();
			System.out.println("새롭게 등록할 기업ID를 입력하세요");
			System.out.print("기업ID>>");
			companyId = scan.nextLine();

			isExist = coService.checkCompany(companyId);
			if (isExist) {
				System.out.println("아이디가" + companyId + "인 기업이 이미 존재합니다.");
				System.out.println("다시 입력해주세요.");
			}
		} while (isExist);

//		pw를 입력받을때 재확인 절차를 거쳐 같을때만 pw정보를 넣어줌
		String companyPw = "";
		while (true) {
			System.out.println("기업Password>> ");
			String pw1 = scan.nextLine();
			System.out.println("기업Password 재확인>> ");
			String pw2 = scan.nextLine();

			if (pw1.equals(pw2)) {
				companyPw = pw2;
				break;
			} System.out.println("비밀번호가 같지 않습니다. 다시 입력해주세요");
		}

//		사용자에게 각 속성값을 입력받는다
		System.out.println("기업명>> ");
		String name = scan.nextLine();

		System.out.println("양식은 000-00-00000 입니다");
		System.out.println("사업자 등록번호>> ");
		String bizNo = scan.nextLine();

		System.out.println("사업자명>> ");
		String ownerName = scan.nextLine();

		System.out.println("양식은 000-000-0000 입니다");
		System.out.println("기업전화번호>> ");
		String phoneNum = scan.nextLine();

		System.out.println("기업주소>> ");
		String addr = scan.nextLine();

		System.out.println("기업규모>> ");
		String scale = scan.nextLine();

//		사용자에게 입력받은 기업정보를 CompanyVO객체에 넣어준다
		CompanyVO coVo = new CompanyVO();
		coVo.setCompanyId(companyId);
		coVo.setCompanyPw(companyPw);
		coVo.setName(name);
		coVo.setBizNo(bizNo);
		coVo.setOwnerName(ownerName);
		coVo.setPhoneNum(phoneNum);
		coVo.setAddr(addr);
		coVo.setScale(scale);
		
		int cnt = coService.registCompany(coVo);
		if (cnt > 0) {
			System.out.println(name + "기업 추가 작업 성공!");
		} else {
			System.out.println(name + "기업 추가 작업 실패...");
		}
	}

	/**
	 * 기업정보 삭제 메서드
	 */
	public void deleteCompany() {
		System.out.println();
		System.out.println("삭제할 기업 정보를 입력하세요");
		System.out.print("기업ID>>");
		String companyId = scan.nextLine();

		try {
			int cnt = coService.removeCompany(companyId);
			if (cnt > 0) {
				System.out.println(companyId + "기업정보 삭제 작업 성공!");
			} else {
				System.out.println(companyId + "기업정보 삭제 작업 실패...");
			}
		} catch (Exception e) {
			System.out.println("기업에 등록된 숙박시설이 있어서 삭제가 불가능합니다.");
			System.out.println("숙박시설을 모두 삭제하신 후 다시 시도해 주세요.");
			e.printStackTrace();
		}
	}

	/**
	 * 기업정보 수정 메서드-enter를 누르면 수정되지 않고 원래 값 그대로 유지됨
	 */
	public void updateCompany() {
		boolean isExist = false;
		String companyId = "";
		do {
			System.out.println();
			System.out.println("수정하고 싶은 기업의 ID를 입력하세요");
			System.out.print("기업ID>>");
			companyId = scan.nextLine();

			isExist = coService.checkCompany(companyId);
			if (!isExist) {
				System.out.println("ID가 " + companyId + "인 기업은 존재하지 않습니다.");
				System.out.println("다시 입력해주세요.");
			}
		} while (!isExist);


		System.out.println("수정을 원하시지 않으면 데이터 입력 없이 enter키를 누르세요");
		System.out.println("기업명>> ");
		String name = scan.nextLine();
		
		System.out.println("사업자명>> ");
		String ownerName = scan.nextLine();
		
		System.out.println("양식은 000-000-0000 입니다");
		System.out.println("기업전화번호>> ");
		String phoneNum = scan.nextLine();

		System.out.println("기업주소>> ");
		String addr = scan.nextLine();

		System.out.println("기업규모>> ");
		String scale = scan.nextLine();

//		VO객체를 생성후 사용자에게 입력받은 기업정보를 넣어준다
		CompanyVO coVo = new CompanyVO();
		coVo.setCompanyId(companyId);
		coVo.setName(name);
		coVo.setOwnerName(ownerName);
		coVo.setPhoneNum(phoneNum);
		coVo.setAddr(addr);
		coVo.setScale(scale);
		
		int cnt = coService.modifyCompany(coVo);
		if (cnt > 0) {
			System.out.println("ID가" + companyId + "인 기업 정보 수정 성공!");
		} else {
			System.out.println("ID가" + companyId + "인 기업 정보 수정 실패...");
		}
	}

	/**
	 * 전체 기업정보를 출력하는 메서드
	 */
	public void displayAllCompany() {
		System.out.println();
		System.out.println("-----------------------------------");

		List<CompanyVO> coList = coService.selectAllCompany();
		if (coList.size() == 0) {
			System.out.println("조회된 데이터가 없습니다.");
		} else {
			for (CompanyVO coVo : coList) {
				System.out.println(
						"기업ID: " + coVo.getCompanyId() + "\n기업명: " + coVo.getName() + "\n사업자 등록번호: " + coVo.getBizNo()
						+ "\n기업대표: " + coVo.getOwnerName() + "\n기업 전화번호: " + coVo.getPhoneNum() + "\n기업주소:"+ coVo.getAddr() 
						+ "\n기업규모:" + coVo.getScale() + "\n제재횟수:" + coVo.getSanctionsCount());
				System.out.println("------------------------------------");
			}
		}
		System.out.println("출력 작업 끝");
	}

	/**
	 * 기업정보 검색 메서드-검색 후 기업소유의 숙박시설을 볼 수 있는 기능 추가
	 */
	public void searchCompany() {
		System.out.println();
		System.out.println("검색할 기업정보를 입력하세요");
		System.out.println("값을 입력하지 않고 enter를 누르는 항목은 모든 값이 검색됩니다");

		System.out.println("기업ID>>");
		String companyId = scan.nextLine().trim();
		System.out.println("기업명>>");
		String name = scan.nextLine().trim();
		System.out.println("양식은 000-00-00000 입니다");
		System.out.println("사업자 등록번호>>");
		String bizNo = scan.nextLine().trim();
		System.out.println("기업 대표자명>>");
		String ownerName = scan.nextLine().trim();
		System.out.println("양식은 000-000-0000 입니다");
		System.out.println("기업 전화번호>>");
		String phoneNum = scan.nextLine().trim();
		System.out.println("기업 주소>>");
		String addr = scan.nextLine().trim();
		System.out.println("기업 규모>>");
		String scale = scan.nextLine().trim();

		//VO객체 생성 후 입력받은 값 넣어줌
		CompanyVO coVo = new CompanyVO();
		coVo.setCompanyId(companyId);
		coVo.setName(name);
		coVo.setBizNo(bizNo);
		coVo.setOwnerName(ownerName);
		coVo.setPhoneNum(phoneNum);
		coVo.setAddr(addr);
		coVo.setScale(scale);

		System.out.println();
		System.out.println("-----------------------------------");

//		VO객체가 들어있는 list구현
		List<CompanyVO> coList = coService.searchCompany(coVo);
		if (coList.size() == 0) {
			System.out.println("검색된 데이터가 없습니다.");
		} else {//검색된 데이터를 통해 간단 정보만 출력
			for (CompanyVO coVo2 : coList) {
				System.out.println("기업ID: " + coVo2.getCompanyId() + ", 기업명: " + coVo2.getName() + ", 사업자 등록번호: "+ coVo2.getBizNo());
				System.out.println("--------------------------------------");
			}
			
			//간단정보 출력후 1상세정보보기, 2숙박시설보기 선택 기능 
			System.out.println("1.기업의 상세정보 보기");
			System.out.println("2.기업소유의 숙박시설 보기");
			System.out.println("나가려면 1과 2를 제외한 키를 눌러주세요");
			int input = Integer.parseInt(scan.nextLine());
			
			//1: 상세정보보기
			if (input == 1) {
				for (CompanyVO coVo2 : coList) {
					System.out.println("기업 " + coVo2.getName() + "의 상세정보는 다음과 같습니다." + "\n기업대표: " + coVo2.getOwnerName()
							+ "\n기업 전화번호: " + coVo2.getPhoneNum() + "\n기업주소:" + coVo2.getAddr() 
							+ "\n기업규모:"+ coVo2.getScale() + "\n제재횟수:" + coVo2.getSanctionsCount());
					System.out.println("--------------------------------------");
				}
				
				
			//2: 기업소유의 숙박시설 보기-조인쿼리 이용
			} else if (input == 2) {
				for (CompanyVO coVo2 : coList) {
					List<AccommodationsVO> accomList = accomService.searchAccomJoin(coVo2.getCompanyId());
					if (accomList.size() == 0) {
						System.out.println("기업소유의 숙박시설이 존재하지 않습니다.");
					} else {
						System.out.println("기업 "+coVo2.getName()+"이 소유한 숙박시설의 정보는 다음과 같습니다");
						System.out.println(accomList);
					}
				}
			}
		}
		System.out.println("검색 작업 끝");
	}

	public void login() {
		CompanyVO cvi = new CompanyVO();

		String companyId = "";
		String companyPw = "";

		System.out.println();
		System.out.println("로그인을 시작합니다");
		System.out.print("기업회원 ID>> ");
		companyId = scan.nextLine();

		if (!coService.checkCompany(companyId)) {
			System.out.println("존재하지 않는 아이디입니다");
			return;
		}

		System.out.print("회원 비밀번호 >> ");
		companyPw = scan.nextLine();

		CompanyVO company = coService.login(companyId.trim(), companyPw.trim());

		if (company != null) {
			CompanyService.getInstance().setVo(company);
			System.out.println(companyId + " 회원 로그인 성공!");
		} else {
			System.out.println(companyId + " id 혹은 pw가 틀렸습니다");
		}
	}


	public void logout() {
		CompanyVO cv = CompanyService.getInstance().getVo();

		if (cv == null) {
			System.out.println("로그인하세요");
			return;
		}

		System.out.println("로그아웃하시겠습니까");
		System.out.println("1.로그아웃 2.아니오");
		System.out.print("번호입력>> ");
		int choice = Integer.parseInt(scan.nextLine());

		switch (choice) {
			case 1:
				System.out.println("로그아웃 성공!");
				coService.logout(cv);
				break;
			case 2:
				System.out.println("아니오");
				return;
			default:
				System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
				logout();
				break;
		}
	}

	
	public static void main(String[] args) {
		CompanyController coCntr = new CompanyController();
		coCntr.start();
	}
	
	
	


}
