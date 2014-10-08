package me.geret13.piplayer.server;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.net.Socket;

public class tester {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("127.0.0.1", 6691);
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

        dos.writeInt(0x00);
        dos.writeUTF("v1.0");

        System.out.println(dis.readInt());
        System.out.println(dis.readBoolean());


    }
}
