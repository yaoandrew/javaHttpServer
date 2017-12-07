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

            Request request = new Request (reader.readLine());
            System.out.println("Request received");
            System.out.println(request.getHttpMethod() + " " + request.getResource());

            Response response = new Response();

            writer.write(response.getStatusLine());
            writer.write(response.getHeaders());
            writer.write(response.getSeperator());
            writer.write(response.getNewLine());
            System.out.println("Response sent");
            writer.close();
            reader.close();


        } catch (Exception e) {
            System.out.println(e);
        }

    }
}