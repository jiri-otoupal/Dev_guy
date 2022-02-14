package com.jiri;

import java.io.IOException;

public interface RendererCaching {
    abstract void cacheForOutput(char a) throws IOException;
    abstract void cacheForOutput(String a) throws IOException;
}
