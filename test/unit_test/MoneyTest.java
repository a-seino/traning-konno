package unit_test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Money.javaに対するテストケースのクラス
 * @author 紺野由夏
 *
 */
class MoneyTest {

	@Test
	/**
	 * テスト項目：No.1
	 * 対象：Money()メソッド
	 */
	public void testMoney1() {
		int amount1 = 1;
		Money result1 = new Money(amount1);
		assertEquals(amount1, result1.amount);
	}

	@Test
	/**
	 * テスト項目：No.2
	 * 対象：Money()メソッド
	 */
	public void testMoney2() {
		int amount2 = 0;
		Money result2 = new Money(amount2);
		assertEquals(amount2, result2.amount);
	}

	@Test
	/**
	 * テスト項目：No.3
	 * 対象：Money()メソッド
	 */
	public void testMoney3() {
		int amount3 = -1;
		assertThrows(IllegalArgumentException.class, () -> new Money(amount3));
	}
}
