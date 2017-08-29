import java.util.ArrayList;

/**
 * Created by connerlane on 7/17/16.
 */
public class Domino {//elizababe lol ;^/
    public int side1;
    public int side2;
    public int position;
    public int points;

    public Domino(int side1In, int side2In, int positionIn) {
        side1 = side1In;
        side2 = side2In;
        position = positionIn;
        points = side1In + side2In;
    }
}
