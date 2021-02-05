package no.hvl.dat110.rpc.tests;

import no.hvl.dat110.rpc.RPCStub;
import no.hvl.dat110.rpc.RPCUtils;

public class TestIntIntStub extends RPCStub {

    private final byte RPCID = 3;

    public int m(int x) {

        byte[] request = RPCUtils.marshallInteger(RPCID, x);

        byte[] reply = rpcclient.call(request);

        return RPCUtils.unmarshallInteger(reply);
    }
}
