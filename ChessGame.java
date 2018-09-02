import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Creates a Chess Game with event, site, date, white player, black player,
 * result, and opening attributes
 * @author adussa3
 * @version 1.0
 */
public class ChessGame {

    private StringProperty event = new SimpleStringProperty(this, "NA");
    private StringProperty site = new SimpleStringProperty(this, "NA");
    private StringProperty date = new SimpleStringProperty(this, "NA");
    private StringProperty white = new SimpleStringProperty(this, "NA");
    private StringProperty black = new SimpleStringProperty(this, "NA");
    private StringProperty result = new SimpleStringProperty(this, "NA");
    private StringProperty opening = new SimpleStringProperty(this, "NA");

    private List<String> moves;

    /**
     * [ChessGame - a constructor that describes the ChessGame]
     * @param  event  [takes in the game's event]
     * @param  site   [takes in the game's site]
     * @param  date   [takes in the game's date]
     * @param  white  [takes in the game's white]
     * @param  black  [takes in the game's black]
     * @param  result [takes in the game's result]
     */
    public ChessGame(String event, String site, String date,
                     String white, String black, String result) {

        this.event.set(event);
        this.site.set(site);
        this.date.set(date);
        this.white.set(white);
        this.black.set(black);
        this.result.set(result);

        moves = new ArrayList<>();
    }

    /**
     * [addMove - adds move to the Move ArrayList]
     * @param move [the move you are adding]
     */
    public void addMove(String move) {
        moves.add(move);
    }

    /**
     * [getMove - gets the nth move]
     * @param  n [the move you want to get]
     * @return   [the nth move]
     */
    public String getMove(int n) {
        return moves.get(n - 1);
    }

    /**************************************************************************/

    /**
     * [getMoves - gets all the moves from the moves ArrayList]
     * @return [the moves in the ArrayList]
     */
    public String getMoves() {

        int step = 1;
        String str = "";

        for (String m : moves) {
            str += step + ". " + m + " ";
            step++;
        }
        return str;
    }

    /**
     * [Searches to see whether the game has an opening]
     * @return [returns an opening if the game has one, else it returns null]
     */
    public String getOpening() {

        if (moves.get(0).startsWith("e4 e5")
            && moves.get(1).startsWith("Nf3 Nc6")
            && moves.get(2).startsWith("Nf3 Nc6")) {
            opening.set("Giuoco Piano");
        }

        if (moves.get(0).startsWith("e4 e5")
            && moves.get(1).startsWith("Nf3 Nc6")
            && moves.get(2).startsWith("Bb5")) {
            opening.set("Ruy Lopez");
        }

        if (moves.get(0).startsWith("e4 c5")) {
            opening.set("Sicilian Defence");
        }

        if (moves.get(0).startsWith("d4 d5")
            && moves.get(1).startsWith("c4")) {
            opening.set("Queen's Gambit");
        }

        if (moves.get(0).startsWith("d4")) {
            opening.set("Indian Defence");
        }

        if (moves.get(0).startsWith("e4 e5")
            && moves.get(1).startsWith("Nf3 d6")) {
            opening.set("Philidor Defence");
        }

        return opening.get();

    }

    /**************************************************************************/

    /**
     * [getEvent - gets the game's event]
     * @return [the game's event]
     */
    public String getEvent() {
        return event.get();
    }

    /**
     * [getSite - get the game's site]
     * @return [the game's site]
     */
    public String getSite() {
        return site.get();
    }

    /**
     * [getDate - get the game's date]
     * @return [the game's date]
     */
    public String getDate() {
        return date.get();
    }

    /**
     * [getWhite - get the game's white]
     * @return [the game's white]
     */
    public String getWhite() {
        return white.get();
    }

    /**
     * [getBlack - get the game's black]
     * @return [the game's black]
     */
    public String getBlack() {
        return black.get();
    }

    /**
     * [getResult - get the game's result]
     * @return [the game's result]
     */
    public String getResult() {
        return result.get();
    }
}