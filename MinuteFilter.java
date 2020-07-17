import java.io.*;
/**
 * Write a description of MinuteFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MinuteFilter implements Filter{
    private int minmin,maxmin;
    public MinuteFilter(int a, int b){
        minmin = a;
        maxmin = b;
    }
    @Override
    public boolean satisfies(String id){
        try{
            return (MovieDatabase.getMinutes(id)>=minmin && MovieDatabase.getMinutes(id)<=maxmin);
        }
        catch(IOException ioe){
            System.out.println("yo");
        }
        return false;
    }
}
