import java.math.BigInteger;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class GetListOfRoots {
    Long calculateY(long x, long g, long p){
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
    public static Boolean isPrime(long x) {
        for(long i=2;i<=Math.sqrt(x);i++)
            if(x%i==0)
                return false;
        return true;
    }

    public static long gcd(long a, long b){
        if(b==0)
            return a;
        return gcd(b, a%b);
    }


    static long power(long a, long z, long m)
    {
        long a1 = a;
        long z1 = z;
        long x = 1;
        while (z1 != 0) {
            while(z1 % 2 == 0) {
                z1 /= 2;
                a1 = (a1*a1) % m;
            }
            z1 = z1 - 1;
            x = (x*a1) % m;
        }
        return x;
    }





    public static Set<Long> getDividers(long n) {
        Set<Long> divies = new HashSet<>();


        for(long i = 2; i < n; i++) {
            if (isPrime(i)) {
                if(n % i == 0) {
                    divies.add(i);
                }
            }
        }

        return divies;
    }

    static List<Long> getPrimitiveRoots(long p) {
        List<Long> primitiveRoots = new LinkedList<>();

        Set<Long> divies = getDividers(p-1);

        boolean flag;
        for(long i = 2; i < p; i++) {
            flag = true;
            for (long div : divies) {
                if( power(i, (p-1) / div, p) == 1) {
                    flag = false;
                    break;
                }
            }

            if(flag) {
                primitiveRoots.add(i);
            }
        }

        return primitiveRoots;
    }


    static int phi(int n) {
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
