package OnyxTCP;

import java.util.ArrayList;
import java.util.List;

public class OnyxTCPPair {
    public OnyxTCPPayloadType type;
    public String payload;

    public OnyxTCPPair(OnyxTCPPayloadType type, String payload)
    {
        this.type = type;
        this.payload = payload;
    }

    public static OnyxTCPPair GetFinalizingPair()
    {
        return new OnyxTCPPair(OnyxTCPPayloadType.Finalizer, "");
    }

//    public ArrayList<byte[]> PrepareToBoxing()
//    {
//        List<byte[]> box = new List<byte[]>();
//        //byte[] __payload = Encoding.ASCII.GetBytes(payload);
//        int size = __payload.Length;
//        int type = (int)this.type;
//        //box.Add(OnyxTCPProtocol.IntToByteArray(size));
//        //box.Add(OnyxTCPProtocol.IntToByteArray(type));
//        //box.Add(__payload);
//
//        return box;
//    }
}
