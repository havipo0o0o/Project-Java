package connect;

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
    public boolean Check(String s){
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
        if(t > 0 && t1 > 0 && t2 > 0) {
            return true;
        }
        return false;
    }
    public void InputUser() {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter username");
        String userName = myObj.nextLine();
        while(true) {

            System.out.println("Enter password:");
            String password = myObj.nextLine();
            if(Check(password)){
                break;
            }
        }
    }

}
