package me.geret13.piplayer.server;

import com.google.common.collect.Lists;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Map;

public class ConnectionAcceptorThread extends Thread {

    private ServerSocket serverSocket;

    public List<Connection> connections = Lists.newArrayList();

    public ConnectionAcceptorThread(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Connection connection = new Connection(serverSocket.accept());
                connections.add(connection);
                connection.start();

                System.out.println("New connection from: " + connection.socket.getRemoteSocketAddress().toString());

                Thread.sleep(100);
            } catch (InterruptedException e) {
                return;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
