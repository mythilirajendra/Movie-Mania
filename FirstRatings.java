import java.util.*;
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
/**
 * Write a description of FirstRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FirstRatings {
    public ArrayList<Movie> loadMovies(String filename) throws IOException{
        ArrayList<Movie> m = new ArrayList<Movie>();
        FileResource fr = new FileResource(filename);
        CSVParser cp = fr.getCSVParser();
        for(CSVRecord r : cp.getRecords()){
            Movie nmovie = new Movie(r.get("id"),r.get("title"),r.get("year"),r.get("genre"),r.get("director"),r.get("country"),r.get("poster"),Integer.parseInt(r.get("minutes").trim()));   
            m.add(nmovie);
        }
        return m;
    }
    public void testLoadMovies() throws IOException{
        ArrayList<Movie> m = loadMovies("data/ratedmoviesfull.csv");
        System.out.println("# of movies:"+m.size());
        //for(int i=0;i<m.size();i++)
          //  System.out.println(m.get(i));
        int cnt = 0;
        for(int i=0;i<m.size();i++){
            if(m.get(i).getGenres().contains("Comedy"))
                 cnt++;
        }
        System.out.println(cnt);
        cnt = 0;
        for(int i=0;i<m.size();i++){
            if(m.get(i).getMinutes()>150)
                 cnt++;
        }
        System.out.println(cnt);
        cnt = 0;
        HashMap<String,Integer> dm = new HashMap<String,Integer>();
        for(int i=0;i<m.size();i++){
            String dir[] = m.get(i).getDirector().split(",");
            for(String name : dir)
                if(!dm.keySet().contains(name))
                    dm.put(name,1);
                else{
                    int num = dm.get(name);
                    dm.put(name,num+1);
                }
        }
        for(String dir : dm.keySet())
            if(dm.get(dir)>cnt)
                cnt = dm.get(dir);
        System.out.println("max # of movies made by a director:"+cnt);
        for(String dir : dm.keySet())
            if(dm.get(dir)==cnt)
                System.out.println(dir+",");
    }
    public ArrayList<Rater> loadRaters(String filename) throws IOException{
        ArrayList<Rater> m = new ArrayList<Rater>();
        FileResource fr = new FileResource(filename);
        CSVParser cp = fr.getCSVParser();
        for(CSVRecord r : cp.getRecords()){ 
            EfficientRater rtr = new EfficientRater(r.get("rater_id"));
            if(!m.contains(rtr)){
                rtr.addRating(r.get("movie_id"),Double.parseDouble(r.get("rating")));
                m.add(rtr);
            }
            else{
                int idx = m.indexOf(rtr);
                m.get(idx).addRating(r.get("movie_id"),Double.parseDouble(r.get("rating")));
            }
        }
        return m;
    }
    public void test(){
        PlainRater r1 = new PlainRater("1");
        r1.addRating("1521",9);
        PlainRater r2 = new PlainRater("1");
        r2.addRating("12215",8);
        PlainRater r3 = new PlainRater("3");
        System.out.println("is r1=r2?,yes:"+r1.equals(r2));
        System.out.println("is r3=r2?,no:"+r3.equals(r2));
        System.out.println("hashcode pf r1"+r1.hashCode());
        System.out.println("hashcode pf r2"+r2.hashCode());
        System.out.println("hashcode pf r3"+r3.hashCode());
    }
    
    public void testLoadRaters() throws IOException{
        ArrayList<Rater> m = loadRaters("data/ratings.csv");
        ArrayList<String> moviesrated = new ArrayList<String>();
        System.out.println("# of raters:"+m.size());
        /*for(int i=0;i<m.size();i++)
            System.out.println(m.get(i));*/
        int cnt = 0;
        for(Rater r : m)
            if(r.getItemsRated().size()>cnt)
                cnt = r.getItemsRated().size();
        System.out.println("max # of ratings made by a rater:"+cnt+",they are:");
        for(Rater r : m)
            if(r.getItemsRated().size()==cnt)
                System.out.println(r+",");
        cnt = 0;
        for(Rater r : m)
            if(r.getItemsRated().contains("1798709"))
                ++cnt;
        System.out.println("1798709:"+cnt);
        cnt = 0;
        for(Rater r : m)
            for(String movie : r.getItemsRated())
                if(!moviesrated.contains(movie)){
                    ++cnt;
                    moviesrated.add(movie);
                }
        System.out.println("uniq:"+cnt);
        System.out.println("# of ratings for rater 193:"+m.get(m.indexOf(new EfficientRater("193"))).getItemsRated().size());
    }
}
