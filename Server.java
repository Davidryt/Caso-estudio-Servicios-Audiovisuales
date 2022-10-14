import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;


import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

public class Server {
    
    private final EmbeddedMediaPlayerComponent mediaPlayerComponent;
    
    public static void main(final String[] args) {
        new NativeDiscovery().discover();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Server(args);
            }
        });
    }
    
    private Server(String[] args) {
        JFrame frame = new JFrame("vlcj");
        
        mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
        
        frame.setContentPane(mediaPlayerComponent);
        
        //TO DO! choose the correct arguments for the methods below. Add more method calls as necessary
        //frame.setLocation(...);
        //frame.setSize(...);
        //...
        
        
        //TO DO!! configure the video delivery via RTP
        //...
    }
}