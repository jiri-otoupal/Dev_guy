package com.jiri.control;

import com.googlecode.lanterna.input.KeyStroke;

public interface IController {
    /**
     * Invokes action bind on key
     *
     * @param key Key pressed
     */
    void invokeActionFromKey(KeyStroke key);
}
