import java.math.BigInteger;

class Elgamal {


//class for encoder and decoder
    private BigInteger p;
    private BigInteger g;
    private BigInteger y;
    private BigInteger x;

    Elgamal(BigInteger p, BigInteger g, BigInteger x){
        this.p = p;
        this.x = x;
        this.g = g;
        MathCalculations newY = new MathCalculations();
        this.y = newY.calculateY(x,g,p);
    }
}
