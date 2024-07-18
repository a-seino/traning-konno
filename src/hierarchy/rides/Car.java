package hierarchy.rides;

/**
 *  「乗り物で走る」という「run」メソッド、及び「車で走る」という「carRun」メソッドを
 *  実装するための車を表すCarインターフェースを作成せよ
 * @author 紺野由夏
 *
 */
public interface Car {
	/**
	 * 乗り物で走るメソッド
	 * @param carModel 車の種類
	 */
	abstract void run(String carModel);

	/**
	 * 車で走るメソッド
	 */
	abstract int carRun();
}
