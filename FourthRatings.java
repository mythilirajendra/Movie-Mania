import java.util.*;
import java.io.*;
/**
 * Write a description of FourthRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FourthRatings {
    public double getAvgByID(String id, int min){
        double avg = 0.0;
        int tot = 0;
        double sum = 0.0;
        for(Rater r : RaterDatabase.getRaters()){
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
    private double dotProduct(Rater me, Rater r){
        ArrayList<String> rbm = me.getItemsRated();
        int cnt=0; //extra
        double res = 0.0;
        for(int i=0;i<rbm.size();i++)
            if(r.getRating(rbm.get(i))!=-1){
                res = res + (r.getRating(rbm.get(i))-5)*(me.getRating(rbm.get(i))-5);
                ++cnt;}
        return res;
    }
    public ArrayList<Rating> getSimilarities(String id){
        ArrayList<Rating> w = new ArrayList<Rating>();
        ArrayList<Rater> allraters = RaterDatabase.getRaters();
        for(int i=0;i<allraters.size();i++)
            if(allraters.get(i).getID()!=id){
                double dp = dotProduct(RaterDatabase.getRater(id),allraters.get(i));
                if(dp>=0)
                    w.add(new Rating(allraters.get(i).getID(),dp));
            }
        Collections.sort(w,Collections.reverseOrder());
        
        //System.out.println(w);
        return w;
    }
    public ArrayList<Rating> getSimilarRatings(String id, int n, int min) throws IOException{
        ArrayList<Rating> otherraters = getSimilarities(id);
        ArrayList<Rating> res = new ArrayList<Rating>();
        Rater me = RaterDatabase.getRater(id);
        ArrayList<String> myMovies = MovieDatabase.filterBy(new TrueFilter());
        for(String mid : myMovies){
            //System.out.println("movie:"+MovieDatabase.getTitle(mid));
            int cnt = 0;
            int wsum = 0;
            for(int i=0;i<n;i++){
                Rater curr = RaterDatabase.getRater(otherraters.get(i).getItem());
                if(curr.getRating(mid)!=-1){
                    //System.out.println("rated!");
                    ++cnt;
                    wsum+=(otherraters.get(i).getValue()*curr.getRating(mid));
                }
            }
            if(cnt>=min){
                //System.out.println("accepted!");
                res.add(new Rating(mid,wsum/cnt));}
        }
        Collections.sort(res,Collections.reverseOrder());
        
        return res;
    }
    public ArrayList<Rating> getSimilarRatingsFilter(String id, int n, int min, Filter f) throws IOException{
        ArrayList<Rating> otherraters = getSimilarities(id);
        ArrayList<Rating> res = new ArrayList<Rating>();
        Rater me = RaterDatabase.getRater(id);
        ArrayList<String> myMovies = MovieDatabase.filterBy(new TrueFilter());
        for(String mid : myMovies){
            if(f.satisfies(mid)){
                int cnt = 0;
                int wsum = 0;
                if(MovieDatabase.getTitle(mid).equals("Rush"))
                    System.out.println("Evaluating Rush rn!");
                for(int i=0;i<n;i++){
                    Rater curr = RaterDatabase.getRater(otherraters.get(i).getItem());
                    if(curr.getRating(mid)!=-1){
                        ++cnt;
                        wsum+=(otherraters.get(i).getValue()*curr.getRating(mid));
                    }
                }
                if(cnt>=min){
                    res.add(new Rating(mid,wsum/cnt));
                    if(MovieDatabase.getTitle(mid).equals("Rush"))
                    System.out.println("Rush got accepted:"+ wsum/cnt +" is the value");
                }
            }
        }
        return res;
    }
}
