package com.jiri.control;

import com.googlecode.lanterna.input.KeyStroke;

public interface IController {
    void invokeActionFromKey(KeyStroke key);
}
