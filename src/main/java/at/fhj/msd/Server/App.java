package at.fhj.msd.Server;

public class App {

      public static void main(String[] args) throws InterruptedException {
            Server server = new Server(1234);
            server.ReadingfromClient();
      }
}
