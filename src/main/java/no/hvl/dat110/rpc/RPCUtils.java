package no.hvl.dat110.rpc;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class RPCUtils {

    // Utility methods for marshalling and marshalling of parameters and return values
    // in RPC request and RPC responses
    // data bytearrays and return byte arrays is according to the
    // RPC message syntax [rpcid,parameter/return value]

    public static byte[] marshallString(byte rpcid, String str) {

        byte[] encoded;

        // TODO: marshall RPC identifier and string into byte array

        encoded = new byte[str.length() + 1];
        encoded[0] = rpcid;
        System.arraycopy(str.getBytes(StandardCharsets.UTF_8),
                0, encoded, 1, str.length());

        return encoded;
    }

    public static String unmarshallString(byte[] data) {

        String decoded;

        // TODO: unmarshall String contained in data into decoded

        decoded = new String(data, 1, data.length - 1, StandardCharsets.UTF_8);

        return decoded;
    }

    public static byte[] marshallVoid(byte rpcid) {

        byte[] encoded;

        // TODO: marshall RPC identifier in case of void type
        encoded = new byte[1];
        encoded[0] = rpcid;

        return encoded;

    }

    public static void unmarshallVoid(byte[] data) {

        // TODO: unmarshall void type
    }

    public static byte[] marshallBoolean(byte rpcid, boolean b) {

        byte[] encoded = new byte[2];

        encoded[0] = rpcid;

        if (b) {
            encoded[1] = 1;
        } else {
            encoded[1] = 0;
        }

        return encoded;
    }

    public static boolean unmarshallBoolean(byte[] data) {

        return (data[1] > 0);

    }

    public static byte[] marshallInteger(byte rpcid, int x) {

        byte[] encoded;

        // TODO: marshall RPC identifier and string into byte array
        encoded = new byte[5];

        encoded[0] = rpcid;
        byte[] integer = toByteArray(x);
        System.arraycopy(integer, 0, encoded, 1, integer.length);
        return encoded;
    }

    private static byte[] toByteArray(int value) {
        return new byte[]{
                (byte) (value >> 24),
                (byte) (value >> 16),
                (byte) (value >> 8),
                (byte) value};
    }


    public static int unmarshallInteger(byte[] data) {

        // TODO: unmarshall integer contained in data

        byte[] integer = new byte[4];
        System.arraycopy(data, 1, integer, 0, 4);

        return toInteger(integer);
    }

    private static int toInteger(byte[] bytes) {
        return ByteBuffer.wrap(bytes).getInt();
    }
}
