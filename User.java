package kokomo.user;

import java.util.Random;
// 택시 이용자 클래스
public class User {
	// 멤버 변수
	private static int userIDCounter = 8229; // 사용ID serialNum
	private int userID; // 사용자 ID
	private int money; // 사용자 가진 돈
	private int distance; // 목적지까지 거리
	private int time; // 현재시간
	private int usePoint = 0; // 쓸 포인트
	private double point; // 사용자 포인트
	private String name; // 사용자 이름
	
	// 생성자
	public User() {
		this.userID = userIDCounter++;
	}
	
	public User(String name, int money, int point) {
		this.userID = userIDCounter++;
		this.name = name;
		this.money = money;
		this.point = point;
		this.distance = generateRandomDistance();
		this.time = generateRandomTime();
	}

	// 메서드================================================

	// 거리 랜덤 생성 메서드(목적지 관련)
	private int generateRandomDistance() {
		Random random = new Random();
		return random.nextInt(1000, 30000); 
	} // 1000m부터 30000m까지의 랜덤한 거리 생성

	// 시간 랜덤 생성 메서드(할증 관련)
	private int generateRandomTime() {
		Random random = new Random();
		return random.nextInt(0, 23); 
	} // 0부터 23까지의 랜덤한 시간 생성

	// 사용자 정보 출력 메서드
	public String toString() {
		return String.format("사용자ID: %d 고객이름: %s Koko페이머니 잔액 : "
				+ "%5d, 가진 Point: %5.0f, 목적지까지의 거리 : %5d, 탑승 시간: %2d",
				userID, name, money, point, distance, time);
	}

	// 게터 세터
	public int getUserID() {
		return userID;
	}

	public String getName() {
		return name;
	}

	public int getMoney() {
		return money;
	}

	public double getPoint() {
		return point;
	}

	public int getDistance() {
		return distance;
	}

	public int getTime() {
		return time;

	}

	public int getUsePoint() {
		return usePoint;
	}

	public void setUserID(int userID) {
		// userID는 생성자에서만 설정되므로 외부에서 변경할 필요가 없습니다.
	}

	public void setUsePoint(int usePoint) {
		this.usePoint = usePoint;
	}

	public void setPoint(double point) {
		this.point = point;
	}

	public void setMoney(int money) {
		this.money = money;

	}

	public void setPoint(int point) {
		this.point = point;
	}

}
