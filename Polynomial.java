import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;

public class Polynomial {
    double[] coeffs;
    int[] exps;

    public Polynomial() {
        this.coeffs = new double[]{0};
        this.exps = new int[]{0};
    }

    public Polynomial(double[] coeffs, int[] exps) {
        double[] copy_coeffs = coeffs.clone();
        this.coeffs = copy_coeffs;

        int[] copy_exps = exps.clone();
        this.exps = copy_exps;
    }

    public Polynomial add(Polynomial Q) {
        double[] coeffs_sum = new double[99];
        int[] exps_sum = new int[99];
        int count = 0;

        for (int i = 0; i < 99; i++) {
                exps_sum[i] = i;
                for (int j = 0; j<Q.exps.length; j++){
                    if (Q.exps[j] == i) {
                        coeffs_sum[i] += Q.coeffs[j];
                    }
                }
                for (int j = 0; j<this.exps.length; j++){
                    if (this.exps[j] == i) {
                        coeffs_sum[i] += this.coeffs[j];
                    }
                }

                if (coeffs_sum[i] !=0){
                    count++;
                }
            }

        double[] coeffs_final = new double[count];
        int[] exps_final = new int[count];    
        
        int writeId = 0;
        for (int i = 0; i<99; i++) {
            if(coeffs_sum[i] !=0) {
                coeffs_final[writeId] = coeffs_sum[i];
                exps_final[writeId] = i;
                writeId++;
            }
        }

        return new Polynomial(coeffs_final, exps_final);
    }

    public double evaluate(double x) {
        int n = this.coeffs.length;
        double sum = 0;

        for (int i = 0; i < n; i++) {
            sum += this.coeffs[i] * Math.pow(x, this.exps[i]);
        }

        return sum;
    }

    public boolean hasRoot(double r) {
        return evaluate(r) == 0;
    }


    public Polynomial multiply(Polynomial Q) {
    Polynomial result = new Polynomial();
    
    for (int i = 0; i < Q.coeffs.length; i++) {
        double c = Q.coeffs[i];
        int e = Q.exps[i];

        double[] tempCoeffs = new double[this.coeffs.length];
        int[] tempExps = new int[this.exps.length];
        for (int j = 0; j < this.coeffs.length; j++) {
            tempCoeffs[j] = this.coeffs[j] * c;
            tempExps[j] = this.exps[j] + e;
        }
        Polynomial product = new Polynomial(tempCoeffs, tempExps);

        result = result.add(product);
        }
        
    
    return result;
    }


    public Polynomial(File f) throws Exception {
        Scanner scanner = new Scanner(f);

        String p = scanner.nextLine();

        p = p.charAt(0) + p.substring(1).replace("-", "+-");
        String terms[] = p.split("\\+");

        double[] tempCoeffs = new double[terms.length];
        int[] tempExps = new int[terms.length];

        int count = 0;

        for (int i=0; i<terms.length; i++) {
            String term = terms[i];
            double coeff;
            int exp;

            if(term.contains("x")) {
                String[] vals = term.split("x", -1);

                if(vals[0].equals("")) {
                    coeff = 1;
                }
                
                else if(vals[0].equals("-")){
                    coeff = -1;
                }

                else {
                    coeff = Double.parseDouble(vals[0]);
                }

                if(vals[1].equals("")) {
                    exp = 1;
                }

                else {
                    exp = Integer.parseInt(vals[1]);
                }

            }

            else {
                coeff = Double.parseDouble(term);
                exp = 0;
            }

            tempCoeffs[count] = coeff;
            tempExps[count] = exp;
            count++;
        }

        double[] coeffs_final = new double[count];
        int[] exps_final = new int[count];

        for (int i=0; i<count; i++) {
            coeffs_final[i] = tempCoeffs[i];
            exps_final[i] = tempExps[i];
        }

        this.coeffs = coeffs_final;
        this.exps = exps_final;

        scanner.close();
    }

    public void saveToFile(String file) throws Exception {
        PrintStream ps = new PrintStream(file);

        String text = "";

        for (int i=0; i<this.coeffs.length; i++) {

            double c = this.coeffs[i];
            int e = this.exps[i];

            if (i > 0 && c > 0) {
                text += "+";
            }

            else if (e == 0) {
                text += c;
            }

            if (e==1) {
                text += c + "x";
            }

            else {
                text += c + "x" + e;
            }
        }

        ps.println(text);
        ps.close();
    }


    @Override
    public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if(!(obj instanceof Polynomial))
			return false;
		
		Polynomial p = (Polynomial)obj;
		
        if (this.coeffs.length != p.coeffs.length) {
            return false;
        }

        for (int i = 0; i < this.coeffs.length; i++) {
            if (this.coeffs[i] != p.coeffs[i]) {
               return false; 
            }

            if (this.exps[i] != p.exps[i]) {
                return false;
            }
        }

        return true;
    }
}
