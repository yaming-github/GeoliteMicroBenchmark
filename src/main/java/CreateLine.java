import com.esri.core.geometry.*;
import edu.ucr.cs.bdlab.geolite.twod.LineString2D;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.PrecisionModel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CreateLine {
    public static void createLineTest() throws IOException {
        //        System.out.println("Create multi-segment linestrings without discard:");
        int n = 501;
        //        System.out.println("The number of segments: " + (n - 1));
        int i = 0;
        BufferedWriter writer = new BufferedWriter(new FileWriter("out.txt"));
        writer.write("insert into test([\n");

        //        long startTime = System.currentTimeMillis();
        //        ArrayList<LineString2D> lineString2DS = new ArrayList<LineString2D>();
        try {
            for (i = 0; i < 1000; i++) {
                StringBuilder out = new StringBuilder();
                LineString2D lineString2D = new LineString2D();
                for (int j = 0; j < n; j++)
                    lineString2D.addPoint(1000 * Math.random(), 1000 * Math.random());
                //                lineString2DS.add(lineString2D);
                lineString2D.toWKT(out);
                writer.write("{\"id\": " + (4000 + i) + ", \"geo\": st_geom_from_text('" + out.toString() + "')},\n");
            }
            writer.write("]);");
            writer.close();
        } catch (OutOfMemoryError e) {
            System.err.println(i);
        }
        //        long endTime = System.currentTimeMillis();
        //        System.out.print("Running time of Geolite:");
        //        System.out.printf("   %d ms\n", endTime - startTime);

        //        startTime = System.currentTimeMillis();
        //        ArrayList<Polyline> polylines = new ArrayList<Polyline>();
        //        try {
        //            for (i = 0; i < 100000; i++) {
        //                com.esri.core.geometry.Polyline polyline = new Polyline();
        //                for (int j = 0; j < n - 1; j++) {
        //                    com.esri.core.geometry.Line line =
        //                            new Line(10 * Math.random(), 10 * Math.random(), 10 * Math.random(), 10 * Math.random());
        //                    polyline.addSegment(line, false);
        //                }
        //                polylines.add(polyline);
        //            }
        //        } catch (OutOfMemoryError e) {
        //            System.err.println(i);
        //        }
        //        endTime = System.currentTimeMillis();
        //        System.out.print("Running time of Esri:");
        //        System.out.printf("   %d ms\n", endTime - startTime);
        //
        //        startTime = System.currentTimeMillis();
        //        ArrayList<LineString> lineStrings = new ArrayList<LineString>();
        //        try {
        //            for (i = 0; i < 100000; i++) {
        //                Coordinate[] coors = new Coordinate[n + 1];
        //                for (int j = 0; j < n + 1; j++) {
        //                    if (j == n)
        //                        coors[j] = coors[0];
        //                    else
        //                        coors[j] = new Coordinate(10 * Math.random(), 10 * Math.random());
        //                }
        //                LineString lineString = new org.locationtech.jts.geom.LineString(coors, new PrecisionModel(), 4326);
        //                lineStrings.add(lineString);
        //            }
        //        } catch (OutOfMemoryError e) {
        //            System.err.println(i);
        //        }
        //        endTime = System.currentTimeMillis();
        //        System.out.print("Running time of JTS:");
        //        System.out.printf("   %d ms\n", endTime - startTime);
    }

    public static void main(String[] args) throws IOException {
        createLineTest();
    }
}
