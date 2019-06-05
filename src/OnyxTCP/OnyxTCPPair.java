package OnyxTCP;

import sun.nio.cs.UnicodeEncoder;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class OnyxTCPPair {
    public OnyxTCPPayloadType type;
    public String payload;

    public OnyxTCPPair(OnyxTCPPayloadType type, String payload) {
        this.type = type;
        this.payload = payload;
    }

    public static OnyxTCPPair getFinalizingPair() {
        return new OnyxTCPPair(OnyxTCPPayloadType.Finalizer, "");
    }

    public ArrayList<byte[]> prepareToBoxing() throws UnsupportedEncodingException {
        ArrayList<byte[]> box = new ArrayList<byte[]>();
        byte[] __payload = this.type == OnyxTCPPayloadType.File ?
                payload.getBytes("ASCII") :
                payload.getBytes("Unicode");
        int size = __payload.length;
        int type = this.type.ordinal();
        box.add(OnyxTCPProtocol.intToBytes(size));
        box.add(OnyxTCPProtocol.intToBytes(type));
        box.add(__payload);

        return box;
    }
}
