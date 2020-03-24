import edu.ucr.cs.bdlab.geolite.Point;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.PrecisionModel;

public class CreatePointdiscard {
    public static void createPoint() {
        System.out.println("Create 10000000 3-dimension points (unit:ms):");
        System.out.println("\nWith discard:");
        int i = 0;

        long startTime = System.currentTimeMillis();
        try {
            for (i = 0; i < 10000000; i++) {
                Point point =
                        new edu.ucr.cs.bdlab.geolite.Point(10 * Math.random(), 10 * Math.random(), 10 * Math.random());
            }
        } catch (OutOfMemoryError e) {
            System.err.println(i);
        }
        long endTime = System.currentTimeMillis();
        System.out.print("Running time of Geolite:");
        System.out.printf("   %d ms\n", endTime - startTime);

        startTime = System.currentTimeMillis();
        try {
            for (i = 0; i < 10000000; i++) {
                com.esri.core.geometry.Point3D point =
                        new com.esri.core.geometry.Point3D(10 * Math.random(), 10 * Math.random(), 10 * Math.random());
            }
        } catch (OutOfMemoryError e) {
            System.err.println(i);
        }
        endTime = System.currentTimeMillis();
        System.out.print("Running time of Esri:");
        System.out.printf("   %d ms\n", endTime - startTime);

        startTime = System.currentTimeMillis();
        try {
            for (i = 0; i < 10000000; i++) {
                org.locationtech.jts.geom.Point point = new org.locationtech.jts.geom.Point(
                        new Coordinate(10 * Math.random(), 10 * Math.random(), 10 * Math.random()),
                        new PrecisionModel(), 4326);
            }
        } catch (OutOfMemoryError e) {
            System.err.println(i);
        }
        endTime = System.currentTimeMillis();
        System.out.print("Running time of JTS:");
        System.out.printf("   %d ms\n", endTime - startTime);
    }

    public static void main(String[] args) {
        createPoint();
    }
}
