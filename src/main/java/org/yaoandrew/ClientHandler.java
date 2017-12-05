package org.yaoandrew;

import java.io.*;
import java.net.Socket;

class ClientHandler implements Runnable {
    Socket client;

    ClientHandler (Socket client) {
        this.client = client;
    }

    public void run () {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            System.out.println("Client connected");
            System.out.println(reader.readLine());

            writer.write(Response.getStatusLine());
            writer.newLine();
            writer.write("Server: Yao1.0");
            writer.newLine();
            writer.newLine();
            writer.write("Hello World");
            writer.newLine();
            System.out.println("Response sent");
            writer.close();
            reader.close();


        } catch (Exception e) {
            System.out.println(e);
        }

    }
}