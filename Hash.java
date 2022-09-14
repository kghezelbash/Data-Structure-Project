import java.util.Random;

public class Hash {
    private float u;
    private float b;
    int[][] hashes;
    float[][] vector;
    public Hash(int k) {
        vector = makeVector(k);
    }

    public int[][] makeHash(float[][] points){
        hashes = matrixProduct(points, vector);
        return hashes;
    }

    public float[][] makeVector(int k) {
        Random r = new Random();
        float[][] a = new float[2][k];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < k; j++) {
                a[i][j] = (float) r.nextGaussian();
            }
        }
        return a;
    }

    public int[][] matrixProduct(float[][] a, float[][] b) {
        int c[][] = new int[a.length][b[0].length];
        float t = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                c[i][j] = 0;
                t=0;
                for (int k = 0; k < a[0].length; k++) {
                    t += a[i][k] * b[k][j];
                }
                c[i][j] = (int) t;
            }
        }
        return c;
    }
}
