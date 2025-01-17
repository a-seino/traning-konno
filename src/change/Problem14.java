package change;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.function.BiFunction;
import java.util.stream.Stream;

/**
 * 入出力の問題
 *
 */
public class Problem14 {

	/**
	 * csvファイルを読み込み、加算結果を出力する
	 * @param args
	 */
	public static void main(String[] args) {
		execBufferedReader();
		execStream();
		execList();

		/*
		 * ここから問題
		 */

		/*
		 * 課題１
		 * 【実装イメージ】で3通り提示しましたが、すべて実行して試してみてください。
		 * 問題なく動くんだな、ということが理解できれば完了として構いません。
		 * 可能ならデバッグでステップ実行してもらうとより理解が深まると思います。
		 */

		/*
		 * 課題２
		 * 【実装イメージ】からメモリ展開しないどちらかを選択して、
		 * 現在はSystem.outに出力している計算結果をファイルに出力するようにしてください。
		 * 今回は出力方法は問いません。（目的とすることが実現できることが大事です）
		 */

		/*
		 * 課題３
		 * 現在は【ポイント】①の
		 * 1,2
		 * 3,1
		 * …
		 * を対象とした加算のみになっていますが、
		 * これを改造して②の
		 * 1,2,+
		 * 3,1,-
		 * …
		 * が処理出来るようにしてみてください。
		 * 演算は
		 * 「+」：加算
		 * 「-」：減算
		 * 「*」：乗算
		 * 「/」：除算
		 * の4通りで考えてください。
		 * 2項演算で構いませんが、お願いされていることすべて終わってしまってものすごく暇です・・・
		 * となるようであれば逆ポーランド記法による複数回演算に改造してみてください。
		 */

		/*
		 * 課題４
		 * 出力内容をその行の演算だけではなく、
		 * １つ前の行で行った演算の結果を加算して出力するようにしてみてください。
		 * ※１：１つ前の行でも、その前の行との加算は行っています。
		 * ※２：修正ポイントはsumメソッドでなくても構いません。（IIRフィルタが参考になるかもしれません）
		 */
	}

	/**
	 * csvファイルを読み込み、加算結果を出力する。<br>
	 * 入力ストリームを使用して1行づつ読み込んで処理するパターン
	 *
	 */
	protected static void execBufferedReader() {
		// 好きな方を使って下さい
		//      try (BufferedReader reader = new BufferedReader(new FileReader("./data/in/Problem14_01.csv"))) {
		try (BufferedReader reader = Files.newBufferedReader(Paths.get("./data/in/Problem14_01.csv"))) {
			/*            String line;
			while ((line = reader.readLine()) != null) {
			    System.out.println(sum(line));
			}
			*/

			// 2問目　ファイル出力（途中）

			// FileWriter：出力内容が文字化けしてしまう
			/*			FileWriter fileWriter = new FileWriter(new File("./data/in/Problem14_02.csv"));

						String line = null;
						while ((line = reader.readLine()) != null) {
							fileWriter.write(sum(line));
							fileWriter.write(System.lineSeparator());
						}
						fileWriter.close();
			*/

			// BufferedWriterを使用；文字化けする（OutputStreamで文字コードの指定が必要）
			//String outPath = "./data/out/Problem14_02_out.csv";
			//BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outPath), StandardCharsets.UTF_8));

			/*
				String outPath = "./data/out/Problem14_02_out.csv";
				BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outPath), StandardCharsets.UTF_8));

				String line = null;
				while ((line = reader.readLine()) != null) {
					writer.write(String.valueOf(calc(line)));
					writer.newLine();
				}

				writer.close();
			*/

			// Filesを使用
			String line = null;
			String path = "./data/out/Problem14_02_out.csv";
			// 書き込みたいファイルのパス
			Path filePath = Paths.get(path);
			// 書き込みたい文字列を保持するList
			List<String> sumList = new ArrayList<>();

			int preAns = 0;
			while ((line = reader.readLine()) != null) {
				int ans = calc(line, preAns);
				sumList.add(String.valueOf(ans));
				preAns = ans;
			}

			// Files.write:引数にパス(Pathオブジェクト)、書き込みたい文字列、エンコードしたい文字コード、ファイル読み込み方法の指定
			Files.write(filePath, sumList, StandardCharsets.UTF_8);

		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}

	/**
	 * csvファイルを読み込み、加算結果を出力する。<br>
	 * ストリームAPIを使用して1行づつ処理するパターン
	 *
	 */
	protected static void execStream() {
		try (Stream<String> stream = Files.lines(Paths.get("./data/in/Problem14_01.csv"))) {

			stream.forEach(line -> {
				// System.out.println(sum(line));
				System.out.println(rpnCalc(line));
			});
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}

	/**
	 * csvファイルを読み込み、加算結果を出力する。<br>
	 * 全行分のデータをリストで取得して処理するパターン
	 *
	 */
	protected static void execList() {
		List<String> lines;
		try {
			lines = Files.readAllLines(Paths.get("./data/in/Problem14_01.csv"));
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}

		int preAns = 0;
		for (String line : lines) {
			//System.out.println(sum(line));
			//System.out.println(calc(line));
			int ans = calc(line, preAns);
			System.out.println(ans);
			preAns = ans;
		}
	}

	/**
	 * 入力値をカンマで区切り加算した結果を返却する。
	 * @param line 対象文字列
	 * @return 加算結果
	 */
	protected static int sum(String line) {
		int sum = 0;
		for (String val : line.split(",")) {
			sum += Integer.parseInt(val);
		}
		return sum;
	}

	/**
	 * 入力値を元に演算した結果を返却する
	 * @param line 対象文字列
	 * @return 計算結果
	 */
	protected static int calc(String line) {
		int ans = 0;

		// 入力値をカンマ区切りにした配列
		String input[] = line.split(",");

		// 計算する用に数値だけ別配列で保持
		int num[] = new int[2];
		num[0] = Integer.parseInt(input[0]);
		num[1] = Integer.parseInt(input[1]);

		// 配列inputの要素数確認用
		//System.out.println("配列の長さ:" + input.length);

		// 配列の中身確認用
		//for (String s : input) {
		//	System.out.println("配列の中身(入力値):" + s);
		//}

		//for (int i : num) {
		//	System.out.println("配列の中身(数値):" + i);
		//}

		// 入力されていた演算子と一致する計算結果を返す
		switch (input[2]) {
		case "+":
			ans = num[0] + num[1];
			break;

		case "-":
			ans = num[0] - num[1];
			break;

		case "*":
			ans = num[0] * num[1];
			break;

		case "/":
			ans = num[0] / num[1];
			break;

		default:
			throw new IllegalArgumentException("演算子以外の文字を取得しました：" + input[2]);
		}

		return ans;
	}

	/**
	 * 入力値を元に演算した結果を返却する(問題4：1つ前の計算結果を加算)
	 * @param line 対象文字列
	 * @param pre 一つ前の計算結果
	 * @return
	 */
	protected static int calc(String line, int pre) {
		// ひとつ前の計算結果を加算した値を返す
		return calc(line) + pre;
	}

	/**
	 * 逆ポーランド記法で複数回演算した結果を返却する
	 * @param line 対象文字列
	 * @return 計算結果
	 */
	protected static int rpnCalc(String line) {
		//int ans = 0;
		//int value1 = 0;
		//int value2 = 0;
		Stack<Integer> stack = new Stack<>();

		for (String s : line.split(",")) {
			BiFunction<Integer, Integer, Integer> bf = null;
			// 入力されていた演算子と一致する計算結果を返す
			switch (s) {
			case "+":
				/*			value1 = stack.pop();
							value2 = stack.pop();
							stack.push(value2 + value1);*/
				bf = (x, y) -> y + x;
				break;

			case "-":
				/*				value1 = stack.pop();
								value2 = stack.pop();
								stack.push(value2 - value1);*/
				bf = (x, y) -> y - x;
				break;

			case "*":
				/*				value1 = stack.pop();
								value2 = stack.pop();
								stack.push(value2 * value1);*/
				bf = (x, y) -> y * x;
				break;

			case "/":
				/*				value1 = stack.pop();
								value2 = stack.pop();
								stack.push(value2 / value1);*/
				bf = (x, y) -> y / x;
				break;

			default:
				bf = null;
				stack.push(Integer.valueOf(s));
				break;
			}

			if (bf != null) {
				stack.push(bf.apply(stack.pop(), stack.pop()));
			}
		}

		return stack.pop();
	}
}
