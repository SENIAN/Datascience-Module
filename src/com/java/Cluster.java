import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mohammed on 5/20/2016.
 */
public class Cluster {

    public Points center;
    public List points;
    public int id;

    public Cluster(int id) {
        this.id = id;
        this.points = new ArrayList();
        this.center = null;

    }

    public static void main(String[] args) {
        List clusters = new ArrayList<>();
        List points = new ArrayList<>();
    }

}

