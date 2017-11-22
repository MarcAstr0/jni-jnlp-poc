jni-jnlp-poc
============

The goal of this POC is to be able to make a [Java Web Start (JNLP)](https://docs.oracle.com/javase/8/docs/technotes/guides/javaws/) application that also uses libraries written in C/C++. In order to achieve that, the [Java Native Interface (JNI)](https://docs.oracle.com/javase/7/docs/technotes/guides/jni/) will be used. The POC aims to also package the C/C++ libraries inside the resulting JAR.

[This tutorial](https://www.protechtraining.com/content/java_fundamentals_tutorial-_java_native_interface_jni) will be the base for the JNI code. For now, the compiled C code will be kept as a resource.

Building and dependencies is handled by [Maven](https://maven.apache.org/). To generate the Jar run:

```
mvn clean compile assembly:single
```

The included `libHelloImpl.jnilib` binary was compiled on macOS Sierra. 