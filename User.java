package Test;


import java.io.Serializable;
import java.util.Scanner;

public class User implements Serializable {
    private static final long serialVersionUID = -5399605122490343339L;

    private String username;
    private String password;
    public User() {

    }
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void inputUser() {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter username");
        this.username = myObj.nextLine();
        System.out.println("Enter password:");
        this.password = myObj.nextLine();
    }

}

