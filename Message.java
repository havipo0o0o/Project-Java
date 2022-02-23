package connect;

import java.io.Serializable;
import java.util.Scanner;

public class Message implements Serializable {
    private static final long serialVersionUID = -5399605122490343339L;

    private String message;
    public Message() {

    }

    public Message(String inMessage) {
        this.message = inMessage;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void inputMessage() {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter Message");
        this.message = myObj.nextLine();  // Read massage input
    }

}