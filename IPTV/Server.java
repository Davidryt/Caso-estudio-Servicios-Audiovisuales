import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import java.awt.Canvas;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;


import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;

import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.headless.HeadlessMediaPlayer;

import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;


import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;




public class Server {
    
    
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
        MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory();

        EmbeddedMediaPlayer mediaPlayer = mediaPlayerFactory.newEmbeddedMediaPlayer();
        EmbeddedMediaPlayer mediaPlayer2 = mediaPlayerFactory.newEmbeddedMediaPlayer();

        Canvas c1 = new Canvas();
        Canvas c2 = new Canvas();
        c1.setBackground(Color.black);
        c2.setBackground(Color.black);
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        p1.setLayout(new BorderLayout());
        p2.setLayout(new BorderLayout());
        p1.add(c1, BorderLayout.CENTER);
        p1.add(c2, BorderLayout.CENTER);
        frame.add(p1, BorderLayout.CENTER);
        /*mediaPlayer.setVideoSurface(mediaPlayerFactory.newVideoSurface(c1));
        mediaPlayer2.setVideoSurface(mediaPlayerFactory.newVideoSurface(c2));
        frame.setLocation(100, 100);        //TODO ESTO NO HACE FALTA NO QUEREMOS VER
        frame.setSize(1050, 600);*/ 

        frame.setBounds(100, 100, 600, 400);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);


        if(args.length != 2) {
            System.out.println("Specify two MRL to stream");
            System.exit(1);
        }


        String media1 = args[0];
        String options1 = formatRtpStream("127.0.0.1", 5004);

        String media2 = args[1];
        String options2 = formatRtpStream("127.0.0.1", 5014);
        
        System.out.println("Streaming '" + media1 + "' to '" + options1 + "'");
        System.out.println("Streaming '" + media2 + "' to '" + options2 + "'");

        mediaPlayer.playMedia(media1,
        options1,
        ":no-sout-rtp-sap",
        ":no-sout-standard-sap",
        ":sout-all",
        ":sout-keep"
        );

        mediaPlayer2.playMedia(media2,
        options2,
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