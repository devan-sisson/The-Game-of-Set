package SetGame;

import javax.swing.*;

import java.awt.*;

import javax.swing.JToggleButton;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import java.awt.CardLayout;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;


public class SetGui extends JFrame implements IGameView {
    public SetGui() {
        getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
                FormFactory.RELATED_GAP_COLSPEC,
                FormFactory.DEFAULT_COLSPEC,
                FormFactory.RELATED_GAP_COLSPEC,
                FormFactory.DEFAULT_COLSPEC,
                FormFactory.RELATED_GAP_COLSPEC,
                FormFactory.DEFAULT_COLSPEC,
                FormFactory.RELATED_GAP_COLSPEC,
                FormFactory.DEFAULT_COLSPEC,
                FormFactory.RELATED_GAP_COLSPEC,
                FormFactory.DEFAULT_COLSPEC,},
                new RowSpec[] {
                        FormFactory.RELATED_GAP_ROWSPEC,
                        FormFactory.DEFAULT_ROWSPEC,
                        FormFactory.RELATED_GAP_ROWSPEC,
                        FormFactory.DEFAULT_ROWSPEC,
                        FormFactory.RELATED_GAP_ROWSPEC,
                        FormFactory.DEFAULT_ROWSPEC,
                        FormFactory.RELATED_GAP_ROWSPEC,
                        FormFactory.DEFAULT_ROWSPEC,
                        FormFactory.RELATED_GAP_ROWSPEC,
                        FormFactory.DEFAULT_ROWSPEC,}));
        setSize(new Dimension(1000, 650));


        _state = new GameState(this);
        _state.StartGame();
    }
    GameState _state;

    int nextColumn = 2;

    private void GenerateColumn()
    {
        Card card = new Card();
        card.addMouseListener(new CardMouseAdapter(_state));
        getContentPane().add(card, nextColumn + ", 2");
        card = new Card();
        card.addMouseListener(new CardMouseAdapter(_state));
        getContentPane().add(card, nextColumn + ", 4");
        card = new Card();
        card.addMouseListener(new CardMouseAdapter(_state));
        getContentPane().add(card, nextColumn + ", 6");

        nextColumn+=2;

    }

    private void RemoveColumn()
    {
        for(int i = 0; i < 3; i++)
        {
            getContentPane().remove(getContentPane().getComponentCount()-1);
        }
        nextColumn-=2;
    }

    public void RefreshCards(ArrayList<CardData> cardsInPlay)
    {
        while(cardsInPlay.size() > getContentPane().getComponentCount())
        {
            GenerateColumn();
        }
        while(cardsInPlay.size() < getContentPane().getComponentCount())
        {
            RemoveColumn();
        }

        for(int i = 0; i < getContentPane().getComponentCount(); i++)
        {
            Card card = ((Card)getContentPane().getComponents()[i]);
            card.setCardData(cardsInPlay.get(i));
            card.setSelected(false);
        }
        repaint();
    }
    //int score = 0;
    public void SetFound()
    {
        JOptionPane.showMessageDialog (this, "You found a SET!");
        //score++;
        //textbox.setText("Score: " + score);
    }

    public void NotSet(ArrayList<CardData> selection )
    {
        selection.clear();
        JOptionPane.showMessageDialog(this, "You do not have a SET!");
    }
}
