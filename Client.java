package connect;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

class Client {
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
            IOException, ClassNotFoundException
    {
        try (Socket socket = new Socket("localhost", 1234)) {

            // writing to server
            System.out.println("Client connected");
            ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());

            // reading from server
            ObjectInputStream is = new ObjectInputStream(socket.getInputStream());

            // object of scanner class
            Scanner sc = new Scanner(System.in);
            String line = null;
            System.out.println("Enter Message/Login/Register/Exit");
            line = sc.nextLine();
            while (!line.equalsIgnoreCase("Exit")) {
                if(line.equalsIgnoreCase("Register")){
                    os.writeUTF(line);
                    os.flush();
                    while(true) {
                        // sending the user input to server
                        User member = new User();
                        do {
                            member.inputUser();
                        }while(!Check(member.getPassword()));
                        os.writeObject(member);
                        os.flush();
                        // displaying server reply
                        String line1 = is.readUTF();
                        if(!line1.equalsIgnoreCase("Username already existed")){
                            System.out.println("You have to re-register");
                        }
                        else{
                            System.out.println("You have successfully registered");
                            break;
                        }
                    }
                }
                else if(line.equalsIgnoreCase("Login")){
                    os.writeUTF(line);
                    os.flush();
                    while(true) {
                        // sending the user input to server
                        User member = new User();
                        member.inputUser();
                        os.writeObject(member);
                        os.flush();
                        // displaying server reply
                        String line1 = is.readUTF();
                        if(line1.equalsIgnoreCase("Login succeed")){
                            System.out.println("Successful login");
                            break;
                        }
                        else{
                            System.out.println("Incorrect account or password");
                        }
                    }
                }

                else {
                    os.writeUTF(line);
                    os.flush();
                    // sending the user input to server
                    Message news = new Message();
                    news.inputMessage();
                    os.writeObject(news);
                    os.flush();
                    // displaying server reply
                    Message returnMessage = (Message) is.readObject();
                    System.out.println("return Message is=" + returnMessage.getMessage());
                }
            }

            // closing the scanner object
            sc.close();
            socket.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
