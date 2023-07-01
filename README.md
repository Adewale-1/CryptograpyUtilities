# CryptoUtilities

This repository contains a Java class `CryptoUtilities` and its corresponding test class `CryptoUtilitiesTest`. The `CryptoUtilities` class provides utility methods that could be used with RSA cryptosystems. It includes methods for generating random numbers, finding the greatest common divisor of two numbers, checking if a number is even, and checking if a number is prime. The `CryptoUtilitiesTest` class provides unit tests for these methods.

## Getting Started

To get started with the code in this repository, you need to have Java installed on your machine. You can download Java from [Oracle's website](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html).

## Usage

Import the `CryptoUtilities` class into your Java project and call its static methods as needed. For example:

```java
import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

public class Main {

    public static void main(String[] args) {
        NaturalNumber n = new NaturalNumber2(10);
        boolean isEven = CryptoUtilities.isEven(n);
        System.out.println("Is " + n + " even? " + isEven);
    }
}
```

## Methods

The `CryptoUtilities` class provides the following methods:

- `randomNumber(NaturalNumber n)`: Returns a random number uniformly distributed in the interval [0, n].
- `reduceToGCD(NaturalNumber n, NaturalNumber m)`: Finds the greatest common divisor of n and m.
- `isEven(NaturalNumber n)`: Reports whether n is even.
- `powerMod(NaturalNumber n, NaturalNumber p, NaturalNumber m)`: Updates n to its p-th power modulo m.
- `isWitnessToCompositeness(NaturalNumber w, NaturalNumber n)`: Reports whether w is a "witness" that n is composite.
- `isPrime1(NaturalNumber n)` and `isPrime2(NaturalNumber n)`: Reports whether n is a prime; may be wrong with "low" probability.
- `generateNextLikelyPrime(NaturalNumber n)`: Generates a likely prime number at least as large as some given number.

## Testing

The `CryptoUtilitiesTest` class provides unit tests for all the methods in the `CryptoUtilities` class. You can run these tests to verify the correctness of the methods. The tests use the JUnit testing framework.

## Contributing

Contributions are welcome. Please open an issue to discuss your ideas or submit a pull request with your changes.

## License

This project is licensed under the MIT License.

## Acknowledgments

The components used in this project are from the Ohio State University Standard Library.
