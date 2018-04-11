package OnyxTCP;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class OnyxTCPParser {
    private ArrayList<OnyxTCPPair> pairs;

    public String outputData = new String();

    public OnyxTCPParser(OnyxTCPProtocol protocol)
    {
        pairs = protocol.pairs;
        try {
            parse();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void parse() throws IOException {
        String filename = "";
        for (int i = 0; i < pairs.size(); i++) {
            OnyxTCPPair pair = pairs.get(i);
            switch(pair.type)
            {
                case Output:
                    outputData = pair.payload;
                    break;
                case FileInfo:
                    filename = pair.payload;
                    break;
                case File:
                    FileWriter writer = new FileWriter(filename);
                    writer.write(pair.payload);
                    writer.close();
                    break;
                default:
                    outputData = pair.payload;
                    break;
            }
        }
    }
}
