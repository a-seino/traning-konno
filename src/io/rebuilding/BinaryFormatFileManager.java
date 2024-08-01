package io.rebuilding;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 問題③：
 * 文字コードを意識せずバイナリデータで読み書きを実施するよう、
 * RebuildFileManagerを継承した 新たなクラスを作成
 * @author 紺野由夏
 *
 */
public class BinaryFormatFileManager extends RebuildFileManager {
	private PathSearch search;
	private RebuildRule rule;

	/**
	 * コンストラクタ
	 * @param search パス探索用インターフェース
	 * @param rule 再編成ルール用インターフェース
	 */
	public BinaryFormatFileManager(PathSearch search, RebuildRule rule) {
		//this.search = search;
		//this.rule = rule;
		super(search, rule);
	}

	@Override
	Function<Path, Path> getRebuildRule() {
		return p -> {
			String path = p.toString();
			// javaの表記上のエスケープと正規表現のエスケープのため「\」は倍必要
			String replacePath = path.replaceAll("data\\\\in", "data\\\\out");
			return Paths.get(replacePath);
		};
	}

	@Override
	public void rebuild(Path path) {
		// 探索したパス一覧を取得する
		List<Path> getPathList = search.search(path);

		// パス一覧より対象のリストを取得する
		List<Path> targetPathList = getPathList.stream()
				.filter(p -> rule.isTarget(p)) // 編成対象のもののみを抽出
				.collect(Collectors.toList()); // リストにして返却

		// 変換ルールを作成する
		Map<Path, List<Path>> rebuildMap = rule.rebuildPaths(targetPathList, getRebuildRule());

		makeBinaryFormatFile(rebuildMap);
	}

	/**
	 * * 変換ルールより、ファイルを再編成して出力する
	 * @param rebuildMap 再編成ルール
	 */
	protected void makeBinaryFormatFile(Map<Path, List<Path>> rebuildMap) {
		// 書き込み先のディレクトリを先に作成しておく
		rebuildMap.keySet().stream().forEach(p -> {
			// 書き込み対象の親ディレクトリが存在しない場合作成
			if (!Files.exists(p.getParent())) {
				try {
					Files.createDirectories(p.getParent());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		// ファイル作成
		for (Path path : rebuildMap.keySet()) {
			// 書き込み先ファイル
			Path destPath = path;

			// 読み込みファイル
			List<Path> srcPaths = rebuildMap.get(path);

			// 読み込んだファイルを書き込み先に出力していく
			for (Path srcPath : srcPaths) {
				// ファイルの読み込み
				try {
					byte readAllBytes[] = Files.readAllBytes(srcPath);

					// 書き込み先が存在する場合
					if (Files.exists(destPath)) {
						Files.write(destPath, readAllBytes, StandardOpenOption.TRUNCATE_EXISTING);
					} else {
						// ファイルが存在しない場合
						Files.write(destPath, readAllBytes, StandardOpenOption.CREATE);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
