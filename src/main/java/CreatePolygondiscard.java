import com.esri.core.geometry.Line;
import edu.ucr.cs.bdlab.geolite.twod.MultiPolygon2D;
import edu.ucr.cs.bdlab.geolite.twod.Polygon2D;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.LinearRing;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.geom.PrecisionModel;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CreatePolygondiscard {
    public static void createPolygon() throws IOException {
        //        System.out.println("Create polygons:");
        int n = 100;
        //        System.out.println("The number of segments: " + n);
        int i = 0;
        BufferedWriter writer = new BufferedWriter(new FileWriter("out.txt"));
        writer.write("insert into test([\n");

        //        long startTime = System.currentTimeMillis();
        try {
            for (i = 0; i < 1; i++) {
                StringBuilder out = new StringBuilder();
                MultiPolygon2D multiPolygon2D = new MultiPolygon2D();
                for (int k = 0; k < 50; k++) {
                    edu.ucr.cs.bdlab.geolite.twod.Polygon2D polygon2D = new Polygon2D();
                    for (int z = 0; z < 20; z++) {
                        double a = 1000 * Math.random();
                        double b = 1000 * Math.random();
                        for (int j = 0; j < n; j++) {
                            if (j == 0)
                                polygon2D.addPoint(a, b);
                            else
                                polygon2D.addPoint(1000 * Math.random(), 1000 * Math.random());
                        }
                        polygon2D.addPoint(a, b);
                        polygon2D.closeLastRingNoCheck();
                    }
                    multiPolygon2D.addPolygon(polygon2D);
                }
                multiPolygon2D.toWKT(out);
                writer.write("{\"id\": " + (5002 + i) + ", \"geo\": st_geom_from_text('" + out.toString() + "')}\n");
            }
            writer.write("]);");
            writer.close();
        } catch (OutOfMemoryError e) {
            System.err.println(i);
        }
        //        long endTime = System.currentTimeMillis();
        //        System.out.print("Running time of Geolite:");
        //        System.out.printf("   %d ms\n", endTime - startTime);
        //
        //        startTime = System.currentTimeMillis();
        //        try {
        //            for (i = 0; i < 1000000; i++) {
        //                com.esri.core.geometry.Polygon polygon = new com.esri.core.geometry.Polygon();
        //                com.esri.core.geometry.Line[] lines = new Line[n];
        //                double a = 10 * Math.random();
        //                double b = 10 * Math.random();
        //                for (int j = 0; j < n; j++) {
        //                    if (j == 0)
        //                        lines[j] = new Line(a, b, 10 * Math.random(), 10 * Math.random());
        //                    else if (j == n - 1)
        //                        lines[j] = new Line(10 * Math.random(), 10 * Math.random(), a, b);
        //                    else
        //                        lines[j] = new Line(10 * Math.random(), 10 * Math.random(), 10 * Math.random(),
        //                                10 * Math.random());
        //                    polygon.addSegment(lines[j], false);
        //                }
        //            }
        //        } catch (OutOfMemoryError e) {
        //            System.err.println(i);
        //        }
        //        endTime = System.currentTimeMillis();
        //        System.out.print("Running time of Esri:");
        //        System.out.printf("   %d ms\n", endTime - startTime);
        //
        //        startTime = System.currentTimeMillis();
        //        try {
        //            for (i = 0; i < 1000000; i++) {
        //                Coordinate[] coords = new Coordinate[n + 1];
        //                for (int j = 0; j < n + 1; j++) {
        //                    if (j == n)
        //                        coords[j] = coords[0];
        //                    else
        //                        coords[j] = new Coordinate(10 * Math.random(), 10 * Math.random());
        //                }
        //                LinearRing linearRing = new LinearRing(coords, new PrecisionModel(), 4326);
        //                Polygon polygon = new Polygon(linearRing, new PrecisionModel(), 4326);
        //            }
        //        } catch (OutOfMemoryError e) {
        //            System.err.println(i);
        //        }
        //        endTime = System.currentTimeMillis();
        //        System.out.print("Running time of JTS:");
        //        System.out.printf("   %d ms\n", endTime - startTime);
    }

    public static void main(String[] args) throws IOException {
        createPolygon();
    }
}
