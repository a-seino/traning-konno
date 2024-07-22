package change;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import race.Driver;
import race.ExtremeDriver;
import race.NormalDriver;
import race.SuperDriver;
import race.vehicle.Boat;
import race.vehicle.Car;
import race.vehicle.FastBoat;
import race.vehicle.FastCar;
import race.vehicle.NormalBoat;
import race.vehicle.NormalCar;
import race.vehicle.parts.Engine;
import race.vehicle.parts.NormalEngine;
import race.vehicle.parts.NormalPropeller;
import race.vehicle.parts.NormalTire;
import race.vehicle.parts.PowerEngine;
import race.vehicle.parts.PowerPropeller;
import race.vehicle.parts.PowerTire;
import race.vehicle.parts.Propeller;
import race.vehicle.parts.Tire;

/**
 * 継承、実装の問題
 *
 */
public class Problem11 {

	/**
	 * 様々な部品を組み合わせ、ボートレースをする問題
	 * @param args
	 */
	public static void main(String args[]) {

		// 1台目のボートを作る
		Engine engine01 = new NormalEngine();
		Propeller propeller01 = new NormalPropeller();
		Boat boat01 = new NormalBoat(engine01, propeller01, "01");

		Driver driver01 = new NormalDriver();
		boat01.ride(driver01);
		boat01.setFuel(100);

		// 2台目のボートを作る
		Engine engine02 = new NormalEngine();
		Propeller propeller02 = new PowerPropeller();
		Boat boat02 = new FastBoat(engine02, propeller02, "02");

		Driver driver02 = new NormalDriver();
		boat02.ride(driver02);
		boat02.setFuel(100);

		// 3台目のボートを作る
		Engine engine03 = new PowerEngine();
		Propeller propeller03 = new NormalPropeller();
		Boat boat03 = new NormalBoat(engine03, propeller03, "03");

		Driver driver03 = new ExtremeDriver();
		boat03.ride(driver03);
		boat03.setFuel(100);

		//List<Boat> boatList = Arrays.asList(boat01, boat02, boat03);

		// レースの走行距離
		int mileage = 100;

		//rase(boatList, mileage);
		//graphicalRace(boatList, mileage);

		/* -- ここから問題 -- */
		/*
		 * エンジン、プロペラ、ボート、ドライバーを追加しレースをせよ
		 */
		// 以下回答
		Engine engine04 = new PowerEngine();
		Propeller propeller04 = new PowerPropeller();
		Boat boat04 = new FastBoat(engine04, propeller04, "04");

		Driver driver04 = new ExtremeDriver();
		boat04.ride(driver04);
		boat04.setFuel(50);

		List<Boat> boatList2 = Arrays.asList(boat01, boat02, boat03, boat04);

		System.out.println("エンジン、プロペラ、ボート、ドライバーを追加しレースをする");
		//rase(boatList2, mileage);
		graphicalRace(boatList2, mileage);

		/*
		 * レースの走行距離が長い場合、燃料が切れてレースが終わらない。
		 * 参加しているボートの燃料が全て切れた場合、レースを中断する
		 * ように修正せよ
		 */

		/*
		 * 出力結果のが以下となるようなgraphicalRaceメソッドを作成せよ
		 * ・現状の出力イメージ
		 * 01が3進みました
		 * 02が0進みました
		 * 03が13進みました
		 * 01がトータルで3進みました
		 * 02がトータルで0進みました
		 * 03がトータルで13進みました
		 * ～繰り返し～
		 *
		 * ・新しい出力イメージ
		 * ==================================================|ゴール
		 * >>>01
		 * 02
		 * >>>>>>>>>>>>>03
		 * ～繰り返し～
		 */

		/*
		 * コンストラクタに以下を持つ抽象クラスcarを作成し、
		 * レースを実施せよ
		 * コンストラクタ
		 * ・エンジン(既存インターフェースを使用)
		 * ・タイヤ(新規インターフェースを作成)
		 * ・車体番号
		 */

		Engine engineCar01 = new NormalEngine();
		Tire tireCar01 = new NormalTire();
		Car car01 = new NormalCar(engineCar01, tireCar01, "car01");

		Driver driverCar01 = new ExtremeDriver();
		car01.ride(driverCar01);
		car01.setFuel(100);

		Engine engineCar02 = new NormalEngine();
		Tire tireCar02 = new PowerTire();
		Car car02 = new NormalCar(engineCar02, tireCar02, "car02");

		Driver driverCar02 = new SuperDriver();
		car02.ride(driverCar02);
		car02.setFuel(100);

		Engine engineCar03 = new PowerEngine();
		Tire tireCar03 = new NormalTire();
		Car car03 = new FastCar(engineCar03, tireCar03, "car03");

		Driver driverCar03 = new NormalDriver();
		car03.ride(driverCar03);
		car03.setFuel(100);

		List<Car> carList = Arrays.asList(car01, car02, car03);

		// レースの走行距離
		int carMileage = 50;

		System.out.println("\nCarでレースをする");
		graphicalCarRace(carList, carMileage);
	}

	/**
	 * レースを実施する
	 * @param list 出場車リスト
	 * @param distance 距離
	 */
	public static void rase(List<Boat> list, int distance) {
		// 出場車のリストを表示する
		list.stream().forEach(boat -> boat.outputInfo());

		// それぞれのボートが進んだ距離を保持するマップを作成する
		Map<String, Integer> distanceMap = list.stream()
				.collect(Collectors.toMap(
						(Boat s) -> s.getBoatName(), // キーをボートの番号にする
						(Boat s) -> 0)); // 値は進んだ距離のため0固定にする

		boolean isRace = true;

		// どれかがゴールするまで続ける
		do {
			// 参加しているボートの燃料が全て切れた場合、レースを中断する
			int fuelFlg = 0;
			for (Boat boat : list) {
				/* ---------- 燃料すべて0対応 START ---------- */
				if (boat.getFuel() == 0) {
					fuelFlg++;
					if (fuelFlg == list.size()) {
						isRace = false;
						System.out.println("ボートの燃料が全て切れたので、レースを中断します");
						break;
					}
				}
				/* ---------- 燃料すべて0対応 END ---------- */
				int addDistance = boat.drive();
				System.out.println(boat.getBoatName() + "が" + addDistance + "進みました");

				// 進んだ距離をマップに設定する
				int curDistance = distanceMap.get(boat.getBoatName());
				distanceMap.put(boat.getBoatName(), curDistance + addDistance);
			}

			// 進んだ距離の累計とゴール判定
			for (String key : distanceMap.keySet()) {
				// 進んだ距離の累計を取得
				int curDistance = distanceMap.get(key);
				System.out.println(key + "がトータルで" + curDistance + "進みました");

				if (curDistance > distance) {
					isRace = false;
				}
			}
		} while (isRace);

		// 結果を出力
		judge(distanceMap);
	}

	/**
	 * 走行距離から着順を決める
	 * @param result レース結果
	 */
	private static void judge(Map<String, Integer> result) {

		List<String> rankList = new ArrayList<>();

		// 引数のマップを走行距離で降順ソートし、キーをリストに詰める
		result.entrySet().stream()
				.sorted(Collections.reverseOrder(Map.Entry.comparingByValue())) //MAPのバリュー(走行距離)で降順ソート
				.forEach(s -> {
					rankList.add(s.getKey());
				});

		int rank = 0;
		int prevDistance = 0;

		System.out.println("=========== 結果発表 ===========");

		// 順位判定をおこなう
		for (int i = 0; i < rankList.size(); i++) {
			int curDistance = result.get(rankList.get(i));

			// 前の走行距離と同じ場合は順位は変えない
			if (curDistance != prevDistance) {
				rank++;
			}
			System.out.println(rank + "位" + rankList.get(i));

			prevDistance = curDistance;

		}

	}

	/**
	 * レース状況を視覚的に表示しながら実施する。
	 * @param list 出場車リスト
	 * @param distance 距離
	 */
	public static void graphicalRace(List<Boat> list, int distance) {
		// ゴールまでの距離を示す部分を作成
		//String goal = "|ゴール";
		/*    	for (int i = 0; i < distance; i++) {
		    		goal = "=" + goal;
		    	}*/
		String goal = String.join("", Stream.generate(() -> "=").limit(distance).collect(Collectors.toList())) + "|ゴール";

		// 出場車のリストを表示する
		list.stream().forEach(boat -> boat.outputInfo());

		// それぞれのボートが進んだ距離を保持するマップを作成する
		Map<String, Integer> distanceMap = list.stream()
				.collect(Collectors.toMap(
						(Boat s) -> s.getBoatName(), // キーをボートの番号にする
						(Boat s) -> 0)); // 値は進んだ距離のため0固定にする

		boolean isRace = true;

		// どれかがゴールするまで続ける
		do {
			// 参加しているボートの燃料が全て切れた場合、レースを中断する
			int fuelFlg = 0;
			for (Boat boat : list) {
				/* ---------- 燃料すべて0対応 START ---------- */
				if (boat.getFuel() == 0) {
					fuelFlg++;
					if (fuelFlg == list.size()) {
						isRace = false;
						System.out.println("ボートの燃料が全て切れたので、レースを中断します");
						break;
					}
				}
				/* ---------- 燃料すべて0対応 END ---------- */
				int addDistance = boat.drive();

				// 進んだ距離をマップに設定する
				int curDistance = distanceMap.get(boat.getBoatName());
				distanceMap.put(boat.getBoatName(), curDistance + addDistance);
			}

			// 進んだ距離を視覚的に表示
			System.out.println(goal);

			/*
						list.stream()
								.map((Boat s) -> String.join("",
										Stream.generate(() -> ">").limit(distanceMap.get(s.getBoatName()))
												.collect(Collectors.toList()))
										+ s.getBoatName())
								.forEach(System.out::println);
			*/

			// 進んだ距離の累計とゴール判定
			for (String key : distanceMap.keySet()) {
				// 進んだ距離の累計を取得
				int curDistance = distanceMap.get(key);
				// 進んだ距離を示すための文字列を生成
				String graphicalMileage = String.join("",
						Stream.generate(() -> ">").limit(curDistance).collect(Collectors.toList()));
				// ボートのListから、keyとボートの名前が一致するものを探して、進んだ距離とボート名を連結して出力
				list.stream().filter(s -> s.getBoatName() == key).map(s -> graphicalMileage + s.getBoatName())
						.forEach(System.out::println);

				if (curDistance > distance) {
					isRace = false;
				}
			}
		} while (isRace);

		// 結果を出力
		judge(distanceMap);

	}

	public static void graphicalCarRace(List<Car> list, int distance) {
		// ゴールまでの距離を示す部分を作成
		String goal = String.join("", Stream.generate(() -> "=").limit(distance).collect(Collectors.toList())) + "|ゴール";

		// 出場車のリストを表示する
		list.stream().forEach(car -> car.outputInfo());

		// それぞれのボートが進んだ距離を保持するマップを作成する
		Map<String, Integer> distanceMap = list.stream()
				.collect(Collectors.toMap(
						(Car s) -> s.getCarName(), // キーをボートの番号にする
						(Car s) -> 0)); // 値は進んだ距離のため0固定にする

		boolean isRace = true;

		// どれかがゴールするまで続ける
		do {
			// 参加しているボートの燃料が全て切れた場合、レースを中断する
			int fuelFlg = 0;
			for (Car car : list) {
				/* ---------- 燃料すべて0対応 START ---------- */
				if (car.getFuel() == 0) {
					fuelFlg++;
					if (fuelFlg == list.size()) {
						isRace = false;
						System.out.println("ボートの燃料が全て切れたので、レースを中断します");
						break;
					}
				}
				/* ---------- 燃料すべて0対応 END ---------- */
				int addDistance = car.drive();

				// 進んだ距離をマップに設定する
				int curDistance = distanceMap.get(car.getCarName());
				distanceMap.put(car.getCarName(), curDistance + addDistance);
			}

			// 進んだ距離を視覚的に表示
			System.out.println(goal);

			// 進んだ距離の累計とゴール判定
			for (String key : distanceMap.keySet()) {
				// 進んだ距離の累計を取得
				int curDistance = distanceMap.get(key);
				// 進んだ距離を示すための文字列を生成
				String graphicalMileage = String.join("",
						Stream.generate(() -> ">").limit(curDistance).collect(Collectors.toList()));
				// ボートのListから、keyとボートの名前が一致するものを探して、進んだ距離とボート名を連結して出力
				list.stream().filter(s -> s.getCarName() == key).map(s -> graphicalMileage + s.getCarName())
						.forEach(System.out::println);

				if (curDistance > distance) {
					isRace = false;
				}
			}
		} while (isRace);

		// 結果を出力
		judge(distanceMap);

	}
}
