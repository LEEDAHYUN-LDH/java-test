package kokomo.helper;

// 각 상황에 따른 메뉴를 출력
public class MenuMaker {

	// 메서드============================================

	// 메인 메뉴
	public void mainMenu() {
		System.out.println("======= Kokomo T =======");
		System.out.println("[1] 택시 호출");
		System.out.println("[2] KoPoint 조회");
		System.out.println("[3] 종 료");
		System.out.println("========================");
		System.out.print("<입력> : ");
	}

	// 목적지 입력 메뉴
	public void inputDestination() {
		System.out.print("[목적지를 입력해주세요] : ");
	}

	// 택시 종류 선택 메뉴
	public void choiceTaxiMenu() {
		System.out.println("======= 택시 선택 =======");
		System.out.println("[1] 루비 택시");
		System.out.println("[2] 다이아몬드 택시");
		System.out.println("[3] 밴택시(6인승)");
		System.out.println("[0] 이전 메뉴");
		System.out.println("=======================");
		System.out.print("<입력> : ");
	}

	// 택시 호출 방법 선택 메뉴
	public void dispatchingMenu() {
		System.out.println("======= 택시 호출 =======");
		System.out.println(">택시 배차 방법을 고르세요<");
		System.out.println("[1] 가까운 거리 택시 호출");
		System.out.println("[2] 평점 높은 택시 호출");
		System.out.println("[3] 카풀 택시 호출(요금n지불)");
		System.out.println("[0] 이전 메뉴");
		System.out.println("=======================");
		System.out.print("<입력> : ");
	}

	// 택시 배차 방법에 따라 각각의 배차 방법을 반환하는 메서드
	public String callTaxiAlarm(int dispatch) {
		String temp = null;

		switch (dispatch) {
		case 1:
			temp = "가장 가까운 거리의 ";
			break;
		case 2:
			temp = "가장 평점이 높은 ";
			break;
		case 3:
			temp = "가까운 거리의 카풀 ";
			break;
		}
		return temp;
	}

	// 목적지에 도착했을때 표시하는 메뉴
	public void destinationEnd(String destination) {
		System.out.println("\n[목적지 " + destination + "에 도착하였습니다.]");
	}

}
