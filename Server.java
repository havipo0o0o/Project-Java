package Test;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static final int port = 4444;

    public void  runServer() throws IOException, ClassNotFoundException{
        ServerSocket ss = new ServerSocket(port);
        System.out.println("le systeme est pret pour accepter les connexions");
        Socket socket = ss.accept();
        ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
        User ha = new User("havipo0o0o","Haphong123");
        User m = (User) is.readObject();
        if (ha.getPassword().equalsIgnoreCase(m.getPassword())) {
            if (ha.getUsername().equalsIgnoreCase(m.getUsername())) {
                String line2 = "Username already existed";
                os.writeUTF(line2);
                os.flush();
            } else {
                os.writeUTF("Sorry!!");
            }
        } else {
            os.writeUTF("Sorry!!");
        }
        socket.close();
    }

    public static void main(String[] args) throws ClassNotFoundException, IOException {
        new Server().runServer();
    }
}