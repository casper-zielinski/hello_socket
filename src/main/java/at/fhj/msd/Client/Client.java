package at.fhj.msd.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class Client {
      private String HOST;
      private int PORT;

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
        
      @SuppressWarnings("CallToPrintStackTrace")
      public String ask() {
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
                        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        
                        out.println(s);
                        return in.readLine();
                
            } catch (IOException e) {
                  e.printStackTrace();
            }
            return "Something went wrong";
          }
}