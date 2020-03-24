import com.esri.core.geometry.Point3D;
import edu.ucr.cs.bdlab.geolite.Point;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.PrecisionModel;

import java.util.ArrayList;
import java.util.Arrays;

public class CreatePoint {
    public static void createPoint() {
        System.out.println("Create 10000000 3-dimension points:");
        System.out.println("\nWithout discard:");
        int i = 0;

        long startTime = System.currentTimeMillis();
        try {
            ArrayList<Point> points = new ArrayList<Point>();
            for (i = 0; i < 10000000; i++) {
                points.add(
                        new edu.ucr.cs.bdlab.geolite.Point(10 * Math.random(), 10 * Math.random(), 10 * Math.random()));
            }
        } catch (OutOfMemoryError e) {
            System.err.println(i);
        }
        long endTime = System.currentTimeMillis();
        System.out.print("Running time of Geolite:");
        System.out.printf("   %d ms\n", endTime - startTime);

        startTime = System.currentTimeMillis();
        try {
            ArrayList<Point3D> point3DS = new ArrayList<Point3D>();
            for (i = 0; i < 10000000; i++) {
                point3DS.add(
                        new com.esri.core.geometry.Point3D(10 * Math.random(), 10 * Math.random(), 10 * Math.random()));
            }
        } catch (OutOfMemoryError e) {
            System.err.println(i);
        }
        endTime = System.currentTimeMillis();
        System.out.print("Running time of Esri:");
        System.out.printf("   %d ms\n", endTime - startTime);

        startTime = System.currentTimeMillis();
        try {
            ArrayList<org.locationtech.jts.geom.Point> points = new ArrayList<org.locationtech.jts.geom.Point>();
            for (i = 0; i < 10000000; i++) {
                points.add(new org.locationtech.jts.geom.Point(
                        new Coordinate(10 * Math.random(), 10 * Math.random(), 10 * Math.random()),
                        new PrecisionModel(), 4326));
            }
        } catch (OutOfMemoryError e) {
            System.err.println(i);
        }
        endTime = System.currentTimeMillis();
        System.out.print("Running time of JTS:");
        System.out.printf("   %d ms\n", endTime - startTime);

        //        System.out.println("10000000000 coordinates");
        //        double[][] coords = new double[10000000000][3];
        //        for (int i = 0; i < 10000000000; i++) {
        //            coords[i][0] = 10 * Math.random();
        //            coords[i][1] = 10 * Math.random();
        //            coords[i][2] = 10 * Math.random();
        //        }
        //        double[] xs = new double[10000000000];
        //        double[] ys = new double[10000000000];
        //        double[] zs = new double[10000000000];
        //        for (int i = 0; i < 10000000000; i++) {
        //            xs[i] = 10 * Math.random();
        //            ys[i] = 10 * Math.random();
        //            zs[i] = 10 * Math.random();
        //        }
        //        double[][] coords1 = new double[10000000000][3];
        //        double[] as = new double[10000000000];
        //        double[] bs = new double[10000000000];
        //        double[] cs = new double[10000000000];
        //        long startTime = System.nanoTime();
        //        for (int i = 0; i < 10000000000; i++) {
        //            coords1[i] = Arrays.copyOf(coords[i], coords[i].length);
        //        }
        //        long endTime = System.nanoTime();
        //        System.out.print("Running time of Arrays.copyOf():");
        //        System.out.printf("   %d\n", (endTime - startTime) / 10000000000);
        //
        //        startTime = System.nanoTime();
        //        for (int i = 0; i < 10000000000; i++) {
        //            as[i] = xs[i];
        //            bs[i] = ys[i];
        //            cs[i] = zs[i];
        //        }
        //        endTime = System.nanoTime();
        //        System.out.print("Running time of direct assignment:");
        //        System.out.printf("   %d\n", (endTime - startTime) / 10000000000);
    }

    public static void main(String[] args) {
        createPoint();
    }
}
