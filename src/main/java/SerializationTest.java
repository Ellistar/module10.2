import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;
import java.util.Scanner;

public class SerializationTest {
    public static void main(String[] args) throws IOException {

        User [] users = new User[2];
        try (InputStream fis = new FileInputStream("src/main/java/file.txt");
             Scanner scanner = new Scanner(fis)) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] splitLine = line.split(" ");
               for(int i = 0; i < 2; i++) {
                   User user = new User(splitLine[0], splitLine[1]);
                   users[i] = user;
               }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String toJson = gson.toJson(users);
        System.out.println(toJson);
        OutputStream fos = new FileOutputStream("users.json");
        fos.write(toJson.getBytes());
    }
}
