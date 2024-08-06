package SetGame;

public class CardData {
    public enum Shape
    {
        Oval,
        Diamond,
        Squiggle
    }
    public enum ColorCode
    {
        Green,
        Red,
        Purple
    }
    public enum Number
    {
        One,
        Two,
        Three
    }
    public enum Fill
    {
        Open,
        Solid,
        Striped
    }

    Shape _shape;
    ColorCode _color;
    Number _number;
    Fill _fill;

    public CardData(Shape shape, ColorCode color, Number number, Fill fill)
    {
        _shape = shape;
        _color = color;
        _number = number;
        _fill = fill;
    }

    public Shape getShape()
    {
        return _shape;
    }
    public ColorCode getColor()
    {
        return _color;
    }
    public Number getNumber()
    {
        return _number;
    }
    public Fill getFill()
    {
        return _fill;
    }

    public String getFileIcon()
    {
        //need to figure out how to use relative paths
        String code = String.format("assets/%s_%s_%s_%s.png", _shape, _color, _number, _fill);
        return code;
    }
}