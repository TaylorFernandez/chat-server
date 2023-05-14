import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApplication {
    public static void main(String[] args) {
        int port = 8080; // Specify the port number to listen on

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started. Listening on port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept(); // Wait for a client connection
                System.out.println("Accepted connection!");
                // Handle the client connection in a separate thread
                Thread clientThread = new Thread(() -> handleClient(clientSocket));
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleClient(Socket clientSocket) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             OutputStream outputStream = clientSocket.getOutputStream()) {

            // Read the data sent by the client
            StringBuilder requestData = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                requestData.append(line).append("\n");
            }

            System.out.println("Received data from client:\n" + requestData.toString());

            // Send a response back to the client
            String response = "Hello, client! I received your message.";
            outputStream.write(response.getBytes());

            // Clean up
            clientSocket.close();
        } catch (IOException e) {
            System.out.println("Connection closed");
        }
    }
}
