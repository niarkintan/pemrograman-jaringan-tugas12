package pemrograman.jaringan.tugas12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Asus
 */
public class EchoServer {
    private ServerSocket serverSocket;
    private Socket clieSocket;
    private PrintWriter out;
    private BufferedReader in;
    
    public void start(int port){
        try {
            serverSocket = new ServerSocket(port);
            clieSocket = serverSocket.accept();
            out = new PrintWriter(clieSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clieSocket.getInputStream()));
            
            String inputLine;
            
            while((inputLine = in.readLine()) != null){
                out.println(inputLine);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void stop(){
        try {
            in.close();
            out.close();
            serverSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    public static void main(String[] args) {
        EchoServer server = new EchoServer();
        server.start(6666);
    }
}
