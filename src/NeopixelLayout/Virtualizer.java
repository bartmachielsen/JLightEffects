package NeopixelLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Created by Bart Machielsen on 29-4-2016.
 */
public class Virtualizer {
    ArrayList<JFrame> frames = new ArrayList<>();

    ///                                                                                                             TODO FIX THIS SHIT, NOT SO MUCH JFRAMES!!!!!
    public Virtualizer(ArrayList<ScreenSide> screenSides, GraphicsDevice graphicsDevice) {


        GraphicsConfiguration graphicsConfiguration = graphicsDevice.getDefaultConfiguration();
        JFrame jFrame = new JFrame(graphicsConfiguration);
        jFrame.setUndecorated(true);
        jFrame.setVisible(true);
        jFrame.setSize(300, 100);
        jFrame.setBackground(new Color(0, 0, 0, 0));
        frames.add(jFrame);


        jFrame.setLayout(new FlowLayout());
        jFrame.add(new JLabel(graphicsDevice.toString()));

        jFrame.setLocation((int) (graphicsConfiguration.getBounds().x + graphicsConfiguration.getBounds().getWidth() / 2.0),
                (int) (graphicsConfiguration.getBounds().y + graphicsConfiguration.getBounds().getHeight() / 2.0));

        jFrame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                for (JFrame frame : frames) {
                    frame.setVisible(true);
                    frame.dispose();
                }
            }
        });


        for (ScreenSide screenSide : screenSides) {
            for (Pixel pixel : screenSide.getPixels()) {
                JFrame jFrame2 = new JFrame(graphicsConfiguration);
                jFrame2.setUndecorated(true);
                jFrame2.setVisible(true);
                jFrame2.setSize(30, 30);
                jFrame2.setBackground(Color.blue);
                frames.add(jFrame2);

                jFrame2.setLocation(graphicsConfiguration.getBounds().x + pixel.berekenLocatieX(1920),
                        graphicsConfiguration.getBounds().y + pixel.berekenLocatieY(1080));

                jFrame2.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);
                        for (JFrame frame : frames) {
                            frame.setVisible(true);
                            frame.dispose();
                        }
                    }
                });


            }
        }
    }

}
