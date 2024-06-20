package kokomo.helper;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

// CSV 파일에 택시 이용자와 택시 정보에 대한 데이터를 출력
public class PrintTaxiAppReport {
	// 멤버 변수
	private final String fileName = "KokomoT_data.csv"; // CSV파일 이름

	// 생성자
	public PrintTaxiAppReport() { // 생성자 생성시 CVS파일 가장 위 목록 생성
		try (BufferedWriter dataWr = new BufferedWriter(new FileWriter(fileName))) {
			// 목록생성: 사용자ID, 이름, 목적지, 택시등급, 택시번호, 호출방법, 이동거리, 요금, 할증유무
			dataWr.write("\uFEFFKokomoTaxi 사용자 분석표");
			dataWr.newLine();
			dataWr.write("\uFEFF사용자ID,이름,목적지,택시등급,택시번호,"
							+ "호출방법,이동거리(m),요금(원),할증유무");

		} catch (IOException e) { // 예외 처리
			System.out.println(e);
		}
	}

	// csv파일에 추가하는 메서드
	public void appendReport(String appInfo, boolean append) { // append가 ture면 계속 쓰기 false면 덮어쓰기
		try (BufferedWriter dataWr = 
				new BufferedWriter(new FileWriter(fileName, append))) {
			// CSV 파일에 데이터 작성
			dataWr.newLine();
			dataWr.write("\uFEFF" + appInfo);

		} catch (IOException e) { // 예외 처리
			System.out.println(e);
		}
	}
}
