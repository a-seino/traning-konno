package race.wrap;

public class TopWrapper implements StringWrapper {

	@Override
	public String wrap(String target, char wrapper) {
		// 改行コードで分割
		String[] targetLine = target.split(System.lineSeparator());

		StringBuilder retBuilder = new StringBuilder();

		int maxSize = 0;

		// 入力された文字列の最大文字数を調べる
		for (int i = 0; i < targetLine.length; i++) {
			// 各行の最も長い文字数をベースにラッピング数を決める
			int curSize = getWrappingCount(targetLine[i]);
			if (maxSize < curSize) {
				maxSize = curSize;
			}
		}

		// 最大文字数+2字分の指定文字を出力
		for (int i = 0; i < maxSize + 2; i++) {
			retBuilder.append(wrapper);
		}

		return retBuilder.toString();
	}
}
