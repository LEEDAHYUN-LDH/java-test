package kokomo.taxi;

import kokomo.user.User;

public class Taxi implements Calculator {
	// 멤버변수
	public final double POINTRATE = 0.05;
	protected int baseFee; // 기본요금
	protected double feePerMeter; // 미터 당 요금
	protected double totalFee; // 최종 요금
	protected double addRate;
	protected double realTotalFee;
	protected boolean isAdd; // 할증유무
	protected String taxiType; // 택시 종류(루비, 다이아, 밴)
	protected String taxiSerial; // 택시 번호
	protected String addFee;
	User user; // User 클래스를 멤버변수로 사용

	// 생성자
	public Taxi() {
		user = new User(); // 생성자에서 User 인스턴스 생성
	}

	// 메서드=================================================

	// 최종 요금 계산 메서드(인터페이스)
	@Override
	public double calculator(User user) {
		double calcFeePerMeter = (feePerMeter * user.getDistance());
		if (user.getTime() < 4 || user.getTime() > 22) { // 할증 시간 유무
			baseFee = baseFee + 500;
			feePerMeter = feePerMeter * addRate;
			this.isAdd = true;
		} else {
			this.isAdd = false;
		}

		this.totalFee = baseFee + (int) (calcFeePerMeter / 10) * 10;
		return this.totalFee;

	}
	
	// 포인트 계산 메서드(인터페이스)
	@Override
	public double calPoint(User user) {
		double point = user.getPoint() + this.realTotalFee * POINTRATE;
		user.setPoint(point);
		return point;
	}

	// 카풀 최종 요금 계산 메서드
	public double calculator(User user, int numOfPerson) {
		double calcFeePerMeter = (feePerMeter * user.getDistance());
		if (user.getTime() < 4 || user.getTime() > 22) { // 할증 시간 유무
			baseFee = baseFee + 500;
			feePerMeter = feePerMeter * 1.2;
		} else {
		}
		this.totalFee = (((baseFee + calcFeePerMeter) / numOfPerson) / 10) * 10;
		return this.totalFee;
	}

	// 포인트 적립에 대한 경우의 수 계산 메서드
	public double payFee(User user) {
		if (this.totalFee <= user.getUsePoint()) {
			if (this.totalFee < user.getPoint()) {
				realTotalFee = 0;
				user.setPoint(user.getPoint() - this.totalFee);
			} else if (this.totalFee > user.getPoint()) {
				realTotalFee = this.totalFee - user.getPoint();
				user.setPoint(0);
			}
		} else if (this.totalFee > user.getUsePoint()) {
			if (user.getPoint() > user.getUsePoint()) {
				realTotalFee = this.totalFee - user.getUsePoint();
				user.setPoint(user.getPoint() - user.getUsePoint());

			} else if (user.getPoint() <= user.getUsePoint()) {
				realTotalFee = this.totalFee - user.getPoint();
				user.setPoint(0);
			}
		}

		return realTotalFee;
	}

	// 택시에 대한 정보와 유저에 대한 정보를 한번에 보여주는 메서드
	public String showInfo(User user) {
		String isAddInfo = (user.getTime() < 4 || user.getTime() > 22) ? "할증 적용" : "할증 미적용";
		this.addFee = isAddInfo;
		return "택시 종류 : " + taxiType + "\n기본 요금 : " + baseFee + "원" + "\n미터당요금 : " 
				+ feePerMeter + "원" + "\n(할증률: " + (int) ((addRate * 100) - 100) + " %)" 
		+ "\n탑승 시간 : " + user.getTime() + "시" + "\n할증 유무 : " + isAddInfo;
	}

	// 게터 세터
	public int getBaseFee() {
		return baseFee;
	}

	public double getFeePerMeter() {
		return feePerMeter;
	}

	public double getTotalFee() {
		return totalFee;
	}

	public String getTaxiType() {
		return taxiType;
	}

	public String getTaxiSerial() {
		return taxiSerial;
	}

	public double getRealTotalFee() {
		return realTotalFee;
	}

	public boolean getisAdd() {
		return isAdd;
	}

	public String getAddFee() {
		return addFee;
	}

	public void setTotalFee(double totalFee) {
		this.totalFee = totalFee;
	}

	public void setRealTotalFee(double realTotalFee) {
		this.realTotalFee = realTotalFee;
	}

	public void setAdd(boolean isAdd) {
		this.isAdd = isAdd;
	}

}
