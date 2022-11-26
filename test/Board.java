package test;


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

    private Board(Tile[][] boardGame, Boolean[][] boardGameBoolean) {
        this.boardGame = boardGame;
        this.boardGameBoolean = boardGameBoolean;
    }
    private Board(){
        this.boardGame = new Tile[15][15];
        this.boardGameBoolean= new Boolean[15][15];

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
        //false - nothing there
        boolean checkCircle = false;
        //valid if w is more extend then the board
        if(w.isVertical()){
            if((15-w.getCol())<w.getTiles().length)
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
            if ((15 - w.getRow()) < w.getTiles().length)
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
        for (int i=0; i<w.getTiles().length;i++)
            if()
        if (w.isVertical())
        {

        }
        return myWords;
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
















