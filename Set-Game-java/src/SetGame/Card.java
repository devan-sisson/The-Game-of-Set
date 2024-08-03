package SetGame;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.io.IOException;

public class Card extends JToggleButton {
    CardData _data;

    static final int[][] Positions = new int[][]{new int[]{0}, new int[]{-30, 30}, new int[]{-50, 0, 50}};

    public Card()
    {
        setPreferredSize(new Dimension(320, 200));
    }

    public void setCardData(CardData data)
    {
        _data = data;
//        ImageIcon icon = new ImageIcon(data.getFileIcon());
//        this.setSelectedIcon(icon);
//        this.setIcon(icon);

        this.setIcon(new ImageIcon(data.getFileIcon()));

		repaint();
    }

    public CardData getCardData()
    {
        return _data;
    }

    @Override
    public void setSize(Dimension newDim)
    {

    }
}

