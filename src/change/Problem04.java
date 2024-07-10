package change;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import util.Country;
import util.NumericUtil;

/**
 * 国別の人口を調査する問題
 * @param args
 */
public class Problem04 {

	/**
	 * 国別の人口を調査する問題
	 * @param args
	 */
	public static void main(String[] args) {

		// テストデータとして、1000人分の人口データを取得
		List<Country> countryList = makeTestData(1000);

		/* -- ここから問題 -- */
		/* 国毎の人口を集計するメソッド(aggregateNumber)を実装せよ
		 *
		 *
		 */
		aggregateNumber(countryList);

		/* -- ここから問題 -- */
		/* 列挙値「Country」に新しい国を追加し、再度人口を集計せよ
		 *
		 *
		 */

		// Switch文を使わずに集計する場合
		aggregateNumberArray(countryList);
	}

	/**
	 * 引数で指定された要素数分の国情報を作成し返却する。
	 * @param indexCount 作成する要素数
	 * @return 国情報リスト
	 */
	private static List<Country> makeTestData(int indexCount) {

		// 国情報の配列をリストに変換する。
		List<Country> countryList = Arrays.asList(Country.values());

		// 国情報リストから国番号が最大のものを取得する
		Optional<Country> maxNumCountry = countryList.stream().max((l, r) -> l.getNumber() - r.getNumber());
		int maxNumber = maxNumCountry.get().getNumber();

		// 国番号の最大に合わせて、ランダム値を取得する
		List<Integer> dataList = NumericUtil.makeRandomList(indexCount, maxNumber);

		// 作成したランダム値のリストを国情報リストに変換する
		List<Country> retList = dataList.parallelStream() // 並列で処理
				.map(r -> Country.getCountry(r)) // 数値を国番号として国情報を取得
				.collect(Collectors.toList()); // 取得したものをリストに格納

		return retList;
	}

	/**
	 * 国ごとの人口を集計し、コンソールに出力
	 * @param countryList 1000人分の人口データ
	 */
	private static void aggregateNumber(List<Country> countryList) {
		// ※ 判定にif文ではなく、switch文を使用すること
		// 以下回答
		// 国ごとの人口をカウントする変数
		int jpn = 0;
		int usa = 0;
		int chn = 0;
		int ind = 0;
		int gbr = 0;
		int itr = 0;
		int fra = 0;
		int aus = 0;
		int deu = 0;
		int bel = 0;

		for (Country country : countryList) {
			switch (country) {
			case JPN:
				jpn++;
				break;
			case USA:
				usa++;
				break;
			case CHN:
				chn++;
				break;
			case IND:
				ind++;
				break;
			case GBR:
				gbr++;
				break;
			case ITR:
				itr++;
				break;
			case FRA:
				fra++;
				break;
			case AUS:
				aus++;
				break;
			case DEU:
				deu++;
				break;
			case BEL:
				bel++;
				break;
			default:
				System.out.println("国情報に登録されていません：" + country.getNumber() + "「" + country.getJpName() + "」");
				break;
			}
		}

		// 国ごとの人口を出力
		System.out.println("日本の人口：" + jpn + "人");
		System.out.println("アメリカの人口：" + usa + "人");
		System.out.println("中国の人口：" + chn + "人");
		System.out.println("インドの人口：" + ind + "人");
		System.out.println("イギリスの人口：" + gbr + "人");
		System.out.println("イタリアの人口：" + itr + "人");
		System.out.println("フランスの人口：" + fra + "人");
		System.out.println("オーストラリアの人口：" + aus + "人");
		System.out.println("ドイツの人口：" + deu + "人");
		System.out.println("ベルギーの人口：" + bel + "人");
		// カウント抜けがないかのチェック（すべて足して1000ならすべて集計されている）
		//System.out.println(jpn + usa + chn + ind + gbr + itr + fra + aus + deu + bel);
	}

	// 以下、Switch文を使わずに集計するパターン
	/**
	 *  国ごとの人口を国番号と配列を用いて集計し、コンソールに出力
	 * @param countryList 1000人分の人口データ
	 */
	public static void aggregateNumberArray(List<Country> countryList) {
		// 国ごとの人口をカウント・保持する配列を用意、各要素に0を代入→いったん保留
		int counter[] = new int[countryList.size()];
		for (int i = 0; i < countryList.size(); i++) {
			counter[i] = 0;
		}

		// 拡張for文で、国番号と対応する要素番号の値をインクリメントして人口を集計する
		for (Country country : countryList) {
			counter[country.getNumber()]++;
		}

		System.out.println("国番号を利用してカウントした場合");
		// 拡張for文で国名と人口を出力
		for (Country country : Country.values()) {
			System.out.println(country.getJpName() + "の人口：" + counter[country.getNumber()] + "人");
		}
	}
}
