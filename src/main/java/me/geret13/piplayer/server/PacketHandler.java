package me.geret13.piplayer.server;

import java.io.IOException;

public class PacketHandler {

    public static void handlePacket(Connection con) throws IOException {
        switch (con.state) {
            case 0:
                handleState0(con);
                break;
            case 1:
                handleState1(con);
                break;
        }
    }

    private static void handleState0(Connection con) throws IOException {
        switch (con.dis.readInt()) {
            case 0x00:
                handleAnnouncePacket(con, con.dis.readUTF());
                break;
        }
    }

    private static void handleAnnouncePacket(Connection con, String version) throws IOException {
        con.dos.writeInt(0x00);
        if (!version.equals(PiPlayerServer.VERSION)) {
            con.dos.writeBoolean(false);
            con.dos.writeUTF(PiPlayerServer.VERSION);
            con.kill();
        }
        con.dos.writeBoolean(true);
        con.state = 1;
    }

    private static void handleState1(Connection con) throws IOException {
        switch (con.dis.readInt()) {
            case 0x00:
                handleControlPacket(con, con.dis.readInt());
                break;
        }
    }

    private static void handleControlPacket(Connection con, int control) {
        switch (control) {
            case 0x00: // Play
                break;
            case 0x01: // Pause
                break;
            case 0x02: // Stop
                break;
            case 0x03: // Next
                break;
            case 0x04: // Previous
                break;
            case 0x05: // Forward
                break;
            case 0x06: // Backward
                break;
        }
    }
}
