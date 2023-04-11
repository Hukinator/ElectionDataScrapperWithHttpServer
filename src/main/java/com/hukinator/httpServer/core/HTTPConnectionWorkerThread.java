package com.hukinator.httpServer.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class HTTPConnectionWorkerThread extends Thread{

    private final static Logger LOGGER = LoggerFactory.getLogger(HTTPConnectionWorkerThread.class);


    private Socket socket;
    private String html;
    public HTTPConnectionWorkerThread(Socket socket, String html){
        this.socket = socket;
        this.html = html;
    }

    @Override
    public void run() {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();

            final String CRLF = "\n\r";
            String response = "HTTP/1.1 200 OK" + CRLF +
                    "Content-Length: " + html.getBytes().length + CRLF +
                    CRLF +
                    html +
                    CRLF + CRLF;
            //more
            outputStream.write(response.getBytes());


            LOGGER.info("Connection Processing finished");
        } catch (IOException e) {
            LOGGER.error("Problem with communication", e);
        }finally {
            if (inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {}

            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {}
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {}
            }
        }
    }
}
