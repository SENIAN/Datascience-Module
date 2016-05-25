import jdk.internal.util.xml.impl.Input;

import java.awt.*;
import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.List;

import static java.lang.Integer.parseInt;

/**
 * Created by Mohammed on 5/20/2016.
 */
public class AlgorithmK {

    public List<Point> Observations = new ArrayList<>();
    public List Clusters;
    private int NUM_OF_POINTS;
    private int NUM_OF_CLUSTERS;
    public double X_VALUE_COORDINATE;
    public double Y_VALUE_COORDINATE;
    File file;
    Client client;

    public AlgorithmK() {
        Clusters = new ArrayList<>();
    }


    public static void main(String[] args) throws Throwable {
        AlgorithmK kmean = new AlgorithmK();
        //Settings.getInstance();
        kmean.fetchData();
        kmean.groupData();

    }

    // Fetches the data from the WineData2.txt
    // I've made a txt so that its delimited at the end of the line by a ;
    // Thus because otherwise there would be no point to which where to stop

    public Map fetchData() throws Throwable {
        Vector<String> v = new Vector();
        Vector<String> v2 = new Vector<>();
        client = new Client();
        URL path = ClassLoader.getSystemResource("WineData2");
        file = new File(path.toURI());
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = "";
        String splitregex = ";";
        String splitregex2 = ",";
        while ((line = br.readLine()) != null) {
            StringTokenizer stringTokenizer = new StringTokenizer(line, splitregex);
            while (stringTokenizer.hasMoreElements()) {
                v.add(stringTokenizer.nextToken());
            }
        }
        // sets the mapValues
        for (int i = 0; i < v.size(); i++) {
            client.setOfferID(i);
            client.obs.put(client.getOfferID(), v.get(i));
        }

        // returns the map filled with all the offers and vectors ready to be called
        return client.obs;

    }

    public void groupData() {
        int temp2 = 0;
        Map<Integer, String> allData = client.getObs();
        for (int z = 0; z < allData.size(); z++) {
            String data = allData.get(z);
            String[] temp = data.split(",");
            int x = 0;
            int y = 0;
            for (String s : temp) {
                x++;
                temp2 = Integer.parseInt(s);
                Point point = new Point();
                if (temp2 > 0) {
                    y++;
                    point.setLocation(z, x);
                }
                if (y > 0) {
                    Observations.add(point);
                    y--;
                }

            }
        }
        Observations.forEach((K) -> {
            System.out.println(K);
        });
    }


    public void execute() {


    }

    public double EuclideanDistance(double X_VALUE_COORDINATE, double Y_VALUE_COORDINATE) {
        double distance = Math.pow(X_VALUE_COORDINATE - Y_VALUE_COORDINATE, 2);
        return Math.sqrt(distance);
    }
}
