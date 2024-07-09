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

		// 国ごとの人口をカウント・保持する配列を用意、各要素に0を代入→いったん保留
		/*    	int counter[] = new int[countCountry];
		    	for (int i = 0; i < countCountry; i++) {
		    		counter[i] = 0;
		    	}*/

		for (Country country : countryList) {
			switch (country.getNumber()) {
			case 0:
				jpn += 1;
				break;
			case 1:
				usa += 1;
				break;
			case 2:
				chn += 1;
				break;
			case 3:
				ind += 1;
				break;
			case 4:
				gbr += 1;
				break;
			case 5:
				itr += 1;
				break;
			case 6:
				fra += 1;
				break;
			case 7:
				aus += 1;
				break;
			default:
				System.out.println("国情報に登録されていません：" + country.getNumber() + "「" + country.getJpName() + "」");
				break;
			}
		}

	}
}
