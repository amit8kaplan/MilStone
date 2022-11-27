package test;


import org.omg.CORBA.UNKNOWN;

import java.util.ArrayList;

public class Board {
    private static Board _instanceBoard = null;



    public static Board getBoard(){
        if(_instanceBoard==null)
            _instanceBoard=new Board();
        return _instanceBoard;
    }

    static int tileOnBoard =0;
    public Tile[][] boardGame;
    public Boolean[][] boardGameBoolean;
    public ArrayList<Word> allWords;
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
        this.boardGameBoolean= new Boolean[15][15];
        this.allWords = new ArrayList<Word>();

    }
    private Board(Tile[][] boardGame, Boolean[][] boardGameBoolean, ArrayList<Word> allWords) {
        this.boardGame = boardGame;
        this.boardGameBoolean = boardGameBoolean;
        this.allWords = new ArrayList<Word>();
        this.allWords.addAll(allWords);
    }

    public Tile[][] getBoardGame() {
        return boardGame;
    }

    public Boolean[][] getBoardGameBoolean() {
        return boardGameBoolean;
    }

    public Tile[][] getTiles(){
        return getBoardGame();
    }
    public boolean cheackIfFirstPositionIsOut (int row, int col){
        if (col<0 || row>14||row<0 || row>14)
            return false;
        return true;
    }
    public boolean checkifStar (int distance, int on, int Wlength)
    {
        if (distance!=7)
            return false;
        if (on>7)
            return false;
        if ((on-7)<0 && (Wlength-7)<0)
            return false;
        return true;
    }
    //helps me to know if the word based on other tile and if tile in word is override other tile
    //return true if the word is valid
    //return false if isnt
    public boolean CheckIfWordIsLegelInerstOn(int index, Word w)
    {
        //checkIfTileExist - if true: there is equal tile there
        boolean checkIfTileExist = false;
        for (int i=index; i<w.getTiles().length; i++) {
            if (w.getTiles()[i].equals(this.boardGame[index][i]))
                checkIfTileExist = true;
            else if (this.boardGame[index][i] != null)
                return false;
        }
        if (checkIfTileExist)
            return true;
        return false;
    }
    public boolean CheckIfWordLegalsAroundhorizon(int side, int first, int length)
    {
        if (side==0) {
            for (int i = 0; i < length; i++) {
                if (this.boardGame[side+1][i] != null)
                    return true;
            }
        }
        else if (side==14) {
            for (int i = 0; i <length; i++) {
                if (this.boardGame[side - 1][i] != null)
                    return true;
            }
        }
        else {
            for (int i = 0; i <length; i++) {
                if (this.boardGame[side - 1][i] != null || this.boardGame[side+1][i] != null)
                    return true;
            }
        }
        if (first>0)
            if(this.boardGame[side][first-1]!=null)
                return true;
        if ((length+side)<14)
            if(this.boardGame[side][length+1]!=null)
                return true;
        return false;
    }
    public boolean CheckIfWordLegalsAroundVer(int side, int first, int length)
    {
        if(side ==0){
            for (int i = 0; i < length; i++) {
                if (this.boardGame[i][side + 1] != null)
                    return true;
            }
        }
        else if (side==14) {
            for (int i = 0; i < length; i++) {
                if (this.boardGame[i][side - 1] != null)
                    return true;
            }
        }
        else {
            for (int i = 0; i <length; i++) {
                if (this.boardGame[i][side - 1] != null || this.boardGame[i][side + 1] != null)
                    return true;
            }
        }
        if (first>0)
            if(this.boardGame[first-1][side]!=null)
                return true;
        if ((length+first)<14)
            if(this.boardGame[length+1][side]!=null)
               return true;
        return false;
    }
    public boolean boardLegal(Word w){
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
            if(!CheckIfWordIsLegelInerstOn(w.getRow(), w))
                return false;
            if (!CheckIfWordLegalsAroundVer(w.getCol(), w.getRow(),w.getTiles().length))
                return false;

            //check the seround

//            if (w.getCol()==0) {
//                for (int i = 0; i < w.getTiles().length; i++) {
//                    if (this.boardGame[i][w.getCol() + 1] != null)
//                        checkCircle = true;
//                }
//            }
//            else if (w.getCol()==14) {
//                for (int i = 0; i < w.getTiles().length; i++) {
//                    if (this.boardGame[i][w.getCol() - 1] != null)
//                        checkCircle = true;
//                }
//            }
//            else {
//                for (int i = 0; i < w.getTiles().length; i++) {
//                    if (this.boardGame[i][w.getCol() - 1] != null || this.boardGame[i][w.getCol() + 1] != null)
//                        checkCircle = true;
//                }
//            }
//            if (w.getRow()>0)
//                if(this.boardGame[w.getRow()-1][w.getCol()]!=null)
//                    checkCircle=true;
//            if ((w.getTiles().length+w.getRow())<14)
//                if(this.boardGame[w.getTiles().length+1][w.getCol()]!=null)
//                    checkCircle=true;
        }
        else {
            if ((15 - w.getCol()) < w.getTiles().length)
                return false;
            if (tileOnBoard == 0) {
                if (!checkifStar(w.getRow(), w.getCol(), w.getTiles().length))
                    return false;
                return true;
            }
            if(!CheckIfWordIsLegelInerstOn(w.getCol(), w))
                return false;
            if (!CheckIfWordLegalsAroundhorizon(w.getRow(), w.getCol(), w.getTiles().length))
                return false;
//            if (w.getRow()==0) {
//                for (int i = 0; i < w.getTiles().length; i++) {
//                    if (this.boardGame[w.getRow()+1][i] != null)
//                        checkCircle = true;
//                }
//            }
//            else if (w.getRow()==14) {
//                for (int i = 0; i < w.getTiles().length; i++) {
//                    if (this.boardGame[w.getRow() - 1][i] != null)
//                        checkCircle = true;
//                }
//            }
//            else {
//                for (int i = 0; i < w.getTiles().length; i++) {
//                    if (this.boardGame[w.getRow() - 1][i] != null || this.boardGame[w.getRow()+1][i] != null)
//                        checkCircle = true;
//                }
//            }
//            if (w.getCol()>0)
//                if(this.boardGame[w.getRow()][w.getCol()-1]!=null)
//                    checkCircle=true;
//            if ((w.getTiles().length+w.getRow())<14)
//                if(this.boardGame[w.getRow()][w.getTiles().length+1]!=null)
//                    checkCircle=true;
        }
        return true;
    }
    public boolean dictionaryLegal(Word w){
        return true;
    }
    public ArrayList<Word> getWords(Word w){
        ArrayList<Word> myWords = new ArrayList<Word>();
        myWords.add(w);
        if (tileOnBoard==0){
            return myWords;
        }

        Tile[] t;
        boolean untilTheEndOfTheRowStart= true;
        int indexStart;
        int indexEnd;
        int indexUp;
        int indexDown;
        boolean untilTheEndOfTheRowEnd=true;
        boolean untilTheEndOfTheColStart=true;
        boolean untilTheEndOfTheColEnd=true;
        if (w.isVertical()){
            for (int i=w.getRow(); i<(w.getTiles().length+w.getRow()); i++){
                untilTheEndOfTheRowStart=true;
                untilTheEndOfTheRowEnd=true;
                indexStart=1;
                indexEnd=1;
                if (this.boardGame[i][w.getCol()]==null) {
                    while ((w.getCol()-indexStart)>=0 || untilTheEndOfTheRowStart) {
                        if (this.boardGame[i][w.getCol()-indexStart] ==null)
                            untilTheEndOfTheRowStart=false;
                        else
                            indexStart++;
                    }
                    while ((w.getCol()+indexEnd)<=14 || untilTheEndOfTheRowEnd) {
                        if (this.boardGame[i][w.getCol()+indexEnd] ==null)
                            untilTheEndOfTheRowEnd=false;
                        else
                            indexEnd++;
                    }
                    if (indexEnd==1)
                        indexEnd=0;
                    if (indexStart==1)
                        indexStart=0;
                    if (indexEnd+indexStart>0){
                        t=new Tile[indexStart+indexEnd-1];
                        for(int j=w.getCol()-indexStart+1; j<=w.getCol()+indexEnd-1; j++)
                            t[j]= boardGame[i][j];
                        myWords.add(new Word(t,i,w.getCol()-indexStart+1, false));
                    }
                }
            }
            untilTheEndOfTheColStart=true;
            untilTheEndOfTheColEnd=true;
            indexUp=0;
            indexDown=0;
            while ((w.getRow()-indexUp-1)>=0 || untilTheEndOfTheColStart)
            {
                if (this.boardGame[w.getRow()-indexUp-1][w.getCol()] ==null)
                    untilTheEndOfTheColStart=false;
                else
                    indexUp++;
            }
            while ((w.getTiles().length+w.getRow()+indexDown-1)<=14 || untilTheEndOfTheColEnd)
            {
                if (this.boardGame[w.getTiles().length+w.getRow()+indexDown-1][w.getCol()] ==null)
                    untilTheEndOfTheColEnd=false;
                else
                    indexDown++;
            }
            if (indexDown+indexUp>0){
                t=new Tile[indexUp+w.getTiles().length+indexDown];
                for(int i=w.getRow()-indexUp; i<w.getTiles().length+indexUp+indexDown; i++)
                    t[i]=boardGame[i][w.getCol()];
                myWords.add(new Word(t,w.getRow()-indexUp,w.getCol(),true));
            }
        }
        else {
            for (int i=w.getCol(); i<(w.getTiles().length+w.getCol()); i++){
                untilTheEndOfTheRowStart=true;
                untilTheEndOfTheRowEnd=true;
                indexStart=1;
                indexEnd=1;
                if (this.boardGame[w.getRow()][i]==null) {
                    while ((w.getRow()-indexStart)>=0 || untilTheEndOfTheRowStart) {
                        if (this.boardGame[w.getRow()-indexStart][i] ==null)
                            untilTheEndOfTheRowStart=false;
                        else
                            indexStart++;
                    }
                    while ((w.getRow()+indexEnd)<=14 || untilTheEndOfTheRowEnd) {
                        if (this.boardGame[w.getRow()+indexEnd][i] ==null)
                            untilTheEndOfTheRowEnd=false;
                        else
                            indexEnd++;
                    }
                    if (indexEnd==1)
                        indexEnd=0;
                    if (indexStart==1)
                        indexStart=0;
                    if (indexEnd+indexStart>0){
                        t=new Tile[indexStart+indexEnd-1];
                        for(int j=w.getRow()-indexStart+1; j<=w.getRow()+indexEnd-1; j++)
                            t[j]= boardGame[i][j];
                        myWords.add(new Word(t,i,w.getRow()-indexStart+1, true));
                    }
                }
            }
            untilTheEndOfTheColStart=true;
            untilTheEndOfTheColEnd=true;
            indexUp=0;
            indexDown=0;
            while ((w.getCol()-indexUp-1)>=0 || untilTheEndOfTheColStart)
            {
                if (this.boardGame[w.getRow()][w.getCol()-indexUp-1] ==null)
                    untilTheEndOfTheColStart=false;
                else
                    indexUp++;
            }
            while ((w.getTiles().length+w.getCol()+indexDown-1)<=14 || untilTheEndOfTheColEnd)
            {
                if (this.boardGame[w.getRow()][w.getTiles().length+w.getCol()+indexDown-1] ==null)
                    untilTheEndOfTheColEnd=false;
                else
                    indexDown++;
            }
            if (indexDown+indexUp>0){
                t=new Tile[indexUp+w.getTiles().length+indexDown];
                for(int i=w.getCol()-indexUp; i<w.getTiles().length+indexUp+indexDown; i++)
                    t[i]=boardGame[w.getRow()][i];
                myWords.add(new Word(t,w.getRow()-indexUp,w.getCol(),false));
            }

        }
        return myWords;
    }

    public int getScore(Word w){
        int sumPiToWord=0;
        int Pi;
        int sum =0;
        //sum the word
        for(Tile t : w.getTiles())
        {
            Pi=this.PiBoard[w.getRow()][w.getCol()];
            if (Pi==2 || Pi==3)
                sum+=(t.getScore()*Pi);
            else if (Pi==1 || Pi==4 || Pi==5)
                sumPiToWord+=Pi;
            else
                sum+=t.getScore();
        }
        sum*=sumPiToWord;
        return sum;
    }
    public int tryPlaceWord(Word word){
        int sum =0;
        if (!boardLegal(word))
            return 0;
        ArrayList<Word> words = new ArrayList<>();
        words = getWords(word);
        for (Word w: words){
            if (!dictionaryLegal(w))
                return 0;
            sum+=getScore(w);
        }

        return sum;
    }
    

}








//            //checkIfTileExist - if true: there is equal tile there
//            boolean checkIfTileExist = false;
//            for (int i=w.getRow(); i<w.getTiles().length; i++) {
//                if (w.getTiles()[i].equals(this.boardGame[w.getRow()][i]))
//                    checkIfTileExist = true;
//                else if (this.boardGame[w.getRow()][i] != null)
//                    return false;
//            }
//            if (checkIfTileExist)
//                return true;

//            //checkIfTileExist - if true: there is equal tile there
//            boolean checkIfTileExist = false;
//            for (int i=w.getCol(); i<w.getTiles().length; i++) {
//                if (w.getTiles()[i].equals(this.boardGame[w.getRow()][i]))
//                    checkIfTileExist = true;
//                else if (this.boardGame[w.getRow()][i] != null)
//                    return false;
//            }
//            if (checkIfTileExist)
//                return true;
















