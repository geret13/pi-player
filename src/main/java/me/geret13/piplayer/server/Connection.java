package me.geret13.piplayer.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Connection extends Thread {

    public Socket socket;
    public DataInputStream dis;
    public DataOutputStream dos;

    public int state = 0;

    public Connection(Socket socket) throws IOException {
        this.socket = socket;
        this.dis = new DataInputStream(this.socket.getInputStream());
        this.dos = new DataOutputStream(this.socket.getOutputStream());
    }

    @Override
    public void run() {
        while (true) {
            try {
                PacketHandler.handlePacket(this);
                Thread.sleep(10);
            } catch (InterruptedException e) {
                kill();
                return;
            } catch (IOException e) {
                e.printStackTrace();
                kill();
                return;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void kill() {
        PiPlayerServer.instance().connectionAcceptorThread.connections.remove(this);
        this.interrupt();
        try {
            socket.close();
        } catch (Exception e) {
            // Ignore
        }
        socket = null;
    }
}
