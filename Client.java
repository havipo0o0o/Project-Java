package connect;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

class Client {

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

            while (!"exit".equalsIgnoreCase(line)) {
                System.out.println("Enter Message/Login/Register");
                line = sc.nextLine();
                if(line.equalsIgnoreCase("Register")){
                    while(true) {
                        os.writeUTF(line);
                        os.flush();
                        // sending the user input to server
                        User member = new User();
                        member.InputUser();
                        os.writeObject(member);
                        os.flush();
                        // displaying server reply
                        String line1 = is.readUTF();
                        if(!line1.equalsIgnoreCase("Username already existed")){
                            System.out.println("You have successfully registered");
                            break;
                        }
                    }
                }
                else if(line.equalsIgnoreCase("Login")){
                    while(true) {
                        os.writeUTF(line);
                        os.flush();
                        // sending the user input to server
                        User member = new User();
                        member.InputUser();
                        os.writeObject(member);
                        os.flush();
                        // displaying server reply
                        String line1 = is.readUTF();
                        if(line1.equalsIgnoreCase("Login succeed")){
                            System.out.println("Successful login");
                            break;
                        }
                        System.out.println("Incorrect account or password");
                    }
                }

                else {
                    while (true) {
                        os.writeUTF(line);
                        os.flush();
                        // sending the user input to server
                        Message news = new Message();
                        news.InputMessage();
                        os.writeObject(news);
                        os.flush();
                        // displaying server reply
                        Message returnMessage = (Message) is.readObject();
                        System.out.println("return Message is=" + returnMessage.getMessage());
                    }
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
