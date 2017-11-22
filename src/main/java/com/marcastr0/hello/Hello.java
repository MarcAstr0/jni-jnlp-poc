package com.marcastr0.hello;

import cz.adamh.utils.NativeUtils;

import java.io.IOException;

public class Hello {
    public native void sayHi(String who);

    static {
        try {
            NativeUtils.loadLibraryFromJar("/libHelloImpl.jnilib");
        } catch (IOException e) {
            // This is probably not the best way to handle exception :-)
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Hello hello = new Hello();
        hello.sayHi(args[0]);
    }
}
