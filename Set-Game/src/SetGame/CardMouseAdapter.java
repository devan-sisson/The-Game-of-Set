package SetGame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class CardMouseAdapter extends MouseAdapter {
    GameState _state;

    public CardMouseAdapter(GameState state)
    {
        _state = state;
    }
    @Override
    public void mouseClicked(MouseEvent e) {

        Card source = (Card)e.getSource();
        if (source.isSelected())
        {
            _state.AddCardToSelection(source.getCardData());
        }
        else
        {
            _state.RemoveCardFromSelection(source.getCardData());
        }
    }
}
