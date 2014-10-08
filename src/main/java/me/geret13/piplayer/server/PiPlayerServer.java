package me.geret13.piplayer.server;

public class PiPlayerServer {
    private static PiPlayerServer instance;

    public final static String VERSION = "v1.0";

    public ConnectionAcceptorThread connectionAcceptorThread;

    public static void main(String[] args) {
        instance = new PiPlayerServer();
    }

    public static PiPlayerServer instance() {
        return instance;
    }

    public PiPlayerServer() {
        try {
            this.connectionAcceptorThread = new ConnectionAcceptorThread(6691);
            this.connectionAcceptorThread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
