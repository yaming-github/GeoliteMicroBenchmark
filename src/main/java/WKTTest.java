import com.esri.core.geometry.*;
import edu.ucr.cs.bdlab.geolite.*;
import edu.ucr.cs.bdlab.wktparser.ParseException;
import edu.ucr.cs.bdlab.wktparser.WKTParser;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.WKTReader;

import java.io.*;

public class WKTTest {
    public static void main(String[] args) throws ParseException, IOException, org.locationtech.jts.io.ParseException {
        System.out.println("Comparision of WKTParser(100,000 lines)(unit:ns):");
        WKTParser wktParser = new WKTParser();
        com.esri.core.geometry.OperatorImportFromWkt wktImporter = OperatorImportFromWkt.local();
        WKTReader wktReader = new WKTReader();
        IGeometry[] geometry = new IGeometry[1000000];
        OGCStructure[] structure = new OGCStructure[1000000];
        Geometry[] geometries = new Geometry[1000000];
        BufferedReader in1 = new BufferedReader(new FileReader("OSM2015_all_objects.csv"));
        BufferedReader in2 = new BufferedReader(new FileReader("OSM2015_all_objects1.csv"));
        BufferedReader in3 = new BufferedReader(new FileReader("OSM2015_all_objects2.csv"));
        String str;
        int i = 0;

        long startTime = System.nanoTime();
        try {
            for (i = 0; i < 1000000; i++) {
                str = in1.readLine();
                if (i == 0)
                    geometry[i] = wktParser.parse(str, null);
                else
                    geometry[i] = wktParser.parse(str, geometry[i - 1]);
            }
        } catch (OutOfMemoryError e) {
            System.err.println(i);
        }
        long endTime = System.nanoTime();
        System.out.print("Running time of Geolite:");
        System.out.printf("   %d\n", (endTime - startTime) / 1000000);

        startTime = System.nanoTime();
        try {
            for (i = 0; i < 1000000; i++) {
                str = in2.readLine();
                structure[i] = wktImporter.executeOGC(WktImportFlags.wktImportNonTrusted, str, null);
            }
        } catch (OutOfMemoryError e) {
            System.err.println(i);
        }
        endTime = System.nanoTime();
        System.out.print("Running time of Esri:");
        System.out.printf("   %d\n", (endTime - startTime) / 1000000);

        startTime = System.nanoTime();
        try {
            for (i = 0; i < 1000000; i++) {
                str = in3.readLine();
                geometries[i] = wktReader.read(str);
            }
        } catch (OutOfMemoryError e) {
            System.err.println(i);
        }
        endTime = System.nanoTime();
        System.out.print("Running time of JTS:");
        System.out.printf("   %d\n", (endTime - startTime) / 1000000);
    }
}
