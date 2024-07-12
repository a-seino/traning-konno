package hierarchy.sushi;

/**
 * Trayクラスを継承した緑皿クラス
 * @author 紺野由夏
 *
 */
public class GreenTray extends Tray {
	int price = 420;

	@Override
	int getPrice() {
		return this.price;
	}
}
