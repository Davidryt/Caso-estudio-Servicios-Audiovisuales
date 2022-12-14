import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.SwingUtilities;
//import vlcj-3.10.1*;   alex eres bobo

import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerEventAdapter;

public class Client{
    

    private final JFrame frame;
    
    private final EmbeddedMediaPlayerComponent mediaPlayerComponent;
    
    public static void main(final String[] args) {
        new NativeDiscovery().discover();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Client(args);
            }
        });
    }
    
    public Client(String[] args) {
        
        
        frame = new JFrame("Media Player");
        frame.setBounds(100, 100, 600, 400);

        
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                mediaPlayerComponent.release();
                System.exit(0);
            }
        });
        
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        
        mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
        contentPane.add(mediaPlayerComponent, BorderLayout.CENTER);
        
        JPanel controlsPane = new JPanel();
        JButton playButton = new JButton("Play");
        controlsPane.add(playButton);

        JButton pauseButton = new JButton("Pause");
        controlsPane.add(pauseButton);

        JButton stopButton = new JButton("Stop");
        controlsPane.add(stopButton);
        contentPane.add(controlsPane, BorderLayout.SOUTH);

        JPanel navbar = new JPanel();
        JTextField navurl =new JTextField(40);
        navbar.add(navurl);
        
        JButton goButton = new JButton("GO");
        navbar.add(goButton);
        
        
        contentPane.add(navbar, BorderLayout.NORTH);
        
        goButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TO DO!! configure the playback of the video received via RTP, or resume a paused playback.
                String url=navurl.getText();
                mediaPlayerComponent.getMediaPlayer().playMedia(url);
            }
        });


        //Handler for PLAY button
        //-----------------------
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TO DO!! configure the playback of the video received via RTP, or resume a paused playback.
                mediaPlayerComponent.getMediaPlayer().play();
            }
        });
        
        //TO DO! implement a PAUSE button to pause video playback.
        //...
        
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TO DO!! configure the playback of the video received via RTP, or resume a paused playback.
                
                mediaPlayerComponent.getMediaPlayer().pause();
            }
        });

        //TO DO! implement a STOP button to stop video playback and exit the application.
        //...
        
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TO DO!! configure the playback of the video received via RTP, or resume a paused playback.
                
                mediaPlayerComponent.getMediaPlayer().stop();
            }
        });

        mediaPlayerComponent.getMediaPlayer().addMediaPlayerEventListener(new MediaPlayerEventAdapter() {
            @Override
            public void playing(MediaPlayer mediaPlayer) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        frame.setTitle(String.format(
                            "MIRA EL VIDEO - %s",
                            mediaPlayerComponent.getMediaPlayer().getMediaMeta().getTitle()
                        ));
                    }
                });
            }

            @Override
            public void finished(MediaPlayer mediaPlayer) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        closeWindow();
                    }
                });
            }

            @Override
            public void error(MediaPlayer mediaPlayer) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        JOptionPane.showMessageDialog(
                            frame,
                            "Failed to play media",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                        );
                        closeWindow();
                    }
                });
            }
        });

        frame.setContentPane(contentPane);
        frame.setVisible(true);
        //mediaPlayerComponent.getMediaPlayer().playMedia("rtp://127.0.0.1:6969");

        
        
    }

    private void closeWindow() {
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }
    
}

