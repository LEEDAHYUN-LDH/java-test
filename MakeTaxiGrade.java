package kokomo.helper;

import java.util.ArrayList;
import java.util.HashMap;

// 생성 된 택시 번호판에 각 택시 등급 비율에 따라 등급을 부여
public class MakeTaxiGrade {
	// 멤버변수
	int numOfTaxi; // 택시 대수
	int numOfRuby; // 루비 택시 대수
	int numOfdiamond; // 다이아몬드 택시 대수
	int numOfVanTaxi; // 밴 택시 대수

	// 해쉬맵과 각각의 택시 등급에 따른 어레이 생성
	HashMap<String, ArrayList<String>> taxiMap;
	ArrayList<String> ruby = new ArrayList<String>();
	ArrayList<String> dia = new ArrayList<String>();
	ArrayList<String> ven = new ArrayList<String>();

	// 생성자
	public MakeTaxiGrade(double rubyTaxiRate, double diamondTaxiRate, 
					double vanTaxiRate, ArrayList<String> taxiNum) {

		taxiMap = new HashMap<String, ArrayList<String>>();
		this.numOfTaxi = taxiNum.size();
		this.numOfRuby = (int) (numOfTaxi * rubyTaxiRate);
		this.numOfdiamond = (int) (numOfTaxi * diamondTaxiRate);
		this.numOfVanTaxi = (int) (numOfTaxi * vanTaxiRate);
	}

	// 메서드==============================================================
	// 택시의 등급에 따라 택시 분류
	public HashMap<String, ArrayList<String>> TaxiGradeListMaker(ArrayList<String> taxiNum) {
		
		// 등급 비율에 따라 택시 번호판 나누기
		for (int i = 0; i < numOfRuby; i++) { // 루비 택시
			ruby.add(taxiNum.get(i));
		}
		taxiMap.put("루비", ruby);

		for (int i = numOfRuby; i < (numOfRuby + numOfdiamond); i++) { // 다이아 택시
			dia.add(taxiNum.get(i));
		}
		taxiMap.put("다이아몬드", dia);

		for (int i = (numOfRuby + numOfdiamond); i < numOfTaxi; i++) { // 밴(6인승) 택시
			ven.add(taxiNum.get(i));
		}
		taxiMap.put("밴(6인승)", ven);

		return taxiMap;
	}

	// 게터
	public int getNumOfRuby() {
		return numOfRuby;
	}

	public int getNumOfdiamond() {
		return numOfdiamond;
	}

	public int getNumOfVanTaxi() {
		return numOfVanTaxi;
	}
}
