package race.wrap;

import java.util.Arrays;

/**
 *入力文字列の上/左右/下をラッピングするクラス
 * @author 紺野由夏
 *
 */
public class AllWrapper implements StringWrapper {

	@Override
	public String wrap(String target, char wrapper) {
		// 改行コードで分割
		String[] targetLine = target.split(System.lineSeparator());
		StringBuilder retBuilder = new StringBuilder();

		// 最大文字数を調べる
		int maxSize = getMaxSize(targetLine);

		// 上部・下部のラッピング用文字列を用意
		String TopBottomWrap = TopBottomWrapper(maxSize, wrapper);

		// 上部のラッピングを設定
		retBuilder.append(TopBottomWrap + System.lineSeparator());

		// 左右のラッピングを設定
		Arrays.stream(targetLine).map(s -> wrapper + s + wrapper)
				.forEach(s -> retBuilder.append(s + System.lineSeparator()));

		// 下部のラッピングを設定
		retBuilder.append(TopBottomWrap);

		return retBuilder.toString();
	}

	/**
	 * 上部・下部のラッピングで使う文字列を返す
	 * @param maxSize ラッピング対象文字列の最大文字数
	 * @param wrapper ラッピングする文字列
	 * @return 上部・下部のラッピングで使う文字列
	 */
	String TopBottomWrapper(int maxSize, char wrapper) {
		StringBuilder retBuilder = new StringBuilder();
		// 最大文字数+2字分の指定文字を設定
		for (int i = 0; i < maxSize + 2; i++) {
			retBuilder.append(wrapper);
		}

		return retBuilder.toString();
	}
}
