import lombok.Data;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by Mohammed on 5/20/2016.
 */

@Data
public class Client {

    private static Client instance  = null;

    public static Client getInstance() {
        if(instance==null) {
            instance = new Client();
        }
        return instance;
    }

    public int OfferID;
    public Map<Integer, String> obs =  new HashMap();
    public int userID;
}
