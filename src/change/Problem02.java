package change;

import java.util.ArrayList;
import java.util.List;

import util.DateUtil;
import util.NumericUtil;

/**
 * ランダムで生成した数値を並び変える問題
 * @param args
 */
public class Problem02 {

	/**
	 * ランダムで生成した数値を振り分ける問題
	 * @param args
	 */
	public static void main(String[] args) {

		/*
		 * ランダムに生成した数値のリストについて
		 * バブルソートを使用して並び替えを実施したい。
		 * 途中まで実装してあるため、残りを実装せよ
		 */

		// テスト用のランダムデータを作成
		List<Integer> testList = NumericUtil.makeRandomList(10, 10);

		/* -- ここから問題 -- */
		// バブルソートを使用して昇順ソート(テストデータは使いまわすため、コピーして使用)
		//bubbleSortAsc(new ArrayList<Integer>(testList));

		// バブルソートを使用して降順ソート(テストデータは使いまわすため、コピーして使用)
		//bubbleSortDesc(new ArrayList<Integer>(testList));

		// 他にどのようなソートアルゴリズムがあるか調べ、作成せよ
		// 選択ソートを使用して昇順ソート
		//selectionSortAsc(new ArrayList<Integer>(testList));
		// 選択ソートを使用して降順ソート
		//selectionSortDesc(new ArrayList<Integer>(testList));
		// 挿入ソートを使用して昇順ソート
		//insertionSortAsc(new ArrayList<Integer>(testList));
		// 挿入ソートを使用して降順ソート
		//insertionSortDesc(new ArrayList<Integer>(testList));
		// クイックソートを使用して昇順ソート
		//quickSortAsc(new ArrayList<Integer>(testList));
		// クイックソートを使用して降順ソート
		//quickSortDesc(new ArrayList<Integer>(testList));
		// マージソートを使用して昇順ソート
		//mergeSortAsc(new ArrayList<Integer>(testList));
		// マージソートを使用して降順ソート
		mergeSortDesc(new ArrayList<Integer>(testList));
	}

	/**
	 * 引数で受け取ったリストをバブルソートで昇順に並び替えたものを出力する。
	 * @param testList 並び替え対象リスト
	 * @return 並び替えたリスト
	 */
	public static void bubbleSortAsc(List<Integer> testList) {

		// 処理時間測定用に開始時間を取得
		long startTime = System.currentTimeMillis();

		// 値を入れ替えるための一時変数
		int tmpData;

		for (int i = 0; i < testList.size(); i++) {
			for (int j = i + 1; j < testList.size(); j++) {

				/*-- 問題 ここのif文内の「true」を変更して実装せよ--*/
				if (testList.get(i) > testList.get(j)) {
					tmpData = testList.get(i);
					testList.set(i, testList.get(j));
					testList.set(j, tmpData);
				}

				checkSort(testList, i, j);
			}
		}

		//		testList.stream()
		//				.forEach(System.out::println);

		// 処理時間測定用に終了時間を取得
		long endTime = System.currentTimeMillis();

		// 処理時間を出力
		System.out.println("処理時間:" + DateUtil.getProcTime(startTime, endTime));
		System.out.println("------------------------------------------");

	}

	/** ソート中のリストの状態を出力し、入れ替え判定中の要素には"*"を付与する
	* @param testList 出力対象リスト
	* @param tgtIndex1 入替判定中インデックス
	* @param tgtIndex2 入替判定中インデックス
	*/
	public static void checkSort(List<Integer> testList, int tgtIndex1, int tgtIndex2) {

		for (int i = 0; i < testList.size(); i++) {

			System.out.print("[");
			if (i == tgtIndex1 || i == tgtIndex2) {
				System.out.print("*" + testList.get(i));
			} else {
				System.out.print(testList.get(i));
			}
			System.out.print("]");
		}
		System.out.println("");

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public static void bubbleSortDesc(List<Integer> testList) {
		// 以下回答
		// 処理時間測定用に開始時間を取得
		long startTime = System.currentTimeMillis();

		// 値を入れ替えるための一時変数
		int tmpData;

		for (int i = 0; i < testList.size(); i++) {
			for (int j = i + 1; j < testList.size(); j++) {
				if (testList.get(i) < testList.get(j)) {
					tmpData = testList.get(i);
					testList.set(i, testList.get(j));
					testList.set(j, tmpData);
				}
				// ソート中のリストの状態確認
				checkSort(testList, i, j);
			}
		}

		// 処理時間測定用に終了時間を取得
		long endTime = System.currentTimeMillis();

		// 処理時間を出力
		System.out.println("処理時間:" + DateUtil.getProcTime(startTime, endTime));
		System.out.println("------------------------------------------");
	};

	// 以下、その他のソートアルゴリズムを使用

	/**
	 * 引数で受け取ったリストを選択ソートで昇順に並び替えたものを出力する。
	 * @param testList 並び替え対象リスト
	 * @return 並び替えたリスト
	 */
	public static void selectionSortAsc(List<Integer> testList) {
		System.out.println("選択ソート昇順");
		// 処理時間測定用に開始時間を取得
		long startTime = System.currentTimeMillis();

		// 最小値を保持するための変数
		int indexMin;
		// 値を入れ替えるための一時変数
		int tmpData;

		for (int i = 0; i < testList.size() - 1; i++) {
			indexMin = i;

			for (int j = i + 1; j < testList.size(); j++) {
				if (testList.get(j) < testList.get(indexMin)) {
					indexMin = j;
				}
			}
			tmpData = testList.get(i);
			testList.set(i, testList.get(indexMin));
			testList.set(indexMin, tmpData);

			// ソート中のリストの状態確認
			checkSort(testList, i, indexMin);
		}
		// 処理時間測定用に終了時間を取得
		long endTime = System.currentTimeMillis();

		// 処理時間を出力
		System.out.println("処理時間:" + DateUtil.getProcTime(startTime, endTime));
		System.out.println("------------------------------------------");
	}

	/**
	 * 引数で受け取ったリストを選択ソートで降順に並び替えたものを出力する。
	 * @param testList 並び替え対象リスト
	 * @return 並び替えたリスト
	 */
	public static void selectionSortDesc(List<Integer> testList) {
		System.out.println("選択ソート降順");
		// 処理時間測定用に開始時間を取得
		long startTime = System.currentTimeMillis();

		// 最小値の要素番号を保持するための変数
		int indexMax;
		// 値を入れ替えるための一時変数
		int tmpData;

		for (int i = 0; i < testList.size() - 1; i++) {
			indexMax = i;

			for (int j = i + 1; j < testList.size(); j++) {
				if (testList.get(j) > testList.get(indexMax)) {
					indexMax = j;
				}
			}
			tmpData = testList.get(i);
			testList.set(i, testList.get(indexMax));
			testList.set(indexMax, tmpData);

			// ソート中のリストの状態確認
			checkSort(testList, i, indexMax);
		}
		// 処理時間測定用に終了時間を取得
		long endTime = System.currentTimeMillis();

		// 処理時間を出力
		System.out.println("処理時間:" + DateUtil.getProcTime(startTime, endTime));
		System.out.println("------------------------------------------");
	}

	// 以下7月3日実施
	/**
	 * 引数で受け取ったリストを挿入ソートで昇順に並び替えたものを出力する。
	 * @param testList 並び替え対象リスト
	 * @return 並び替えたリスト
	 */

	public static void insertionSortAsc(List<Integer> testList) {
		System.out.println("挿入ソート昇順");
		// 処理時間測定用に開始時間を取得
		long startTime = System.currentTimeMillis();

		// 値を入れ替えるための一時変数
		int tmpData;
		// 1番目の要素から、ひとつ前の要素と比べて並び替えを行う
		for (int i = 1; i < testList.size(); i++) {
			for (int j = i - 1; j >= 0 && testList.get(j) > testList.get(j + 1); j--) {
				tmpData = testList.get(j);
				testList.set(j, testList.get(j + 1));
				testList.set(j + 1, tmpData);

				// ソート中のリストの状態確認
				checkSort(testList, i, j);
			}
		}
		// 処理時間測定用に終了時間を取得
		long endTime = System.currentTimeMillis();

		// 処理時間を出力
		System.out.println("処理時間:" + DateUtil.getProcTime(startTime, endTime));
		System.out.println("------------------------------------------");
	}

	/**
	 * 引数で受け取ったリストを挿入ソートで降順に並び替えたものを出力する。
	 * @param testList 並び替え対象リスト
	 * @return 並び替えたリスト
	 */

	public static void insertionSortDesc(List<Integer> testList) {
		System.out.println("挿入ソート降順");
		// 処理時間測定用に開始時間を取得
		long startTime = System.currentTimeMillis();

		// 値を入れ替えるための一時変数
		int tmpData;
		// 1番目の要素から、ひとつ前の要素と比べて並び替えを行う
		for (int i = 1; i < testList.size(); i++) {
			int j = i - 1;
			while (j >= 0 && testList.get(j) < testList.get(j + 1)) {
				tmpData = testList.get(j);
				testList.set(j, testList.get(j + 1));
				testList.set(j + 1, tmpData);
				j--;

				// ソート中のリストの状態確認
				checkSort(testList, i, j);
			}
		}
		// 処理時間測定用に終了時間を取得
		long endTime = System.currentTimeMillis();

		// 処理時間を出力
		System.out.println("処理時間:" + DateUtil.getProcTime(startTime, endTime));
		System.out.println("------------------------------------------");
	}

	/**
	 * 引数で受け取ったリストをクイックソートで昇順に並び替えたものを出力する。
	 * @param testList 並び替え対象リスト
	 * @return 並び替えたリスト
	 */
	public static void quickSortAsc(List<Integer> testList) {
		System.out.println("クイックソート昇順");
		// testListの要素数は10で固定のため、今回は要素数の判定は割愛
		// 処理時間測定用に開始時間を取得
		long startTime = System.currentTimeMillis();
		// クイックソート処理を呼び出し
		quickSortAsc(testList, 0, testList.size() - 1);

		// 処理時間測定用に終了時間を取得
		long endTime = System.currentTimeMillis();

		// 処理時間を出力
		System.out.println("処理時間:" + DateUtil.getProcTime(startTime, endTime));
		System.out.println("------------------------------------------");
	}

	public static void quickSortAsc(List<Integer> testList, int start, int end) {
		// ソートの軸になる要素の要素番号を保持する変数
		int pivot;
		// 要素の位置を保持する変数
		int s, e;
		// 値を入れ替えるための一時変数
		int tmpData;

		// 再帰処理の終了条件：分割した配列の要素数が1個以下になったら
		if (start >= end) {
			return;
		}

		// startとendの差が同じ＝隣同士の場合は入れ替え
		if ((end - start) == 0) {
			if (testList.get(start) > testList.get(end)) {
				tmpData = testList.get(start);
				testList.set(start, testList.get(end));
				testList.set(end, tmpData);
				// ソート中のリストの状態確認
				checkSort(testList, start, end);
			}
			return;
		}

		// 中央にある要素を基準値にする
		pivot = testList.get((start + end) / 2);
		// 基準値をもとに値の並び替えを行う
		s = start - 1;
		e = end + 1;

		while (true) {
			// sをインクリメントし、基準値以上の値を探す
			while (true) {
				s++;
				if (testList.get(s) >= pivot)
					break; // 処理一行のみなら中かっこ省略可
			}
			// eをデクリメントし、基準値以下の値を探す
			while (true) {
				e--;
				if (testList.get(e) <= pivot)
					break;
			}

			// sがe以上なら終了(基準値をもとにした並び替えが終わった状態)
			if (s >= e)
				break;

			// 値の入れ替え
			tmpData = testList.get(s);
			testList.set(s, testList.get(e));
			testList.set(e, tmpData);
			// ソート中のリストの状態確認
			checkSort(testList, s, e);
		}

		// sとeが同じならsをインクリメント
		if (s == e)
			s++;

		// ブロックの分割
		quickSortAsc(testList, start, e);
		quickSortAsc(testList, s, end);
	}

	/**
	 * 引数で受け取ったリストをクイックソートで降順に並び替えたものを出力する。
	 * @param testList 並び替え対象リスト
	 * @return 並び替えたリスト
	 */
	public static void quickSortDesc(List<Integer> testList) {
		System.out.println("クイックソート降順");
		// 処理時間測定用に開始時間を取得
		long startTime = System.currentTimeMillis();

		// クイックソート処理の呼び出し
		quickSortDesc(testList, 0, testList.size() - 1);

		// 処理時間測定用に終了時間を取得
		long endTime = System.currentTimeMillis();

		// 処理時間を出力
		System.out.println("処理時間:" + DateUtil.getProcTime(startTime, endTime));
		System.out.println("------------------------------------------");
	}

	public static void quickSortDesc(List<Integer> testList, int start, int end) {
		// ソートの軸になる要素の要素番号を保持する変数
		int pivot;
		// 要素の位置を保持する変数
		int s, e;
		// 値を入れ替えるための一時変数
		int tmpData;

		// 再帰処理の終了条件：分割した配列の要素数が1個以下になったら
		if (start >= end) {
			return;
		}

		// startとendの差が同じ＝隣同士の場合は入れ替え
		if ((end - start) == 0) {
			if (testList.get(start) < testList.get(end)) {
				tmpData = testList.get(start);
				testList.set(start, testList.get(end));
				testList.set(end, tmpData);
				// ソート中のリストの状態確認
				checkSort(testList, start, end);
			}
			return;
		}

		// 中央にある要素を基準値にする
		pivot = testList.get((start + end) / 2);
		// 基準値をもとに値の並び替えを行う
		s = start - 1;
		e = end + 1;

		while (true) {
			// sをインクリメントし、基準値以下の値を探す
			while (true) {
				s++;
				if (testList.get(s) <= pivot) {
					break;
				}
			}
			// eをデクリメントし、基準値以上の値を探す
			while (true) {
				e--;
				if (testList.get(e) >= pivot) {
					break;
				}
			}

			// sがe以上なら終了(基準値をもとにした並び替えが終わった状態)
			if (s >= e)
				break;

			// 値の入れ替え
			tmpData = testList.get(s);
			testList.set(s, testList.get(e));
			testList.set(e, tmpData);
			// ソート中のリストの状態確認
			checkSort(testList, s, e);
		}

		// sとeが同じならsをインクリメント
		if (s == e)
			s++;

		// ブロックの分割
		quickSortDesc(testList, start, e);
		quickSortDesc(testList, s, end);
	}

	// クイックソートを再帰処理なしでできるか？
	public static void testQuickSort(List<Integer> testList) {
		// ソートの軸になる要素の要素番号を保持する変数
		int pivot;
		// 要素の位置を保持する変数
		int s, e;

		s = 0;
		e = 9;
		pivot = testList.get(4);


		while (s >= e) {
			while (true) {
				if (testList.get(s) <= pivot) { break; }
				s++;
			}
			while (true) {
				if (testList.get(e) >= pivot) { break; }
				e--;
			}

			int tmpData = testList.get(s);
			testList.set(s, testList.get(e));
			testList.set(e, tmpData);
			// ソート中のリストの状態確認
			checkSort(testList, s, e);

			if (s == e) { s++; }
		}
	}
	// クイックソートを再帰処理なしでできるか？ここまで


	/**
	 * 引数で受け取ったリストをマージソートで昇順に並び替えたものを出力する。
	 * @param testList 並び替え対象リスト
	 * @return 並び替えたリスト
	 */
	public static void mergeSortAsc(List<Integer> testList) {
		System.out.println("マージソート昇順");
		// 処理時間測定用に開始時間を取得
		long startTime = System.currentTimeMillis();

		// マージソート処理の呼び出し
		List<Integer> subList = new ArrayList<>(testList);
		mergeSortAsc(testList, subList, 0, testList.size() - 1);

		// 処理時間測定用に終了時間を取得
		long endTime = System.currentTimeMillis();

		// 処理時間を出力
		System.out.println("処理時間:" + DateUtil.getProcTime(startTime, endTime));
		System.out.println("------------------------------------------");
	}

	public static void mergeAsc(List<Integer> list, List<Integer> auxList, int low, int mid, int high) {
		// 要素番号として使う変数を用意
		int k = low;
		int i = low;
		int j = mid + 1;

		// 要素番号iとjの間に要素があるとき→iはリストの前半、jはリストの後半
		while (i <= mid && j <= high) {
			// 小さい順にコピーしていく
			if (list.get(i) <= list.get(j)) {
				auxList.set(k++, list.get(i++)); // k、iで要素番号を指定してからそれぞれをインクリメント
				// ソート中のリストの状態確認
				checkSort(auxList, k - 1, i - 1);
			} else {
				auxList.set(k++, list.get(j++));
				// ソート中のリストの状態確認
				checkSort(auxList, k - 1, j - 1);
			}
		}
		// 残りの要素をコピー
		// 1つ前のwhile文でmid以降(要素番号j)はコピーが終わっている→判定はiのみ
		while (i <= mid) {
			auxList.set(k++, list.get(i++));
			// ソート中のリストの状態確認
			checkSort(auxList, k - 1, i - 1);
		}

		// ソートされた順序を元のリストにコピーして反映
		for (i = low; i <= high; i++) {
			list.set(i, auxList.get(i));
		}
	}

	public static void mergeSortAsc(List<Integer> list, List<Integer> auxList, int low, int high) {
		// 対象リストの要素数が1以下の場合
		if (high <= low) {
			return;
		}

		// リストの中点を探す シフト演算子から書き換え
		int mid = low + (high - low) / 2;

		// 要素数が1になるまでリストを再帰的に分割→マージ
		mergeSortAsc(list, auxList, low, mid); // 左半分の処理
		mergeSortAsc(list, auxList, mid + 1, high); // 右半分の処理
		mergeAsc(list, auxList, low, mid, high); // 2つをマージ
	}

	/**
	 * 引数で受け取ったリストをマージソートで降順に並び替えたものを出力する。
	 * @param testList 並び替え対象リスト
	 * @return 並び替えたリスト
	 */
	public static void mergeSortDesc(List<Integer> testList) {
		System.out.println("マージソート降順");
		// 処理時間測定用に開始時間を取得
		long startTime = System.currentTimeMillis();

		// マージソート処理の呼び出し
		List<Integer> subList = new ArrayList<>(testList);
		mergeSortDesc(testList, subList, 0, testList.size() - 1);

		// 処理時間測定用に終了時間を取得
		long endTime = System.currentTimeMillis();

		// 処理時間を出力
		System.out.println("処理時間:" + DateUtil.getProcTime(startTime, endTime));
		System.out.println("------------------------------------------");
	}

	public static void mergeDesc(List<Integer> list, List<Integer> auxList, int low, int mid, int high) {
		// 要素番号として使う変数を用意
		int k = low;
		int i = low;
		int j = mid + 1;

		// 要素番号iとjの間に要素があるとき→iはリストの前半、jはリストの後半
		while (i <= mid && j <= high) {
			// 小さい順にコピーしていく
			if (list.get(i) >= list.get(j)) {
				auxList.set(k++, list.get(i++)); // k、iで要素番号を指定してからそれぞれをインクリメント
				// ソート中のリストの状態確認
				checkSort(auxList, k - 1, i - 1);
			} else {
				auxList.set(k++, list.get(j++));
				// ソート中のリストの状態確認
				checkSort(auxList, k - 1, j - 1);
			}
		}
		// 残りの要素をコピー
		// 1つ前のwhile文でmid以降(要素番号j)はコピーが終わっている→判定はiのみ
		while (i <= mid) {
			auxList.set(k++, list.get(i++));
			// ソート中のリストの状態確認
			checkSort(auxList, k - 1, i - 1);
		}

		// ソートされた順序を元のリストにコピーして反映
		for (i = low; i <= high; i++) {
			list.set(i, auxList.get(i));
		}
	}

	public static void mergeSortDesc(List<Integer> list, List<Integer> auxList, int low, int high) {
		// 対象リストの要素数が1以下の場合
		if (high <= low) {
			return;
		}

		// リストの中点を探す シフト演算子から書き換え
		int mid = low + (high - low) / 2;

		// 要素数が1になるまでリストを再帰的に分割→マージ
		mergeSortDesc(list, auxList, low, mid); // 左半分の処理
		mergeSortDesc(list, auxList, mid + 1, high); // 右半分の処理
		mergeDesc(list, auxList, low, mid, high); // 2つをマージ
	}


}
