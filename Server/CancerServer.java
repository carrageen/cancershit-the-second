package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

class CancerServer {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(4269);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Room room = new Room();
        boolean running = true;

        try {
            while (running) {
                assert serverSocket != null;
                Socket client = serverSocket.accept();
                room.addConnection(client);
            }
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
