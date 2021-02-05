package no.hvl.dat110.rpc;

// server-side (remote objects) must implement this interface

public interface RPCImpl {

    byte[] invoke(byte[] request);
}
