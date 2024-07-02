package create;

public class Problem101 {

    public static void main(String[] args) {
        /*
         * 1～10を足した結果を出力せよ
         */

    	// 以下回答
    	int result = 0;

    	for ( int i = 1; i <= 10; i++) {
    		result += i;
    	}

    	System.out.println(result);
    }

}
