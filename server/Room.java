package server;

import java.net.Socket;
import java.util.ArrayList;

import cancerApi.Message;

class Room {
    private ArrayList<UserConnection> userConnections;
    private String name;
    private int rekt;

    public Room() {
        userConnections = new ArrayList<UserConnection>();
        setName("room");
    }

    public void addConnection(Socket socket) {
        UserConnection uc = new UserConnection(socket, this);
        userConnections.add(uc);
        rekt++;
        new Thread(uc).start();
    }

    public void send(Message msg) {
        for (UserConnection u : userConnections) {
            u.send(msg);
        }
        System.out.println(name + ": " + msg.text);
    }

    public void remove(UserConnection uc) {
        userConnections.remove(uc);
        rekt--;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getCommands() {
        return "*******\n" +
        "* !help : to show this message\n" +
        "* !name : to get your username\n" +
        "* !yell 'sample text' : to yell your following message\n" +
        "* !room : to get the name of the current room\n" +
        "* !dudes : displays the amount of currently connected dudes\n" +
        "* Kappa : display some spicy ASCII Kappa\n" +
        "*******";
    }

    public String getDudes() {

        return "/******* " +
        rekt +
        " *******\\";
    }
}
