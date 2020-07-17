import java.io.*;
/**
 * Write a description of GenreFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GenreFilter implements Filter{
    private String genre;
    public GenreFilter(String g){
        genre = g;
    }
    @Override
    public boolean satisfies(String id){
        try{
            return MovieDatabase.getGenres(id).contains(genre);
        }
        catch(IOException ioe){
            System.out.println("yo");
        }
        return false;
    }
}
