package kr.or.ddit.teampro.accommodations.accomController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import kr.or.ddit.teampro.accommodations.accomService.AccommodationsService;
import kr.or.ddit.teampro.accommodations.accomVo.AccommodationsVO;
import kr.or.ddit.teampro.company.coService.CompanyService;
import kr.or.ddit.teampro.company.coVo.CompanyVO;
import kr.or.ddit.teampro.room.rmService.RoomService;
import kr.or.ddit.teampro.room.rmVo.RoomVO;

public class AccommodationsController {
	private CompanyService coService;
	private AccommodationsService accomService;
	private RoomService rmService;

	private Scanner scan = new Scanner(System.in);

	public AccommodationsController() {
		coService = CompanyService.getInstance();
		accomService = AccommodationsService.getInstance();
		rmService = RoomService.getInstance();
	}

	public void displayMenu() {
		System.out.println();
		System.out.println("----------------------");
		System.out.println("  === 작 업 선 택 ===");
		System.out.println("  1. 숙박시설 등록");
		System.out.println("  2. 숙박시설정보 삭제");
		System.out.println("  3. 숙박시설정보 수정");
		System.out.println("  4. 전체 숙박시설 출력");
		System.out.println("  5. 숙박시설 검색");
		System.out.println("  6. 작업 끝.");
		System.out.println("----------------------");
		System.out.print("원하는 작업 선택 >> ");
	}

	public void start() {
		int choice;
		do {
			displayMenu(); // 메뉴 출력
			choice = scan.nextInt(); // 메뉴번호 입력받기
			switch (choice) {
			case 1: insertAccommodations(); break;
			case 2: deleteAccommodations(); break;
			case 3: updateAccommodations(); break;
			case 4: displayAllAccommodations(); break;
			case 5: searchAccom(); break;
			case 6: System.out.println("작업을 마칩니다."); break;
			default: System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
			}
		} while (choice != 6);// 6이 아닐때까지 돈다
	}

	/**
	 * 숙박시설 등록 메서드
	 */
	private void insertAccommodations() {
		boolean isExist;
		String accomName = "";
		String companyId = "";
		do {
			scan.nextLine();// 버퍼비우기
			System.out.println();
			System.out.println("새롭게 등록할 숙박시설명을 입력하세요");
			System.out.println("양식은 <A호텔 오류점> 입니다");
			System.out.print("숙박시설명>>");
			accomName = scan.nextLine();
			System.out.print("기업ID를 확인해주세요>>");
			companyId = scan.nextLine();

			isExist = accomService.checkAccom(accomName, companyId);
			if (isExist) {
				System.out.println("이름이" + accomName + "인 숙박시설이 이미 존재합니다.");
				System.out.println("다시 입력해주세요.");
			}
		} while (isExist);

		System.out.println("시설주소>> ");
		String accomAddr = scan.nextLine();
		System.out.println("시설 전화번호>> ");
		String phoneNum = scan.nextLine();

		//시설타입은 번호로 입력받음, 1-4값이 아닌 경우 재입력하게 구현
		System.out.println("시설타입은 번호로 입력해 주세요");
		System.out.println("시설타입>>1.호텔 2.모텔 3.펜션 4.리조트");
		int choice;
		String type = "";
		do {
			choice = scan.nextInt();
			switch (choice) {
			case 1: type = "호텔"; break;
			case 2: type = "모텔"; break;
			case 3: type = "펜션"; break;
			case 4: type = "리조트"; break;
			default: System.out.println("번호를 잘못 입력했습니다. 다시 입력하세요");
			}
		} while (choice < 1 || choice > 4);

		//설립날짜 수정은 임시로 값을 받고 그 값이 빈값이 아니면 넣어주는 방식으로 구현 
		System.out.println("양식은 <yyyy-MM-dd> 입니다");
		System.out.println("설립날짜>>");
		String strSetUpDate = scan.nextLine();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date setUpDate = new Date();
		try {
			setUpDate = formatter.parse(strSetUpDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		
		scan.nextLine();// 버퍼비우기
		System.out.println("상세설명(서비스가능언어 / 아동,반려견출입 가능여부 / 기타사항)");
		System.out.println("서비스 가능 언어>> ");
		String lang = "서비스가능 언어:" + scan.nextLine();
		
		
		System.out.println("아동출입 가능여부(Y,N)>> ");
		String kids = "";
		while (true) {
			String input = scan.nextLine();
			if (input.equals("Y") || (input.equals("N"))) {
				kids = "아동출입 가능여부:" + input;
				break;
			} else {
				System.out.println("Y,N 형태로만 다시 입력해주세요 ");
			}
		}
		System.out.println("반려견출입 가능여부(Y,N)>> ");
		String pet = "";
		while (true) {
			String input = scan.nextLine();
			if (input.equals("Y") || (input.equals("N"))) {
				pet = "반려견출입 가능여부:" + input;
				break;
			} else {
				System.out.println("Y,N 형태로만 다시 입력해주세요 ");
			}
		}
		System.out.println("기타사항>> ");
		String etc = "기타사항:" + scan.nextLine();
		
//		위에서 받은 4가지 값을 합쳐서 description에 넣어준다
		String description = lang + "\n" + kids + "\n" + pet + "\n" + etc;

		AccommodationsVO accomVo = new AccommodationsVO();
		accomVo.setAccomName(accomName);
		accomVo.setCompanyId(companyId);
		accomVo.setAccomAddr(accomAddr);
		accomVo.setPhoneNum(phoneNum);
		accomVo.setType(type);
		accomVo.setSetUpDate(setUpDate);
		accomVo.setDescription(description);

		int cnt = accomService.registAccom(accomVo);
		if (cnt > 0) {
			System.out.println(accomName + "숙박시설 추가 작업 성공!");
		} else {
			System.out.println(accomName + "숙박시설 추가 작업 실패...");
		}
	}

	/**
	 * 숙박시설 정보를 삭제하기 위한 메서드-pw 재확인 작업
	 */
	private void deleteAccommodations() {
		scan.nextLine();// 버퍼비우기
		System.out.println();
		System.out.println("삭제할 숙박시설명을 입력하세요");
		System.out.print("숙박시설명>>");
		String accomName = scan.nextLine();
		System.out.print("기업ID를 확인해주세요>>");
		String companyId = scan.next();

//		pw를 재확인하기 위한 코드
//		위에서 입력받은 companyId를 가진 VO객체를 만들고 리스트에 담는다
//		다시 리스트에서 acccommodationsVO객체를 만든 후 password를 빼와서 comPw에 넣어준다
		String compPw = "";
		CompanyVO coVo = new CompanyVO();
		coVo.setCompanyId(companyId);
		
		List<CompanyVO> coList = coService.searchCompany(coVo);
		for (CompanyVO accomVo : coList) {
			compPw = accomVo.getCompanyPw();
		}
		
		while (true) {
			System.out.println("삭제를 위해 password를 재확인합니다.");
			System.out.println("Password>> ");
			String inputPw = scan.next();
			if (compPw.equals(inputPw)) {
				compPw = inputPw;
				break;
			} else {
				System.out.println("비밀번호가 같지 않습니다. 다시 입력해주세요");
			}
		}

		
		int cnt = accomService.removeAccom(accomName, companyId);
		if (cnt > 0) {
			System.out.println(accomName + " 숙박시설 삭제 작업 성공!");
		} else {
			System.out.println(accomName + " 숙박시설 삭제 작업 실패......");
		}

	}

	/**
	 * 숙박시설정보를 수정하기 위한 메서드
	 */
	private void updateAccommodations() {
		boolean isExist = false;
		String accomName = "";
		String companyId = "";
		do {
			System.out.println();
			System.out.println("수정할 숙박시설명을 입력하세요");
			System.out.print("숙박시설명>>");
			accomName = scan.next();
			System.out.print("기업ID를 확인해주세요>>");
			companyId = scan.next();

			isExist = accomService.checkAccom(accomName, companyId);

			if (!isExist) {
				System.out.println("ID가 "+companyId+"이고 " + accomName + "인 숙박시설은 존재하지 않습니다.");
				System.out.println("다시 입력해주세요.");
			}
		} while (!isExist);

//		VO객체생성- 위에서 받은 시설명,아이디 2가지 속성을 우선 넣어줌
//		-String타입은 입력받으면 넣어주고
//		다른 속성은 빈값이 아닌 경우 넣어줄 수 있도록 구현
//		-String타입이 아닌 경우 nextLine쓸 수가 없어서 isEmpty메서드를 통해 넣어줌
		AccommodationsVO accomVo = new AccommodationsVO();
		accomVo.setAccomName(accomName);
		accomVo.setCompanyId(companyId);

		scan.nextLine();// 버퍼비우기
		
		
		System.out.println("수정을 원하지 않는 경우, 데이터 입력 없이 enter키를 누르세요");
		System.out.println("시설 주소>> ");
		String accomAddr = scan.nextLine();
		accomVo.setAccomAddr(accomAddr);

		System.out.println("시설 전화번호>> ");
		String phoneNum = scan.nextLine();
		accomVo.setPhoneNum(phoneNum);

		//시설타입은 번호로 입력받음, 1-4값이 아닌 경우 재입력하게 구현
		int intType = 0;
		String tempType = null;
		do {
			System.out.println("시설타입은 번호로 입력해 주세요");
			System.out.println("시설타입>>1.호텔 2.모텔 3.펜션 4.리조트");
			tempType = scan.nextLine();
			if (!tempType.isEmpty()) {
				intType = Integer.parseInt(tempType);
				switch (intType) {
				case 1: accomVo.setType("호텔"); break;
				case 2: accomVo.setType("모텔"); break;
				case 3: accomVo.setType("펜션"); break;
				case 4: accomVo.setType("리조트"); break;
				default:
					System.out.println("번호를 잘못 입력했습니다. 다시 입력하세요");
				}
			}
		} while (!tempType.isEmpty() && (intType < 1 || intType > 4));

		
		//설립날짜 수정은 임시로 값을 받고 그 값이 빈값이 아니면 넣어주는 방식으로 구현 
		System.out.println("양식은 <yyyy-MM-dd> 입니다");
		System.out.println("설립날짜>> ");
		String tempSetUpDate = scan.nextLine();
		if (!tempSetUpDate.isEmpty()) {
			try {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				Date setUpDate = new Date();
				setUpDate = formatter.parse(tempSetUpDate);
				accomVo.setSetUpDate(setUpDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		//상세설명은 변경은 Y를 누른 경우 각 속성을 다 입력받아야함 -Y,N형태가 아닌경우 다시 입력받게 함
		System.out.println("상세설명도 변경하시겠습니까?(서비스가능언어 / 아동,반려견출입 가능여부 / 기타사항)");
		System.out.println("Y,N 형태로 입력해주세요>> ");
		String input = scan.nextLine();
		if (input.equals("Y")) {
			System.out.println("서비스 가능 언어>> ");
			String lang = "서비스가능 언어:" + scan.nextLine();

			//아동출입 여부 -Y,N형태가 아닌경우 다시 입력받게 함
			String kids = "";
			while (true) {
				System.out.println("아동출입 가능여부(Y,N)>> ");
				String input_kids = scan.nextLine();
				if (input_kids.equals("Y") || (input_kids.equals("N"))) {
					kids = "아동출입 가능여부:" + input;
					break;
				} else {
					System.out.println("Y,N 형태로만 다시 입력해주세요 ");
				}
			}

			//반려견출입 여부 -Y,N형태가 아닌경우 다시 입력받게 함
			String pet = "";
			while (true) {
				System.out.println("반려견출입 가능여부(Y,N)>> ");
				String input_pet = scan.nextLine();
				if (input_pet.equals("Y") || (input_pet.equals("N"))) {
					pet = "반려견출입 가능여부:" + input_pet;
					break;
				} else {
					System.out.println("Y,N 형태로만 다시 입력해주세요 ");
				}
			}
			System.out.println("기타사항>> ");
			String etc = "기타사항:" + scan.nextLine();
			
			//상세정보에 위에서 입력받은 사항을 합쳐서 넣어준다
			String description = lang + "\n" + kids + "\n" + pet + "\n" + etc;
			accomVo.setDescription(description);
		} else if (input.equals("N")) {
			
		} else {
			System.out.println("Y,N 형태로만 다시 입력해주세요 ");
		}

		int cnt = accomService.modifyAccom(accomVo);
		if (cnt > 0) {
			System.out.println(accomName + "숙박시설 정보 수정 성공!");
		} else {
			System.out.println(accomName + "숙박시설 정보 수정 실패......");
		}

	}

	private void displayAllAccommodations() {
		System.out.println();
		System.out.println("-----------------------------------");
		
//		DATE타입 포맷에 맞춰 출력하기 위한 코드
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date setUpDate = new Date();

		List<AccommodationsVO> accomList = accomService.selectAllAccom();
		if (accomList.size() == 0) {
			System.out.println("조회된 데이터가 없습니다.");
		} else {
			for (AccommodationsVO accomVo : accomList) {
				System.out.println("숙박시설명: " + accomVo.getAccomName() + "\n시설주소: " + accomVo.getAccomAddr()
									+ "\n시설 전화번호: " + accomVo.getPhoneNum() + "\n시설 타입: " + accomVo.getType() 
									+ "\n시설 설립일자: "+ formatter.format(accomVo.getSetUpDate()) + "\n별점: " + accomVo.getStarPoint() 
									+ "\n누적 이용횟수: "+ accomVo.getUseCount() + "\n상세설명\n"+ accomVo.getDescription());
				System.out.println("------------------------------------");
			}
		}
		System.out.println("출력 작업 끝");
	}

	private void searchAccom() {
		scan.nextLine();// 버퍼 비우기
		System.out.println();
		System.out.println("검색할 숙박시설 정보를 입력하세요");
		System.out.println("값을 입력하지 않고 enter를 누르는 항목은 모든 값이 검색됩니다");

		System.out.println("숙박시설명>>");
		String accomName = scan.nextLine().trim();

		System.out.println("주소>>");
		String accomAddr = scan.nextLine().trim();

		System.out.println("양식은 000-000-0000 입니다");
		System.out.println("전화번호>>");
		String phoneNum = scan.nextLine().trim();

		System.out.println("호텔/모텔/펜션/리조트 중 하나를 입력해주세요");
		System.out.println("타입>>");
		String type = scan.nextLine().trim();

//		날짜는 사용자가 입력한 값 이상으로 검색되게 구현
//		날짜는 DATE타입으로 포맷을 맞춰줌 
//		임시로 string타입으로 받고 그 값이 빈값이 아닐때만 넣어주는 방식으로 구현
		System.out.println("입력한 날짜 이후로 설립된 시설이 검색됩니다.");
		System.out.println("양식은 <yyyy-MM-dd> 입니다");
		System.out.println("설립일자>>");
		String tempSetUpDate = scan.nextLine().trim();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date setUpDate = new Date(1970 - 01 - 01); //기본값 세팅
		try {
			if (!tempSetUpDate.equals("")) {
				setUpDate = formatter.parse(tempSetUpDate);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

//		별점은 사용자가 입력한 값 이상으로 검색되게 구현
//		별점은 int타입이라 임시로 string타입으로 받고 그 값이 빈값이 아닐때만 넣어주는 방식으로 구현
		System.out.println("입력한 별점 이상인 시설이 검색됩니다.");
		System.out.println("시설별점>>");
		String tempStarPoint = scan.nextLine();
		int starPoint = 0;
		if (!tempStarPoint.equals("")) {
			starPoint = Integer.parseInt(tempStarPoint);
		}

//		별점은 사용자가 입력한 값 이상으로 검색되게 구현
//		별점은 int타입이라 임시로 string타입으로 받고 그 값이 빈값이 아닐때만 넣어주는 방식으로 구현
		System.out.println("입력한 이용횟수 이상인 시설이 검색됩니다.");
		System.out.println("누적이용횟수>>");
		String tempUseCount = scan.nextLine();
		int useCount = 0;
		if (!tempUseCount.equals("")) {
			useCount = Integer.parseInt(tempUseCount);
		}

		//VO객체 생성 후 사용자에게 입력받은 값 넣어줌
		AccommodationsVO accomVo = new AccommodationsVO();
		accomVo.setAccomName(accomName);
		accomVo.setAccomAddr(accomAddr);
		accomVo.setPhoneNum(phoneNum);
		accomVo.setType(type);
		accomVo.setSetUpDate(setUpDate);
		accomVo.setStarPoint(starPoint);
		accomVo.setUseCount(useCount);
		accomVo.setDescription("");

		System.out.println();
		System.out.println("-----------------------------------");

//		VO객체가 들어있는 list구현
		List<AccommodationsVO> accomList = accomService.searchAccom(accomVo);
		if (accomList.size() == 0) {
			System.out.println("검색된 데이터가 없습니다.");
		} else {//검색된 데이터를 통해 간단 정보만 출력
			for (AccommodationsVO accomVo2 : accomList) {
				System.out.println("숙박시설명: " + accomVo2.getAccomName() + ", 시설주소: " + accomVo2.getAccomAddr()
									+ ", 시설 전화번호: " + accomVo2.getPhoneNum() + ", 시설 타입: " + accomVo2.getType());
				System.out.println("------------------------------------");
			}
			
			//간단정보 출력후 1상세정보보기, 2숙박시설보기 선택 기능 
			System.out.println("1.숙박시설 상세정보 보기");
			System.out.println("2.숙박시설 객실 보기");
			System.out.println("나가려면 1과 2를 제외한 키를 눌러주세요");
			int input = scan.nextInt();
			
			//1: 상세정보보기
			if (input == 1) {
				for (AccommodationsVO accomVo2 : accomList) {
					System.out.println(accomVo2.getAccomName() +"의 상세정보 입니다."+"\n시설 설립일자: " + formatter.format(accomVo2.getSetUpDate()) 
										+ "\n별점: "+ accomVo2.getStarPoint() + "\n누적 이용횟수: " + accomVo2.getUseCount() + "\n상세설명\n" + accomVo2.getDescription());
					System.out.println("------------------------------------");

				}
				
			//2: 숙박시설 객실보기-조인쿼리 이용
			} else if (input == 2) {
				for(AccommodationsVO acVo : accomList) {
					List<RoomVO> rmList = rmService.searchRoomJoin(acVo.getAccomName());
					if (rmList.size() == 0) {
						System.out.println("숙박시설에 객실이 존재하지 않습니다.");
					} else {
						System.out.println("숙박시설의 객실 정보는 다음과 같습니다.");
						System.out.println(rmList);
					} 
				}
			}
		}
		System.out.println("검색 작업 끝");
	}

	
	public static void main(String[] args) {
		AccommodationsController accomCntr = new AccommodationsController();
		accomCntr.start();
	}
}
