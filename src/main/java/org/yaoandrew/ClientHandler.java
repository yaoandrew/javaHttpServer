package org.yaoandrew;

import java.io.*;
import java.net.Socket;

class ClientHandler extends Thread {
    Socket client;
    static final String SIMPLE_GET_RESPONSE = "HTTP/1.1 OK 200\r\n";

    ClientHandler (Socket client) {
        this.client = client;
    }

    public void run () {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            System.out.println("Client connected");
            System.out.println(reader.readLine());

            writer.write(SIMPLE_GET_RESPONSE);
            writer.write("Server: Yao1.0\r\n");
            writer.write("\r\n");
            System.out.println("Response sent");
            writer.close();
            reader.close();


        } catch (Exception e) {
            System.out.println(e);
        }

    }
}