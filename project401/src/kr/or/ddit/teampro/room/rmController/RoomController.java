package kr.or.ddit.teampro.room.rmController;

import java.util.List;
import java.util.Scanner;

import kr.or.ddit.teampro.room.rmService.RoomService;
import kr.or.ddit.teampro.room.rmVo.RoomVO;

public class RoomController {
	private RoomService rmService;

	private Scanner scan = new Scanner(System.in);

	public RoomController() {
		rmService = RoomService.getInstance();
	}

	public void displayMenu() {
		System.out.println();
		System.out.println("----------------------");
		System.out.println("  === 작 업 선 택 ===");
		System.out.println("  1. 객실 등록");
		System.out.println("  2. 객실정보 삭제");
		System.out.println("  3. 객실정보 수정");
		System.out.println("  4. 전체 객실 출력");
		System.out.println("  5. 객실 검색");
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
			case 1: insertRoom(); break;
			case 2: deleteRoom(); break;
			case 3: updateRoom(); break;
			case 4: displayAllRoom(); break;
			case 5: searchRoom(); break;
			case 6: System.out.println("작업을 마칩니다."); break;
			default: System.out.println("번호를 잘못 입력했습니다. 다시 입력하세요");
			}
		} while (choice != 6);// 6이 아닐때까지 돈다
	}

	/**
	 * 객실정보 등록 메서드
	 */
	private void insertRoom() {
		boolean isExist;
		int roomNumber;
		String accomName = "";
		String companyId = "";

		do {
			System.out.println();
			System.out.println("새롭게 등록할 객실정보를 입력하세요");
			System.out.println("기업ID>> ");
			companyId = scan.nextLine();
			System.out.println("숙박 시설명>> ");
			accomName = scan.nextLine();
			System.out.print("객실번호>>");
			roomNumber = scan.nextInt();
			scan.nextLine();// 버퍼비우기

			isExist = rmService.checkRoom(roomNumber, accomName, companyId);
			if (isExist) {
				System.out.println("숙박시설에 번호가" + roomNumber + "인 객실이 이미 존재합니다.");
				System.out.println("다시 입력해주세요.");
			}
		} while (isExist);

		
//		1,2,3이 아닌경우 다시 입력받게 구현 
		System.out.println("1.높은등급 2.중간등급 3.낮은등급");
		System.out.println("객실 등급>> ");
		int grade;
		while(true) {
			int input = scan.nextInt();
			if (input==1 || input==2||input==3) {
				grade = input;
				break;
			} else {
				System.out.println("1,2,3 중에서 다시 입력해주세요 ");
			}
		}

		
		System.out.println("1박 기준으로 입력해 주세요(단위:만 원)");
		System.out.println("숙박 비용>> ");
		int price = scan.nextInt();
		
		System.out.println("적정 인원>> ");
		int appropriateNum = scan.nextInt();

		scan.nextLine();// 버퍼비우기

		
		System.out.println("상세설명(흡연 가능여부 / 기타사항)");
		System.out.println("흡연 가능여부(Y,N)>> ");
		String smoking = "";
		while(true) {
			String input = scan.nextLine();
			if (input.equals("Y") || (input.equals("N"))) {
				smoking = "흡연가능여부:" + input;
				break;
			} else {
				System.out.println("Y,N 형태로만 다시 입력해주세요 ");
			}
		}
		System.out.println("기타사항>> ");
		String etc = "기타사항:" + scan.nextLine();
		String description = smoking + "\n" + etc;

		RoomVO rmVo = new RoomVO();
		rmVo.setRoomNumber(roomNumber);
		rmVo.setAccomName(accomName);
		rmVo.setCompanyId(companyId);
		rmVo.setGrade(grade);
		rmVo.setPrice(price);
		rmVo.setAppropriateNum(appropriateNum);
		rmVo.setDescription(description);

		int cnt = rmService.registRoom(rmVo);

		if (cnt > 0) {
			System.out.println(roomNumber + "호 객실 추가 작업 성공!");
		} else {
			System.out.println(roomNumber + "호 객실 추가 작업 실패");
		}
	}

	/**
	 * 객실정보 삭제 메서드
	 */
	private void deleteRoom() {
		System.out.println();
		System.out.println("삭제할 객실 정보를 입력하세요");
		System.out.print("객실번호>>");
		int roomNumber = scan.nextInt();
		scan.nextLine();// 버퍼비우기
		System.out.println("숙박 시설명>> ");
		String accomName = scan.nextLine();
		System.out.println("기업ID>> ");
		String companyId = scan.nextLine();
		int cnt = rmService.removeRoom(roomNumber, accomName, companyId);

		if (cnt > 0) {
			System.out.println(roomNumber + "호 객실정보 삭제 작업 성공!");
		} else {
			System.out.println(roomNumber + "호 객실정보 삭제 작업 실패...");
		}
	}

	/**
	 * 객실정보 수정 메서드
	 */
	private void updateRoom() {
		boolean isExist = false;
		int roomNumber = 0;
		String accomName="";
		String companyId="";
		do {
			System.out.println();
			System.out.println("수정하고 싶은 객실의 번호를 입력하세요");
			System.out.print("객실번호>>");
			roomNumber = scan.nextInt();
			scan.nextLine();
			System.out.println("숙박 시설명을 확인해주세요 ");
			accomName = scan.nextLine();
			System.out.print("기업ID를 확인해주세요>>");
			companyId = scan.nextLine();

			isExist = rmService.checkRoom(roomNumber,accomName,companyId);
			if (!isExist) {
				System.out.println("번호가" + roomNumber + "인 객실은 존재하지 않습니다.");
				System.out.println("다시 입력해주세요.");
			}
		} while (!isExist);

		RoomVO rmVo = new RoomVO();
		rmVo.setRoomNumber(roomNumber);
		rmVo.setAccomName(accomName);
		rmVo.setCompanyId(companyId);

		
		System.out.println("수정을 원하시지 않으면 데이터 입력 없이 enter키를 누르세요");
		while(true) {
			System.out.println("1.높은등급 2.중간등급 3.낮은등급");
			System.out.println("객실 등급>> ");
			String tempGrade = scan.nextLine();
			if (!tempGrade.isEmpty()) {
				int grade = Integer.parseInt(tempGrade);
				if (grade==1 || grade==2||grade==3) {
					rmVo.setPrice(grade);
					break;
				} else {
					System.out.println("1,2,3 중에서 다시 입력해주세요 ");
				}
			}else {
				break;
			}
		}
		
//		int타입이라 isEmpty메서드 이용해서 빈값이 아닐때만 넣어주게 구현
		System.out.println("1박 기준으로 입력해 주세요(단위:만 원)");
		System.out.println("숙박 비용>> ");
		String tempPrice = scan.nextLine();
		if (!tempPrice.isEmpty()) {
			int price = Integer.parseInt(tempPrice);
			rmVo.setAppropriateNum(price);
		}
		
		
//		int타입이라 isEmpty메서드 이용해서 빈값이 아닐때만 넣어주게 구현
		System.out.println("적정 인원>> ");
		String tempAppropNum = scan.nextLine();
		if (!tempAppropNum.isEmpty()) {
			int appropriateNum = Integer.parseInt(tempAppropNum);
			rmVo.setAppropriateNum(appropriateNum);
		}
		
		
		
		System.out.println("상세설명도 변경하시겠습니까?(흡연 가능여부 / 기타사항)");
		System.out.println("Y,N 형태로 입력해주세요>> ");
		String input=scan.nextLine();
		while(true) {
			if (input.equals("Y")) {
				System.out.println("흡연가능여부(Y,N)>> ");
				String smoking = "흡연가능여부:" + scan.nextLine();
				System.out.println("기타사항>> ");
				String etc = "기타사항:" + scan.nextLine();
				rmVo.setDescription(smoking+"\n"+etc);
				break;
			} else if(input.equals("N")) {
				break;
			}else {
				System.out.println("Y,N 형태로만 다시 입력해주세요 ");
			}
		}	
		
		//
		int cnt = rmService.modifyRoom(rmVo);
		if (cnt > 0) {
			System.out.println(roomNumber + "호 객실 정보 수정 성공!");
		} else {
			System.out.println(roomNumber + "호 객실 정보 수정 실패...");
		}
	}

	/**
	 * 전체 객실정보를 출력하는 메서드
	 */
	private void displayAllRoom() {
		System.out.println();
		System.out.println("-----------------------------------");

		List<RoomVO> rmList = rmService.selectAllRoom();

		if (rmList.size() == 0) {
			System.out.println("조회된 데이터가 없습니다.");
		} else {
			for (RoomVO rmVo : rmList) {
				System.out.println("객실번호: " + rmVo.getRoomNumber() + "\n숙박시설명: " + rmVo.getAccomName() 
									+ "\n객실 등급: "+ rmVo.getGrade() + "\n1박 기준 가격(단위:만 원): " + rmVo.getPrice() 
									+ "\n적정인원(최대인원기준): "+ rmVo.getAppropriateNum() + "\n상세 설명(흡연가능여부 및 기타사항):\n" + rmVo.getDescription());
				System.out.println("------------------------------------");
			}
		}
		System.out.println("출력 작업 끝");
	}

	/**
	 * 객실정보 검색 메서드
	 * 다중검색이 아니라 한가지 조건검색(범위검색)으로 구현 했음 
	 */
	private void searchRoom() {
		int choice;
		do {
			System.out.println("1. 객실등급으로 검색하기");
			System.out.println("2. 숙박비용으로 검색하기");
			System.out.println("3. 적정인원으로 검색하기");
			System.out.println("숫자를 입력해주세요>>");
			choice = scan.nextInt(); // 메뉴번호 입력받기
			
//			1을 입력시 객실등급으로 검색
			if(choice==1) {
				System.out.println("객실 등급 범위를 입력해주세요");
				System.out.println("이상: ");
				int minGrade=scan.nextInt();
				System.out.println("이하: ");
				int maxGrade=scan.nextInt();
				
				//입력받은 min값, max값으로 범위에 맞는 VO객체를 return해주는 메서드를 통해 list생성
				List<RoomVO> rmList = rmService.searchRoomGrade(minGrade,maxGrade);
				if (rmList.size() == 0) {
					System.out.println("검색된 데이터가 없습니다.");
				} else {
					for (RoomVO rmVo2 : rmList) {
						System.out.println("객실번호: " + rmVo2.getRoomNumber() + "\n숙박시설명: " + rmVo2.getAccomName() 
											+"\n객실 등급:"+rmVo2.getGrade()+ "\n1박 기준 가격(단위:만 원): "+ rmVo2.getPrice() 
											+ "\n적정 인원: " + rmVo2.getAppropriateNum() + "\n객실 상세정보: \n" + rmVo2.getDescription());
						System.out.println("------------------------------------");
					}
				}
				break;
			}
			
//			2를 입력시 숙박비용으로 검색
			if(choice==2) {
				System.out.println("객실 비용(1박 기준) 범위를 입력해주세요");
				System.out.println("이상: ");
				int minPrice=scan.nextInt();
				System.out.println("이하: ");
				int maxPrice=scan.nextInt();
				
				//입력받은 min값, max값으로 범위에 맞는 VO객체를 return해주는 메서드를 통해 list생성
				List<RoomVO> rmList = rmService.searchRoomPrice(minPrice,maxPrice);
				if (rmList.size() == 0) {
					System.out.println("검색된 데이터가 없습니다.");
				} else {
					for (RoomVO rmVo2 : rmList) {
						System.out.println("객실번호: " + rmVo2.getRoomNumber() + "\n숙박시설명: " + rmVo2.getAccomName() 
											+"\n객실 등급:"+rmVo2.getGrade()+ "\n1박 기준 가격(단위:만 원): "+ rmVo2.getPrice() 
											+ "\n적정 인원: " + rmVo2.getAppropriateNum() + "\n객실 상세정보: \n" + rmVo2.getDescription());
						System.out.println("------------------------------------");
					}
				}
				break;
			}
			
			
//			3를 입력시 적정인원으로 검색
			if(choice==3) {
				System.out.println("적정인원 범위를 입력해주세요");
				System.out.println("이상: ");
				int minAppro=scan.nextInt();
				System.out.println("이하: ");
				int maxAppro=scan.nextInt();
				
				//입력받은 min값, max값으로 범위에 맞는 VO객체를 return해주는 메서드를 통해 list생성
				List<RoomVO> rmList = rmService.searchRoomAppro(minAppro,maxAppro);
				if (rmList.size() == 0) {
					System.out.println("검색된 데이터가 없습니다.");
				} else {
					for (RoomVO rmVo2 : rmList) {
						System.out.println("객실번호: " + rmVo2.getRoomNumber() + "\n숙박시설명: " + rmVo2.getAccomName() 
											+"\n객실 등급:"+rmVo2.getGrade()+ "\n1박 기준 가격(단위:만 원): "+ rmVo2.getPrice() 
											+ "\n적정 인원: " + rmVo2.getAppropriateNum() + "\n객실 상세정보: \n" + rmVo2.getDescription());
						System.out.println("------------------------------------");
					}
				}
				break;
			}
		} while (choice <=3 && choice >=1);
		System.out.println("검색 작업 끝");
	}

	public static void main(String[] args) {
		RoomController rmController = new RoomController();
		rmController.start();
	}
}
