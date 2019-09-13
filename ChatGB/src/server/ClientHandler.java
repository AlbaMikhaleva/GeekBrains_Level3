package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

class ClientHandler {

    private Socket socket;
    private Server server;
    private AuthService authService;
    private DataOutputStream out;
    private DataInputStream in;
    private String nick;
    private List<String> blackList;

    String getNick() {
        return nick;
    }

    ClientHandler(Server server, Socket socket) {
        try {
            this.blackList = new CopyOnWriteArrayList<>();
            this.server = server;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            this.authService = new AuthServiceImpl();
            this.socket = socket;
            new Thread(() -> {
                try {
                    authorization();
                    read();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    close();
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void read() {
        while (true) {
            try {
                String str = in.readUTF();
                //служебные сообщения
                if (str.startsWith("/")) {
                    if (str.equalsIgnoreCase("/end")) {
                        sendMsg("/serverclosed");
                        break;
                    }
                    if (str.startsWith("/w ")) {
                        String[] tokens = str.split(" ", 3);
                        server.sendPersonalMsg(this, tokens[1], tokens[2]);
                    }
                    if (str.startsWith("/blacklist ")) {
                        String[] tokens = str.split(" ");
                        blackList.add(tokens[1]);
                        sendMsg(
                            "Вы добавили пользователя с ником " + tokens[1] + " в черный список!");
                    }

                } else {
                    server.broadcast(this, nick + " " + str);
                }
                System.out.println("Client " + str);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void authorization() throws IOException {
        while (true) {
            String str = in.readUTF();
            if (str.startsWith("/auth")) {
                String[] tokens = str.split(" ");
                String newNick = authService.getNick(tokens[1], tokens[2]);
                if (newNick != null) {
                    if (!server.isNickBusy(newNick)) {
                        sendMsg("/authOK");
                        nick = newNick;
                        server.subscribe(this);
                        break;
                    } else {
                        sendMsg("Учетная запись уже используется!");
                    }
                } else {
                    sendMsg("Неверный логин/пароль");
                }
            }
        }
    }

    private void changeNick () throws IOException {

    }

    private void close() {
        try {
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        server.unsubscribe(this);
    }

    void sendMsg(String message) {
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    boolean checkBlackList(String nick) {
        return blackList.contains(nick);
    }
}
