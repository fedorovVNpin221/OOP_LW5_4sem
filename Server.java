import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(3333);
            Socket socket = serverSocket.accept();
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            int total = 0;
            out.writeUTF("Welcome to the game of 21!");
            boolean newgame = true;
            while (total < 21) {

                if (newgame){
                    out.writeUTF("Your total is " + total + ". Hit or stand? (h/s)");}
                newgame = false;
                String choice = in.readUTF();

                if (choice.equals("h")) {
                    int card = (int)(Math.random() * 11) + 1;
                    total += card;
                    if (total < 21)
                        out.writeUTF("You draw a " + card + ". Your new total is " + total + ". Hit or stand? (h/s)");
                    else {
                        if (total == 21)
                            out.writeUTF("You draw a " + card + ". Your new total is " + total + ".\nYou win! You got 21!");
                        else
                            out.writeUTF("You draw a " + card + ". Your new total is " + total + ".\nYou lose! You got more than 21");
                    }
                } else if (choice.equals("s")) {
                    out.writeUTF("You stand at " + total + ". Server has 21. You lose!");
                    break;
                }
            }

            in.close();
            out.close();
            socket.close();
            serverSocket.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}