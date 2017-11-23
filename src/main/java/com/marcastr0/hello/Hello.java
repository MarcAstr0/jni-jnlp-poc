package com.marcastr0.hello;

import cz.adamh.utils.NativeUtils;

import javax.swing.*;
import java.io.IOException;

public class Hello extends JFrame {
//    public native void sayHi(String who);

    static {
        try {
            NativeUtils.loadLibraryFromJar("/libHelloImpl.jnilib");
        } catch (IOException e) {
            // This is probably not the best way to handle exception :-)
            e.printStackTrace();
        }
    }

    private Hello(String title) {
        this.setSize(500,500);
        setTitle(title);
    }

    public static void main(String[] args) {
        Hello window = new Hello("Hello World!");
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
