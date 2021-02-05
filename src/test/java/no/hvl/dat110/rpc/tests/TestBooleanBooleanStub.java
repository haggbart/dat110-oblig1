package no.hvl.dat110.rpc.tests;

import no.hvl.dat110.rpc.RPCStub;
import no.hvl.dat110.rpc.RPCUtils;

public class TestBooleanBooleanStub extends RPCStub {

    private final byte RPCID = 4;

    public boolean m(boolean b) {

        byte[] request = RPCUtils.marshallBoolean(RPCID, b);

        byte[] reply = rpcclient.call(request);

        return RPCUtils.unmarshallBoolean(reply);
    }
}
