import java.util.*;
/**
 * Write a description of EfficientRater here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

/**
 * Write a description of class Rater here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EfficientRater implements Rater{
    private String myID;
    private HashMap<String,Rating> myRatings;

    public EfficientRater(String id) {
        myID = id;
        myRatings = new HashMap<String,Rating>();
    }

    public void addRating(String item, double rating) {
        if(!myRatings.keySet().contains(item))
            myRatings.put(item,new Rating(item,rating));
    }

    public boolean hasRating(String item) {
        if(myRatings.keySet().contains(item))
            return true;
        return false;
    }

    public String getID() {
        return myID;
    }

    public double getRating(String item) {
        if(!myRatings.keySet().contains(item))
            return -1;
        return myRatings.get(item).getValue();
    }

    public int numRatings() {
        return myRatings.size();
    }

    public ArrayList<String> getItemsRated() {
        ArrayList<String> list = new ArrayList<String>();
        for(String mid: myRatings.keySet()){
            list.add(mid);
        }
        
        return list;
    }
    
    public String toString(){
        return "id:"+myID+",rated:"+myRatings;
    }
    
    @Override
    public boolean equals(Object object){
        boolean isEqual= false;
        isEqual = (this.myID.equals(((EfficientRater)object).myID));
        return isEqual;
    }

    @Override
    public int hashCode() {
        return Integer.parseInt(this.myID);
    }
}
