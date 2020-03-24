import com.esri.core.geometry.Line;
import com.esri.core.geometry.Polyline;
import edu.ucr.cs.bdlab.geolite.twod.LineString2D;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.PrecisionModel;

public class CreateLinediscard {
    static void createLine() {
        System.out.println("Create multi-segment linestrings with discard:");
        int n = 51;
        System.out.println("The number of segments: " + (n - 1));
        int i = 0;

        System.gc();
        long startTime = System.currentTimeMillis();
        try {
            for (i = 0; i < 10000; i++) {
                LineString2D lineString2D = new LineString2D();
                for (int j = 0; j < n; j++)
                    lineString2D.addPoint(10 * Math.random(), 10 * Math.random());
            }
        } catch (OutOfMemoryError e) {
            System.err.println(i);
        }
        long endTime = System.currentTimeMillis();
        System.out.print("Running time of Geolite:");
        System.out.printf("   %d ms\n", endTime - startTime);

//        startTime = System.currentTimeMillis();
//        try {
//            for (i = 0; i < 10000; i++) {
//                com.esri.core.geometry.Polyline polyline = new Polyline();
//                for (int j = 0; j < n - 1; j++) {
//                    com.esri.core.geometry.Line line =
//                            new Line(10 * Math.random(), 10 * Math.random(), 10 * Math.random(), 10 * Math.random());
//                    polyline.addSegment(line, false);
//                }
//            }
//        } catch (OutOfMemoryError e) {
//            System.err.println(i);
//        }
//        endTime = System.currentTimeMillis();
//        System.out.print("Running time of Esri:");
//        System.out.printf("   %d ms\n", endTime - startTime);

        System.gc();
        startTime = System.currentTimeMillis();
        try {
            for (i = 0; i < 10000; i++) {
                Coordinate[] coors = new Coordinate[n];
                for (int j = 0; j < n; j++)
                    coors[j] = new Coordinate(10 * Math.random(), 10 * Math.random());
                org.locationtech.jts.geom.LineString lineString =
                        new org.locationtech.jts.geom.LineString(coors, new PrecisionModel(), 4326);
            }
        } catch (OutOfMemoryError e) {
            System.err.println(i);
        }
        endTime = System.currentTimeMillis();
        System.out.print("Running time of JTS:");
        System.out.printf("   %d ms\n", endTime - startTime);
        System.gc();
    }

    public static void main(String[] args) {
        createLine();
    }
}
