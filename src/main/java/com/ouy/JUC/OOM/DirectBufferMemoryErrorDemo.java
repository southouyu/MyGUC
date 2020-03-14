package com.ouy.JUC.OOM;

import java.nio.Buffer;
import java.nio.ByteBuffer;

public class DirectBufferMemoryErrorDemo {
    public static void main(String[] args) {
        System.out.println("配置的maxDirectMemory:"+(sun.misc.VM.maxDirectMemory()/(double)1024/1024)+"MB");

        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(6 * 1024 * 1024);

    }

}
