import java.util.ArrayList;
import java.util.List;

public class Test
{
    public static void main(String[] args)
    {
        ArrayList<Double> p1 =  new ArrayList<>(List.of(2.0, -1.5, 3.0));
        ArrayList<Double> p2 =  new ArrayList<>(List.of(-1.0, 2.0, 4.0, -7.0));

        Polynomial a = new Polynomial(p1);
        Polynomial b = new Polynomial(p2);

        System.out.println("a  : " +a);
        System.out.println("b  : " +b);

        System.out.println("a+b: " + a.add(b));
        System.out.println("a-b: " + a.subtract(b));
        System.out.println("a*b: " + a.multiply(b));
    }
}