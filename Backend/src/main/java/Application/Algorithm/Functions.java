package Application.Algorithm;

public class Functions {
    public static double mccormick(Double[] x) {
        double a = x[0];
        double b = x[1];
        return Math.sin(a + b) + (a - b) * (a - b) + 1.0 + 2.5 * b - 1.5 * a;
    }

    public static double michalewicz(Double[] x) {
        int m = 10;
        int d = x.length;
        double sum = 0.0;
        for (int i = 1; i < d; ++i) {
            double j = x[i - 1];
            double k = Math.sin(i * j * j / Math.PI);
            sum += Math.sin(j) * Math.pow(k, 2.0 * m);
        }
        return -sum;
    }

    public static double ackley(Double[] x) {
        double a = 20, b = 0.2, c = 2 * Math.PI;
        double d = x.length;
        double sum1 = 0, sum2 = 0;
        for (int i = 0; i < d; ++i) {
            sum1 += Math.pow(x[i], 2.0);
            sum2 += Math.cos(c * x[i]);
        }
        return -a * Math.exp(-b * Math.sqrt(sum1 / d)) -
                Math.exp(sum2 / d) + a + Math.exp(1);
    }

}
