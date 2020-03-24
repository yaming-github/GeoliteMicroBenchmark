import java.io.*;

public class TestData {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("OSM2015_all_objects.csv"));
        BufferedWriter out = new BufferedWriter(new FileWriter("test.txt"));
        String str;
        out.write("insert into test1([\n");
        for(int i = 0; i < 900000; i++)
            str = in.readLine();
        for(int i = 0; i < 3; i++){
            str = in.readLine();
            if(i == 2)
                out.write("{\"id\": " + i + ", \"geo\": st_geom_from_text('" + str + "')}\n");
            else
                out.write("{\"id\": " + i + ", \"geo\": st_geom_from_text('" + str + "')},\n");
        }
        out.write("]);");
        out.close();
    }
}
