package at.fhj.msd.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class Client {
      private String HOST;
      private int PORT;

      /**
       * Constructor for the Client class.
       *
       * @param HOST The hostname or IP address of the server to connect to.
       * @param PORT The port number on which the server is listening for incoming connections.
       * @throws IllegalArgumentException if the provided host is null or empty, or if the port number is not in the valid range (1024-65535).
       */
      public Client(String HOST, int PORT) {
            if (HOST == null || HOST.isEmpty()) {
              throw new IllegalArgumentException("invalid host");
            }
            if (PORT < 1024 || PORT > 65535) {
              throw new IllegalArgumentException("invalid port");
            }
            this.HOST = HOST;
            this.PORT = PORT;
          }
        
          /**
           * Connects to the server and sends a message.
           * 
           * @return The response from the server.
           */
      @SuppressWarnings("CallToPrintStackTrace")
      public String ask() {
        // Create a socket to connect to the server
        // The socket is closed automatically when the try block is exited
              try (Socket socket = new Socket(this.HOST, this.PORT)) {
                  BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    return in.readLine();
              
            } catch (IOException e) {
              e.printStackTrace();
            }
            return "Something went wrong";
          }
        
          @SuppressWarnings("CallToPrintStackTrace")
          public String ask(String s) {
                try (Socket socket = new Socket(this.HOST, this.PORT))
                {
                        PrintWriter out = new PrintWriter(socket.getOutputStream(), true); // autoFlush helps to flush the stream automatically after each print
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
                        
                        out.println(s);
                        return in.readLine();
                
            } catch (IOException e) {
                  e.printStackTrace();
            }
            return "Something went wrong";
          }
}