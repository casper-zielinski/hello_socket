package at.fhj.msd.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class Client {
      private static final String HOST = "localhost";
      private static final int PORT = 1234;

      public static void main(String[] args) throws IOException {
            try(Socket socket = new Socket(HOST, PORT))
            {
                  try (BufferedReader in = new BufferedReader(new 
                  InputStreamReader(socket.getInputStream())  );
                  PrintWriter out = new PrintWriter(socket.getOutputStream(), true))
                  {
                        System.out.printf("Messege from Server: %s\n", in.readLine());
                        out.println("Hello from Client");
                  }
            }
      }

}
