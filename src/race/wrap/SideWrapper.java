package race.wrap;

/**
 * 文字列の横を包むクラス
 *
 */
public class SideWrapper implements StringWrapper {

	@Override
	public String wrap(String target, char wrapper) {

		// 改行コードで分割
		String[] targetLine = target.split(System.lineSeparator());

		StringBuilder retBuilder = new StringBuilder();

		// 最大の文字数を保持する変数
		int maxSize = 0;
		// 標準入力から受け取った文字列の中から、最大の文字数を探す
		for (int i = 0; i < targetLine.length; i++) {
			if (targetLine[i].length() > maxSize) {
				maxSize = targetLine[i].length();
			}
		}

		// 各行にラッピング文字を設定
		for (int i = 0; i < targetLine.length; i++) {
			retBuilder.append(wrapper);
			retBuilder.append(targetLine[i]);
			// 最大文字数に合わせて空白を後ろにつける
			if (targetLine[i].length() < maxSize) {
				int whitespaceSize = maxSize - targetLine[i].length();
				for (int j = 0; j < whitespaceSize; j++) {
					retBuilder.append(" ");
				}
			}
			retBuilder.append(wrapper);

			// 改行コードで分割して要素数が2つ以上の時で最後の要素以外の時に改行を戻し入れる
			if (2 <= targetLine.length && i < targetLine.length - 1) {
				retBuilder.append(System.lineSeparator());
			}

			// 各行の最も長い文字数をベースにラッピング数を決める
			int curSize = getWrappingCount(targetLine[i]);
			if (maxSize < curSize) {
				maxSize = curSize;
			}
		}
		return retBuilder.toString();
	}

}
