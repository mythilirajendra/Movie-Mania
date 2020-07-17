import java.util.*;
import java.io.*;
/**
 * Write a description of movieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class movieRunnerWithFilters {
    public void printAvgRatings(){
        ThirdRatings tr = new ThirdRatings();
        System.out.println(tr.getRatersSize());
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
    
    public void printAvgRatingswithYearFilter(){
        ThirdRatings tr = new ThirdRatings();
        System.out.println(tr.getRatersSize());
        try{
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("# of movies in DB:"+MovieDatabase.size());
        YearAfterFilter yf = new YearAfterFilter(2000);
        ArrayList<Rating> avg = tr.getAvgByFilter(20,yf);
        System.out.println("# of movies returned:"+avg.size());
        Collections.sort(avg);
        for(Rating r : avg)
            System.out.println(r.getValue()+":"+MovieDatabase.getTitle(r.getItem())+","+MovieDatabase.getYear(r.getItem()));
    }
        catch(IOException ioe){System.out.println("ioe my boy");}
        
    }
    
    public void printAvgRatingswithGenreFilter(){
        ThirdRatings tr = new ThirdRatings();
        System.out.println(tr.getRatersSize());
        try{
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("# of movies in DB:"+MovieDatabase.size());
        GenreFilter gf = new GenreFilter("Comedy");
        ArrayList<Rating> avg = tr.getAvgByFilter(20,gf);
        System.out.println("# of movies returned:"+avg.size());
        Collections.sort(avg);
        for(Rating r : avg)
            System.out.println(r.getValue()+":"+MovieDatabase.getTitle(r.getItem())+","+MovieDatabase.getGenres(r.getItem()));
    }
        catch(IOException ioe){System.out.println("ioe my boy");}
        
    }
    
    public void printAvgRatingsMinuteFilter(){
        ThirdRatings tr = new ThirdRatings();
        System.out.println(tr.getRatersSize());
        try{
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("# of movies in DB:"+MovieDatabase.size());
        MinuteFilter gf = new MinuteFilter(105,135);
        ArrayList<Rating> avg = tr.getAvgByFilter(5,gf);
        System.out.println("# of movies returned:"+avg.size());
        Collections.sort(avg);
        //for(Rating r : avg)
            //System.out.println(r.getValue()+":"+MovieDatabase.getTitle(r.getItem())+","+MovieDatabase.getMinutes(r.getItem()));
    }
        catch(IOException ioe){System.out.println("ioe my boy");}
        
    }
    
    public void printAvgRatingsDirectorFilter(){
        ThirdRatings tr = new ThirdRatings();
        System.out.println(tr.getRatersSize());
        try{
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("# of movies in DB:"+MovieDatabase.size());
        DirectorFilter gf = new DirectorFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack");
        ArrayList<Rating> avg = tr.getAvgByFilter(4,gf);
        System.out.println("# of movies returned:"+avg.size());
        Collections.sort(avg);
        for(Rating r : avg)
            System.out.println(r.getValue()+":"+MovieDatabase.getTitle(r.getItem())+","+MovieDatabase.getDirector(r.getItem()));
    }
        catch(IOException ioe){System.out.println("ioe my boy");}
        
    }
    
    public void YearGenreFilter(){
        ThirdRatings tr = new ThirdRatings();
        System.out.println(tr.getRatersSize());
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
    
    public void YearDirectorMinutes(){
        ThirdRatings tr = new ThirdRatings();
        System.out.println(tr.getRatersSize());
        try{
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("# of movies in DB:"+MovieDatabase.size());
        AllFilters gf = new AllFilters();
        gf.addFilter(new MinuteFilter(70,200));
        gf.addFilter(new YearAfterFilter(1975));
        //gf.addFilter(new DirectorFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack"));
        ArrayList<Rating> avg = tr.getAvgByFilter(3,gf);
        System.out.println("# of movies returned:"+avg.size());
        Collections.sort(avg);
        for(Rating r : avg)
            System.out.println(r.getValue()+":"+MovieDatabase.getTitle(r.getItem())+","+MovieDatabase.getMinutes(r.getItem())+":"+MovieDatabase.getDirector(r.getItem()));
    }
        catch(IOException ioe){System.out.println("ioe my boy");}
        
    }
}
