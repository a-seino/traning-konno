package hierarchy.rides;

/**
 *  普通車を表すクラスStandardCarクラス
 * ※普通車は1回につき80km進むものとする
 * @author 紺野由夏
 *
 */
public class StandardCar implements Car {
	// 走行距離
	private static final int speed = 80;

	/**
	 * 乗り物で走るメソッド
	 * @param carModel 車の種類
	 */
	public void run(String carModel) {
		System.out.println(carModel + "で走ります");
	}

	public int carRun() {
		System.out.println("普通車で" + speed + "km進みました");
		return speed;
	}
}
