jni-jnlp-poc
============

The goal of this POC is to be able to make a [Java Web Start (JNLP)](https://docs.oracle.com/javase/8/docs/technotes/guides/javaws/) application that also uses libraries written in C/C++. In order to achieve that, the [Java Native Interface (JNI)](https://docs.oracle.com/javase/7/docs/technotes/guides/jni/) will be used. The POC aims to also package the C/C++ libraries inside the resulting JAR.