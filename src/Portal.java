import java.io.*;
import java.net.*;
import java.nio.*;
import java.nio.file.*;
import java.util.Scanner;

public class Portal {
    
    private static final int SOCKET_PORT = 2626;
    

    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Are you sending or recieving? Please type in S or R.");
        String response = sc.next();
        
        if(response.equalsIgnoreCase("S"))
        {
        //You are sending
         try { 
                /**
                * System.out.println("Please type in the host that you will be connecting to: ");
                * String hostName = sc.next();
                */
                Socket hammerFist = new Socket("10.193.76.66", SOCKET_PORT);
                System.out.println("It worked so far.");
                File punch = new File("C:\\Users\\agrni\\Desktop\\socks.txt");
                //Should connect to host.
                byte[] data = new byte[(int) punch.length()];
                data = Files.readAllBytes(Paths.get("C:\\Users\\agrni\\Desktop\\socks.txt"));
                hammerFist.getOutputStream().write(data);
                hammerFist.close();
                
            }
            catch (IOException e)
            {
                System.out.println("He's dead Jim.");
                System.out.println(e);
            }
            finally
            {
                System.out.println("En Fin.");
            }
            
        }
        else if (response.equalsIgnoreCase("R"))
        {
        //You are receiving
            try
            {
                ServerSocket serverSocket = null;
                
                serverSocket = new ServerSocket(SOCKET_PORT);
                
                Socket socket = null;
                DataInputStream input = null;
                FileOutputStream output = null;
                
                socket = serverSocket.accept();
                
                input = new DataInputStream(socket.getInputStream());
                output = new FileOutputStream("C:\\Users\\agrni\\Desktop\\picture.png");
                
                int count;
                byte[] buffer = new byte[16384];
                while ((count = input.read(buffer)) > 0){
                    output.write(buffer, 0 , count);
                }
                
                input.close();
                output.close();
                socket.close();
                serverSocket.close();
            }
            catch (IOException e)
            {
                System.out.println("gg. " + e);
            }
            finally
            {
                System.out.println("Finished.");
            }
            
        }
        else {
            System.out.println("Exiting...");
            System.out.println("Done");
        }
        sc.close();
    }

}
