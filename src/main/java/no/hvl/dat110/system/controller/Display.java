package no.hvl.dat110.system.controller;

import no.hvl.dat110.rpc.RPCStub;
import no.hvl.dat110.rpc.RPCUtils;

public class Display extends RPCStub {

    private byte RPCID = 1;

    public void write(String message) {

        // TODO
        // implement marshalling, call and unmarshalling for write RPC method

        byte[] request = RPCUtils.marshallString(RPCID, message);
        byte[] response = rpcclient.call(request);

        RPCUtils.unmarshallVoid(response);

    }
}
