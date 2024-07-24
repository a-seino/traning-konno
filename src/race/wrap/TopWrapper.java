package race.wrap;

/**
 * 入力文字列の上部をラッピングするクラス
 * @author 紺野由夏
 *
 */
public class TopWrapper implements StringWrapper {

	@Override
	public String wrap(String target, char wrapper) {
		// 改行コードで分割
		String[] targetLine = target.split(System.lineSeparator());

		StringBuilder retBuilder = new StringBuilder();

		// 最大文字数を調べる
		int maxSize = getMaxSize(targetLine);

		// 最大文字数+2字分の指定文字を出力
		for (int i = 0; i < maxSize + 2; i++) {
			retBuilder.append(wrapper);
		}

		return retBuilder.toString();
	}
}
