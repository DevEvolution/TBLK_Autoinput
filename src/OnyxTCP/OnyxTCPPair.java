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

    public static OnyxTCPPair getFinalizingPair()
    {
        return new OnyxTCPPair(OnyxTCPPayloadType.Finalizer, "");
    }

    public ArrayList<byte[]> prepareToBoxing()
    {
        ArrayList<byte[]> box = new ArrayList<byte[]>();
        byte[] __payload = payload.getBytes();
        int size = __payload.length;
        int type = this.type.ordinal();
        box.add(OnyxTCPProtocol.intToBytes(size));
        box.add(OnyxTCPProtocol.intToBytes(type));
        box.add(__payload);

        return box;
    }
}
