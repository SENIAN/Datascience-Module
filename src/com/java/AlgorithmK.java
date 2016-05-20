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

    public List Observations;
    public List Points;
    public List Clusters;
    private int NUM_OF_POINTS;
    private int NUM_OF_CLUSTERS;
    public double X_VALUE_COORDINATE;
    public double Y_VALUE_COORDINATE;
    File file;
    Client client;

    public AlgorithmK() {
        Points = new ArrayList<>();
        Clusters =  new ArrayList<>();
    }


    public static void main(String[] args) throws Throwable{
        AlgorithmK kmean = new AlgorithmK();
        kmean.fetchData();
    }

    // Fetches the data from the WineData2.txt
    // I've made a txt so that its delimited at the end of the line by a ;
    // Thus because otherwise there would be no point to which where to stop

    public Map fetchData() throws Throwable {
        Vector<String> v = new Vector();
        client = new Client();
        URL path = ClassLoader.getSystemResource("WineData2");
        file = new File(path.toURI());
        BufferedReader br =  new BufferedReader(new FileReader(file));
        String line ="";
        String splitregex = ";";
        while ((line = br.readLine()) != null) {
            StringTokenizer stringTokenizer = new StringTokenizer(line, splitregex);
            while(stringTokenizer.hasMoreElements()) {
                v.add(stringTokenizer.nextToken());
            }
        }
        // sets the mapValues
        for(int i=0; i < v.size(); i++) {
            client.setOfferID(i);
            client.obs.put(client.getOfferID(), v.get(i));
        }

        // returns the map filled with all the offers and vectors ready to be called
        return client.obs;

    }

    public void groupData() {

    }

    public void execute() {


    }

    public double EuclideanDistance(double X_VALUE_COORDINATE, double Y_VALUE_COORDINATE) {
        double distance = Math.pow(X_VALUE_COORDINATE - Y_VALUE_COORDINATE,2);
        return Math.sqrt(distance);
    }
}
