package hierarchy.life;

/**
 * 課題08 Animalクラスを継承した新たな生物の作成
 * @author 紺野由夏
 *
 */
public class Cat extends Animal {
	public Cat(int lifespan) {
		super(lifespan);
	}

	@Override
	/**
	 * Animalクラスの抽象メソッドをオーバーライド
	 */
	void eat() {
		System.out.println(this.getClass().getSimpleName() + "が魚を食べました");
	}

	@Override
	/**
	 * Creatureクラスの抽象メソッドをオーバーライド
	 */
	void toAct() {
		// 乱数で動作を決める(1～2:子孫が生まれる、3:食べる、それ以外:何もしない)
		int number = getRandomNumber();

		if (2 >= number) {
			Earth.getInstance().birthCreature(new Cat(10));
			Earth.getInstance().birthCreature(new Cat(10));
			System.out.println(this.getClass().getSimpleName() + "は子孫を残す");
		} else if (3 == number) {
			eat();
		} else {
			System.out.println(this.getClass().getSimpleName() + "は何もしない");
		}
	}
}
