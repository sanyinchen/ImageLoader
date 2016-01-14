/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.tony.imageloaderlibrary.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by sanyinchen on 16/1/14.
 */
public class StreamUtils {
    public static void closeFile(Closeable c) {
        try {
            c.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
