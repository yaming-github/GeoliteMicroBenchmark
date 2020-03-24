import com.esri.core.geometry.Line;
import com.esri.core.geometry.Point;
import com.esri.core.geometry.VertexDescription;
import edu.ucr.cs.bdlab.geolite.twod.LineString2D;
import edu.ucr.cs.bdlab.geolite.twod.Polygon2D;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.LinearRing;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.geom.PrecisionModel;

import java.util.ArrayList;

public class CreatePolygon {
    public static void createPolygon() {
        System.out.println("Create polygons:");
        int n = 20;
        System.out.println("The number of segments: " + n);
        int i = 0;

        long startTime = System.currentTimeMillis();
        ArrayList<Polygon2D> polygon2DS = new ArrayList<Polygon2D>();
        try {
            for (i = 0; i < 100000; i++) {
                edu.ucr.cs.bdlab.geolite.twod.Polygon2D polygon2D = new Polygon2D();
                double a = 10 * Math.random();
                double b = 10 * Math.random();
                for (int j = 0; j < n; j++) {
                    if (j == 0)
                        polygon2D.addPoint(a, b);
                    else
                        polygon2D.addPoint(10 * Math.random(), 10 * Math.random());
                }
                polygon2D.addPoint(a, b);
                polygon2D.closeLastRingNoCheck();
                polygon2DS.add(polygon2D);
            }
        } catch (OutOfMemoryError e) {
            System.err.println(i);
        }
        long endTime = System.currentTimeMillis();
        System.out.print("Running time of Geolite:");
        System.out.printf("   %d ms\n", endTime - startTime);

        startTime = System.currentTimeMillis();
        ArrayList<com.esri.core.geometry.Polygon> polygons = new ArrayList<com.esri.core.geometry.Polygon>();
        try {
            for (i = 0; i < 100000; i++) {
                com.esri.core.geometry.Polygon polygon = new com.esri.core.geometry.Polygon();
                com.esri.core.geometry.Line[] lines = new Line[n];
                double a = 10 * Math.random();
                double b = 10 * Math.random();
                for (int j = 0; j < n; j++) {
                    if (j == 0)
                        lines[j] = new Line(a, b, 10 * Math.random(), 10 * Math.random());
                    else if (j == n - 1)
                        lines[j] = new Line(10 * Math.random(), 10 * Math.random(), a, b);
                    else
                        lines[j] = new Line(10 * Math.random(), 10 * Math.random(), 10 * Math.random(),
                                10 * Math.random());
                    polygon.addSegment(lines[j], false);
                }
                polygons.add(polygon);
            }
        } catch (OutOfMemoryError e) {
            System.err.println(i);
        }
        endTime = System.currentTimeMillis();
        System.out.print("Running time of Esri:");
        System.out.printf("   %d ms\n", endTime - startTime);

        startTime = System.currentTimeMillis();
        ArrayList<Polygon> polygons1 = new ArrayList<Polygon>();
        try {
            for (i = 0; i < 100000; i++) {
                Coordinate[] coords = new Coordinate[n + 1];
                for (int j = 0; j < n + 1; j++) {
                    if (j == n)
                        coords[j] = coords[0];
                    else
                        coords[j] = new Coordinate(10 * Math.random(), 10 * Math.random());
                }
                LinearRing linearRing = new LinearRing(coords, new PrecisionModel(), 4326);
                Polygon polygon = new Polygon(linearRing, new PrecisionModel(), 4326);
                polygons1.add(polygon);
            }
        } catch (OutOfMemoryError e) {
            System.err.println(i);
        }
        endTime = System.currentTimeMillis();
        System.out.print("Running time of JTS:");
        System.out.printf("   %d ms\n", endTime - startTime);
    }

    public static void main(String[] args) {
        createPolygon();
    }
}
