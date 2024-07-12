package hierarchy.sushi;

/**
 * Trayクラスを継承した黄皿クラス
 * @author 紺野由夏
 *
 */
public class YellowTray extends Tray {
	// 1皿の値段
	int price = 210;

	@Override
	int getPrice() {
		return this.price;
	}
}
