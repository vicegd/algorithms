package topics.dynamic;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * <h1>Validation Suite for River Travel (DP)</h1>
 * <p>
 * Ensures the algorithm correctly identifies cheaper multi-stop routes 
 * over direct travel routes in a Directed Acyclic Graph representation.
 * </p>
 *
 * @author vicegd
 */
@DisplayName("River Travel Routing - Dynamic Programming")
class RiverTravelTest {
    private static RiverTravel travel;

    @BeforeAll
    static void setup() {
        travel = new RiverTravel();
    }

    @Test
    @DisplayName("Should find optimal downstream routes (Case 1)")
    void shouldCalculateOptimalRoutesCase1() {
        int n = 5;
        int[][] tariff = new int[n][n];
        tariff[0][1] = 3; tariff[0][2] = 8; tariff[0][3] = 9; tariff[0][4] = 20;
        tariff[1][2] = 5; tariff[1][3] = 5; tariff[1][4] = 2;
        tariff[2][3] = 3; tariff[2][4] = 6;
        tariff[3][4] = 2;

        int[][] expected = new int[n][n];
        expected[0][1] = 3; expected[0][2] = 8; expected[0][3] = 8; expected[0][4] = 5;
        expected[1][2] = 5; expected[1][3] = 5; expected[1][4] = 2;
        expected[2][3] = 3; expected[2][4] = 5;
        expected[3][4] = 2;

        int[][] result = travel.calculateMinimumCosts(tariff);

        // assertArrayEquals natively compares multi-dimensional arrays in JUnit 5
        assertArrayEquals(expected, result, "Failed to calculate the correct minimum cost matrix for Case 1.");
    }

    @Test
    @DisplayName("Should find optimal downstream routes with heavier initial tariffs (Case 2)")
    void shouldCalculateOptimalRoutesCase2() {
        int n = 5;
        int[][] tariff = new int[n][n];
        tariff[0][1] = 6; tariff[0][2] = 9; tariff[0][3] = 12; tariff[0][4] = 22;
        tariff[1][2] = 5; tariff[1][3] = 12; tariff[1][4] = 17;
        tariff[2][3] = 4; tariff[2][4] = 14;
        tariff[3][4] = 9;

        int[][] expected = new int[n][n];
        expected[0][1] = 6; expected[0][2] = 9; expected[0][3] = 12; expected[0][4] = 21;
        expected[1][2] = 5; expected[1][3] = 9; expected[1][4] = 17;
        expected[2][3] = 4; expected[2][4] = 13;
        expected[3][4] = 9;

        int[][] result = travel.calculateMinimumCosts(tariff);

        assertArrayEquals(expected, result, "Failed to calculate the correct minimum cost matrix for Case 2.");
    }
}