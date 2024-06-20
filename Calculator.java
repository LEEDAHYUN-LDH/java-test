package kokomo.taxi;

import kokomo.user.User;

public interface Calculator { // 거리당 요금 계산 인터페이스

	public double calculator(User user); // 거리당 최종 요금계산
	public double calPoint(User user); // 요금에 따른 포인트 계산

}


