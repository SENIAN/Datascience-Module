import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Fatima on 23-06-16.
 */

@Data
@AllArgsConstructor
public class Individual<T> {
    private T individual;



    public void setIndividual(T individual) {
        this.individual = individual;
    }

    public T getIndividual() {
        return individual;
    }
}
