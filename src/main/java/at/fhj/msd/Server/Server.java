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
    private int PORT;
    private int count;
    private String message;
    public boolean isRunning = true;

    /**
     * Constructor for the Server class.
     *
     * @param PORT The port number on which the server will listen for incoming connections.
     * @param count The number of clients connected to the server.
     *             This is initialized to 0 when the server is created.
     * @throws IllegalArgumentException if the provided port number is not in the valid range (1024-65535).
     * The range is chosen to avoid using well-known ports (0-1023). 
     */
    public Server(int PORT) {
        if (PORT < 1024 || PORT > 65535) {
          throw new IllegalArgumentException("invalid value for port");
        }
        this.PORT = PORT;
        this.count = 0;
      }
    

      /**
       * Starts the server and listens for incoming connections.
       * When a client connects, it reads a message from the client and sends a response back.
       * The server runs indefinitely until interrupted with Ctrl+C command. (this is because of the while(true) loop)
       * @throws InterruptedException 
       */
    @SuppressWarnings("CallToPrintStackTrace")
      public void listen() throws InterruptedException {
        System.out.printf("Start listening on port %d\n\n>Press Ctrl+C to stop<\n\n", this.PORT);
        while (isRunning())
        {
          
            //Initializes a ServerSocket on the specified port and waits for a client to connect.
            //The ServerSocket is closed automatically when the try block is exited.
            try (ServerSocket server = new ServerSocket(this.PORT)) {
            Socket socket = server.accept(); // Waits for a client to connect

              BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
              PrintWriter out = new PrintWriter(socket.getOutputStream(), true); //autoFlush helps to flush the stream automatically after each print
              /*
                  It is important that the client also performs a flush() when sending,
                  otherwise it will not work! The easiest way to do this is, of course, 
                  with the autoFlush flag when creating the object → PrintWriter out = new PrintWriter(socket.getOutputStream(), true); 
               */
              
              System.out.printf("client #%d connected...\n", ++this.count);
              String s = "socket";
              Thread.sleep(1000); // Sleep for 1 second to simulate some processing time
              if (in.ready()) { // to check if there is any data to read, with the [].ready() method of the BufferedReader 
                s = in.readLine();
              }
              out.println("hello" + " " + s);
          
            } catch (IOException e) {
            System.out.println("Server error");
            e.printStackTrace();
            }
        }
      }

      public boolean isRunning() {
        return isRunning;
      }

      public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
      }

    @SuppressWarnings("CallToPrintStackTrace")
      public void ReadingfromClient() throws InterruptedException {
         while (isRunning)
        {
          
            //Initializes a ServerSocket on the specified port and waits for a client to connect.
            //The ServerSocket is closed automatically when the try block is exited.
            try (ServerSocket server = new ServerSocket(this.PORT)) {
            Socket socket = server.accept(); // Waits for a client to connect

              BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
              // PrintWriter out = new PrintWriter(socket.getOutputStream(), true); //autoFlush helps to flush the stream automatically after each print
              /*
                  It is important that the client also performs a flush() when sending,
                  otherwise it will not work! The easiest way to do this is, of course, 
                  with the autoFlush flag when creating the object → PrintWriter out = new PrintWriter(socket.getOutputStream(), true); 
               */
              System.out.printf("client #%d connected...\n", ++this.count);
              String message = " ";
              Thread.sleep(1000); // Sleep for 1 second to simulate some processing time
              if (in.ready()) { // to check if there is any data to read, with the [].ready() method of the BufferedReader 
                message = in.readLine();
              }
              if (message.equals("/exit")) {
                System.out.println("Client disconnected");
                break;
              }
              System.out.println(message);
              
          
            } catch (IOException e) {
            System.out.println("Server error");
            e.printStackTrace();
            }
        }
      }

      public String getmessage() {
        return this.message;
      }
    
}
