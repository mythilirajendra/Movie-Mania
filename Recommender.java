import java.util.*;
import java.io.*;
/**
 * Write a description of recommender here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public interface Recommender {
    public ArrayList<String> getItemsToRate() throws IOException;
    public void printRecommendationsFor (String webRaterID);
}
