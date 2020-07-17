import java.io.*;
public interface Filter {
	public boolean satisfies(String id)  throws IOException;
}
