package kokomo.taxi;

import kokomo.user.User;

// 밴(6인승) 등급 택시
public class Ven extends Taxi {
	String taxiType = "밴(6인승)";

	@Override
	public double calculator(User user) {
		baseFee = 10000;
		feePerMeter = 0.8;
		addRate = 1.1;
		return super.calculator(user);
	}

	@Override
	public double calPoint(User user) {
		return super.calPoint(user);
	}

	@Override
	public String showInfo(User user) {
		super.taxiType = "밴(6인승)";
		baseFee = 10000;
		feePerMeter = 0.8;
		addRate = 1.1;
		return super.showInfo(user);
	}

	@Override
	public String getTaxiType() {
		return taxiType;
	}
}
