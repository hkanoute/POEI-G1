import utils.ConfigReader;
import utils.XrayApiClient;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class main {
    public static void main(String[] args) throws IOException, InterruptedException {

        XrayApiClient xray = new XrayApiClient();
        System.getenv().forEach((key, value) -> System.out.println(key + "=" + value));

        //Path myDirectory = Path.of("src/test/resources/features");

      //  xray.export("/export/cucumber?keys=" + ConfigReader.getProperty("KEYS"), myDirectory);


    }
}
