import java.util.*;
import java.io.*;
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("data/ratedmoviesfull.csv", "data/ratings.csv");
    }
    
    public SecondRatings(String moviefile, String ratingsfile){
        FirstRatings fr = new FirstRatings();
        try{
        myMovies = fr.loadMovies(moviefile);
        myRaters = fr.loadRaters(ratingsfile);}
        catch(IOException ioe){
            new SecondRatings();
        }
    }
    
    public int getMovieSize(){
        return myMovies.size();
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
    
    public ArrayList<Rating> getAvg(int minr){
        ArrayList<Rating> avgrting = new ArrayList<Rating>();
        for(Movie m : myMovies){
            double avgr = getAvgByID(m.getID(),minr);
            //assuming 0.0 means min cond not met
            if(avgr!=0.0)
                avgrting.add(new Rating(m.getID(),avgr));
        }
        return avgrting;
    }
    
    public String getTitle(String id){
        for(Movie m : myMovies){
            if(m.getID().equals(id))
                return m.getTitle();
        }
        return "no movie with that ID found.";
    }
    
    public String getID(String title){
        for(Movie m : myMovies){
            if(m.getTitle().equals(title))
                return m.getID();
        }
        return "no movie with that title found.";
    }
}
