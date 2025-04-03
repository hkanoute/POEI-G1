package utils;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.json.JSONObject;

public class XrayApiClient {
    private final String baseUrl;
    private final String clientId;
    private final String clientSecret;
    private final HttpClient httpClient;
    private String authToken;
    private long tokenExpiryTime;


    public XrayApiClient() {
        this.baseUrl = "https://xray.cloud.getxray.app/api/v2";
        this.clientId = System.getenv("CLIENT_ID");
        this.clientSecret = System.getenv("CLIENT_SECRET");

        this.httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(20))
                .build();
    }


    public void authenticate() throws IOException, InterruptedException {
        String authUrl = baseUrl + "/authenticate";
        JSONObject jo = new JSONObject();
        jo.put("client_id", clientId);
        jo.put("client_secret", clientSecret);


        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(authUrl))
                .header("Content-Type", "application/json; charset=UTF-8")
                .header("Accept", "application/json; charset=UTF-8")
                .POST(HttpRequest.BodyPublishers.ofString(jo.toString()))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            authToken = response.body().replace("\"", "");
            tokenExpiryTime = System.currentTimeMillis() + 3600000;
        } else {
            throw new IOException("Authentication failed: " + response.statusCode() + " - " + response.body());
        }
    }

    private boolean needsAuthentication() {
        return authToken == null || System.currentTimeMillis() > tokenExpiryTime;
    }


    public String get(String endpoint) throws IOException, InterruptedException {
        if (needsAuthentication()) {
            authenticate();
        }

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + endpoint))
                .header("Authorization", "Bearer " + authToken)
                .header("Content-Type", "application/json; charset=UTF-8")
                .header("Accept", "application/json; charset=UTF-8")
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() >= 200 && response.statusCode() < 300) {
            return response.body();
        } else {
            throw new IOException("GET request failed: " + response.statusCode() + " - " + response.body());
        }
    }


    public Path export(String endpoint, Path outputDirectory) throws IOException, InterruptedException {
        if (needsAuthentication()) {
            authenticate();
        }

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + endpoint))
                .header("Authorization", "Bearer " + authToken)
                .header("Content-Type", "application/json; charset=UTF-8")
                .header("Accept", "application/zip; charset=UTF-8")
                .GET()
                .build();

        // Get the response as bytes
        HttpResponse<byte[]> response = httpClient.send(
                request,
                HttpResponse.BodyHandlers.ofByteArray()
        );

        if (response.statusCode() != 200) {
            throw new IOException("Export failed: " + response.statusCode());
        }

        // Create the output directory if it doesn't exist
        Files.createDirectories(outputDirectory);

        // Define the path for the zip file
        Path zipFilePath = outputDirectory.resolve("features.zip");
        Files.write(zipFilePath, response.body());

        // Extract the zip file
        try (ZipInputStream zis = new ZipInputStream(Files.newInputStream(zipFilePath))) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (!entry.isDirectory()) {
                    Path filePath = outputDirectory.resolve(entry.getName());
                    Files.createDirectories(filePath.getParent());
                    Files.copy(zis, filePath, StandardCopyOption.REPLACE_EXISTING);
                }
                zis.closeEntry();
            }
        }

        System.out.println("Features extracted to: " + outputDirectory);
        return outputDirectory;
    }



    public String post(String endpoint, String requestBody) throws IOException, InterruptedException {
        if (needsAuthentication()) {
            authenticate();
        }

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + endpoint))
                .header("Authorization", "Bearer " + authToken)
                .header("Content-Type", "application/json; charset=UTF-8")
                .header("Accept", "application/json; charset=UTF-8")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() >= 200 && response.statusCode() < 300) {
            return response.body();
        } else {
            throw new IOException("POST request failed: " + response.statusCode() + " - " + response.body());
        }
    }

}