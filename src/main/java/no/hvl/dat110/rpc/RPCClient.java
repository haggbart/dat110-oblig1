package no.hvl.dat110.rpc;

import no.hvl.dat110.messaging.Connection;
import no.hvl.dat110.messaging.Message;
import no.hvl.dat110.messaging.MessagingClient;

public class RPCClient {

    private MessagingClient msgclient;
    private Connection connection;

    public RPCClient(String server, int port) {

        msgclient = new MessagingClient(server, port);
    }

    public void register(RPCStub remote) {
        remote.register(this);
    }

    public void connect() {

        // TODO: connect using the underlying messaging layer connection
        connection = msgclient.connect();
    }

    public void disconnect() {

        // TODO: disconnect/close the underlying messaging connection
        connection.close();
    }

    public byte[] call(byte[] rpcrequest) {

        byte[] rpcreply = null;
		
		/* TODO: 
		
		Make a remote call on the RPC server by sending the RPC request message
		and receive an RPC reply message
		
		rpcrequest is the marshalled rpcrequest from the client-stub
		rpctreply is the rpcreply to be unmarshalled by the client-stub
		
		*/

        Message message = new Message(rpcrequest);

        connection.send(message);

        rpcreply = connection.receive().getData();

        return rpcreply;

    }

}
