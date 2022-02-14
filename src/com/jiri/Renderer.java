package com.jiri;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Renderer implements RendererCaching {
    OutputStream buffer;

    public Renderer() {
        this.buffer = new BufferedOutputStream(System.out);
    }

    public void cacheForOutput(char a) throws IOException {
        this.buffer.write(a);
    }

    public void cacheForOutput(String a) throws IOException {
        for (char ch : a.toCharArray())
            this.buffer.write(ch);
    }

    public void output() throws IOException {
        this.buffer.flush();
    }
}
