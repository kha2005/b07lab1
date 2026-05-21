public class Polynomial {
    double[] coeffs;

    public Polynomial() {
        this.coeffs = new double[]{0};
    }

    public Polynomial(double[] coeffs) {
        double[] copy = coeffs.clone();
        this.coeffs = copy;
    }

    public Polynomial add(Polynomial Q) {
        int maxLength = Math.max(this.coeffs.length, Q.coeffs.length);
        double[] sum = new double[maxLength];

        for (int i = 0; i < maxLength; i++) {
            double c1 = 0;
            double c2 = 0;

            if (i < this.coeffs.length){
                c1 = this.coeffs[i];
            }
            if (i < Q.coeffs.length){
                c2 = Q.coeffs[i];
            }

            sum[i] = c1 + c2;
        }

        return new Polynomial(sum);
    }

    public double evaluate(double x) {
        int n = this.coeffs.length;
        double sum = 0;

        for (int i = 0; i < n; i++) {
            sum += this.coeffs[i] * Math.pow(x, i);
        }

        return sum;
    }

    public boolean hasRoot(double r) {
        return evaluate(r) == 0;
    }

}