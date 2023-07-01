import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.random.Random;
import components.random.Random1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Utilities that could be used with RSA cryptosystems.
 *
 * @author Adewale Adenle
 *
 */
public final class CryptoUtilities {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CryptoUtilities() {
    }

    /**
     * Useful constant, not a magic number: 3.
     */
    private static final int THREE = 3;

    /**
     * Pseudo-random number generator.
     */
    private static final Random GENERATOR = new Random1L();

    /**
     * Returns a random number uniformly distributed in the interval [0, n].
     *
     * @param n
     *          top end of interval
     * @return random number in interval
     * @requires n > 0
     * @ensures
     * 
     *          <pre>
     * randomNumber = [a random number uniformly distributed in [0, n]]
     *          </pre>
     */
    public static NaturalNumber randomNumber(NaturalNumber n) {
        assert !n.isZero() : "Violation of: n > 0";
        final int base = 10;
        NaturalNumber result;
        int d = n.divideBy10();
        if (n.isZero()) {
            /*
             * Incoming n has only one digit and it is d, so generate a random
             * number uniformly distributed in [0, d]
             */
            int x = (int) ((d + 1) * GENERATOR.nextDouble());
            result = new NaturalNumber2(x);
            n.multiplyBy10(d);
        } else {
            /*
             * Incoming n has more than one digit, so generate a random number
             * (NaturalNumber) uniformly distributed in [0, n], and another
             * (int) uniformly distributed in [0, 9] (i.e., a random digit)
             */
            result = randomNumber(n);
            int lastDigit = (int) (base * GENERATOR.nextDouble());
            result.multiplyBy10(lastDigit);
            n.multiplyBy10(d);
            if (result.compareTo(n) > 0) {
                /*
                 * In this case, we need to try again because generated number
                 * is greater than n; the recursive call's argument is not
                 * "smaller" than the incoming value of n, but this recursive
                 * call has no more than a 90% chance of being made (and for
                 * large n, far less than that), so the probability of
                 * termination is 1
                 */
                result = randomNumber(n);
            }
        }
        return result;
    }

    /**
     * Finds the greatest common divisor of n and m.
     *
     * @param n
     *          one number
     * @param m
     *          the other number
     * @updates n
     * @clears m
     * @ensures n = [greatest common divisor of #n and #m]
     */
    public static void reduceToGCD(NaturalNumber n, NaturalNumber m) {

        /*
         * Use Euclid's algorithm; in pseudocode: if m = 0 then GCD(n, m) = n
         * else GCD(n, m) = GCD(m, n mod m)
         */

        // TODO - fill in body
        // Check if m = 0
        if (!m.isZero()) {

            NaturalNumber remainder = n.divide(m);

            n.copyFrom(remainder);

            reduceToGCD(m, n);

            // Clear m
            n.transferFrom(m);
        }

    }

    /**
     * Reports whether n is even.
     *
     * @param n
     *          the number to be checked
     * @return true iff n is even
     * @ensures isEven = (n mod 2 = 0)
     */
    public static boolean isEven(NaturalNumber n) {

        // TODO - fill in body

        /*
         * This line added just to make the program compilable. Should be
         * replaced with appropriate return statement.
         */
        boolean check = false;

        NaturalNumber tempN = new NaturalNumber2(n);

        NaturalNumber two = new NaturalNumber2(2);

        NaturalNumber d = tempN.divide(two);

        // Check if n is even
        if (d.isZero()) {

            check = true;

        }
        return check;
    }

    /**
     * Updates n to its p-th power modulo m.
     *
     * @param n
     *          number to be raised to a power
     * @param p
     *          the power
     * @param m
     *          the modulus
     * @updates n
     * @requires m > 1
     * @ensures n = #n ^ (p) mod m
     */
    public static void powerMod(NaturalNumber n, NaturalNumber p,
            NaturalNumber m) {
        assert m.compareTo(new NaturalNumber2(1)) > 0 : "Violation of: m > 1";

        /*
         * Use the fast-powering algorithm as previously discussed in class,
         * with the additional feature that every multiplication is followed
         * immediately by "reducing the result modulo m"
         */

        // TODO - fill in

        NaturalNumber one = new NaturalNumber2(1);

        NaturalNumber two = new NaturalNumber2(2);

        NaturalNumber newPower = new NaturalNumber2(p);

        // Create a copy of n to be multiplied at the end.
        NaturalNumber tempN = new NaturalNumber2(n);

        if (p.isZero()) {

            n.copyFrom(one);

        } else {
            newPower.divide(two);

            powerMod(n, newPower, m);

            NaturalNumber newN = new NaturalNumber2(n);

            // value of n to the power of 2
            n.multiply(newN);

            n.transferFrom(n.divide(m));

            if (!isEven(p)) {

                n.multiply(tempN);

                n.transferFrom(n.divide(m));

            }

        }

    }

    /**
     * Reports whether w is a "witness" that n is composite, in the sense that
     * either it is a square root of 1 (mod n), or it fails to satisfy the
     * criterion for primality from Fermat's theorem.
     *
     * @param w
     *          witness candidate
     * @param n
     *          number being checked
     * @return true iff w is a "witness" that n is composite
     * @requires n > 2 and 1 < w < n - 1
     * @ensures
     * 
     *          <pre>
     * isWitnessToCompositeness =
     *     (w ^ 2 mod n = 1)  or  (w ^ (n-1) mod n /= 1)
     *          </pre>
     */
    public static boolean isWitnessToCompositeness(NaturalNumber w,
            NaturalNumber n) {
        assert n.compareTo(new NaturalNumber2(2)) > 0 : "Violation of: n > 2";
        assert (new NaturalNumber2(1)).compareTo(w) < 0 : "Violation of: 1 < w";
        n.decrement();
        assert w.compareTo(n) < 0 : "Violation of: w < n - 1";
        n.increment();

        // TODO - fill in body

        boolean check = false;

        NaturalNumber tempW = new NaturalNumber2(w);

        NaturalNumber tempN = new NaturalNumber2(n);

        NaturalNumber two = new NaturalNumber2(2);

        NaturalNumber one = new NaturalNumber2(1);

        // w^2 mod n
        powerMod(w, two, tempN);

        // If w^2 mod n == 1, then n is a composite(Not prime) and w is a witness
        if (w.compareTo(one) == 0) {

            check = true;
        }

        w.copyFrom(tempW);

        tempN.decrement();

        powerMod(w, tempN, n);

        // If w^(n-1) mod n != 1, then n is a composite(Not prime) and w is a witness
        if (w.compareTo(one) != 0) {

            check = true;
        }

        return check;
    }

    /**
     * Reports whether n is a prime; may be wrong with "low" probability.
     *
     * @param n
     *          number to be checked
     * @return true means n is very likely prime; false means n is definitely
     *         composite
     * @requires n > 1
     * @ensures
     * 
     *          <pre>
     * isPrime1 = [n is a prime number, with small probability of error
     *         if it is reported to be prime, and no chance of error if it is
     *         reported to be composite]
     *          </pre>
     */
    public static boolean isPrime1(NaturalNumber n) {
        assert n.compareTo(new NaturalNumber2(1)) > 0 : "Violation of: n > 1";

        boolean isPrime;

        if (n.compareTo(new NaturalNumber2(THREE)) <= 0) {
            /*
             * 2 and 3 are primes
             */
            isPrime = true;
        } else if (isEven(n)) {
            /*
             * evens are composite
             */
            isPrime = false;
        } else {
            /*
             * odd n >= 5: simply check whether 2 is a witness that n is
             * composite (which works surprisingly well :-)
             */
            isPrime = !isWitnessToCompositeness(new NaturalNumber2(2), n);
        }
        return isPrime;
    }

    /**
     * Reports whether n is a prime; may be wrong with "low" probability.
     *
     * @param n
     *          number to be checked
     * @return true means n is very likely prime; false means n is definitely
     *         composite
     * @requires n > 1
     * @ensures
     * 
     *          <pre>
     * isPrime2 = [n is a prime number, with small probability of error
     *         if it is reported to be prime, and no chance of error if it is
     *         reported to be composite]
     *          </pre>
     */
    public static boolean isPrime2(NaturalNumber n) {
        assert n.compareTo(new NaturalNumber2(1)) > 0 : "Violation of: n > 1";

        /*
         * Use the ability to generate random numbers (provided by the
         * randomNumber method above) to generate several witness candidates --
         * say, 10 to 50 candidates -- guessing that n is prime only if none of
         * these candidates is a witness to n being composite (based on fact #3
         * as described in the project description); use the code for isPrime1
         * as a guide for how to do this, and pay attention to the requires
         * clause of isWitnessToCompositeness
         */

        // TODO - fill in body
        boolean check = true;

        final int candidates = 50;

        NaturalNumber tempN = new NaturalNumber2(n);

        NaturalNumber one = new NaturalNumber2(1);

        final int thirdValue = 3;

        NaturalNumber valueThree = new NaturalNumber2(thirdValue);

        // Check if n is less than or equal to three, if it is, then 2 and 3 are primes
        if (n.compareTo(valueThree) > 0) {

            for (int i = 0; i < candidates; i++) {
                NaturalNumber randomValue = randomNumber(tempN);

                while (randomValue.compareTo(one) <= 0
                        || randomValue.compareTo(tempN) >= 0) {

                    randomValue = randomNumber(n);
                }

                // Check if it is not prime
                if (isWitnessToCompositeness(randomValue, n)) {
                    check = false;
                }

            }

        }
        return check;
    }

    /**
     * Generates a likely prime number at least as large as some given number.
     *
     * @param n
     *          minimum value of likely prime
     * @updates n
     * @requires n > 1
     * @ensures n >= #n and [n is very likely a prime number]
     */
    public static void generateNextLikelyPrime(NaturalNumber n) {
        assert n.compareTo(new NaturalNumber2(1)) > 0 : "Violation of: n > 1";

        /*
         * Use isPrime2 to check numbers, starting at n and increasing through
         * the odd numbers only (why?), until n is likely prime
         */

        // TODO - fill in body
        NaturalNumber two = new NaturalNumber2(2);
        // boolean check = false;
        // loop until isPrime return true for the value of n
        if (isEven(n)) {
            n.increment();
            // add 2 each time n is odd
        }
        while (!isPrime2(n)) {
            n.add(two);

        }

    }

    /**
     * Main method.
     *
     * @param args
     *             the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        /*
         * Sanity check of randomNumber method -- just so everyone can see how
         * it might be "tested"
         */
        final int testValue = 17;
        final int testSamples = 100000;
        NaturalNumber test = new NaturalNumber2(testValue);
        int[] count = new int[testValue + 1];
        for (int i = 0; i < count.length; i++) {
            count[i] = 0;
        }
        for (int i = 0; i < testSamples; i++) {
            NaturalNumber rn = randomNumber(test);
            assert rn.compareTo(test) <= 0 : "Help!";
            count[rn.toInt()]++;
        }
        for (int i = 0; i < count.length; i++) {
            out.println("count[" + i + "] = " + count[i]);
        }
        out.println("  expected value = "
                + (double) testSamples / (double) (testValue + 1));

        /*
         * Check user-supplied numbers for primality, and if a number is not
         * prime, find the next likely prime after it
         */
        while (true) {
            out.print("n = ");
            NaturalNumber n = new NaturalNumber2(in.nextLine());
            if (n.compareTo(new NaturalNumber2(2)) < 0) {
                out.println("Bye!");
                break;
            } else {
                if (isPrime1(n)) {
                    out.println(n + " is probably a prime number"
                            + " according to isPrime1.");
                } else {
                    out.println(n + " is a composite number"
                            + " according to isPrime1.");
                }
                if (isPrime2(n)) {
                    out.println(n + " is probably a prime number"
                            + " according to isPrime2.");
                } else {
                    out.println(n + " is a composite number"
                            + " according to isPrime2.");
                    generateNextLikelyPrime(n);
                    out.println("  next likely prime is " + n);
                }
            }
        }

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
