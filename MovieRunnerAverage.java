import java.util.*;
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MovieRunnerAverage {
    public void printAvgRatings(){
        SecondRatings sr = new SecondRatings();
        System.out.println(sr.getMovieSize()+","+sr.getRatersSize());
        ArrayList<Rating> avg = sr.getAvg(3);
        Collections.sort(avg);
        for(Rating r : avg)
            System.out.println(r.getValue()+":"+sr.getTitle(r.getItem()));
    }
    
    public void getAvgOneMovie(){
        SecondRatings sr = new SecondRatings();
        System.out.println(sr.getAvgByID(sr.getID("The Godfather"),3));
    }
}
