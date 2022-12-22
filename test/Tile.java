package test;


import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

/**
 * Tile Class
 * The class contain inner class (bag)
 * var: static CountTileInArray - how much tile in the bag (zero meaning - no more tiles)
 * final public char letter - valid letter A-Z
 *  final public int score - score of the letter
 *  all the objects in the class is immutable.
 *  @version 1.12.22 submit version
 * @author Amit Kaplan 209049972
 */
public class Tile {

    static int CountTileInArray =98;
    public static class Bag{
        int[] LettersGame;
        public Tile[] arrayTile;
        private final int lettersNum = 26;
        /**
         * Bag Class - this class is the only class that can generate new Tiles!
         * LettersGame[] - every index is different char in ABC ([0]=A), the array declare how the words divides fer Char
         * Tile[] - every index represent the ABC, with score to every letter
         * to obligate that there is only one Bag - wh will use Singleton
         */
        private static Bag _intance = null;

        /**
         * Singleton pattern
         * @return if there isnt Bag - make new one. otherWise return ref to the existing bag
         */
        public static Bag getBag(){
            if(_intance==null){
                _intance=new Bag();
            }
            return _intance;
        }


        @Override
        public String toString() {
            return "Bag{" +
                    "LettersGame=" + Arrays.toString(LettersGame) +
                    ", arrayTile=" + Arrays.toString(arrayTile) +
                    '}';
        }

        /**
         * LetterGame -declare how mant shows there wiil be to every letter
         * the letter "A" ([0]) - should be 9 (as we asked to do in the project). But, i declere 10 because in 9 somthing doenst eork to me fine and in than its okey
         * what didnt words to me okey with 9 in A - if i insert word with "9*A" - the last one doenst classified and stay null, i dont know why
         *
         * ArrayTile[] - every index represent the ABC, with score to every letter
         */
        private Bag(){
            //change i make - the first one from 10 to 9
            this.LettersGame = new int[] {9, 2, 2, 4, 12, 2, 3, 2, 9, 1, 1, 4, 2, 6, 8, 2, 1, 6, 4, 6, 4, 2, 2, 1, 2, 1};
            this.arrayTile= new Tile[26];
            int values[] = {1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10 };
            this.arrayTile[0]= new Tile('A',1);
            this.arrayTile[1]= new Tile('B',3);
            this.arrayTile[2]= new Tile('C',3);
            this.arrayTile[3]= new Tile('D',2);
            this.arrayTile[4]= new Tile('E',1);
            this.arrayTile[5]= new Tile('F',4);
            this.arrayTile[6]= new Tile('G',2);
            this.arrayTile[7]= new Tile('H',4);
            this.arrayTile[8]= new Tile('I',1);
            this.arrayTile[9]= new Tile('J',8);
            this.arrayTile[10]= new Tile('K',5);
            this.arrayTile[11]= new Tile('L',1);
            this.arrayTile[12]= new Tile('M',3);
            this.arrayTile[13]= new Tile('N',1);
            this.arrayTile[14]= new Tile('O',1);
            this.arrayTile[15]= new Tile('P',3);
            this.arrayTile[16]= new Tile('Q',10);
            this.arrayTile[17]= new Tile('R',1);
            this.arrayTile[18]= new Tile('S',1);
            this.arrayTile[19]= new Tile('T',1);
            this.arrayTile[20]= new Tile('U',1);
            this.arrayTile[21]= new Tile('V',4);
            this.arrayTile[22]= new Tile('W',4);
            this.arrayTile[23]= new Tile('X',8);
            this.arrayTile[24]= new Tile('Y',4);
            this.arrayTile[25]= new Tile('Z',10);

        }

        /**
         * shallow copy
         * @param lettersGame
         * @param arrayTile
         */
        private Bag(int[] lettersGame, Tile[] arrayTile) {

            this.LettersGame = lettersGame;
            this.arrayTile = arrayTile;
        }

        /**
         * this helps method gets position in the array
         * @param position - index of array
         *
         * @return false if there isn't any available Tile in this position.
         * true if there is available, we will Subtract from  CountTileInArray and from the positon!
         * O(1)
         */
        public boolean checkIfPositionInLetterGameIsEmpty(int position){
            if (LettersGame[position]==0)
                return false;
            CountTileInArray--;
            this.LettersGame[position]--;
            return true;
        }

        /**
         * return randon tile from the array
         * if the amount of tiles in the array is above 20 - he will run until he finds randon and valid tile!
         * if the amount is under 20 - he run from the random tile and make a circle to check every position if therer is any good tile!
         * @return randon tile
         * O(n)
         */
        public Tile getRand(){
            if (CountTileInArray <=0)
                return null;
            Random rand = new Random();
            int randPosition;
            if (CountTileInArray >20) {
                boolean ifPositionEmpty = true;//meaning true :empty!
                while (ifPositionEmpty) {
                    randPosition = rand.nextInt(26);
                    if (checkIfPositionInLetterGameIsEmpty(randPosition))
                    {
                        ifPositionEmpty = false;
                        return this.arrayTile[randPosition];
                    }
                }
            }
            randPosition = rand.nextInt(26);
            for (int i=randPosition; i<LettersGame.length; i++)
                if (checkIfPositionInLetterGameIsEmpty(i))
                    return this.arrayTile[i];
            for (int i=0; i<randPosition; i++)
                if (checkIfPositionInLetterGameIsEmpty(i))
                    return this.arrayTile[i];
            return null;
        }

        /**
         *
         * @param charTile - check if its "A-Z"
         * @return true if "A-Z" else false
         */
        public boolean checkIfLetterValid(char charTile)
        {
            final int ascci = (int)charTile;
            //validation to the data from the client - if not A-Z then return null
            if ((ascci-65)<0 || (ascci-90)>0)
                return false;
            return true;
        }

        /**
         * very similar to getRand()
         * @param charTile - we will check if its valid char
         * @return if the char is exist in the bag - then return it
         * else - return null
         */
        public Tile getTile(char charTile)
        {
            if (!checkIfLetterValid(charTile))
                return null;
            int position = (int)charTile - 65;
            if (checkIfPositionInLetterGameIsEmpty(position))
                return this.arrayTile[position];
            return null;

        }

        /**
         * given Tile t- insert it into the bag
         * we only update the amount of tile in the array
         * if there is over 98 - do nothing
         * @param t
         */
        public void put(Tile t) {
            int position = (int)t.letter - 65;
            if (checkIfLetterValid(t.letter) && CountTileInArray<98)
            {
                this.LettersGame[position]++;
                CountTileInArray++;
            }

        }

        /**
         * copy of letterGame[] - deep copy
         * @return
         */
        public int[] getQuantities(){
            int[] CopyLetterGame= new int[this.LettersGame.length];
            System.arraycopy(this.LettersGame, 0, CopyLetterGame,0,this.LettersGame.length);
            return CopyLetterGame;
        }

        /**
         * if we called this method and the CountTileInArray ==-1 (meaning - we never insert there before) then run over the array and count the tils!
         * @return int - amount of thile in the bag
         */
        public int size(){
            if (CountTileInArray ==-1)
            {
                CountTileInArray=0;
                for (int i=0; i<LettersGame.length; i++)
                    CountTileInArray+= LettersGame[i];
            }
            return CountTileInArray;
        }
    }
    public final char letter;
    public final int score;

    private Tile(char letter, int score) {
        this.letter = letter;
        this.score = score;
    }

    public char getLetter() {
        return letter;
    }

    public int getScore() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return getLetter() == tile.getLetter() && getScore() == tile.getScore();
    }

    @Override
    public int hashCode() {
        return Objects.hash(letter, score);
    }
}
