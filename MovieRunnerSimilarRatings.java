import java.io.*;
import java.util.*;
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MovieRunnerSimilarRatings {
    public void printAvgRatings(){
        FourthRatings tr = new FourthRatings();
        System.out.println("# of raters in DB:"+RaterDatabase.size());
        try{
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("# of movies in DB:"+MovieDatabase.size());
        ArrayList<Rating> avg = tr.getAvg(35);
        System.out.println("# of movies returned:"+avg.size());
        Collections.sort(avg);
        for(Rating r : avg)
            System.out.println(r.getValue()+":"+MovieDatabase.getTitle(r.getItem()));
    }
        catch(IOException ioe){System.out.println("ioe my boy");}
        
    }
    
    public void YearGenreFilter(){
        FourthRatings tr = new FourthRatings();
        System.out.println("# of raters in DB:"+RaterDatabase.size());
        try{
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("# of movies in DB:"+MovieDatabase.size());
        AllFilters gf = new AllFilters();
        gf.addFilter(new YearAfterFilter(1990));
        gf.addFilter(new GenreFilter("Drama"));
        ArrayList<Rating> avg = tr.getAvgByFilter(8,gf);
        System.out.println("# of movies returned:"+avg.size());
        Collections.sort(avg);
        for(Rating r : avg)
            System.out.println(r.getValue()+":"+MovieDatabase.getTitle(r.getItem())+","+MovieDatabase.getGenres(r.getItem())+":"+MovieDatabase.getYear(r.getItem()));
    }
        catch(IOException ioe){System.out.println("ioe my boy");}
        
    }
    
    public void PrintSimilarRatings(){
        FourthRatings tr = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        System.out.println("# of raters in DB:"+RaterDatabase.size());
        try{
            MovieDatabase.initialize("ratedmoviesfull.csv");
            System.out.println("# of movies in DB:"+MovieDatabase.size());
            ArrayList<Rating> gsr = tr.getSimilarRatings("71",20,5);
            Collections.sort(gsr,Collections.reverseOrder());
            System.out.println("top 5 movies:");
            for(int i=0;i<5;i++)
            System.out.println(MovieDatabase.getTitle(gsr.get(i).getItem())+":"+gsr.get(i).getValue());
        }
        catch(IOException ioe){System.out.println("ioe my boy");}
    }
    public void printSimilarRatingsGenre(){
        FourthRatings tr = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        System.out.println("# of raters in DB:"+RaterDatabase.size());
        try{
            MovieDatabase.initialize("ratedmoviesfull.csv");
            System.out.println("# of movies in DB:"+MovieDatabase.size());
            ArrayList<Rating> gsr = tr.getSimilarRatingsFilter("964",20,5,new GenreFilter("Mystery"));
            Collections.sort(gsr,Collections.reverseOrder());
            System.out.println("top 5 movies:");
            for(int i=0;i<5;i++){
            System.out.println(MovieDatabase.getTitle(gsr.get(i).getItem())+":"+gsr.get(i).getValue());
            System.out.println(MovieDatabase.getGenres(gsr.get(i).getItem()));}
        }
        catch(IOException ioe){System.out.println("ioe my boy");}
    }
    public void printSimilarRatingsDirector(){
        FourthRatings tr = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        System.out.println("# of raters in DB:"+RaterDatabase.size());
        try{
            MovieDatabase.initialize("ratedmoviesfull.csv");
            System.out.println("# of movies in DB:"+MovieDatabase.size());
            ArrayList<Rating> gsr = tr.getSimilarRatingsFilter("120",10,2,new DirectorFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh"));
            Collections.sort(gsr,Collections.reverseOrder());
            System.out.println("top 5 movies:");
            for(int i=0;i<5;i++){
            System.out.println(MovieDatabase.getTitle(gsr.get(i).getItem())+":"+gsr.get(i).getValue());
            System.out.println(MovieDatabase.getDirector(gsr.get(i).getItem()));}
        }
        catch(IOException ioe){System.out.println("ioe my boy");}
    }
    public void printSimilarRatingsGenreMinutes(){
        FourthRatings tr = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        System.out.println("# of raters in DB:"+RaterDatabase.size());
        try{
            MovieDatabase.initialize("ratedmoviesfull.csv");
            System.out.println("# of movies in DB:"+MovieDatabase.size());
            AllFilters filters = new AllFilters();
            filters.addFilter(new GenreFilter("Drama"));
            filters.addFilter(new MinuteFilter(80,160));
            ArrayList<Rating> gsr = tr.getSimilarRatingsFilter("168",10,3,filters);
            Collections.sort(gsr,Collections.reverseOrder());
            System.out.println("top 5 movies:");
            for(int i=0;i<15;i++){
                System.out.println(gsr.get(i).getItem());
            //System.out.println(MovieDatabase.getTitle(gsr.get(i).getItem())+":"+gsr.get(i).getValue());
            //System.out.println(MovieDatabase.getMinutes(gsr.get(i).getItem())+","+MovieDatabase.getGenres(gsr.get(i).getItem()));
        }
        }
        catch(IOException ioe){System.out.println("ioe my boy");}
    }
    public void printSimilarRatingsYearMinutes(){
        FourthRatings tr = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        System.out.println("# of raters in DB:"+RaterDatabase.size());
        try{
            MovieDatabase.initialize("ratedmoviesfull.csv");
            System.out.println("# of movies in DB:"+MovieDatabase.size());
            AllFilters filters = new AllFilters();
            filters.addFilter(new YearAfterFilter(1975));
            filters.addFilter(new MinuteFilter(70,200));
            ArrayList<Rating> gsr = tr.getSimilarRatingsFilter("314",10,5,filters);
            Collections.sort(gsr,Collections.reverseOrder());
            System.out.println("top 5 movies:");
            for(int i=0;i<5;i++){
            System.out.println(MovieDatabase.getTitle(gsr.get(i).getItem())+":"+gsr.get(i).getValue());
            System.out.println(MovieDatabase.getMinutes(gsr.get(i).getItem())+","+MovieDatabase.getYear(gsr.get(i).getItem()));}
        }
        catch(IOException ioe){System.out.println("ioe my boy");}
    }
    
    
    
    public void test() throws IOException{
        FourthRatings tr = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        ArrayList<Rating> sim= tr.getSimilarities("65");
        System.out.println(sim);
    }
}
