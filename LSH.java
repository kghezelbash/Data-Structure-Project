import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class LSH {
    private int n;
    private int k;
    private int j;
    private float[][] floatPoints;
    private ArrayList<Point> points;
    private Hash hash;

    public LSH() {
        points = new ArrayList<>();
    }

    public ArrayList<Point> getPoints() {
        return points;
    }

    public void setPoints(ArrayList<Point> points) {
        this.points = points;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    void readParameters(String filePath){
        try {
            File myObj = new File(filePath);
            Scanner myReader = new Scanner(myObj);
            n = Integer.parseInt(myReader.nextLine());
            k = Integer.parseInt(myReader.nextLine());
            j = Integer.parseInt(myReader.nextLine());
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        hash =new Hash(k);
        floatPoints = new float[n][2];
    }

    void readPoints(String filePath){
        try {
            int i = 0;
            File myObj = new File(filePath);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] datas = data.split(" ");
                Point point = new Point(Float.parseFloat(datas[0]),Float.parseFloat(datas[1]));
                points.add(point);
                floatPoints[i][0] = point.getX();
                floatPoints[i][1] = point.getY();
                i++;
             }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        hash.makeHash(floatPoints);
    }

    ArrayList<Point> search(float x, float y){
        Point queryPoint = new Point(x,y);
        ArrayList<Point> neighbors = new ArrayList<>();
        for(Point point:points){
            if(is_neighbor(point, queryPoint) && !point.equals(queryPoint)){
                neighbors.add(point);
            }
        }
        return neighbors;
    }

    boolean is_neighbor(Point point, Point queryPoint){
        int i = points.indexOf(point);
        int z = points.indexOf(queryPoint);
        int numbers =0;
        for(int q=0;q<hash.hashes[i].length;q++){
            if(hash.hashes[i][q] == hash.hashes[z][q])
                numbers++;
        }
        return numbers > z;
    }

    public static void main(String[] args) {
        LSH lsh = new LSH();
        lsh.readParameters("/home/kiana/Documents/uni/ds/project/parameters");
        lsh.readPoints("/home/kiana/Documents/uni/ds/project/points.txt");
        System.out.println(lsh.search(lsh.points.get(0).getX(), lsh.points.get(0).getY()));
    }
}
