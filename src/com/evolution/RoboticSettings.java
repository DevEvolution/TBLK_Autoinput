package com.evolution;

import java.io.*;

public class RoboticSettings implements Serializable
{
    public static int versionId = 200506;

    public static int timeout = 100;
    public static int lineTimeout = 100;
    public static int startTimeout = 5000;
    public static int delayTimeout = 5000;

    private static boolean settingsNeedUpdate = false;

    public static void LoadSettings()
    {
        try(ObjectInputStream instream = new ObjectInputStream(new FileInputStream("settings.onyxconfig")))
        {
            int version = instream.readInt();
            if(version<200000)
                loadOldVersionSettings(instream, version);
            else {
                loadV2VersionSettings(instream, version);
            }

            if(version!=versionId)
                settingsNeedUpdate = true;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

        if(settingsNeedUpdate)
            updateSettingsVersion();
    }

    private static void loadV2VersionSettings(ObjectInputStream instream, int version) throws IOException
    {
        if(version==200506) {
            RoboticSettings.timeout = instream.readInt();
            RoboticSettings.lineTimeout = instream.readInt();
            RoboticSettings.startTimeout = instream.readInt();
            RoboticSettings.delayTimeout = instream.readInt();
        }
    }

    private static void updateSettingsVersion()
    {
        try(ObjectOutputStream outstream = new ObjectOutputStream( new FileOutputStream("settings.onyxconfig")))
        {
            outstream.writeInt(versionId);
            outstream.writeInt(timeout);
            outstream.writeInt(lineTimeout);
            outstream.writeInt(startTimeout);
            outstream.writeInt(delayTimeout);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    private static void loadOldVersionSettings(ObjectInputStream instream, int first) throws IOException
    {
        RoboticSettings.lineTimeout = instream.readInt();
        RoboticSettings.startTimeout = instream.readInt();
        RoboticSettings.delayTimeout = instream.readInt();
    }
}
