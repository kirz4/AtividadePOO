

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static org.junit.Assert.assertEquals;

public class ChatTest {

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private ObjectOutputStream clientOutput;
    private ObjectInputStream clientInput;
    private ObjectOutputStream serverOutput;
    private ObjectInputStream serverInput;

    @Before
    public void setUp() throws IOException {
        serverSocket = new ServerSocket(12345);
        new Thread(() -> {
            try {
                clientSocket = serverSocket.accept();
                serverOutput = new ObjectOutputStream(clientSocket.getOutputStream());
                serverInput = new ObjectInputStream(clientSocket.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        Socket socket = new Socket("localhost", 12345);
        clientOutput = new ObjectOutputStream(socket.getOutputStream());
        clientInput = new ObjectInputStream(socket.getInputStream());
    }

    @After
    public void tearDown() throws IOException {
        clientOutput.close();
        clientInput.close();
        serverOutput.close();
        serverInput.close();
        serverSocket.close();
    }

    @Test
    public void testClientServerConnection() throws IOException, ClassNotFoundException {
        String testMessage = "Olá, servidor!";
        clientOutput.writeObject(testMessage);

        String receivedMessage = (String) serverInput.readObject();
        assertEquals(testMessage, receivedMessage);
    }

    @Test
    public void testServerClientResponse() throws IOException, ClassNotFoundException {
        String testMessage = "Olá, cliente!";
        serverOutput.writeObject(testMessage);

        String receivedMessage = (String) clientInput.readObject();
        assertEquals(testMessage, receivedMessage);
    }

    @Test
    public void testProlixoMessageExchange() throws IOException, ClassNotFoundException {
        String clientMessage = "Olá, Melkor!";
        clientOutput.writeObject(clientMessage);

        String receivedClientMessage = (String) serverInput.readObject();
        assertEquals(clientMessage, receivedClientMessage);

        String serverResponse = "Estou tocando minha eternidade.";
        serverOutput.writeObject(serverResponse);

        String receivedServerMessage = (String) clientInput.readObject();
        assertEquals(serverResponse, receivedServerMessage);
    }
}
