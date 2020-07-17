import java.util.*;
import java.io.*;
/**
 * Write a description of RecommendationRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RecommendationRunner implements Recommender{
    public ArrayList<String> getItemsToRate(){
        ArrayList<String> res = new ArrayList<String>();
        try{
            ArrayList<String> myMovies = MovieDatabase.filterBy(new TrueFilter());
            Random r = new Random();
            for(int i=0;i<15;i++){
                int rid = r.nextInt(myMovies.size());
                res.add(myMovies.get(rid));
            }
        }
        catch(IOException ioe){System.out.println("ioe!");};
        return res;
    }
    public void printRecommendationsFor(String webRaterID){
        try{
            FourthRatings fr = new FourthRatings();
            ArrayList<Rating> suggested = fr.getSimilarRatings(webRaterID,7,1);
            System.out.println("<h1>top 10 movies suggested:</h1>");
            System.out.println("<table>");
            System.out.println("<tr>");
            System.out.println("<th>Name</th>");
            System.out.println("<th>Genre</th>");
            System.out.println("</tr>");
            for(int i=0;i<10;i++){
                System.out.println("<tr>");
                String id = suggested.get(i).getItem();
                System.out.println("<td>"+MovieDatabase.getTitle(id)+"</td>");
                System.out.println("<td>"+MovieDatabase.getGenres(id)+"</td>");
                System.out.println("</tr>");
            }
            System.out.println("</table>");
        }
        catch(IOException ioe){System.out.println("ioe!");};
    }
    
}
