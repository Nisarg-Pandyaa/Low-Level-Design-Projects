package Music_Player.factories;

import Music_Player.devices.IAudioOutputDevice;
import Music_Player.devices.WiredEarbudAdapter;
import Music_Player.devices.HeadphoneAdapter;
import Music_Player.devices.BluetoothSpeakerAdapter;
import Music_Player.externels.WiredEarbudAPI;
import Music_Player.externels.HeadphoneAPI;
import Music_Player.externels.BluetoothSpeakerAPI;
import Music_Player.enums.DeviceType;

public class DeviceFactory {
    public static IAudioOutputDevice createDevice(DeviceType deviceType){
        return switch (deviceType) {
            case HEADPHONE -> new HeadphoneAdapter(new HeadphoneAPI());
            case BLUETOOTHSPEAKER -> new BluetoothSpeakerAdapter(new BluetoothSpeakerAPI());
            default -> new WiredEarbudAdapter(new WiredEarbudAPI());
        };

    }
}
