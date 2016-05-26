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

    public static List<Point> Observations = new ArrayList<>();
    public List Clusters;
    private int NUM_OF_POINTS;
    private int NUM_OF_CLUSTERS;
    public double X_VALUE_COORDINATE;
    public double Y_VALUE_COORDINATE;
    File file;
    Client client;


    private static AlgorithmK instance = null;

    public static AlgorithmK getInstance() {
        if(instance==null) {
            instance = new AlgorithmK();
        }
        return instance;
    }

    public AlgorithmK() {
        Clusters = new ArrayList<>();
    }


    // Fetches the data from the WineData2.txt
    // I've made a txt so that its delimited at the end of the line by a ;
    // Thus because otherwise there would be no point to which where to stop

    public Map fetchData() throws Throwable {
        Vector<String> v = new Vector();
        client = client.getInstance();
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
        // Eerst temp int variable voor alle variablen in de hashmap
        int temp2 = 0;
        // Ophalen van de data die eerder via fetchData is gemaakt.
        Map<Integer, String> allData = client.getObs();
        // De AllData hashmap is 32 values groot omdat er in totaal 32 wijnen zijn 0-31
        //
        for (int z = 0; z < allData.size(); z++) {
            String data = allData.get(z);
            String[] temp = data.split(",");
            // Na het maken van de points van iedere wijn moeten de values x en y weer op 0 staan.
            // Data zou opzich nog gewoon kloppen maar het zou een gigantische chart plot genereren with no use.
            int x = 0;
            int y = 0;
            // Extracten van de String data uit de array temp. Om vervolgens hier integers van te maken en te stoppen
            // in de int variable temp2;
            for (String s : temp) {
                x++;
                temp2 = Integer.parseInt(s);
                // voor elke string char in de string array wordt er een nieuwe point gemaakt.
                // De reden waarom deze hier staat en niet in de if statement temp2 > 0 is omdat deze publiekelijk beschikbaar moet zijn
                // voor de Observations ArrayList bij Y.
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
        // Uitprinten van de resultaten van observations ArrayList.
        Observations.forEach((K) -> {
        //    System.out.println(K);

        });
    }


    public void execute() {


    }

    public double EuclideanDistance(double X_VALUE_COORDINATE, double Y_VALUE_COORDINATE) {
        double distance = Math.pow(X_VALUE_COORDINATE - Y_VALUE_COORDINATE, 2);
        return Math.sqrt(distance);
    }
}
