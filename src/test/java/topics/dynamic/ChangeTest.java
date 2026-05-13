package topics.dynamic;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the Change (coin change) Dynamic Programming implementation.
 *
 * Covers three classic cases where the greedy approach fails and confirms that
 * the DP solution always returns the true minimum coin count.
 *
 * Test Cases:
 * - Case 1: coins=[1,6,4],                   amount=15 -> greedy: 5, DP: 4
 * - Case 2: coins=[1,2,20,50,100,200],       amount=60 -> greedy: 3, DP: 3
 * - Case 3: coins=[1,4,5,12,20,50,100,200],  amount=15 -> greedy: 4, DP: 3
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
   * Case 1: Greedy fails - coins [1, 6, 4], amount 15.
   *
   * Greedy picks 6+6+1+1+1 = 5 coins.
   * DP finds   6+4+4+1   = 4 coins (optimal).
   */
  @Test
  @DisplayName("Case 1: coins=[1,6,4], amount=15 -> 4 coins")
  void testChange() {
    int[] coins = {1, 6, 4};
    LOG.trace("Case with an amount of 15");
    assertEquals(4, new Change().change(15, coins));
  }

  /**
   * Case 2: From ChangeNotOptimal greedy case 1 - coins [1,2,20,50,100,200], amount 60.
   *
   * Optimal: 20+20+20 = 3 coins.
   */
  @Test
  @DisplayName("Case 2: coins=[1,2,20,50,100,200], amount=60 -> 3 coins")
  void testChange2() {
    int[] coins = {1, 2, 20, 50, 100, 200};
    LOG.trace("Case with an amount of 60");
    assertEquals(3, new Change().change(60, coins));
  }

  /**
   * Case 3: From ChangeNotOptimal greedy case 2 - coins [1,4,5,12,20,50,100,200], amount 15.
   *
   * Greedy picks 12+1+1+1 = 4 coins.
   * DP finds   5+5+5     = 3 coins (optimal).
   */
  @Test
  @DisplayName("Case 3: coins=[1,4,5,12,20,50,100,200], amount=15 -> 3 coins")
  void testChange3() {
    int[] coins = {1, 4, 5, 12, 20, 50, 100, 200};
    LOG.trace("Case with an amount of 15");
    assertEquals(3, new Change().change(15, coins));
  }
}

