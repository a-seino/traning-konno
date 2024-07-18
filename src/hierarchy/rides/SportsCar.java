package hierarchy.rides;

/**
 * スポーツカーを表すクラスSportsCarクラス
 * ※スポーツカーは1回につき120km進むものとする
 * @author 紺野由夏
 *
 */
public class SportsCar implements Car {
	// 走行距離
	private static final int speed = 120;

	/**
	 * 乗り物で走るメソッド
	 * @param carModel 車の種類
	 */
	public void run(String carModel) {
		System.out.println(carModel + "で走ります");
	}

	/**
	 * スポーツカーで走る
	 */
	public int carRun() {
		System.out.println("スポーツカーで" + speed + "km進みました");
		return speed;
	}
}
