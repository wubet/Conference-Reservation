import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestUtil {

    Public statis String readFileAsString(String filenName){
        byte[] encoded = new byte[0];
        try{
            encoded = Files.readAllBytes(Paths.get("src/test/resources/org/uwb/edu/css533" + fileName))
        }
        catch(IOException exception){
            log.error("Exception in TestUtil", exception)
        }
        return new String(encoded, StandardCharsets.UTF_8);
    }

}
