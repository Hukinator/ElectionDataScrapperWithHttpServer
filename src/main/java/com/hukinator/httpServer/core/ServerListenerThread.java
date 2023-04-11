package com.hukinator.httpServer.core;

import com.hukinator.httpServer.httpServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListenerThread extends Thread {

    private final static Logger LOGGER = LoggerFactory.getLogger(ServerListenerThread.class);



    private int port;
    private String webroot;
    private ServerSocket serverSocket;
    private String html;

    public ServerListenerThread(int port, String webroot, String html) throws IOException {
        this.port = port;
        this.webroot = webroot;
        this.html = html;
        this.serverSocket = new ServerSocket(this.port);

    }

    @Override
    public void run() {
        try {
            while(serverSocket.isBound() && !serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();

                LOGGER.info(" connection accepted" + socket.getInetAddress());

                HTTPConnectionWorkerThread workerThread = new HTTPConnectionWorkerThread(socket, html);
                workerThread.start();

            }
        } catch (IOException e) {
            LOGGER.error("Problem with setting Socket", e);
        } finally {
            if (serverSocket != null){
                try {
                    serverSocket.close();
                } catch (IOException e) {}
            }
        }
    }
}
