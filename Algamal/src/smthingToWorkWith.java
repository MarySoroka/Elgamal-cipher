import java.math.BigInteger;
public class smthingToWorkWith {
        private BigInteger p;
        private BigInteger g;
        private BigInteger y;

        public void ElgamalPublicKey(BigInteger p, BigInteger x, BigInteger g) {
            this.p = p;
            this.g = g;
            this.y = MathCalculations.power(g,x,p);
        }

        public BigInteger getP() {
            return p;
        }

        public BigInteger getG() {
            return g;
        }

        public BigInteger getY() {
            return y;
        }


}
