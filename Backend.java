import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Backend {
    public static void main(String[] args) throws IOException {
        int port = 8080;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/api/analyze", new AnalyzeHandler());
        server.setExecutor(null); // creates a default executor
        System.out.println("Java Backend Server started on port " + port);
        server.start();
    }

    static class AnalyzeHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            // Handle CORS
            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
            exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");

            if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod())) {
                exchange.sendResponseHeaders(204, -1);
                return;
            }

            if ("POST".equalsIgnoreCase(exchange.getRequestMethod())) {
                InputStream is = exchange.getRequestBody();
                String body = new String(is.readAllBytes(), StandardCharsets.UTF_8);

                // Very simple mock response because we can't easily parse JSON without org.json / Jackson
                // This simulates analyzing the resume
                
                int score = 65 + new Random().nextInt(30); // Random score between 65 and 95
                
                String jsonResponse = "{\n" +
                        "  \"score\": " + score + ",\n" +
                        "  \"matchedSkills\": [\"JavaScript\", \"React\", \"HTML\", \"CSS\", \"Communication\"],\n" +
                        "  \"missingSkills\": [\"Python\", \"Machine Learning\", \"Docker\"],\n" +
                        "  \"feedback\": \"Your resume is well structured but lacks some backend technologies. Consider adding more detail to your experience section.\"\n" +
                        "}";

                exchange.getResponseHeaders().set("Content-Type", "application/json");
                byte[] responseBytes = jsonResponse.getBytes(StandardCharsets.UTF_8);
                exchange.sendResponseHeaders(200, responseBytes.length);
                OutputStream os = exchange.getResponseBody();
                os.write(responseBytes);
                os.close();
            } else {
                exchange.sendResponseHeaders(405, -1); // Method Not Allowed
            }
        }
    }
}
