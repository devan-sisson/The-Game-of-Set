package SetGame;

import java.util.*;

public class GameState {
    final int CARD_TABLE = 12;
    final int SET_SIZE = 3;
    ArrayList<CardData> _cardsInPlay;
    ArrayList<CardData> _selection;
    ArrayList<CardData> _cardsInDeck;
    IGameView view;

    public GameState(IGameView game)
    {
        view = game;
        _cardsInPlay = new ArrayList<CardData>();
        _selection = new ArrayList<CardData>();
        _cardsInDeck = new ArrayList<CardData>();
        for ( int i = 0; i < SET_SIZE; i++)
        {
            for (int j = 0; j < SET_SIZE; j++)
            {
                for(int k = 0; k < SET_SIZE; k++)
                {
                    for(int l = 0; l < SET_SIZE; l++)
                    {
                        _cardsInDeck.add(new CardData(CardData.Shape.values()[i], CardData.ColorCode.values()[j], CardData.Number.values()[k], CardData.Fill.values()[l]));
                    }
                }
            }
        }

    }

    public void StartGame()
    {
        for( int i = 0; i < CARD_TABLE; i++)
        {
            DrawCard();
        }
        while(!AnySetAvailable())
        {
            DrawThree();
        }
        view.RefreshCards(_cardsInPlay);
    }
    private void DrawCard()
    {
        int index = (int)(Math.random() * _cardsInDeck.size());
        _cardsInPlay.add(_cardsInDeck.get(index));
        _cardsInDeck.remove(index);
    }
    private boolean IsSet(ArrayList<CardData> selection)
    {
        return ColorsOK(selection) && FillOK(selection) && ShapeOK(selection) && NumberOK(selection);
    }

    private boolean ColorsOK(ArrayList<CardData> selection)
    {
        if (selection.get(0).getColor() == selection.get(1).getColor() && selection.get(1).getColor() == selection.get(2).getColor())
        {
            return true;
        }
        else if (selection.get(0).getColor() != selection.get(1).getColor() && selection.get(1).getColor() != selection.get(2).getColor()  && selection.get(0).getColor() != selection.get(2).getColor())
        {
            return true;
        }
        else return false;
    }


    private boolean FillOK(ArrayList<CardData> selection)
    {
        if (selection.get(0).getFill() == selection.get(1).getFill() && selection.get(1).getFill() == selection.get(2).getFill())
        {
            return true;
        }
        else if (selection.get(0).getFill() != selection.get(1).getFill() && selection.get(1).getFill() != selection.get(2).getFill()  && selection.get(0).getFill() != selection.get(2).getFill())
        {
            return true;
        }
        else return false;
    }


    private boolean ShapeOK(ArrayList<CardData> selection)
    {
        if (selection.get(0).getShape() == selection.get(1).getShape() && selection.get(1).getShape() == selection.get(2).getShape())
        {
            return true;
        }
        else if (selection.get(0).getShape() != selection.get(1).getShape() && selection.get(1).getShape() != selection.get(2).getShape()  && selection.get(0).getShape() != selection.get(2).getShape())
        {
            return true;
        }
        else return false;
    }


    private boolean NumberOK(ArrayList<CardData> selection)
    {
        if (selection.get(0).getNumber() == selection.get(1).getNumber() && selection.get(1).getNumber() == selection.get(2).getNumber())
        {
            return true;
        }
        else if (selection.get(0).getNumber() != selection.get(1).getNumber() && selection.get(1).getNumber() != selection.get(2).getNumber()  && selection.get(0).getNumber() != selection.get(2).getNumber())
        {
            return true;
        }
        else return false;
    }

    private boolean AnySetAvailable()
    {
        for(int i = 0; i < _cardsInPlay.size(); i++)
        {
            for(int j = i+1; j < _cardsInPlay.size(); j++)
            {
                if(j == i)
                    continue;
                for(int k = j+1; k < _cardsInPlay.size(); k++)
                {
                    if(k == i || k == j)
                        continue;
                    ArrayList<CardData> cards = new ArrayList<CardData>();
                    cards.add(_cardsInPlay.get(i));
                    cards.add(_cardsInPlay.get(j));
                    cards.add(_cardsInPlay.get(k));
                    if(IsSet(cards))
                        return true;
                }
            }
        }
        return false;
    }

    private void DrawThree()
    {
        for (int i = 0; i < SET_SIZE; i++)
        {
            DrawCard();
        }

    }

    public void AddCardToSelection(CardData data)
    {

        _selection.add(data);
        if (_selection.size() == SET_SIZE)
        {
            if(IsSet(_selection))
            {
                view.SetFound();
                for (Iterator<CardData> i = _selection.iterator(); i.hasNext();)
                {
                    CardData item = i.next();
                    _cardsInPlay.remove(item);
                }
                _selection.clear();
                if (_cardsInPlay.size()< CARD_TABLE && _cardsInDeck.size() > 0)
                {
                    DrawThree();
                }
                while(!AnySetAvailable())
                {
                    DrawThree();
                }
                view.RefreshCards(_cardsInPlay);

            }
            else
            {
                view.NotSet(_selection);
                view.RefreshCards(_cardsInPlay);
            }
        }
    }
    public void RemoveCardFromSelection(CardData data)
    {
        _selection.remove(data);
    }
}
