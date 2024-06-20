package kokomo.helper;

import java.util.ArrayList;
import java.util.Random;

// 택시 번호반을 생성
public class MakeTaxiName {
	// 멤버변수
	private int numOfTexi; // 만들고 싶은 택시 개수 입력
	private int[] texiFirstIndex; // 택시번호판 첫번째 숫자
	private int[] texiLastIndex;// 택시번호판 뒷 숫자
	private int index;
	Random random = new Random();
	ArrayList<String> taxiName;

	// 생성자
	public MakeTaxiName(int numOfTexi) {
		taxiName = new ArrayList<String>();
		this.numOfTexi = numOfTexi; // 생성자에서 택시 번호 받아옴
		this.texiFirstIndex = new int[numOfTexi];
		this.texiLastIndex = new int[numOfTexi];
	}

	// 메서드 =================================================
	// 택시 번호판 생성 메서드: 번호판 예시 "000아0000"
	public ArrayList<String> taxiNumberMaker() {
		String[] texiMidIndex = { "아", "바", "사", "자" };

		// 택시 앞 번호 중복 되지 않게 랜덤 생성
		for (int i = 0; i < numOfTexi; i++) {
			texiFirstIndex[i] = random.nextInt(100, 999);
			for (int j = 0; j < i; j++) {
				if (texiFirstIndex[i] == texiFirstIndex[j]) {
					i--;
				}
			}
		}

		// 택시 뒷 번호 중복 되지 않게 랜덤 생성
		for (int i = 0; i < numOfTexi; i++) {
			texiLastIndex[i] = random.nextInt(1000, 9999);
			for (int j = 0; j < i; j++) {
				if (texiLastIndex[i] == texiLastIndex[j]) {
					i--;
				}
			}
		}

		// 택시 번호표를 가진 ArrayList 생성
		for (int i = 0; i < numOfTexi; i++) {
			index = random.nextInt(texiMidIndex.length); // 번호판 한글
			String texiIndex = (String) (texiFirstIndex[i] + 
								texiMidIndex[index] + texiLastIndex[i]);
			taxiName.add(texiIndex);
		}

		return taxiName;
	}

}