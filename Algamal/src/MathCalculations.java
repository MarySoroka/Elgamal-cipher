import java.math.BigInteger;
import java.util.*;

public class MathCalculations {
//define is it prime number
   public boolean isPrime(int len, String key){
       BigInteger bigKey = new BigInteger(key, 10);
       return bigKey.isProbablePrime(len);
   }

//define prime dividers for big integer number
private Set<BigInteger> defineDelivers(BigInteger key){
       Set<BigInteger> dividers = new HashSet<>();
       BigInteger div  = new BigInteger("2");
       while (div.compareTo(key) != 0) {
           div = div.nextProbablePrime();
           dividers.add(div);
       }
       return dividers;
   }

//power big integer
    public static BigInteger power(BigInteger a, BigInteger z, BigInteger m)
    {
        BigInteger a1 = a;
        BigInteger z1 = z;
        BigInteger x = BigInteger.valueOf(1);
        while (z1.compareTo(BigInteger.ZERO) == 0) {
            while((z1.mod(BigInteger.TWO)).compareTo(BigInteger.ZERO) == 0) {
                z1 = z1.divide(BigInteger.TWO);
                a1 = (a1.pow(2)).mod(m);
            }
            z1 = z1.subtract(BigInteger.ONE);
            x = (x.multiply(a1)).mod(m);
        }
        return x;
    }

//define primitives roots for big integer
   public List<BigInteger> getPrimitivesRoot(BigInteger key){
       List<BigInteger> primitiveRoots = new LinkedList<>();

       Set<BigInteger> dividers = defineDelivers(key.subtract(BigInteger.ONE));

       boolean flag;
       BigInteger i = BigInteger.TWO;
       while (i.compareTo(key) != 0) {
           flag = true;
           for (BigInteger div : dividers) {
               if( (power(i, ((key.subtract(BigInteger.ONE))).divide(div), key)).compareTo(BigInteger.ONE) == 0) {
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

    public static int ElerFunc (int n) {
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
