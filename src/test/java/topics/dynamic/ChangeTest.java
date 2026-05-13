package topics.dynamic;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the {@link Change} (coin change) Dynamic Programming
 * implementation.
 *
 * <p>Covers three classic cases where the greedy approach fails and confirms
 * that the DP solution always returns the true minimum coin count.
 *
 * <table border="1" summary="Test cases">
 *   <tr><th>Case</th><th>Coins</th><th>Amount</th><th>Greedy</th><th>DP (optimal)</th></tr>
 *   <tr><td>1</td><td>[1,6,4]</td><td>15</td><td>5</td><td>4</td></tr>
 *   <tr><td>2</td><td>[1,2,20,50,100,200]</td><td>60</td><td>3</td><td>3</td></tr>
 *   <tr><td>3</td><td>[1,4,5,12,20,50,100,200]</td><td>15</td><td>4</td><td>3</td></tr>
 * </table>
 *
 * @author vicegd
 * @see Change
 */
@DisplayName("Coin Change - Dynamic Programming")
class ChangeTest {
  private static final Logger LOG = LoggerFactory.getLogger(ChangeTest.class);

  @BeforeAll
  static void setup() {
    LOG.trace("Change Tests - Setup");
  }

  @AfterAll
  static void teardown() {
    LOG.trace("Change Tests - Teardown");
  }

  /**
   * Case 1: greedy fails with {@code coins=[1,6,4]}, {@code amount=15}.
   *
   * <p>Greedy picks 6+6+1+1+1 = 5 coins.
   * DP finds 6+4+4+1 = <strong>4 coins</strong> (optimal).
   */
  @Test
  @DisplayName("Case 1: coins=[1,6,4], amount=15 -> 4 coins")
  void testChange() {
    int[] coins = {1, 6, 4};
    LOG.trace("Case with an amount of 15");
    assertEquals(4, new Change().change(15, coins));
  }

  /**
   * Case 2: greedy agrees with DP — {@code coins=[1,2,20,50,100,200]},
   * {@code amount=60}.
   *
   * <p>Both greedy and DP find 20+20+20 = <strong>3 coins</strong>.
   */
  @Test
  @DisplayName("Case 2: coins=[1,2,20,50,100,200], amount=60 -> 3 coins")
  void testChange2() {
    int[] coins = {1, 2, 20, 50, 100, 200};
    LOG.trace("Case with an amount of 60");
    assertEquals(3, new Change().change(60, coins));
  }

  /**
   * Case 3: greedy fails with {@code coins=[1,4,5,12,20,50,100,200]},
   * {@code amount=15}.
   *
   * <p>Greedy picks 12+1+1+1 = 4 coins.
   * DP finds 5+5+5 = <strong>3 coins</strong> (optimal).
   */
  @Test
  @DisplayName("Case 3: coins=[1,4,5,12,20,50,100,200], amount=15 -> 3 coins")
  void testChange3() {
    int[] coins = {1, 4, 5, 12, 20, 50, 100, 200};
    LOG.trace("Case with an amount of 15");
    assertEquals(3, new Change().change(15, coins));
  }
}

