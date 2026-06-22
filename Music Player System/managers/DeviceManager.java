package Music_Player.managers;

import Music_Player.devices.IAudioOutputDevice;
import Music_Player.enums.DeviceType;
import Music_Player.factories.DeviceFactory;

public class DeviceManager {
    private static DeviceManager instance = null;
    private IAudioOutputDevice currentOutputDevice;

    private DeviceManager(){
        currentOutputDevice = null;
    }

    public static synchronized DeviceManager getInstance(){
        if(instance==null){
            instance = new DeviceManager();
        }
        return instance;
    }

    public void connect(DeviceType deviceType){

           currentOutputDevice =   DeviceFactory.createDevice(deviceType);

           switch (deviceType){
               case HEADPHONE -> {
                   System.out.println("Headphones Connected...");
                   break;
               }
               case BLUETOOTHSPEAKER -> {
                   System.out.println("Bluetooth Speaker Connected...");
                   break;
               }
               case WIREDEARBUD -> {
                   System.out.println("Wired Earbud Connected...");
                   break;
               }
           }

    }

    public IAudioOutputDevice getCurrentOutputDevice(){
        if(currentOutputDevice==null){
            throw new RuntimeException("No Output Device Is Connected...");
        }
        return currentOutputDevice;
    }

    public boolean hasOutputDevice(){
        return currentOutputDevice!=null;
    }
}
