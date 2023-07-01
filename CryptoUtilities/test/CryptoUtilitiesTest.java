import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * @author Adewale Adenle
 *
 */
public class CryptoUtilitiesTest {

    /*
     * Tests of reduceToGCD
     */
    // test for lower bound
    @Test
    public void testReduceToGCD_0_0() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(0);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    // test for routine
    @Test
    public void testReduceToGCD_30_21() {
        NaturalNumber n = new NaturalNumber2(30);
        NaturalNumber nExpected = new NaturalNumber2(3);
        NaturalNumber m = new NaturalNumber2(21);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    // test for upper bounds
    @Test
    public void testReduceToGCD_999999999_999999989() {
        NaturalNumber n = new NaturalNumber2("999999999");
        NaturalNumber nExpected = new NaturalNumber2("1");
        NaturalNumber m = new NaturalNumber2("999999989");
        NaturalNumber mExpected = new NaturalNumber2("0");
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    // Test when both n and m are already the GCD of each other
    @Test
    public void testReduceToGCD_36_12() {
        NaturalNumber n = new NaturalNumber2(36);
        NaturalNumber nExpected = new NaturalNumber2(12);
        NaturalNumber m = new NaturalNumber2(12);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    @Test
    public void testReduceToGCD_35_7() {
        NaturalNumber n = new NaturalNumber2(35);
        NaturalNumber nExpected = new NaturalNumber2(7);
        NaturalNumber m = new NaturalNumber2(7);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    /*
     * Tests of isEven
     */
    //  Lower Bounds
    @Test
    public void testIsEven_0() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(0);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    // Routine
    @Test
    public void testIsEven_1() {
        NaturalNumber n = new NaturalNumber2(1);
        NaturalNumber nExpected = new NaturalNumber2(1);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(false, result);
    }

    @Test
    public void testIsEven_2() {
        NaturalNumber n = new NaturalNumber2(2);
        NaturalNumber nExpected = new NaturalNumber2(2);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    @Test
    public void testIsEven_3() {
        NaturalNumber n = new NaturalNumber2(3);
        NaturalNumber nExpected = new NaturalNumber2(3);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(false, result);
    }

    @Test
    public void testIsEven_LargeEven() {
        NaturalNumber n = new NaturalNumber2("100000000000000000000000000000");
        NaturalNumber nExpected = new NaturalNumber2(
                "100000000000000000000000000000");
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    // Upper Bound
    @Test
    public void testIsEven_LargeOdd() {
        NaturalNumber n = new NaturalNumber2("12345678901234567890123456789");
        NaturalNumber nExpected = new NaturalNumber2(
                "12345678901234567890123456789");
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(false, result);
    }

    /*
     * Tests of powerMod
     */
    @Test
    public void testPowerMod_zero() {
        NaturalNumber n = new NaturalNumber2(3);
        NaturalNumber m = new NaturalNumber2(5);
        NaturalNumber p = new NaturalNumber2();
        NaturalNumber nExpected = new NaturalNumber2(1);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
    }

    // Lower Bound
    @Test
    public void testPowerMod_lowerBound() {
        NaturalNumber n = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(1);
        NaturalNumber m = new NaturalNumber2(2);
        CryptoUtilities.powerMod(n, p, m);
        NaturalNumber expected = new NaturalNumber2(1);
        assertEquals(expected, n);
    }

    // Boundary
    @Test
    public void testPowerMod_boundaryCondition() {
        NaturalNumber n = new NaturalNumber2(987654321);
        NaturalNumber p = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(3);
        CryptoUtilities.powerMod(n, p, m);
        NaturalNumber expected = new NaturalNumber2(1);
        assertEquals(expected, n);
    }

    @Test
    public void testPowerMod_3_3_5() {
        NaturalNumber n = new NaturalNumber2(3);
        NaturalNumber p = new NaturalNumber2(3);
        NaturalNumber m = new NaturalNumber2(5);
        NaturalNumber nExpected = new NaturalNumber2(2);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
    }

    @Test
    public void testPowerMod_10_10_100() {
        NaturalNumber n = new NaturalNumber2(10);
        NaturalNumber p = new NaturalNumber2(10);
        NaturalNumber m = new NaturalNumber2(100);
        NaturalNumber nExpected = new NaturalNumber2(0);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
    }

    /*
     * Tests of isWitnessToCompositeness
     */
    // Test with composite(Not Prime)-> Lower Bound
    @Test
    public void testIsWitness_composite() {
        NaturalNumber w = new NaturalNumber2(2);
        NaturalNumber n = new NaturalNumber2(15);
        boolean nExpected = true;
        boolean result = CryptoUtilities.isWitnessToCompositeness(w, n);
        assertEquals(nExpected, result);
    }

    // Test with Prime
    @Test
    public void testIsWitness_primeNumber() {
        NaturalNumber w = new NaturalNumber2(3);
        NaturalNumber n = new NaturalNumber2(997);
        boolean nExpected = false;
        boolean result = CryptoUtilities.isWitnessToCompositeness(w, n);
        assertEquals(nExpected, result);
    }

    //Test with Prime and a Non-witness
    @Test
    public void testIsWitness_primeNotWitness() {
        NaturalNumber w = new NaturalNumber2(3);
        NaturalNumber n = new NaturalNumber2(7);
        boolean nExpected = false;
        boolean result = CryptoUtilities.isWitnessToCompositeness(w, n);
        assertEquals(nExpected, result);
    }

    @Test
    public void testIsWitness_compositeWitness() {
        NaturalNumber w = new NaturalNumber2(2);
        NaturalNumber n = new NaturalNumber2(15);
        boolean nExpected = true;
        boolean result = CryptoUtilities.isWitnessToCompositeness(w, n);
        assertEquals(nExpected, result);
    }

    /*
     * Tests of isPrime2
     */
    // Lower Bounds
    @Test
    public void testIsPrime2_lowerBounds() {
        NaturalNumber n = new NaturalNumber2(2);
        boolean nExpected = true;
        boolean result = CryptoUtilities.isPrime2(n);
        assertEquals(nExpected, result);
    }

    // odd Prime number
    @Test
    public void testIsPrime2_oddPrime() {
        NaturalNumber n = new NaturalNumber2(3347);
        boolean nExpected = true;
        boolean result = CryptoUtilities.isPrime2(n);
        assertEquals(nExpected, result);
    }

    @Test
    public void testIsPrime2_randomComposite() {
        NaturalNumber n = new NaturalNumber2(102478);
        boolean nExpected = false;
        boolean result = CryptoUtilities.isPrime2(n);
        assertEquals(nExpected, result);
    }

    // Upper Bound
    @Test
    public void testIsPrime2_4000() {
        NaturalNumber n = new NaturalNumber2(4000);
        boolean isItExpected = false;
        assertEquals(isItExpected, CryptoUtilities.isPrime2(n));
    }

    /*
     * Tests of generateNextLikelyPrime
     */
    // Lower Boundary
    @Test
    public void testgenerateNext_2() {
        NaturalNumber n = new NaturalNumber2(2);
        NaturalNumber expected = new NaturalNumber2(3);
        CryptoUtilities.generateNextLikelyPrime(n);
        int result = n.compareTo(expected);
        assertEquals(result, 0);
    }

    // Test with odd composite -> Routine test
    @Test
    public void testGenerateNextLikelyPrime_Composite() {
        NaturalNumber n = new NaturalNumber2(749);
        NaturalNumber nExpected = new NaturalNumber2(751);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals(nExpected, n);
    }

    @Test
    public void testGenerateNextLikelyPrime_14325() {
        NaturalNumber n = new NaturalNumber2(14325);
        CryptoUtilities.generateNextLikelyPrime(n);
        NaturalNumber nExpected = new NaturalNumber2(14327);
        assertEquals(nExpected, n);
    }

}
