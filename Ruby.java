package kokomo.taxi;

import kokomo.user.User;

// 루비 등급 택시
public class Ruby extends Taxi {
	String taxiType = "루비";

	@Override
	public double calculator(User user) {
		baseFee = 3500;
		feePerMeter = 0.5;
		addRate = 1.03;
		return super.calculator(user);
	}

	@Override
	public double calPoint(User user) {
		return super.calPoint(user);
	}

	@Override
	public String showInfo(User user) {
		super.taxiType = "루비";
		baseFee = 3500;
		feePerMeter = 0.5;
		addRate = 1.03;
		return super.showInfo(user);
	}

	@Override
	public String getTaxiType() {
		return taxiType;
	}
}
