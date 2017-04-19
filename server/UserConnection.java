package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import cancerApi.Message;

class UserConnection implements Runnable {
    private Socket client;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private Room room;
    private User user;
    private CommandScanner cm = new CommandScanner();

    public UserConnection(Socket socket, Room room) {
        this.client = socket;
        this.user = new User();
        this.room = room;

        cm.bind("!help", (s) -> send(new Message("Currently known commands: \n" + room.getCommands())));
        cm.bind("!name", (s) -> send(new Message("Your name is " + user.getName())));
        cm.bind("!room", (s) -> send(new Message("You are in room " + room.getName())));
        cm.bind("!yell", (s) -> {
            for (String b : s) room.send(new Message(user.getName() + ": " + b.toUpperCase()));
        });
        cm.bind("!dudes", (s) -> send(new Message("Currently connected dudes: \n" + room.getDudes())));
        cm.bind("Kappa", (s) -> send(new Message("░░░░░░░░░\n" +
                "░░░░▄▀▀▀▀▀█▀▄▄▄▄░░░░\n" +
                "░░▄▀▒▓▒▓▓▒▓▒▒▓▒▓▀▄░░\n" +
                "▄▀▒▒▓▒▓▒▒▓▒▓▒▓▓▒▒▓█░\n" +
                "█▓▒▓▒▓▒▓▓▓░░░░░░▓▓█░\n" +
                "█▓▓▓▓▓▒▓▒░░░░░░░░▓█░\n" +
                "▓▓▓▓▓▒░░░░░░░░░░░░█░\n" +
                "▓▓▓▓░░░░▄▄▄▄░░░▄█▄▀░\n" +
                "░▀▄▓░░▒▀▓▓▒▒░░█▓▒▒░░\n" +
                "▀▄░░░░░░░░░░░░▀▄▒▒█░\n" +
                "░▀░▀░░░░░▒▒▀▄▄▒▀▒▒█░\n" +
                "░░▀░░░░░░▒▄▄▒▄▄▄▒▒█░\n" +
                " ░░░▀▄▄▒▒░░░░▀▀▒▒▄▀░░\n" +
                "░░░░░▀█▄▒▒░░░░▒▄▀░░░\n" +
                "░░░░░░░░▀▀█▄▄▄▄▀")));
    }

    @Override
    public void run() {
        try (
                ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
                ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream())) {
            input = ois;
            output = oos;

            room.send(new Message(user + " connected"));

            try {
                while (!client.isInputShutdown()) {
                    Message msg = (Message) input.readObject();
                    handleMessage(msg);
                }
            } catch (IOException ioe) {
                disconnect();
            } catch (ClassNotFoundException cnfe) {
                cnfe.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        room.send(new Message(user + " disconnected"));
        room.remove(this);
    }

    public synchronized void send(Message msg) {
        messageDelay();
        try {
            output.writeObject(msg);
            output.flush();
        } catch (IOException e) {
            disconnect();
        }
    }

    private void disconnect() {
        try {
            input.close();
            output.close();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void messageDelay() {
        try {
            int messageDelay = 17;
            Thread.sleep(messageDelay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void handleMessage(Message msg) {
        if (!cm.scan(msg.text)) {
            room.send(new Message(user.getName() + ": " + msg.text));
        }
    }
}

