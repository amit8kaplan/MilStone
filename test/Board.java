package test;


import org.omg.CORBA.UNKNOWN;

import java.util.ArrayList;

/**
 * Board Class
 * The class contain tne board of the game
 * singleton pattern to the board
 *  @version 1.12.22 submit version
 * @author Amit Kaplan 209049972
 */
public class Board {
    private static Board _instanceBoard = null;



    public static Board getBoard(){
        if(_instanceBoard==null)
            _instanceBoard=new Board();
        return _instanceBoard;
    }

    private Tile.Bag bag = Tile.Bag.getBag();
    static int tileOnBoard =0;
    public Tile[][] boardGame;
    public int[][]hashBoard;
    private int[][] PiBoard = {{5,0,0,2,0,0,0,5,0,0,0,2,0,0,5},
                                {0,4,0,0,0,3,0,0,0,3,0,0,0,4,0},
                                {0,0,4,0,0,0,2,0,2,0,0,0,4,0,0},
                                {2,0,0,4,0,0,0,2,0,0,0,4,0,0,2},
                                {0,0,0,0,4,0,0,0,0,0,4,0,0,0,0},
                                {0,3,0,0,0,3,0,0,0,3,0,0,0,3,0},
                                {0,0,2,0,0,0,2,0,2,0,0,0,2,0,0},
                                {5,0,0,2,0,0,0,1,0,0,0,2,0,0,5},
                                {0,0,2,0,0,0,2,0,2,0,0,0,2,0,0},
                                {0,3,0,0,0,3,0,0,0,3,0,0,0,3,0},
                                {0,0,0,0,4,0,0,0,0,0,4,0,0,0,0},
                                {2,0,0,4,0,0,0,2,0,0,0,4,0,0,2},
                                {0,0,4,0,0,0,2,0,2,0,0,0,4,0,0},
                                {0,4,0,0,0,3,0,0,0,3,0,0,0,4,0},
                                {5,0,0,2,0,0,0,5,0,0,0,2,0,0,5}
    };

    private Board(){
        this.boardGame = new Tile[15][15];

        this.hashBoard= new int[15][15];

    }
    private Board(Tile[][] boardGame, int[][]hashBoard) {
        this.boardGame = boardGame;
        this.hashBoard=hashBoard;


    }

    public Tile[][] getBoardGame() {
        return boardGame;
    }


    /**
     * this method check the hashboard - and every spot with data will get into Tile[][[ by running all A-Z to find the correct tile!
     * in milestone 1 we didnt use this func!
     * it return local var, that doenst save in the class to later
     * if we want to - we can save it as @param boardTile[][] in the class and update from there!
     * @return
     */
    public Tile[][] getTiles(){
//        return getBoardGame();
         Tile[][] boardGame = new Tile[15][15];
         for (int i=0; i<this.hashBoard.length; i++)
         {
             for(int j=0; j<this.hashBoard[i].length; j++)
                 for (int k=0; k<25; k++)
                     if(bag.arrayTile[k].hashCode()==this.hashBoard[i][j])
                         boardGame[i][j]=bag.arrayTile[k];
         }
         return boardGame;
    }

    /**
     * if the word start out of bounce it return false;
     * @param row
     * @param col
     * @return
     */
    public boolean cheackIfFirstPositionIsOut (int row, int col){
        if (col<0 || row>14||row<0 || col>14)
            return false;
        return true;
    }

    /**
     * heip method - check if on of the letters in the word are on star !
     * @param distance
     * @param on
     * @param Wlength
     * @return true if this is on the star, else false;
     */
    public boolean checkifStar (int distance, int on, int Wlength)
    {
        if (distance!=7)
            return false;
        if (on>7)
            return false;
        if (on+Wlength-1<7)
            return false;
        return true;
    }

    /**
     * helps me to know if the word based on other tile and if tile in word is override other tile
     * this is to horizon word (vertical = false)
     * @param index - the col position of first word
     * @param w - the word we cehck
     * @return true if the word valid , else false
     *  **if the word insert totally on other word (exact same word) then return false
     */
    public boolean CheckIfWordIsLegelInerstOnHorzion(int index, Word w)
    {
        int count=0;
        for(int i=index; i<w.getTiles().length+index;i++){
            if(w.getTiles()[i-index].hashCode()==this.hashBoard[w.getRow()][i]) {
                count++;
            }
            else if(this.hashBoard[w.getRow()][i] != 0)
                return false;
        }
        if (w.getTiles().length==count)
            return false;
        return true;
    }

    /**
     * helps me to know if the word based on other tile and if tile in word is override other tile
     * this is to vartical word (vertical = true)
     * @param index - the ROW position of first word
     * @param w - the word we cehck
     * @return true if the word valid , else false
     *  **if the word insert totally on other word (exact same word) then return false
     */
    public boolean CheckIfWordIsLegelInerstOnVer(int index, Word w)
    {

        boolean checkIfTileExist = true;
        int count=0;
        for(int i=index; i<w.getTiles().length+index;i++){
            if(w.getTiles()[i-index].hashCode()==this.hashBoard[i][w.getCol()]) {
                count++;
                checkIfTileExist = true;

            }
            else if(this.hashBoard[i][w.getCol()] != 0)
                return false;
        }
        
        if (w.getTiles().length==count)
            return false;
        return true;
    }

    /**
     * helps me to know if the word near to other word
     * this is to horizon word (vertical = false)
     * @param side - the row of the word
     * @param first - the col of the first letter
     * @param length - the length of the word
     * @return false if there isnt any letter near my word, else true
     */
    public boolean CheckIfWordLegalsAroundhorizon(int side, int first, int length)
    {
        if (side==0){
            for (int i = 0; i < length; i++) {
                if (this.hashBoard[side+1][i] != 0)
                    return true;
            }
        }
        else if (side==14) {
            for (int i = 0; i <length; i++) {
                if (this.hashBoard[side - 1][i] != 0)
                    return true;
            }
        }
        else {
            for (int i = 0; i <length; i++) {
                if (this.hashBoard[side - 1][i+first] != 0 || this.hashBoard[side+1][i+first] != 0)
                    return true;
            }
        }
        if (first>0)
            if(this.hashBoard[side][first-1]!=0)
                return true;
        if ((length+side)<14)
            if(this.hashBoard[side][length+1]!=0)
                return true;
        return false;

    }
    /**
     * helps me to know if the word near to other word
     * this is to ver word (vertical = true)
     * @param side - the col of the word
     * @param first - the word of the first letter
     * @param length - the length of the word
     * @return false if there isnt any letter near my word, else true
     */
    public boolean CheckIfWordLegalsAroundVer(int side, int first, int length)
    {

        if(side ==0){
            for (int i = 0; i < length; i++) {
                if (this.hashBoard[i][side + 1] != 0)
                    return true;
            }
        }
        else if (side==14) {
            for (int i = 0; i < length; i++) {
                if (this.hashBoard[i][side - 1] != 0)
                    return true;
            }
        }
        else {
            for (int i = 0; i <length; i++) {
                if (this.hashBoard[i+first][side - 1] != 0 || this.hashBoard[i+first][side + 1] != 0)

                    return true;
            }
        }
        if (first>0)
            if(this.hashBoard[first-1][side]!=0)
                return true;
        if ((length+first)<14)
            if(this.hashBoard[length+1][side]!=0)
                return true;
        return false;
    }

    /**
     * this method check if the Word w can insert to the board
     * the method divide to 2 parts (by if) - if it vertical or isnt vertical
     * @param w
     * @return if okey - true
     */
    public boolean boardLegal(Word w){
        /**
         * check if the position is out of bounce
         */
        if (!cheackIfFirstPositionIsOut (w.getRow(), w.getCol()))
            return false;
        //false - nothing there
        boolean checkCircle = false;
        //valid if w is more extend then the board

        if(w.isVertical()){
            if((15-w.getRow())<w.getTiles().length)
                return false;
            if (tileOnBoard==0) {
                if (!checkifStar(w.getCol(), w.getRow(), w.getTiles().length))
                    return false;
                return true;
            }
            if(!CheckIfWordIsLegelInerstOnVer(w.getRow(), w))
                return false;
            if (!CheckIfWordLegalsAroundVer(w.getCol(), w.getRow(),w.getTiles().length))
                return false;
        }
        else {
            if ((15 - w.getCol()) < w.getTiles().length)
                return false;
            if (tileOnBoard == 0) {
                if (!checkifStar(w.getRow(), w.getCol(), w.getTiles().length))
                    return false;
                return true;
            }
            if(!CheckIfWordIsLegelInerstOnHorzion(w.getCol(), w))
                return false;
            if (!CheckIfWordLegalsAroundhorizon(w.getRow(), w.getCol(), w.getTiles().length))
                return false;
        }
        return true;
    }

    /**
     *
     * @param w
     * @return alayes true!
     */
    public boolean dictionaryLegal(Word w){
        return true;
    }

    /**
     * this method gets word and check how many new words it make on the board
     * @param w
     * @return all the words that generate because w
     */
    public ArrayList<Word> getWords(Word w){
        ArrayList<Word> myWords = new ArrayList<Word>();
        /**
         * tileOnBoard is how many tiles in the board, static int
         */
        if (tileOnBoard==0){
            myWords.add(w);
            return myWords;
        }
        boolean worddontExtend =true;
        Tile[] t;
        boolean untilTheEndOfTheRowStart= true;
        /**
         * ***************index Up****
         * ******index srart "word"  inderx end*****
         * ****************** index Dowm ******
         * */
        int indexStart;
        int indexEnd;
        int indexUp;
        int indexDown;
        boolean untilTheEndOfTheRowEnd=true;
        boolean untilTheEndOfTheColStart=true;
        boolean untilTheEndOfTheColEnd=true;
        if (w.isVertical()){
            for (int i=w.getRow(); i<(w.getTiles().length+w.getRow()); i++){
                worddontExtend =true;
                untilTheEndOfTheRowStart=true;
                untilTheEndOfTheRowEnd=true;
                indexStart=1;
                indexEnd=1;
                if (this.hashBoard[i][w.getCol()]==0) {
                    while ((w.getCol()-indexStart)>=0 && untilTheEndOfTheRowStart) {
                        if (this.hashBoard[i][w.getCol()-indexStart] ==0)
                            untilTheEndOfTheRowStart=false;
                        else
                            indexStart++;
                    }
                    while ((w.getCol()+indexEnd)<=14 && untilTheEndOfTheRowEnd) {
//                        if (this.boardGame[i][w.getCol()+indexEnd] ==null)
                        if (this.hashBoard[i][w.getCol()+indexEnd] ==0)
                            untilTheEndOfTheRowEnd=false;
                        else
                            indexEnd++;
                    }
                    if (indexEnd+indexStart>2){
                        t=new Tile[indexStart+indexEnd-1];
                        int count =0;
                        for(int j=w.getCol()-indexStart+1; j<w.getCol(); j++) {
                            for (int k = 0; k < 25; k++)
                                if (bag.arrayTile[k].hashCode() == this.hashBoard[i][j]) {
                                    t[count] = bag.arrayTile[k];
                                    count++;
                                }
                        }
                        t[count]= w.getTiles()[i-w.getRow()];
                        count++;
                        for(int j=w.getCol()+1; j<=w.getCol()+indexEnd-1; j++)
                            //this loop is only because hashcode
                            for (int k=0; k<25; k++)
                                if(bag.arrayTile[k].hashCode()==this.hashBoard[i][j]) {
                                    t[count] = bag.arrayTile[k];
                                    count++;
                                }
                        myWords.add(new Word(t,i,w.getCol()-indexStart+1, false));
                    }
                }
            }
            worddontExtend =true;
            untilTheEndOfTheColStart=true;
            untilTheEndOfTheColEnd=true;
            indexUp=0;
            indexDown=0;
            while ((w.getRow()-indexUp-1)>=0 && untilTheEndOfTheColStart)
            {
                if (this.hashBoard[w.getRow()-indexUp-1][w.getCol()] ==0)
                    untilTheEndOfTheColStart=false;
                else
                    indexUp++;
            }
            while ((w.getTiles().length+w.getRow()+indexDown+1)<=14 && untilTheEndOfTheColEnd)
            {
                if (this.hashBoard[w.getTiles().length+w.getRow()+indexDown-1][w.getCol()] ==0)
                    untilTheEndOfTheColEnd=false;
                else
                    indexDown++;
            }
            if (indexDown+indexUp>0){
                t=new Tile[indexUp+w.getTiles().length+indexDown];
                int count =0;
                for(int i=w.getRow()-indexUp; i<w.getRow(); i++) {//this loop is only because hashcode
                    for (int k = 0; k < 25; k++)
                        if (bag.arrayTile[k].hashCode() == this.hashBoard[i][w.getCol()]) {
                            t[count] = bag.arrayTile[k];
                            count++;
                        }
                }
                for (int i=0 ;i<w.getTiles().length; i++)
                {
                    t[count]=w.getTiles()[i];
                    count++;
                }
                for(int i=w.getRow()+w.getTiles().length; i<w.getTiles().length+w.getRow()+indexDown; i++) {//this loop is only because hashcode
                    for (int k = 0; k < 25; k++)
                        if (bag.arrayTile[k].hashCode() == this.hashBoard[i][w.getCol()]) {
                            t[count] = bag.arrayTile[k];
                            count++;
                        }
                }
                myWords.add(new Word(t,w.getRow()-indexUp,w.getCol(),true));
                worddontExtend =false;
            }
        }
        else {
            for (int i=w.getCol(); i<(w.getTiles().length+w.getCol()); i++){
                worddontExtend =true;
                untilTheEndOfTheRowStart=true;
                untilTheEndOfTheRowEnd=true;
                indexStart=1;
                indexEnd=1;

                if (this.hashBoard[w.getRow()][i]==0) {
                    while ((w.getRow()-indexStart)>=0 && untilTheEndOfTheRowStart) {
                        if (this.hashBoard[w.getRow()-indexStart][i] ==0)
                            untilTheEndOfTheRowStart=false;
                        else
                            indexStart++;
                    }
                    while ((w.getRow()+indexEnd)<=14 && untilTheEndOfTheRowEnd) {

                        if (this.hashBoard[w.getRow()+indexEnd][i] ==0)
                            untilTheEndOfTheRowEnd=false;
                        else
                            indexEnd++;
                    }
                    if (indexEnd+indexStart>2){
                        t=new Tile[indexStart+indexEnd-1];
                        int count =0;
                        for(int j=w.getRow()-indexStart+1; j<w.getRow(); j++) { //this loop is only because hashcode
                            for (int k = 0; k < 25; k++)
                                if (bag.arrayTile[k].hashCode() == this.hashBoard[j][i]) {
                                    t[count] = bag.arrayTile[k];
                                    count++;
                                }
                        }
                        t[count]= w.getTiles()[i-w.getCol()];
                        count++;
                        for(int j=w.getRow()+1; j<=w.getRow()+indexEnd-1; j++)
                            for (int k=0; k<25; k++)
                                if(bag.arrayTile[k].hashCode()==this.hashBoard[j][i]) {
                                    t[count] = bag.arrayTile[k];
                                    count++;
                                }
                        myWords.add(new Word(t,w.getRow()-indexStart+1,i, true));

                    }
                }
            }
            untilTheEndOfTheColStart=true;
            untilTheEndOfTheColEnd=true;
            indexUp=0;
            indexDown=0;
            while ((w.getCol()-indexUp-1)>=0 && untilTheEndOfTheColStart)
            {
                if (this.hashBoard[w.getRow()][w.getCol()-indexUp-1] ==0)
                    untilTheEndOfTheColStart=false;
                else
                    indexUp++;
            }
            while ((w.getTiles().length+w.getCol()+indexDown-1)<=14 && untilTheEndOfTheColEnd)
            {
                if (this.hashBoard[w.getRow()][w.getTiles().length+w.getCol()+indexDown-1] ==0)
                    untilTheEndOfTheColEnd=false;
                else
                    indexDown++;
            }
            if (indexDown+indexUp>0){
                int count=0;
                t=new Tile[indexUp+w.getTiles().length+indexDown];
                for(int i=w.getCol()-indexUp; i<w.getCol(); i++)
                {
                    for (int k=0; k<25; k++)
                        if(bag.arrayTile[k].hashCode()==this.hashBoard[w.getRow()][i]) {
                            t[count] = bag.arrayTile[k];
                            count++;
                        }
                }
                for (int i=0 ;i<w.getTiles().length; i++)
                {
                    t[count]=w.getTiles()[i];
                    count++;
                }
                for(int i=w.getCol()+w.getTiles().length; i<w.getTiles().length+w.getCol()+indexDown; i++) {//this loop is only because hashcode
                    for (int k = 0; k < 25; k++)
                        if (bag.arrayTile[k].hashCode() == this.hashBoard[i][w.getCol()]) {
                            t[count] = bag.arrayTile[k];
                            count++;
                        }
                }

                myWords.add(new Word(t,w.getRow(),w.getCol()-indexUp,false));
                worddontExtend =false;
            }

        }
        if (worddontExtend)
            myWords.add(w);
        return myWords;
    }

    public int getScore(Word w) {
        int sumPiToWord = 0;
        int sumPi2ToWord = 0;
        int sumPi3ToWord =0;
        int Pi;
        int sum = 0;
        if (w.isVertical()) {
            for (int row = 0; row < w.getTiles().length; row++) {
                Pi = this.PiBoard[row+w.getRow()][w.getCol()];
                if (Pi == 2 || Pi == 3)
                    sum += (w.getTiles()[row].getScore() * Pi);
                else
                    sum += w.getTiles()[row].getScore();
                if ( Pi == 4)
                    sumPi2ToWord++;
                if (Pi==1 && tileOnBoard==0)
                    sumPi2ToWord++;
                if (Pi == 5)
                    sumPi3ToWord++;
            }
        } else {
            for (int col = 0; col < w.getTiles().length; col++) {
                Pi = this.PiBoard[w.getRow()][col + w.getCol()];
                if (Pi == 2 || Pi == 3)
                    sum += (w.getTiles()[col].getScore() * Pi);
                else
                    sum += w.getTiles()[col].getScore();
                if ( Pi == 4)
                    sumPi2ToWord++;
                if (Pi==1 && tileOnBoard==0)
                    sumPi2ToWord++;
                if (Pi == 5)
                    sumPi3ToWord++;
            }
        }
        while(sumPi2ToWord>0)
        {
            sum*=2;
            sumPi2ToWord--;
        }
        while(sumPi3ToWord>0)
        {
            sum*=3;
            sumPi3ToWord--;
        }

        return sum;

    }

    public int tryPlaceWord(Word word){
        int sum =0;
        Word wWithOutNull = word;
        if (word.isVertical())
        {
            for(int i=0; i<wWithOutNull.getTiles().length;i++)
            {
                if(wWithOutNull.getTiles()[i]==null && this.hashBoard[i+wWithOutNull.getRow()][wWithOutNull.getCol()]==0)
                    return 0;

                else if (wWithOutNull.getTiles()[i]==null && this.hashBoard[i+wWithOutNull.getRow()][wWithOutNull.getCol()]!=0)
                {
                    for (int k=0; k<25; k++)
                        if(bag.arrayTile[k].hashCode()==this.hashBoard[i+wWithOutNull.getRow()][wWithOutNull.getCol()])
                            wWithOutNull.getTiles()[i]=bag.arrayTile[k];

                }
            }
        }
       else
        {
            for(int i=0; i<wWithOutNull.getTiles().length;i++)
            {
                if(wWithOutNull.getTiles()[i]==null && this.hashBoard[wWithOutNull.getRow()][i+wWithOutNull.getCol()]==0)
                    return 0;
                else if (wWithOutNull.getTiles()[i]==null && this.hashBoard[wWithOutNull.getRow()][i+wWithOutNull.getCol()]!=0)
                {
                    for (int k=0; k<25; k++)
                        if(bag.arrayTile[k].hashCode()==this.hashBoard[wWithOutNull.getRow()][i+wWithOutNull.getCol()])
                            wWithOutNull.getTiles()[i]=bag.arrayTile[k];
                }
            }
        }
        if (!boardLegal(wWithOutNull))
            return 0;
        if (tileOnBoard+word.getTiles().length>98)
            return 0;
        ArrayList<Word> words = new ArrayList<>();
        words = getWords(word);
        for (Word w: words){
            if (!dictionaryLegal(w))
                return 0;
            sum+=getScore(w);
        }
        insertWord(word);
        return sum;
    }

    public void insertWord(Word w){
        if (w.isVertical())
        {
            for(int i=w.getRow();i<=w.getTiles().length+w.getRow()-1;i++ )
                if (this.hashBoard[i][w.getCol()]==0)
                    this.hashBoard[i][w.getCol()]=w.getTiles()[i-w.getRow()].hashCode();
        }
        else{
            for(int i=w.getCol(); i<=w.getTiles().length+w.getCol()-1; i++)
                if (this.hashBoard[w.getRow()][i]==0)
                    this.hashBoard[w.getRow()][i]=w.getTiles()[i-w.getCol()].hashCode();
        }
        tileOnBoard+=w.getTiles().length;
    }
}