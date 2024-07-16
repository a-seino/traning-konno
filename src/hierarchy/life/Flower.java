package hierarchy.life;

/**
 * 植物を表す抽象クラス(Plant)を継承した新たな生物を作成
 * @author 紺野由夏
 *
 */
public class Flower extends Plant {
	public Flower(int lifespan) {
		super(lifespan);
	}

	void toAct() {
		// 乱数で動作を決める(1～3:花を咲かせて種子を残す、それ以外:何もしない)
		int number = getRandomNumber();

		if (3 >= number) {
			Earth.getInstance().birthCreature(new Flower(2));
			System.out.println(this.getClass().getSimpleName() + "は花を咲かせて種子を残す");
		} else {
			System.out.println(this.getClass().getSimpleName() + "は何もしない");
		}
	}
}