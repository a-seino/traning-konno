package race.wrap;

import java.util.Comparator;
import java.util.stream.Stream;

import util.StringUtil;

/**
 * 文字列をラップするためのインターフェース
 *
 */
public interface StringWrapper {

	/**
	 * 文字列をラッピングして返却する
	 * @param target ラッピング対象文字列
	 * @param wrapString ラッピングする文字列
	 * @return ラッピングされた文字列
	 */
	String wrap(String target, char wrapper);

	/**
	 * ラッピングするのに必要な文字数を返却する。
	 * @param target ラッピング対象文字数
	 * @return ラッピング文字数
	 */
	default int getWrappingCount(String target) {

		int retCount = 0;

		// ascii文字数の取得
		int asciiCount = StringUtil.countAscii(target);

		// ascii文字数以外の数の取得
		int noAsciiCount = target.toCharArray().length - asciiCount;

		// ascii文字はそのままカウントする
		retCount += asciiCount;

		// ascii以外の文字は2倍でカウントする
		retCount += noAsciiCount * 2;

		return retCount;

	}

	/**
	 * ラッピング対象の文字列の中で最長の文字列の文字数を返す
	 * @param targetLine 標準入力で受け取った文字列のリスト
	 * @return ラッピング対象文字列の最大文字数
	 */
	default int getMaxSize(String[] targetLine) {
		/*		// 最大の文字数を保持する変数
				int maxSize = 0;
				// 標準入力から受け取った文字列の中から、最大の文字数を探す
				for (int i = 0; i < targetLine.length; i++) {
					if (targetLine[i].length() > maxSize) {
						maxSize = targetLine[i].length();
					}
				}


				return maxSize;
		*/

		// Streamのmaxメソッドで最長の文字列を探す→Optionalのgetメソッドで文字列を取得→文字数を返す
		return Stream.of(targetLine).max(Comparator.comparing(String::length)).get().length();
	}

}
