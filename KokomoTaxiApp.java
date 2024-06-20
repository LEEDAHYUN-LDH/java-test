package kokomo;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.Random;
import java.util.Scanner;

import kokomo.helper.MakeTaxiGrade;
import kokomo.helper.MakeTaxiName;
import kokomo.helper.MenuMaker;
import kokomo.helper.PrintTaxiAppReport;
import kokomo.taxi.Ven;
import kokomo.taxi.Diamond;
import kokomo.taxi.Ruby;
import kokomo.taxi.Taxi;
import kokomo.user.User;

//---------------KokomoTaxi 어플 실행----------------

public class KokomoTaxiApp {

	public static void main(String[] args) {

		// =====등록택시 대수======
		int numOfTaxi = 50;
		double rubyTaxiRate = 0.7; // 루비 70프로
		double diamondTaxiRate = 0.2; // 다이아 20프로
		double vanTaxiRate = 0.1; // 밴 10프로
		final double APP_COMMISSION = 0.2; //어플 수수료 20프로

		// =====택시 등급별 번호판 만들기======
		ArrayList<String> taxiLicense = new ArrayList<>(); // 택시 번호판 저장 List
		HashMap<String, ArrayList<String>> taxiMap = new HashMap<>(); // 택시 등급에 따른 번호판 저장 Map

		MakeTaxiName taxiNameList = new MakeTaxiName(numOfTaxi);
		taxiLicense = taxiNameList.taxiNumberMaker();
		MakeTaxiGrade taxiGradeList = new MakeTaxiGrade(rubyTaxiRate, diamondTaxiRate, vanTaxiRate, taxiLicense);
		taxiMap = taxiGradeList.TaxiGradeListMaker(taxiLicense); // Key: 택시등급(루비, 다이아, 밴) value: 택시번호 array
		
		// Taxi, Menu 관련 인스턴스 생성
		MenuMaker menu = new MenuMaker(); // 메뉴
		Taxi choiceTaxi = null; // 택시 인스턴스 참조변수

		// 사용 변수 선언 및 초기화
		int usePoint;
		int taxiTotalIncome = 0;
		int choice = 0;
		int taxiIncome = 0;
		int carpoolMember = 0;
		int numOfPassanger = 0;
		double totalFee = 0;
		String destination = null;
		String dispatchType = null;
		String taxi = null;
		String appInfo;

		// 메뉴 while문을 위한 논리형 변수
		boolean isMainM = true;
		boolean isDestination = false;
		boolean isTaxiM = false;
		boolean isDispatch = false;
		boolean isUsePoint = false;
		boolean isEnd = false;
		
		// 카플인원 변수
		User p1 = null;
		User p2 = null;
		User p3 = null;

		PrintTaxiAppReport printTaxiAppReport = new PrintTaxiAppReport(); // csv파일 생성 및 목록 생성
		Random random = new Random();

		String[] name = { "정주호", "이한울", "이다현", "하지헌" }; // 사용자 설정
		Scanner scanner = new Scanner(System.in);

		// Kokomo T App 실행부
		for (int i = 0; i < name.length; i++) { // 사용자의 수 만큼 반복
			User user = new User(name[i], (int) random.nextInt(1, 5000) * 10, // 사용자 인스턴스 생성
					(int) random.nextInt(2000) * 10); // 가진돈과 포인트를 생성자로 초기화(랜덤)
			System.out.println("****** " + user.getName() + "님 환영합니다!^^ ******");
			
			// 메뉴 출력
			while (isMainM) { // 메인 메뉴: 1.택시호출 2.포인트조회 3.종료
				menu.mainMenu();
				choice = scanner.nextInt(); // 입력
				if (choice == 1) {
					isDestination = true; // 목적지 입력 메뉴로 이동
					choice = -1;
				} else if (choice == 3) { // 프로그램 종료
					isMainM = false;
					isEnd = true;
				} else if (choice == 2) { // 포인트 조회
					System.out.println("=>" + user.getName() + "님의 KoPoint는 [" + (int) (user.getPoint()) + "P] 입니다.\n");
				} else {
					System.out.println("!!! 잘못된 입력입니다. 다시 입력해주세요!!!");
				}

				while (isDestination) { // 목적지 입력
					menu.inputDestination();
					destination = scanner.next(); // 입력
					if (destination == null) { // 예외처리
						System.out.println("!!! 잘못된 입력입니다. 다시 입력해주세요!!!");
					} else {
						System.out.println("입력하신 목적지는 " + "[" + destination + "]" + "입니다.");
						System.out.print("맞으면 O / 틀리거나 다시 입력은 X 를 눌러주세요: ");
						char check = scanner.next().charAt(0); // 입력
						if (check == 'O' || check == 'o') {
							System.out.println("=>" + destination + "까지의 거리는 " + user.getDistance() + "m 입니다.");
							isTaxiM = true; // 택시 종류 메뉴로 이동
							choice = -1;
						} else if (check == 'X' || check == 'x') {
							System.out.println("입력창으로 돌아갑니다.");
						} else {
							System.out.println("!!! 잘못된 입력입니다. 다시 입력해주세요!!!");
						}
					}

					while (isTaxiM) { // 택시 종류 선택: 1.루비 2.다이아 3.밴 0.이전메뉴
						System.out.println();
						menu.choiceTaxiMenu();
						choice = scanner.nextInt(); // 입력
						if (choice == 1 || choice == 2 || choice == 3) {
							isDispatch = true;
							switch (choice) {
							case 1:
								choiceTaxi = new Ruby(); // 선택한 택시의 종류에 따른 인스턴스 생성
								break;
							case 2:
								choiceTaxi = new Diamond();
								break;
							case 3:
								choiceTaxi = new Ven();
								break;
							}
							System.out.println();
							System.out.println("UserID: " + user.getUserID() + "님 근처에 있는 " + choiceTaxi.getTaxiType()
									+ " 택시 리스트");
							System.out.println(taxiMap.get(choiceTaxi.getTaxiType()));
							System.out.println("입니다.\n");
							taxi = taxiMap.get(choiceTaxi.getTaxiType()).remove(0); // 택시 번호 추출
							choice = -1;
						} else if (choice == 0) {
							System.out.println("이전 메뉴로 돌아갑니다.");
							isTaxiM = false;
							break;
						} else {
							System.out.println("!!! 잘못된 입력입니다. 다시 입력해주세요!!!");
						}

						while (isDispatch) { // 택시 호출 방법 선택: 1.가까운 2.평점 3.카풀 0.이전메뉴
							menu.dispatchingMenu();
							choice = scanner.nextInt();
							if (choice == 1 || choice == 2 || choice == 3) {
								dispatchType = menu.callTaxiAlarm(choice);
								System.out.println("\n=>" +
										dispatchType + choiceTaxi.getTaxiType() + " 택시" + taxi + "를 호출하였습니다.\n");
								System.out.println("========================");
								System.out.println(choiceTaxi.showInfo(user)); // 사용자가 선택한 택시 정보표시
								System.out.println("========================");
								totalFee = choiceTaxi.calculator(user); // 택시 요금 계산
								taxiIncome = (int) totalFee;
								taxiTotalIncome += taxiIncome; // Kokomo 택시 어플 회사의 총 수익 계산

								carpoolMember = random.nextInt(2, 3); // 카풀멤버 랜덤 생성
								if (carpoolMember == 2) {
									p1 = new User();
									p2 = new User();
									
								} else if (carpoolMember == 3) {
									p1 = new User();
									p2 = new User();
									p3 = new User();
									
								}
								
								if (choice == 3) { // 카풀 택시를 선택한 경우
									System.out.println("총 요금은 " + (int) totalFee + "원 입니다.");
									totalFee = totalFee / carpoolMember;
									choiceTaxi.setTotalFee(totalFee);
									System.out.println(carpoolMember + "명과 카풀을 하였습니다.");
								}
								menu.destinationEnd(destination);

								System.out.println("=>지불할 금액은 " + (int) totalFee + "원 입니다.\n");
								System.out.println("=============================");
								System.out.println("현재 당신의 포인트는 " + (int) user.getPoint() + "[P]이고, ");
								System.out.println("Koko페이머니 잔액은 " + (int) user.getMoney() + "원 입니다.\n");
								System.out.println("=============================");
								
								isUsePoint = true;

								// Koko페이머니가 지불할 금액보다 적을시 남은 결제 금액 만큼 자동충전을 실시함

								while (isUsePoint) { // 포인트를 사용 여부를 선택하는 부분
									System.out.print("[포인트를 사용하시겠습니까? O/X] : ");
									char check = scanner.next().charAt(0);

									if (check == 'O' || check == 'o') { // 포인트 사용
										System.out.print("=>사용할 포인트를 입력해주세요 : ");
										usePoint = scanner.nextInt();
										if (usePoint >= totalFee) {
											if (totalFee <= user.getPoint()) {
												System.out.println((int) totalFee + "[P]만 사용하였습니다.\n");
											} else {
												System.out.println(
														"소지한 최대 포인트 " + (int) user.getPoint() + "[P]를 모두 사용하였습니다.");
											}

										} else if (usePoint <= totalFee) {
											if (usePoint < user.getPoint()) {
												System.out.println((int) usePoint + "[P]만 사용하였습니다.\n");
											} else {
												System.out.println(
														"소지한 최대 포인트 " + (int) user.getPoint() + "[P]를 모두 사용하였습니다.");
											}

										}

										user.setUsePoint(usePoint);
										System.out.println("=>남은 결제 금액은 " + (int) choiceTaxi.payFee(user) + "원 입니다.\n");
										choiceTaxi.setTotalFee(choiceTaxi.getRealTotalFee()); // 포인트 세팅
										choiceTaxi.calPoint(user); // 포인트 계산

										if (choiceTaxi.getRealTotalFee() > user.getMoney()) {
											System.out.println("=>Koko페이머니 "
													+ (int) (choiceTaxi.getRealTotalFee() - user.getMoney())
													+ "원을 자동으로 충전합니다.\n");
											user.setMoney(0);
										} else {
											System.out.println("요금을 지불했습니다.");
											user.setMoney((int) (user.getMoney() - choiceTaxi.getRealTotalFee()));
										}

										isUsePoint = false;

									} else if (check == 'X' || check == 'x') { // 포인트 사용 안함
										System.out.println(".......결제를 시도 합니다.");
										choiceTaxi.calPoint(user);
										if (choiceTaxi.getTotalFee() > user.getMoney()) {
											System.out.println(
													"=>Koko페이머니 " + (int) (choiceTaxi.getTotalFee() - user.getMoney())
															+ "원을 자동으로 충전합니다.\n");
											user.setMoney(0);
										} else {
											System.out.println("=>요금을 지불했습니다.");
											user.setMoney((int) (user.getMoney() - choiceTaxi.getTotalFee()));
										}

										isUsePoint = false;

									} else {
										System.out.println("!!! 잘못된 입력입니다. 다시 입력해주세요!!!");
										isUsePoint = true;
									}
								}

								System.out.println();
								System.out.println("@나의 포인트는 [" + (int) user.getPoint() + "P]이고, ");
								System.out.println("@Koko페이머니 잔액은 [" + (int) user.getMoney() + "원] 입니다.");

								isUsePoint = false;
								isDispatch = false;
								isTaxiM = false;
								isDestination = false;
								isMainM = false;
								break;

							} else if (choice == 0) { // 택시 호출시 이전메뉴로 돌아가기
								System.out.println("이전 메뉴로 돌아갑니다.");
								isDispatch = false;
								break;
							} else {
								System.out.println("!!! 잘못된 입력입니다. 다시 입력해주세요!!!");
							}
						}

					}
				}
			}
			// 이용완료시 출력 메세지
			System.out.println("\n==================================");
			System.out.println("Kokomo T를 종료합니다.");
			System.out.println("이용해주셔서 감사합니다!^^");
			System.out.println("==================================\n\n\n");
			
			String[] carpoolMemberName = {"김", "이", "박", "하", "오", "손", "민"};
			
			
			// appInfo에 저장 목룍: 사용자ID, 이름, 목적지, 택시등급, 택시번호, 호출방법, 이동거리, 요금, 할증유무
			appInfo = user.getUserID() + "," + user.getName().charAt(0) + "**," 
					+ destination + "," + choiceTaxi.getTaxiType()
					+ "," + taxi + "," + dispatchType + "," + user.getDistance() + "," 
					+ taxiIncome + "," + choiceTaxi.getAddFee();
			printTaxiAppReport.appendReport(appInfo, true); // CSV파일로 저장
			
			// 카풀멤버도 저장에 포함시키기
			if(carpoolMember == 2 && choice == 3) {
				numOfPassanger = numOfPassanger + carpoolMember;
				appInfo = p1.getUserID() + "," + carpoolMemberName[random.nextInt(carpoolMemberName.length - 1)].charAt(0) + "**," 
						+ destination + "," + choiceTaxi.getTaxiType()
						+ "," + taxi + "," + dispatchType + "," + user.getDistance() + "," 
						+ "-," + choiceTaxi.getAddFee();
				printTaxiAppReport.appendReport(appInfo, true); // CSV파일로 저장
				
				appInfo = p2.getUserID() + "," + carpoolMemberName[random.nextInt(carpoolMemberName.length - 1)].charAt(0) + "**," 
						+ destination + "," + choiceTaxi.getTaxiType()
						+ "," + taxi + "," + dispatchType + "," + user.getDistance() + "," 
						+ "-," + choiceTaxi.getAddFee();
				printTaxiAppReport.appendReport(appInfo, true); // CSV파일로 저장
			} 
			if (carpoolMember == 3 && choice == 3) {
				numOfPassanger = numOfPassanger + carpoolMember;
				appInfo = p1.getUserID() + "," + carpoolMemberName[random.nextInt(carpoolMemberName.length - 1)].charAt(0) + "**," 
						+ destination + "," + choiceTaxi.getTaxiType()
						+ "," + taxi + "," + dispatchType + "," + user.getDistance() + "," 
						+ "-," + choiceTaxi.getAddFee();
				printTaxiAppReport.appendReport(appInfo, true); // CSV파일로 저장
				
				appInfo = p2.getUserID() + "," + carpoolMemberName[random.nextInt(carpoolMemberName.length - 1)].charAt(0) + "**," 
						+ destination + "," + choiceTaxi.getTaxiType()
						+ "," + taxi + "," + dispatchType + "," + user.getDistance() + "," 
						+ "-," + choiceTaxi.getAddFee();
				printTaxiAppReport.appendReport(appInfo, true); // CSV파일로 저장
				appInfo = p3.getUserID() + "," + carpoolMemberName[random.nextInt(carpoolMemberName.length - 1)].charAt(0) + "**," 
						+ destination + "," + choiceTaxi.getTaxiType()
						+ "," + taxi + "," + dispatchType + "," + user.getDistance() + "," 
						+ "-," + choiceTaxi.getAddFee();
				printTaxiAppReport.appendReport(appInfo, true); // CSV파일로 저장
			}
			
			numOfPassanger = numOfPassanger + 1;
			choice = -1;

			if (isEnd) { // 가장 처음 메인 메뉴에서 3. 종료를 누렸을때 완전 종료
				break;
			}

			// 다시 처음 부터 시작
			isUsePoint = false;
			isDispatch = false;
			isTaxiM = false;
			isDestination = false;
			isMainM = true;
		
		}
		scanner.close();

		// 회사의 총수익과 이용자 수를 계산
		appInfo = "KokomoT사용자수," + numOfPassanger + "명," + "택시총수익," + taxiTotalIncome + "원, App수수료: " 
					+ (int)(APP_COMMISSION * 100.0) + "%, App총수익: " +  (int)(taxiTotalIncome * APP_COMMISSION) + "원";
		printTaxiAppReport.appendReport(appInfo, true); // csv로 저장

		// 최종 종료시 출력 메세지
		System.out.println("모든 사용자가 이용을 완료하였습니다.");
		System.out.println("Kokomo T 사용자 수는 " + numOfPassanger + "명 이고, 총 수익은 " + taxiTotalIncome + "원 입니다.");
	}
}