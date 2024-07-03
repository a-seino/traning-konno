package create;

import java.util.ArrayList;
import java.util.List;

public class Problem102 {

	public static void main(String[] args) {
		/*
		 * 1～10000のうち素数のみを出力せよ
		 */

		// 以下回答
		List<Integer> prime = new ArrayList<Integer>();

		for (int i = 1; i <= 10000; i++) {
			if (i == 2) {
				// 2は素数なのでリストに追加
				prime.add(i);
			} else if (i > 2 && i % 2 != 0) { // 2より大きい奇数なら
				// 素数かどうかのフラグを用意
				boolean isPrime = true;

				for (int j = 3; j <= Math.sqrt(i); j += 2) {
					if (i % j == 0) {
						// 3以上かつ対象の整数( i )の平方根以下の奇数で割り切れたら素数ではない→フラグをfalseにする
						isPrime = false;
					}
				}

				if (isPrime) {
					// isPrimeがtureのままなら素数なのでリストに追加
					prime.add(i);
				}
			}
		}

		// すべての素数が出力されているかチェック（1～10000の間の素数は1229個→リストの中身も1229個ならOK）
		//System.out.println(prime.size());

		System.out.println("1～10000のうち素数のみを出力：");
		for (Integer num : prime) {
			System.out.println(num);
		}
	}

}
