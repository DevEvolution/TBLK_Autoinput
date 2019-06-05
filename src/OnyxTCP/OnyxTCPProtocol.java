package OnyxTCP;

import com.sun.javaws.exceptions.InvalidArgumentException;
import com.sun.jndi.toolkit.url.Uri;

import javax.swing.*;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Enumeration;

public class OnyxTCPProtocol {
    public ArrayList<OnyxTCPPair> pairs = new ArrayList<OnyxTCPPair>();
    protected Integer count = 0;

    public static int bytesToInt(byte[] bytes) throws IllegalArgumentException {
        if (bytes.length != 4)
            throw new IllegalArgumentException("Cannot parse bytes to int because of incorrect byte[length]");
        int value = bytes[3] & 0xFF;

        value = (value << 8) + (bytes[2] & 0xFF);
        value = (value << 8) + (bytes[1] & 0xFF);
        value = (value << 8) + (bytes[0] & 0xFF);
        return value;
    }

    public static byte[] intToBytes(int value) {
        byte[] bytes = new byte[4];
        bytes[0] = (byte) value;
        bytes[1] = (byte) (value >> 8);
        bytes[2] = (byte) (value >> 16);
        bytes[3] = (byte) (value >> 24);
        return bytes;
    }

    public Integer getPairsCount() {
        return count;
    }

    public void addPair(OnyxTCPPair pair) {
        pairs.add(pair);
        count++;
    }

    public void addPair(OnyxTCPPayloadType type, String payload) {
        OnyxTCPPair pair = new OnyxTCPPair(type, payload);
        pairs.add(pair);
        count++;
    }

    public void addOutput(String text) {
        addPair(OnyxTCPPayloadType.Output, text);
    }

    public void addFile(String path) throws IOException {
        String filename;
        if (path.contains("\\"))
            filename = path.substring(path.lastIndexOf('\\') + 1);
        else
            filename = path.substring(path.lastIndexOf('/') + 1);

        //JOptionPane.showMessageDialog(null, filename);

        addPair(OnyxTCPPayloadType.FileInfo, filename);
        FileReader reader = new FileReader(path);

        StringBuilder builder = new StringBuilder();
        int readed = 0;
        while ((readed = reader.read()) != -1) {
            builder.append((char) readed);
        }
        addPair(OnyxTCPPayloadType.File, builder.toString());
    }

    public void recieve(int portNumber) {
        Integer count = 0;
        try {
            ServerSocket serverSocket = new ServerSocket(portNumber);
            Socket socket = serverSocket.accept();
            InputStream inputStream = socket.getInputStream();

            // Read Count
            byte[] countBytes = new byte[4];
            inputStream.read(countBytes);
            count = bytesToInt(countBytes);

            // Read pairs
            for (int i = 0; i < count; i++) {
                StringBuilder builder = new StringBuilder();

                // Size
                byte[] sizeBytes = new byte[4];
                inputStream.read(sizeBytes);
                int size = bytesToInt(sizeBytes);

                // Type
                byte[] typeBytes = new byte[4];
                inputStream.read(typeBytes);
                OnyxTCPPayloadType type = OnyxTCPPayloadType.values()[bytesToInt(typeBytes)];

                // Payload
                byte[] data = new byte[size];
                inputStream.read(data);
                short utf16char = 0;
                if(type==OnyxTCPPayloadType.File)
                {
                    for (int j = 0; j < size; j++)
                        builder.append((char) data[j]);
                }
                else
                for (int j = 0; j < size; j++) {
                    if(j%2==0) {
                        utf16char = data[j];
                    }
                    else
                    {
                        utf16char = (short)((short)(data[j] << 8) + utf16char); //((utf16char << 8) + data[j]);
                        builder.append((char) utf16char);
                    }
                }

                // Add it to pairs
                addPair(type, builder.toString());
            }

            inputStream.close();
            socket.close();
            serverSocket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void send(String ipAddress, int port) throws IOException {
        Socket client = new Socket();
        client.connect(new InetSocketAddress(ipAddress, port));
        OutputStream outStream = client.getOutputStream();

        outStream.write(intToBytes(count));
        for (OnyxTCPPair pair : pairs) {
            ArrayList<byte[]> data = pair.prepareToBoxing();
            outStream.write(data.get(0));
            outStream.write(data.get(1));
            outStream.write(data.get(2));
        }

        outStream.close();
        client.close();
    }
}
