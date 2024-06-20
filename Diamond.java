package kokomo.taxi;

import kokomo.user.User;

// 다이아몬드 등급 택시
public class Diamond extends Taxi {
	String taxiType = "다이아몬드";

	@Override
	public double calculator(User user) {
		baseFee = 5000;
		feePerMeter = 0.6;
		addRate = 1.06;
		return super.calculator(user);
	}

	@Override
	public double calPoint(User user) {
		return super.calPoint(user);
	}

	@Override
	public String showInfo(User user) {
		super.taxiType = "다이아몬드";
		baseFee = 5000;
		feePerMeter = 0.6;
		addRate = 1.06;
		return super.showInfo(user);
	}

	@Override
	public String getTaxiType() {
		return taxiType;
	}

}
