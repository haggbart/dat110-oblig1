package no.hvl.dat110.messaging.tests;

import no.hvl.dat110.messaging.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class TestMessaging {

    private boolean failure;

    @Test
    public void test() {

        byte[] clientsent = {1, 2, 3, 4, 5};

        Thread server = new Thread(() -> {

            try {
                System.out.println("Messaging server - start");

                MessagingServer server1 = new MessagingServer(MessageConfig.MESSAGINGPORT);

                Connection connection = server1.accept();

                Message request = connection.receive();

                byte[] serverreceived = request.getData();

                Message reply = new Message(serverreceived);

                connection.send(reply);

                connection.close();

                server1.stop();

                System.out.println("Messaging server - stop");

                assertTrue(Arrays.equals(clientsent, serverreceived));

            } catch (Exception e) {
                e.printStackTrace();
                failure = true;
            }

        });

        Thread client = new Thread(() -> {

            try {

                System.out.println("Messaging client - start");

                MessagingClient client1 = new MessagingClient(MessageConfig.MESSAGINGHOST,
                        MessageConfig.MESSAGINGPORT);

                Connection connection = client1.connect();

                Message message1 = new Message(clientsent);

                connection.send(message1);

                Message message2 = connection.receive();

                byte[] clientreceived = message2.getData();

                connection.close();

                System.out.println("Messaging client - stop");

                assertTrue(Arrays.equals(clientsent, clientreceived));
            } catch (Exception e) {
                e.printStackTrace();
                failure = true;
            }
        });

        try {
            server.start();
            client.start();

            server.join();
            client.join();

        } catch (Exception e) {
            e.printStackTrace();
            failure = true;
        } finally {
            if (failure) {
                fail();
            }
        }

    }
}
