package com.mde.security;

import java.io.UnsupportedEncodingException;

public abstract class PasswordEncoderUtils
{
    public static boolean equals(String expected, String actual)
    {
        byte[] expectedBytes = bytesUtf8(expected);
        byte[] actualBytes = bytesUtf8(actual);
        int expectedLength = expectedBytes == null ? -1 : expectedBytes.length;
        int actualLength = actualBytes == null ? -1 : actualBytes.length;
        if (expectedLength != actualLength)
        {
            return false;
        }
        
        int result = 0;
        for (int i = 0; i < expectedLength; i++)
        {
            result |= expectedBytes[i] ^ actualBytes[i];
        }
        return result == 0;
    }
    
    private static byte[] bytesUtf8(String s)
    {
        if (s == null)
        {
            return null;
        }
        try
        {
            return s.getBytes("UTF-8");
        }
        catch (UnsupportedEncodingException e)
        {
            throw new IllegalStateException("Could not get bytes in UTF-8 format", e);
        }
    }
}
