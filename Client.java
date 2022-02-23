package Test;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static boolean Check(String s){
        if (s.length() < 8){
            return false;
        }
        int t=0,t1=0,t2=0;
        for(int count=0 ; count  < s.length(); count++){
            if(s.charAt(count) > 'A' && s.charAt(count) <'Z'){
                t++;
            }
            if(s.charAt(count) > 'a' && s.charAt(count) <'z'){
                t1++;
            }
            if(s.charAt(count) > '0' && s.charAt(count) < '9'){
                t2++;
            }
        }
        return t > 0 && t1 > 0 && t2 > 0;
    }
    public static void main(String[] args) throws UnknownHostException,
            IOException, ClassNotFoundException {
        System.out.println("welcome client");
        Socket socket = new Socket("localhost", 4444);
        System.out.println("Client connected");
        ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
        System.out.println("Ok");
        // sending the user input to server
        User member = new User();
        do {
            member.inputUser();
        }while(!Check(member.getPassword()));
        os.writeObject(member);
        os.flush();
        // displaying server reply
        System.out.println("Envoi des informations au serveur ...");

        ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
        String line1 = is.readUTF();
        System.out.println(line1);
        if(!line1.equalsIgnoreCase("Username already existed")){

            System.out.println("You have to re-register");
        }
        else{
            System.out.println("You have successfully registered");
        }
        socket.close();
    }
}