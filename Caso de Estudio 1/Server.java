import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;

import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.headless.HeadlessMediaPlayer;


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
        frame.setBounds(100, 100, 600, 400);

        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setVisible(true);
        //TO DO! choose the correct arguments for the methods below. Add more method calls as necessary
        //frame.setLocation(...);
        //frame.setSize(...);
        //...
        
        
        //TO DO!! configure the video delivery via RTP
        //...

        if(args.length != 1) {
            System.out.println("Specify a single MRL to stream");
            System.exit(1);
        }


        String media = args[0];
        String options = formatRtpStream("127.0.0.1", 6969);
        
        System.out.println("Streaming '" + media + "' to '" + options + "'");

        mediaPlayerComponent.getMediaPlayer().playMedia(media,
        options,
        ":no-sout-rtp-sap",
        ":no-sout-standard-sap",
        ":sout-all",
        ":sout-keep"
        );

    }

    private static String formatRtpStream(String serverAddress, int serverPort) {
        StringBuilder sb = new StringBuilder(60);
        sb.append(":sout=#rtp{dst=");
        sb.append(serverAddress);
        sb.append(",port=");
        sb.append(serverPort);
        sb.append(",mux=ts}");
    return sb.toString();
}
    

}