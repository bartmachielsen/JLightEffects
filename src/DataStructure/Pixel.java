package DataStructure;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Bart Machielsen on 29-4-2016.
 */
public class Pixel {
    private int id;
    private double verhoudingX, verhoudingY;
    private boolean idForced = false;           //  NECESSAIRY ?? CANT HE CHECK IF NUMBER IS LOGIC  TODO SEARCH OTHER SOLUTION
    private transient Color color;
    private int checkWidth, checkHeight, checkX, checkY;


    public Pixel() {
        this.color = Color.white;
        this.checkWidth = 100;
        this.checkHeight = 100;
        this.checkX = -100;
        this.checkY = -100;

    }



    /* GETTERS AND SETTERS*/
    public void setLocation(int x, int y, int totalX, int totalY) {
        this.verhoudingX = (x / (double) totalX);
        this.verhoudingY = (y / (double) totalY);
    }


    public int berekenLocatieX(int width) {
        return (int) (width * verhoudingX);
    }

    public int berekenLocatieY(int height) {
        return (int) (height * verhoudingY);
    }


    public void parseSubImage(BufferedImage bufferedImage) {

        BufferedImage image = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
        ((image.getGraphics())).drawImage(bufferedImage, 0, 0, null);

        int red = 0;
        int green = 0;
        int blue = 0;
        int i = 0;

        for (int pixel : bufferedImage.getRGB(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight(), null, 0, bufferedImage.getWidth())) {
            Color color = new Color(pixel);
            red += color.getRed();
            green += color.getGreen();
            blue += color.getBlue();
            i++;
        }

        this.color = new Color(red / i, green / i, blue / i);
    }

    public int[] getScreenDimension(int totalWidth, int totalHeight) {
        int[] dimension = new int[4];
        int x = berekenLocatieX(totalWidth);
        int y = berekenLocatieY(totalHeight);


        dimension[0] = x - checkWidth / 2;
        if (dimension[0] >= totalWidth) dimension[0] = totalWidth - 1;
        if (dimension[0] < 0) dimension[0] = 0;
        dimension[1] = y - checkHeight / 2;
        if (dimension[1] >= totalHeight) dimension[1] = totalHeight - 1;
        if (dimension[1] < 0) dimension[1] = 0;
        dimension[2] = checkWidth;

        if (dimension[0] + dimension[2] >= totalWidth) dimension[2] = totalWidth - dimension[0];
        dimension[3] = checkHeight;
        if (dimension[1] + dimension[3] >= totalHeight) dimension[3] = totalHeight - dimension[1];


        return dimension;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isIdForced() {
        return idForced;
    }


    public void setPixelStrategy(ScreenStrategy screenStrategy) {

    }

}
