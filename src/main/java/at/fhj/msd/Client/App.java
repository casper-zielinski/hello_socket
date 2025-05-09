package at.fhj.msd.Client;

public class App {
      public static void main(String[] args) {
            Client client = new Client ("localhost", 1234);
            System.out.println(client.ask("Nelly"));
      }
}
