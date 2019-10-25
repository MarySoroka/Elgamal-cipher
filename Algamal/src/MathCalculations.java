import java.math.BigInteger;
import java.util.*;

public class MathCalculations {

   BigInteger calculateY(BigInteger x, BigInteger g, BigInteger p){
       return power(g,x,p);
   }



//define is it prime number
   public boolean isPrime(int len, String key){
       BigInteger bigKey = new BigInteger(key, 10);
       return bigKey.isProbablePrime(len);
   }

//define prime dividers for big integer number
private List<BigInteger> defineDelivers(BigInteger key){
       List<BigInteger> dividers = new LinkedList<>();
       BigInteger div  = new BigInteger("1");
       BigInteger num;
       while (div.compareTo(key) == -1) {
           div = div.nextProbablePrime();
           num = (key.mod(div));
           if (num.compareTo(BigInteger.ZERO) == 0) {
               dividers.add(div);
           }
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
public  List<BigInteger> getPrimitiveRoots(BigInteger p) {
    List<BigInteger> primitiveRoots = new LinkedList<>();

    List<BigInteger> divies = defineDelivers(p.subtract(BigInteger.ONE));
    BigInteger num;
    int size1;
    BigInteger div1;
    boolean flag;
    for(BigInteger i = BigInteger.TWO; i.compareTo(p) < 0; i = i.add(BigInteger.ONE)) {
        flag = true;
        size1 = divies.size();
        for (int div = 0; div< size1; div++) {

            num = p.subtract(BigInteger.ONE);
            div1 = divies.get(div);
            num =  num.divide(div1);
            int num1 = num.intValue();
            num = num.pow(num1);
            num = num.mod(p);

           if(num.equals(BigInteger.ONE)) {
                flag = false;
            }
        }

        if(flag) {
            primitiveRoots.add(i);
        }
    }

    return primitiveRoots;
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
}
