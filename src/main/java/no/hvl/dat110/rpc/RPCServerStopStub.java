package no.hvl.dat110.rpc;

public class RPCServerStopStub extends RPCStub {

    // client-side (local) implementation of the built-in server stop RPC method
    public void stop() {

        byte[] request = RPCUtils.marshallVoid(RPCCommon.RPIDSTOP);

        rpcclient.call(request);
    }
}
