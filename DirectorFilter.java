import java.io.*;
import java.util.*;
/**
 * Write a description of DirectorFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DirectorFilter implements Filter{
    private String dir;
    public DirectorFilter(String d){
        dir = d;
    }
    @Override
    public boolean satisfies(String id){
        try{
            //System.out.println(dir);
            String[] dirs = dir.split(",");
            for(int i=0;i<dirs.length;i++)
                if(MovieDatabase.getDirector(id).contains(dirs[i]))
                    return true;
        }
        catch(IOException ioe){
            System.out.println("yo");
        }
        return false;
    }
}
