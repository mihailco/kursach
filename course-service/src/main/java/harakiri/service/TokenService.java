package harakiri.service;

import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

@Service
public class TokenService {

    private volatile String currentToken;

    public synchronized void updateToken() {
        try {
            String command = "yc iam create-token";

            Process process = Runtime.getRuntime().exec(command);
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8))) {
                StringBuilder output = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    output.append(line);
                }
                process.waitFor();

                String newToken = output.toString().trim();
                if (!newToken.isEmpty()) {
                    this.currentToken = newToken;
                    saveTokenToProperties(newToken);
                } else {
                    System.err.println("Received empty token from command output.");
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void saveTokenToProperties(String token) {
        Properties props = new Properties();
        props.setProperty("token.iam", token);
        try (OutputStream out = new FileOutputStream("token.properties")) {
            props.store(out, "Updated token");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getCurrentToken() {
        return currentToken;
    }
}
