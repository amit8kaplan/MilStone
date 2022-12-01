package test;


import java.util.Arrays;
import java.util.Objects;

/**
 * Word Class
 * The class contain word, the word built to insert into the game
 * array of tiles, the position (row &col) of the first tile in the board game, and if the word is vertical
 *  @version 1.12.22 submit version
 * @author AmitKaplan
 */
public class Word {

    Tile[] tiles;
	int row;
    int col;
    boolean vertical;

    /**
     *
     * @param o - its Automatic equals function, didnt change nothing
     * @return true if equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word = (Word) o;
        return getRow() == word.getRow() && getCol() == word.getCol() && isVertical() == word.isVertical() && Arrays.equals(getTiles(), word.getTiles());
    }

    /**
     * standart get method
     * @return tile[] - the word
     */
    public Tile[] getTiles() {
        return tiles;
    }
    /**
     * standart get method
     * @return row - position row
     */
    public int getRow() {
        return row;
    }
    /**
     * standart get method
     * @return col - position col
     */
    public int getCol() {
        return col;
    }
    /**
     * standart is method
     * @return true if the word writen vertical (up to dowm in the matrix)
     */
    public boolean isVertical() {
        return vertical;
    }

    /**
     *
     * @param tiles - array of tiles, together built the word
     * @param row - position row
     * @param col - positon col
     * @param vertical - true: the word is writen vertical. false horazion
     *shallow copy! not deep copy
     */
    public Word(Tile[] tiles, int row, int col, boolean vertical) {

        this.tiles = tiles;
        this.row = row;
        this.col = col;
        this.vertical = vertical;
    }
}
