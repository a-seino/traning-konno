package change;

import java.util.List;

import util.NumericUtil;

/**
 * ランダムで生成した数値で勝負をする問題
 * @param args
 */
public class Problem03 {

	/**
	 * ランダムで生成した数値で勝負をする問題
	 * @param args
	 */
	public static void main(String[] args) {

		/*
		 * ユーザAとユーザBで0～15の数値の書いた10枚
		 * のカードを取得し、数値の大小で勝負をする。
		 */

		// ユーザAとBでカードを取得
		List<Integer> cardListA = NumericUtil.makeRandomList(10, 15);
		List<Integer> cardListB = NumericUtil.makeRandomList(10, 15);

		// ユーザAとBで勝負する
		gameComplete(cardListA, cardListB);

		/* -- ここから問題 -- */
		/* 比較するカード数を100枚に増やし、先に3勝した方を勝ちと判断するメソッド(game3Win)
		 * を実装せよ。
		 * 結果には以下を出力すること
		 * ・どちらが勝ったのか
		 * ・3勝の内訳(何戦目で勝ったか)
		 *
		 * */
		// 以下回答
		// ユーザA、Bのカードを用意
		List<Integer> game3WinCardListA = NumericUtil.makeRandomList(100, 15);
		List<Integer> game3WinCardListB = NumericUtil.makeRandomList(100, 15);
		// ユーザAとBで勝負
		game3Win(game3WinCardListA, game3WinCardListB);

		/* 比較するカード数を1000枚に増やし、先に3連勝した方を勝ちと判断するメソッド(game3StraightWin)
		 * を実装せよ。
		 * 結果には以下を出力すること
		 * ・どちらが勝ったのか
		 * ・3勝の内訳(何戦目で勝ったか)
		 *
		 * */

		// 以下回答
		// ユーザA、Bのカードを用意
		List<Integer> game3StraightWinCardListA = NumericUtil.makeRandomList(1000, 15);
		List<Integer> game3StraightWinCardListB = NumericUtil.makeRandomList(1000, 15);
		// ユーザAとBで勝負
		game3StraightWin(game3StraightWinCardListA, game3StraightWinCardListB);
	}

	/**
	 * 引数で与えられたリストの大小比較をおこなう。
	 * @param targetAList ユーザAのリスト
	 * @param targetBList ユーザBのリスト
	 */
	private static void gameComplete(List<Integer> targetAList, List<Integer> targetBList) {

		System.out.println("--------- お互いのカードを大小比較しました。 ---------");

		System.out.println("ユーザAのリスト");
		targetAList.stream().map(s -> "[" + s + "]").forEach(System.out::print);
		System.out.println("");

		System.out.println("ユーザBのリスト");
		targetBList.stream().map(s -> "[" + s + "]").forEach(System.out::print);
		System.out.println("");

		// 要素数に差がある場合、少ない方に合わせて比較をする。
		int gameCount = targetAList.size() > targetBList.size() ? targetBList.size() : targetAList.size();

		int winCountA = 0;
		int winCountB = 0;

		for (int i = 0; i < gameCount; i++) {
			// 大小比較
			if (targetAList.get(i) < targetBList.get(i)) {
				winCountB++;
			} else if (targetBList.get(i) < targetAList.get(i)) {
				winCountA++;
			}
		}

		if (winCountA == winCountB) {
			System.out.println("引き分け");
		} else if (winCountA > winCountB) {
			System.out.println("Aの勝ち");
		} else {
			System.out.println("Bの勝ち");
		}

	}

	/**
	 * 引数で与えられたリストの大小比較をおこない、先に3勝したほうが勝ち。
	 * @param targetAList ユーザAのリスト
	 * @param targetBList ユーザBのリスト
	 */
	private static void game3Win(List<Integer> targetAList, List<Integer> targetBList) {
		System.out.println("--------- お互いのカードを大小比較、先に3勝した方が勝ち。 ---------");

		System.out.println("ユーザAのリスト");
		targetAList.stream().map(s -> "[" + s + "]").forEach(System.out::print);
		System.out.println("");

		System.out.println("ユーザBのリスト");
		targetBList.stream().map(s -> "[" + s + "]").forEach(System.out::print);
		System.out.println("");

		// 勝負回数をカウントする変数
		int gameCount = 0;

		// 1ゲーム中の比較結果をカウントする変数
		int winCountA = 0;
		int winCountB = 0;

		for (int i = 0; i < 100; i++) {
			gameCount++;
			System.out.println(gameCount + "戦目");
			// 大小比較
			if (targetAList.get(i) > targetBList.get(i)) {
				winCountA++;
			} else if (targetAList.get(i) < targetBList.get(i)) {
				winCountB++;
			}

			if (winCountA > 2 || winCountB > 2) {
				break;
			}
		}

		if (winCountA > winCountB) {
			System.out.println("Aの勝ち");
		} else {
			System.out.println("Bの勝ち");
		}

		System.out.println(gameCount + "戦目で勝利");
	}

	/**
	 * 引数で与えられたリストの大小比較をおこない、先に3連勝したほうが勝ち。
	 * @param targetAList ユーザAのリスト
	 * @param targetBList ユーザBのリスト
	 */
	private static void game3StraightWin(List<Integer> targetAList, List<Integer> targetBList) {
		System.out.println("--------- お互いのカードを大小比較、先に三連勝した方が勝ち。 ---------");

		System.out.println("ユーザAのリスト");
		targetAList.stream().map(s -> "[" + s + "]").forEach(System.out::print);
		System.out.println("");

		System.out.println("ユーザBのリスト");
		targetBList.stream().map(s -> "[" + s + "]").forEach(System.out::print);
		System.out.println("");

		// 勝負回数をカウントする変数
		int gameCount = 0;

		// 1ゲーム中の比較結果をカウントする変数
		int winCountA = 0;
		int winCountB = 0;

		for (int i = 0; i < 1000; i++) {
			gameCount++;
			System.out.println(gameCount + "戦目");

			if (targetAList.get(i) > targetBList.get(i)) {
				winCountA++;
				winCountB = 0;
			} else if (targetAList.get(i) < targetBList.get(i)) {
				winCountA = 0;
				winCountB++;
			} else {
				// 引き分けだったらどちらもリセット
				winCountA = 0;
				winCountB = 0;
			}

			// 条件式(i > 1 && (winCountA > 2 || winCountB > 2))も考えましたが
			// 最初の2回回避するより、そのあとの回すべてで比較内容が増えるほうがよくないのでは？と思い直しました。
			if (winCountA > 2 || winCountB > 2) {
				break;
			}
		}

		if (winCountA > winCountB) {
			System.out.println("Aの勝ち");
		} else {
			System.out.println("Bの勝ち");
		}
		System.out.println(gameCount + "戦目で勝利");
	}

}
