public class Main {

    public static void main(String[] args) {
        double[][] coefficients = {
                {2, 1, -1, 8},
                {-3, -1, 2, -11},
                {-2, 1, 2, -3}
        };

        double[] solution = solve(coefficients);

        System.out.println("Solution:");
        for (int i = 0; i < solution.length; i++) {
            System.out.println("x[" + i + "] = " + solution[i]);
        }
    }

    public static double[] solve(double[][] coefficients) {
        int n = coefficients.length;
        double[] solution = new double[n];

        for (int i = 0; i < n; i++) {
            double maxElement = Math.abs(coefficients[i][i]);
            int maxRowIndex = i;
            for (int k = i + 1; k < n; k++) {
                if (Math.abs(coefficients[k][i]) > maxElement) {
                    maxElement = Math.abs(coefficients[k][i]);
                    maxRowIndex = k;
                }
            }

            for (int k = i; k < n + 1; k++) {
                double temp = coefficients[maxRowIndex][k];
                coefficients[maxRowIndex][k] = coefficients[i][k];
                coefficients[i][k] = temp;
            }

            for (int k = i + 1; k < n; k++) {
                double factor = -coefficients[k][i] / coefficients[i][i];
                for (int j = i; j < n + 1; j++) {
                    if (i == j) {
                        coefficients[k][j] = 0;
                    } else {
                        coefficients[k][j] += factor * coefficients[i][j];
                    }
                }
            }
        }
        for (int i = n - 1; i >= 0; i--) {
            solution[i] = coefficients[i][n] / coefficients[i][i];
            for (int k = i - 1; k >= 0; k--) {
                coefficients[k][n] -= coefficients[k][i] * solution[i];
            }
        }

        return solution;
    }
}