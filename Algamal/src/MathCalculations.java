import java.math.BigInteger;
import java.util.*;

public class MathCalculations {
//define prime number
   public boolean isPrime(int len, String key){
       BigInteger bigKey = new BigInteger(key, 10);
       return bigKey.isProbablePrime(len);
   }


}
