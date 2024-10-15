import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 3333);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            String message = in.readUTF();
            System.out.println(message);

            while (true) {
                String response = in.readUTF();
                System.out.println(response);

                if (response.contains("total")) {
                    String choice = br.readLine();
                    out.writeUTF(choice);
                } else {
                    break;
                }
            }

            String finalMessage = in.readUTF();
            System.out.println(finalMessage);

            in.close();
            out.close();
            socket.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}