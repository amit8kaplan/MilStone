package test;


import java.util.Objects;
import java.util.Random;

public class Tile {

    static int CountTileInArray =-1;
    //todo: generate ctor with valid to letter
    public static class Bag{
        int[] LettersGame;
        Tile[] arrayTile;

        private static Bag _intance = null;
        public static Bag getBag(){
            if(_intance==null){
                _intance=new Bag();
            }
            return _intance;
        }
        private Bag(){
            this.LettersGame = new int[26];
            this.arrayTile= new Tile[26];
        }
        private Bag(int[] lettersGame, Tile[] arrayTile) {

            this.LettersGame = lettersGame;
            this.arrayTile = arrayTile;
        }

        // if the position is empty - return false
        // if the position is not empty return true - subtract-- prom this position and from CountTileInArray
        //require - CountTileInArray>0
        public boolean checkIfPositionInLetterGameIsEmpty(int position){
            if (LettersGame[position]==0)
                return false;
            CountTileInArray--;
            this.LettersGame[position]--;
            return true;
        }
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
//            boolean checkAllArray = false;
//            int x=0;
//            int modulox = (randPosition+0)%26;
//            do {
//                if (checkIfPositionInLetterGameIsEmpty(modulox)) {
//                    checkAllArray =true;
//                    return this.arrayTile[modulox];
//                }
//                x++;
//                modulox = (randPosition+x)%26;
//                if (modulox == (randPosition+0))
//            }while (!checkAllArray);
//
//


            for (int i=randPosition; i<LettersGame.length; i++)
                if (checkIfPositionInLetterGameIsEmpty(i))
                    return this.arrayTile[i];
            for (int i=0; i<randPosition; i++)
                if (checkIfPositionInLetterGameIsEmpty(i))
                    return this.arrayTile[i];
            return null;
        }

        public boolean checkIfLetterValid(char charTile)
        {
            final int ascci = (int)charTile;
            //validation to the data from the client - if not A-Z then return null
            if ((ascci-65)<0 || (ascci-90)>0)
                return false;
            return true;
        }
        public Tile getTile(char charTile)
        {
            if (!checkIfLetterValid(charTile))
                return null;
            int position = (int)charTile - 65;
            if (checkIfPositionInLetterGameIsEmpty(position))
                return this.arrayTile[position];
            return null;

        }
        public void put(Tile t) {
            int position = (int)t.letter - 65;
            if (checkIfLetterValid(t.letter) && CountTileInArray<=98)
            {
                this.LettersGame[position]++;
                CountTileInArray++;
            }

        }
        public int[] getQuantities(){
            int[] CopyLetterGame= new int[this.LettersGame.length];
            System.arraycopy(this.LettersGame, 0, CopyLetterGame,0,this.LettersGame.length);
            return CopyLetterGame;
        }

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
//    private Tile(){
//        this.letter = '0';
//        this.score =0;
//
//    }

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
