package at.fhj.msd.Client;

import java.util.Scanner;

public class App {
      static Scanner scanner = new Scanner(System.in);
      public static void main(String[] args) {
             Client client = new Client ("localhost", 1234);
             System.out.println("Enter a message to send to the server:");
             String message = scanner.nextLine();
             client.writetoServer(message);
      }
}
