package org.yaoandrew;

import java.io.*;
import java.net.Socket;

class ClientHandler implements Runnable {
    Socket client;

    ClientHandler(Socket client) {
        this.client = client;
    }

    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            System.out.println("Client connected");
            System.out.println(reader.readLine());

            writer.write(Response.getStatusLine());
            writer.write(Response.getHeaders());
            writer.write(Response.getSeperator());
            writer.write(Response.getNewLine());
            System.out.println("Response sent");
            writer.close();
            reader.close();


        } catch (Exception e) {
            System.out.println(e);
        }

    }
}