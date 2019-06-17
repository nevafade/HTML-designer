 import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.net.ServerSocket;
 import java.net.Socket;
 import java.util.*;
 import java.io.OutputStream;
 import java.io.*;



 public class SimpleHTTPServer
 {

    

 public static void main(String[] args) throws Exception
 {
 final ServerSocket server = new ServerSocket(8080);
 System.out.println("Listening for connection on port 8080 ....");
 while (true){
 try (Socket socket = server.accept())
 {
 
 String httpMethod ,fileName="",httpVersion;
 boolean badRequest=true;
 
 InputStream input = socket.getInputStream();
String END = "\r\n";
int avaliable = 0;
        String received = "";

        while (!received.endsWith(END + END)) {
            avaliable = input.available();

            byte[] bytes = new byte[avaliable];
            input.read(bytes);

            received += new String(bytes);
        }
System.out.println("RECEIVED : "+received);
    StringTokenizer st = new StringTokenizer(received);

        if(received.startsWith("GET") || received.startsWith ("HEAD") && received.endsWith("HTTP/1.0") || received.endsWith("HTTP/1.1")){

            badRequest = false;

            if(st.countTokens() < 3){
                badRequest = true;
            }       
        }

        if(!badRequest){

            httpMethod = st.nextToken();
            fileName = st.nextToken();
            httpVersion=st.nextToken();

        }

	System.out.println("FileName : "+fileName);

    if(fileName.equals("/")){
    PrintWriter out = new PrintWriter(socket.getOutputStream());
    out.println("HTTP/1.1 200 OK");
    out.println("Content-Type: text/html");
    out.println("\r\n");

    BufferedReader in=new BufferedReader(new InputStreamReader(new FileInputStream("updown.txt")));
    String line = in.readLine();
    while(line!=null){
	out.println(line);
        line = in.readLine();
    }
  
    
    out.flush();

    out.close();
	}else{
		PrintWriter out = new PrintWriter(socket.getOutputStream());
    out.println("HTTP/1.1 200 OK");
    out.println("Content-Type: text/html");
    out.println("\r\n");

	out.flush();

    out.close();
		
	}
    
    
}




 
 }
 // spin forever 
 }
 }
 
