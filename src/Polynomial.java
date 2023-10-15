import java.util.ArrayList;
import java.util.Collections;

public class Polynomial implements Cloneable
{
    ArrayList<Double> terms;

    public Polynomial()
    {
        this(new ArrayList<>());
    }

    public Polynomial(ArrayList<Double> terms)
    {
        this.terms = terms;
    }

    public Polynomial(int capacity)
    {
        terms = new ArrayList<>(Collections.nCopies(capacity, 0.0));
    }

    int getPolynomialDegree()
    {
        return this.terms.size()-1;
    }

    int getTermDegree(int index)
    {
        return this.getPolynomialDegree() - index;
    }

    Polynomial add(Polynomial px)
    {
        var p0=this.clone();
        var p1 =px.clone();

        int diff = p0.getPolynomialDegree() - p1.getPolynomialDegree();

        if(diff>=0)
        {
            for (int i = 0; i <= Math.min(p0.getPolynomialDegree(), p1.getPolynomialDegree()); i++)
            {
                p0.terms.set(i + diff, p0.terms.get(i + diff) + p1.terms.get(i));
            }
            return p0;
        }
        else
        {
            for(int i = 0; i <= Math.min(p0.getPolynomialDegree(), p1.getPolynomialDegree()); i++)
            {
                p1.terms.set(i - diff, p1.terms.get(i-diff) + p0.terms.get(i));
            }
            return p1;
        }
    }

    Polynomial subtract(Polynomial px)
    {
        var p0=this.clone();
        var p1 =px.clone();

        for(int i = 0; i <= p1.getPolynomialDegree(); i++)
        {
            p1.terms.set(i, - p1.terms.get(i));
        }

        return p0.add(p1);
    }

    static Double nullProofGet(ArrayList<Double> terms, int index)
    {
        Double n;
        try
        {
            n = terms.get(index);
        }
        catch (Exception e)
        {
            n = 0.0;
        }
        return n;
    }

    Polynomial multiply(Polynomial p1)
    {
        Polynomial r = new Polynomial(this.getPolynomialDegree() + p1.getPolynomialDegree() + 1);
        double n;
        double p;
        int index;

        for(int i = 0; i <= this.getPolynomialDegree(); i++)
        {
            for(int j = 0; j <= p1.getPolynomialDegree(); j++)
            {
                index = this.getTermDegree(i)+p1.getTermDegree(j);
                n = this.terms.get(i)*p1.terms.get(j);
                p = nullProofGet(r.terms, index);
                r.terms.set(index, n + p);
            }
        }
        Collections.reverse(r.terms);

        return r;
    }










    @Override
    public String toString()
    {
        var result = new StringBuilder();
        if(this.terms.get(0) < 0)
            result.append("- ");
        for(int i = 0; i <= this.getPolynomialDegree(); i++)
        {   // 3x^2 + 2x + 1
            if(this.terms.get(i) != 0)
            {
                result.append(Math.abs(this.terms.get(i))); // 3
                if(this.getPolynomialDegree() - i > 0)
                    result.append("x"); // x
                if(this.getPolynomialDegree() - i > 1)
                {
                    result.append("^"); // x
                    result.append(this.getPolynomialDegree() - i); //2
                }
                if(this.getPolynomialDegree() - i > 0)
                    result.append(this.terms.get(i+1) >= 0 ? " + " : " - ");
            }
        }


        return result.toString();
    }

    @Override
    public Polynomial clone()
    {
        return new Polynomial( new ArrayList<>(terms) ) ;
    }
}


