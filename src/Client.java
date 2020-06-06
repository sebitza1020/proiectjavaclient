import javax.swing.*;
import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Client {
    JTextField tPhone;

    public Client(String address, int port) {
        Contact c = new Contact();
        ContactsApp cV = new ContactsApp();
        cV.display(c);
        try {
            Scanner sc = new Scanner(System.in);
            Socket socket = new Socket(address, port);
            System.out.println("Contacts App");

            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

            while (true) {
                System.out.println(inputStream.readUTF());
                String toSend = sc.nextLine();
                outputStream.writeUTF(toSend);

                if(toSend.equals("Done")){
                    System.out.println("Connection: " + socket + " terminated!");
                    socket.close();
                    System.exit(1);
                    break;
                }

                String received = inputStream.readUTF();
                System.out.println(received);
            }
            sc.close();
            inputStream.close();
            outputStream.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
