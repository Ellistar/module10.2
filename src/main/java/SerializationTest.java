import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SerializationTest {
    public static void main(String[] args) throws IOException {

        List<User> users = new ArrayList<>();

        try (InputStream fis = new FileInputStream("src/main/java/file.txt");
             Scanner scanner = new Scanner(fis)) {
            scanner.nextLine();
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] splitLine = line.split(" ");
                String name = splitLine[0];
                int age = Integer.parseInt(splitLine[1]);
               users.add(new User(name, age));
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
