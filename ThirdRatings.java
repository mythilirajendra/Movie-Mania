import java.util.*;
import java.io.*;
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ThirdRatings {
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("data/ratings.csv");
    }
    
    public ThirdRatings(String ratingsfile){
        FirstRatings fr = new FirstRatings();
        try{
            myRaters = fr.loadRaters(ratingsfile);
        }
        catch(IOException ioe){
            new SecondRatings();
        }
    }
    
    
    public int getRatersSize(){
        return myRaters.size();
    }
    
    public double getAvgByID(String id, int min){
        double avg = 0.0;
        int tot = 0;
        double sum = 0.0;
        for(Rater r : myRaters){
            if(r.hasRating(id)){
                tot++;
                sum+=r.getRating(id);
            }
        }
        if(tot>=min)
            avg = sum/tot;
        return avg;
    }
    
    public ArrayList<Rating> getAvg(int minr) throws IOException{
        ArrayList<Rating> avgrting = new ArrayList<Rating>();
        ArrayList<String> myMovies = MovieDatabase.filterBy(new TrueFilter());
        for(String m : myMovies){
            double avgr = getAvgByID(m,minr);
            //assuming 0.0 means min cond not met
            if(avgr!=0.0)
                avgrting.add(new Rating(m,avgr));
        }
        return avgrting;
    }
    
    public ArrayList<Rating> getAvgByFilter(int min, Filter filtercriteria) throws IOException{
        ArrayList<String> mf = MovieDatabase.filterBy(filtercriteria);
        ArrayList<Rating> avgrting = new ArrayList<Rating>();
         for(String m : mf){
            double avgr = getAvgByID(m,min);
            //assuming 0.0 means min cond not met
            if(avgr!=0.0)
                avgrting.add(new Rating(m,avgr));
        }
        return avgrting;
    }
    
}
