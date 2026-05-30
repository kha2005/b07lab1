import java.io.File;

public class Driver {
    public static void main(String [] args) throws Exception {
        Polynomial p = new Polynomial();
        System.out.println(p.evaluate(3));
        double [] c1 = {6,5};
        int [] e1 = {0,3};
        Polynomial p1 = new Polynomial(c1, e1);
        double [] c2 = {-2,-9};
        int[] e2 = {1,4};
        Polynomial p2 = new Polynomial(c2,e2);
        Polynomial s = p1.add(p2);
        System.out.println("s(0.1) = " + s.evaluate(0.1));
        if(s.hasRoot(1))
            System.out.println("1 is a root of s");
        else
            System.out.println("1 is not a root of s");

        //Lab 2
        double [] c3 = {-1,2};
        int [] e3 = {0,2};
        Polynomial p3 = new Polynomial(c3, e3);
        double [] c4 = {-6,-1};
        int[] e4 = {1,2};
        Polynomial p4 = new Polynomial(c4,e4);
        Polynomial prod = p3.multiply(p4);
        if(prod.hasRoot(-6))
            System.out.println("-6 is a root of prod");
        else
            System.out.println("-6 is not a root of prod");


        File f = new File("poly_file.txt");
        prod.saveToFile("poly_file.txt");       
        Polynomial file_p = new Polynomial(f);

        if(file_p.equals(prod)) {
            System.out.println("file_p is equal to prod");
        }
        else {
            System.out.println("file_p is not equal to prod");
        }

    }
}
