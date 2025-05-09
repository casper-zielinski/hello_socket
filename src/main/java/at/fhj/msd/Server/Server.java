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

    public Server(int PORT) {
        if (PORT < 1024 || PORT > 65535) {
          throw new IllegalArgumentException("invalid value for port");
        }
        this.PORT = PORT;
        this.count = 0;
      }
    

    @SuppressWarnings("CallToPrintStackTrace")
      public void listen() {
        System.out.printf("Start listening on port %d\n\n>Press Ctrl+C to stop<\n\n", this.PORT);
        while (true)
        {
            try (ServerSocket server = new ServerSocket(this.PORT)) {
            Socket socket = server.accept();

              BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
              PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
              /*
                  It is important that the client also performs a flush() when sending,
                  otherwise it will not work! The easiest way to do this is, of course, 
                  with the autoFlush flag when creating the object → PrintWriter out = new PrintWriter(socket.getOutputStream(), true); 
               */
              
              System.out.printf("client #%d connected...\n", ++this.count);
              String s = "socket";
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
    
}
