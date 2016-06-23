import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

/**
 * Created by Fatima on 23-06-16.
 */


@Data
@AllArgsConstructor
public class Tuple<T> {

    private Map Tuple;
    private Tuple K_;
    private Tuple T_;

    public Map getTuple() {
        return Tuple;
    }

    public void setTuple(Map tuple) {
        Tuple = tuple;
    }
}

