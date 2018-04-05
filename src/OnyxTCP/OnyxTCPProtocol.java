package OnyxTCP;

import com.sun.javaws.exceptions.InvalidArgumentException;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Enumeration;

public class OnyxTCPProtocol {
    public Integer count = 0;
    public ArrayList<OnyxTCPPair> pairs = new ArrayList<OnyxTCPPair>();

    public void addPair(OnyxTCPPair pair)
    {
        pairs.add(pair);
        count++;
    }

    public void addPair(OnyxTCPPayloadType type, String payload)
    {
        OnyxTCPPair pair = new OnyxTCPPair(type, payload);
        pairs.add(pair);
        count++;
    }

    public void addOutput(String text)
    {
        addPair(OnyxTCPPayloadType.Output, text);
    }

    public void addFile(String path) throws IOException {
        addPair(OnyxTCPPayloadType.FileInfo, path.substring(path.lastIndexOf('/')));
        FileReader reader = new FileReader(path);

        StringBuilder builder = new StringBuilder();
        int readed = 0;
        while( (readed = reader.read())!= -1 )
        {
            builder.append((char) readed);
        }
        addPair(OnyxTCPPayloadType.File, builder.toString());
    }
    
    public void recieve(int portNumber)
    {
        Integer count = 0;
        try
        {
            ServerSocket serverSocket = new ServerSocket(portNumber);
            Socket socket = serverSocket.accept();
            InputStream inputStream = socket.getInputStream();

            // Read Count
            byte[] countBytes = new byte[4];
            inputStream.read(countBytes);
            count = bytesToInt(countBytes);
            
            // Read pairs
            for (int i=0;i<count;i++)
            {
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
                for (int j=0;j<size;j++)
                {
                    builder.append((char)data[j]);
                }

                // Add it to pairs
                addPair(type, builder.toString());
            }

            inputStream.close();
            socket.close();
            serverSocket.close();
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
    }

    public static int bytesToInt(byte[] bytes) throws IllegalArgumentException
    {
        if(bytes.length!=4) throw new IllegalArgumentException("Cannot parse bytes to int because of incorrect byte[length]");
        int value = bytes[3] & 0xFF;

        value = (value<<8) + (bytes[2] & 0xFF);
        value = (value<<8) + (bytes[1] & 0xFF);
        value = (value<<8) + (bytes[0] & 0xFF);
        return value;
    }
}
