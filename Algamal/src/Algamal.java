import java.math.BigInteger;

public class Algamal {


//class for encoder and decoder
    private BigInteger p;
    private BigInteger g;
    private BigInteger y;
    private BigInteger x;

    public Algamal(BigInteger p, BigInteger g, BigInteger x){
        this.p = p;
        this.x = x;
        this.g = g;
        MathCalculations newY = new MathCalculations();
        this.y = newY.calculateY(x,g,p);
    }
}
