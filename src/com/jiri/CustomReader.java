package com.jiri;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;


public class CustomReader {
    private final BlockingQueue<Character> lines = new LinkedBlockingQueue<Character>();
    private char read_char;
    private volatile boolean closed = false;
    private Thread backgroundReaderThread = null;

    public CustomReader(final BufferedReader bufferedReader) {

        backgroundReaderThread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    read_char = (char) bufferedReader.read();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } finally {
                    closed = true;
                }
                try {
                    Thread.sleep(500L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        backgroundReaderThread.setDaemon(true);
        backgroundReaderThread.start();
    }

    public char read() throws IOException {
        return read_char;

    }

    public void close() {
        if (backgroundReaderThread != null) {
            backgroundReaderThread.interrupt();
            backgroundReaderThread = null;
        }
    }
}

