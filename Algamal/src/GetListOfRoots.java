import java.math.BigInteger;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class GetListOfRoots {
    BigInteger calculateY(BigInteger x, BigInteger g, BigInteger p){
        return power(g,x,p);
    }

    public static BigInteger ElerFunc (BigInteger n) {
        BigInteger result = n;
        BigInteger num = BigInteger.TWO;
        BigInteger num1;
        BigInteger num2;
        for (BigInteger i = BigInteger.TWO; num.compareTo(n) < 0;i = i.add(BigInteger.ONE)) {
            num1 = n.mod(i);
            while (num1.equals(BigInteger.ZERO)){
                n = n.divide(i);
                num1 = n.mod(i);
            }
            num2 = result.divide(i);
            result = result.subtract(num2);
            num = i.multiply(i);
        }
        if (n.compareTo(BigInteger.ONE)>0){
            num2 = result.divide(n);
            result = result.subtract(num2);
        }
        return result;
    }

     static BigInteger power(BigInteger a, BigInteger z, BigInteger m)
    {
        BigInteger a1 = a;
        BigInteger z1 = z;
        BigInteger x = BigInteger.ONE;
        while (!z1.equals(new BigInteger("0"))) {
            BigInteger num = z1.mod(BigInteger.TWO);
            while(num.equals(BigInteger.ZERO)) {
                z1 = z1.divide(BigInteger.TWO);
                BigInteger num1 = a1.multiply(a1);
                a1 = num1.mod(m);
                num = z1.mod(BigInteger.TWO);
            }
            z1 = z1.subtract(BigInteger.ONE);
            BigInteger num2 = x.multiply(a1);
            x = num2.mod(m);
        }
        return x;
    }

    /**
     * Check for prime numbers
     * @param x number
     * @return true if number prime
     */
    public static Boolean isPrime(long x) {
        for(long i=2;i<=Math.sqrt(x);i++)
            if(x%i==0)
                return false;
        return true;
    }

    /**
     * Calculate greatest common divisor
     * @param a first number
     * @param b second number
     * @return greatest common divisor of a and b
     */
    public static long gcd(long a, long b){
        if(b==0)
            return a;
        return gcd(b, a%b);
    }


    private static Set<BigInteger> getDividers(BigInteger n) {
        Set<BigInteger> divies = new HashSet<>();
        BigInteger i = BigInteger.TWO;
        BigInteger num;

        while (i.compareTo(n) < 0) {

            num = n.mod(i);
            if(num.equals(BigInteger.ZERO)) {
                    divies.add(i);
            }
            i = i.nextProbablePrime();
        }
        return divies;
    }

    List<BigInteger> getPrimitiveRoots(BigInteger p) {
        List<BigInteger> primitiveRoots = new LinkedList<>();

        Set<BigInteger> divies = getDividers(p.subtract(BigInteger.ONE));

        boolean flag;
        BigInteger i = BigInteger.TWO;
        boolean fl = true;
        while (i.compareTo(p) < 0) {
            flag = true;
            for (BigInteger div : divies) {
                BigInteger num = p.subtract(BigInteger.ONE);
                num = num.divide(div);
                BigInteger num2 = power(i, num, p);
                if( num2.equals(BigInteger.ONE)) {
                    flag = false;
                    break;
                }
            }

            if(flag) {
                primitiveRoots.add(i);
            }

            if ((p.compareTo(new BigInteger("10000")) > 0) && (i.compareTo(new BigInteger("10")) > 0) && fl){
                i = p.subtract(new BigInteger("200"));
                fl = false;

            }
            i = i.add(BigInteger.ONE);
        }

        return primitiveRoots;
    }

    /**
     * Euler function calculation
     * @param n number
     * @return Euler function from n
     */
    public static int phi (int n) {
        int result = n;
        for (int i=2; i*i<=n; ++i)
            if (n % i == 0) {
                while (n % i == 0)
                    n /= i;
                result -= result / i;
            }
        if (n > 1)
            result -= result / n;
        return result;
    }

}
