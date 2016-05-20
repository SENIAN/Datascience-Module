import lombok.Data;

import java.util.Vector;

/**
 * Created by Mohammed on 5/20/2016.
 */
@Data
public class Points {

    /*
    INPUTS
The user-specified parameters of the program should be:
 amount of clusters (𝑘);
 amount of iterations (how many times do you repeat the k-means algorithm to find the best solution).
The dataset to use comes from Chapter 2 of the book:
 the complete dataset can be found at http://eu.wiley.com/WileyCDA/WileyTitle/productCd-
111866146X.html (in the Downloads section)
 the preprocessed dataset (a csv file containing only the information about the observations that have
to be clustered) is available on N@tschool (“Winedata.csv”).
Important note: the clients are the observations/points that have to be clustered. Each of them is a vector
composed by 32 numbers, one for each of the 32 wine offers. These numbers can be only 0 or 1:
 0 if the client didn’t take that offer;
 1 if the client did take that offer.
The points to cluster are thus in 32
     */
    private int clusters = 0;
    private int grafiekx = 0;
    private int grafieky = 0;
}
