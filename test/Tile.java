package test;


import java.util.Objects;
import java.util.Random;

public class Tile {

    static int CountTileInArray =98;
    //todo: generate ctor with valid to letter
    public static class Bag{
        int[] LettersGame;
        public Tile[] arrayTile;


        private static Bag _intance = null;
        public static Bag getBag(){
            if(_intance==null){
                _intance=new Bag();
            }
            return _intance;
        }
        private Bag(){
            this.LettersGame = new int[26];
            this.LettersGame[0] = 10;
            this.LettersGame[1] =2 ;
            this.LettersGame[2] = 2;
            this.LettersGame[3] =4 ;
            this.LettersGame[4] =12 ;
            this.LettersGame[5] = 2;
            this.LettersGame[6] =3 ;
            this.LettersGame[7] = 2;
            this.LettersGame[8] = 9;
            this.LettersGame[9] = 1;
            this.LettersGame[10] =1 ;
            this.LettersGame[11] =4 ;
            this.LettersGame[12] =2 ;
            this.LettersGame[13] = 6;
            this.LettersGame[14] = 8;
            this.LettersGame[15] = 2;
            this.LettersGame[16] = 1;
            this.LettersGame[17] = 6;
            this.LettersGame[18] =4 ;
            this.LettersGame[19]= 6;
            this.LettersGame[20] =4 ;
            this.LettersGame[21] =2 ;
            this.LettersGame[22] = 2;
            this.LettersGame[23] =1 ;
            this.LettersGame[24] = 2;
            this.LettersGame[25] =1 ;



            this.arrayTile= new Tile[26];
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
            if (checkIfLetterValid(t.letter) && CountTileInArray<98)
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
