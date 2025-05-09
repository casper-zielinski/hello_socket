package at.fhj.msd.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Hello world!
 */
public class Server {
    public static final int PORT = 1234;
    public static void main(String[] args) throws IOException {
        System.out.println("Waiting for client connection..."); // Printing before waiting for a response
        try (ServerSocket server = new ServerSocket(PORT); Socket socket = server.accept()) { // Making a ServerSocket and declaring a PORT and then a socket and accepting the ServerSocket, waiting 
            // until response. with server.accept() the socket is open, waiting for a connection. Until someone connects to the server,
            // the code is blocked. Once a connection is established, the "next line of code" is run on the server.
            // server.accept() -> wait until client connects

            System.out.println("Client connected: " + server.getInetAddress().getHostAddress()); // Printing afer connectiong
            
            // With thes lines, we (try) to create the reader and write objects on the server side, based on the established connection (socket), so that we can communicate
            try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
                out.println("Hello from server!"); // Printing to the Client, because --> PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
                System.out.printf("Message sent to client: %s", in.readLine()); // Reading from the Client, because --> BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())
                
            }
        }
    }
}
