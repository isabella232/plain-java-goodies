import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import static java.lang.System.out;

public class ArtSupplyInventory {

    public final static ArrayList<String> list = new ArrayList<>();

    public void setFileToRead(String fileName) {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)))) {
            String line;
            while((line = br.readLine()) != null) {
                list.add(line);
            }
            Collections.sort(list);
        } catch (Exception exception) {}
    }

    public ArrayList<String> getItemFrom(String itemType) {
        if (itemType == "paints") {
            setFileToRead("paints.csv");
        }
        if (itemType == "pencils") {
            setFileToRead("pencils.csv");
        }
        return list;
    }
    public static void main(String[] args) {
        ArtSupplyInventory search = new ArtSupplyInventory();
        out.println(search.getItemFrom("paints"));
    }
}