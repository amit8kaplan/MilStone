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

    private Tile.Bag bag = Tile.Bag.getBag();
    static int tileOnBoard =0;
    public Tile[][] boardGame;
    public int[][]hashBoard;
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
        this.allWords = new ArrayList<Word>();
        this.hashBoard= new int[15][15];

    }
    private Board(Tile[][] boardGame, int[][]hashBoard, ArrayList<Word> allWords) {
        this.boardGame = boardGame;
        this.hashBoard=hashBoard;
        this.allWords = new ArrayList<Word>();
        this.allWords.addAll(allWords);
    }

    public Tile[][] getBoardGame() {
        return boardGame;
    }



    public Tile[][] getTiles(){
        return getBoardGame();
    }
    public boolean cheackIfFirstPositionIsOut (int row, int col){
        if (col<0 || row>14||row<0 || col>14)
            return false;
        return true;
    }
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
    //helps me to know if the word based on other tile and if tile in word is override other tile
    //return true if the word is valid
    //return false if isnt
    public boolean CheckIfWordIsLegelInerstOnHorzion(int index, Word w)
    {
        //checkIfTileExist - if true: there is equal tile there
//        boolean checkIfTileExist = true;
//        for (int i=index; i<w.getTiles().length; i++) {
//            if (w.getTiles()[i].equals(this.boardGame[index][i]))
//                checkIfTileExist = true;
//            else if (this.boardGame[index][i] != null)
//                return false;
//        }
//        if (checkIfTileExist)
//            return true;
//        return false;
        int count=0;
        for(int i=index; i<w.getTiles().length+index;i++){
            if(w.getTiles()[i-index].hashCode()==this.hashBoard[w.getRow()][i]) {
//                checkIfTileExist = true;
                count++;
            }
            else if(this.hashBoard[w.getRow()][i] != 0)
                return false;
        }
//        if (!checkIfTileExist || w.getTiles().length==count)
        if (w.getTiles().length==count)
            return false;
        return true;
    }
    public boolean CheckIfWordIsLegelInerstOnVer(int index, Word w)
    {
        //checkIfTileExist - if true: there is equal tile there
        boolean checkIfTileExist = true;
//        for (int i=index; i<w.getTiles().length; i++) {
//            if (w.getTiles()[i].equals(this.boardGame[index][i]))
//                checkIfTileExist = true;
//            else if (this.boardGame[index][i] != null)
//                return false;
//        }
//        if (checkIfTileExist)
//            return true;
//        return false;
        int count=0;
        for(int i=index; i<w.getTiles().length+index;i++){
            if(w.getTiles()[i-index].hashCode()==this.hashBoard[i][w.getCol()]) {
                count++;
                checkIfTileExist = true;

            }
            else if(this.hashBoard[i][w.getCol()] != 0)
                return false;
        }

//        if (!checkIfTileExist || w.getTiles().length==count)
        if (w.getTiles().length==count)
            return false;
        return true;
    }
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

//        if (side==0) {
//            for (int i = 0; i < length; i++) {
//                if (this.boardGame[side+1][i] != null)
//                    return true;
//            }
//        }
//        else if (side==14) {
//            for (int i = 0; i <length; i++) {
//                if (this.boardGame[side - 1][i] != null)
//                    return true;
//            }
//        }
//        else {
//            for (int i = 0; i <length; i++) {
//                if (this.boardGame[side - 1][i] != null || this.boardGame[side+1][i] != null)
//                    return true;
//            }
//        }
//        if (first>0)
//            if(this.boardGame[side][first-1]!=null)
//                return true;
//        if ((length+side)<14)
//            if(this.boardGame[side][length+1]!=null)
//                return true;
//        return false;
    }
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
//        if(side ==0){
//            for (int i = 0; i < length; i++) {
//                if (this.boardGame[i][side + 1] != null)
//                    return true;
//            }
//        }
//        else if (side==14) {
//            for (int i = 0; i < length; i++) {
//                if (this.boardGame[i][side - 1] != null)
//                    return true;
//            }
//        }
//        else {
//            for (int i = 0; i <length; i++) {
//                if (this.boardGame[i][side - 1] != null || this.boardGame[i][side + 1] != null)
//                    return true;
//            }
//        }
//        if (first>0)
//            if(this.boardGame[first-1][side]!=null)
//                return true;
//        if ((length+first)<14)
//            if(this.boardGame[length+1][side]!=null)
//               return true;
//        return false;
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
            if(!CheckIfWordIsLegelInerstOnVer(w.getRow(), w))
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
            if(!CheckIfWordIsLegelInerstOnHorzion(w.getCol(), w))
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

        if (tileOnBoard==0){
            myWords.add(w);
            return myWords;
        }
        boolean worddontExtend =true;
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
                worddontExtend =true;
                untilTheEndOfTheRowStart=true;
                untilTheEndOfTheRowEnd=true;
                indexStart=1;
                indexEnd=1;
//                if (this.boardGame[i][w.getCol()]==null) {
                if (this.hashBoard[i][w.getCol()]==0) {
                    while ((w.getCol()-indexStart)>=0 && untilTheEndOfTheRowStart) {
//                        if (this.boardGame[i][w.getCol()-indexStart] ==null)
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
//                    if (indexEnd==1)
//                        indexEnd=0;
//                    if (indexStart==1)
//                        indexStart=0;
//                    if (indexEnd+indexStart>2){
//                        t=new Tile[indexStart+indexEnd-1];
//                        for(int j=w.getCol()-indexStart+1; j<=w.getCol()+indexEnd-1; j++)
//                            //this loop is onlt because hashcode
//                            for (int k=0; k<25; k++)
//                                if(bag.arrayTile[k].hashCode()==this.hashBoard[i][j])
//                                    t[j]= bag.arrayTile[k];
////                        t[j]= boardGame[i][j];
//                        myWords.add(new Word(t,i,w.getCol()-indexStart+1, false));
//                    }
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
                            //this loop is onlt because hashcode
                            for (int k=0; k<25; k++)
                                if(bag.arrayTile[k].hashCode()==this.hashBoard[i][j]) {
                                    t[count] = bag.arrayTile[k];
                                    count++;
                                }
//                            t[j]= boardGame[i][j];
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
//                if (this.boardGame[w.getRow()-indexUp-1][w.getCol()] ==null)
                if (this.hashBoard[w.getRow()-indexUp-1][w.getCol()] ==0)
                    untilTheEndOfTheColStart=false;
                else
                    indexUp++;
            }
            while ((w.getTiles().length+w.getRow()+indexDown+1)<=14 && untilTheEndOfTheColEnd)
            {
//                if (this.boardGame[w.getTiles().length+w.getRow()+indexDown-1][w.getCol()] ==null)
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
//                    t[i]=boardGame[i][w.getCol()];
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
//                    t[i]=boardGame[i][w.getCol()];
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
//                if (this.boardGame[w.getRow()][i]==null) {
                if (this.hashBoard[w.getRow()][i]==0) {
                    while ((w.getRow()-indexStart)>=0 && untilTheEndOfTheRowStart) {
//                        if (this.boardGame[w.getRow()-indexStart][i] ==null)
                        if (this.hashBoard[w.getRow()-indexStart][i] ==0)
                            untilTheEndOfTheRowStart=false;
                        else
                            indexStart++;
                    }
                    while ((w.getRow()+indexEnd)<=14 && untilTheEndOfTheRowEnd) {
//                        if (this.boardGame[w.getRow()+indexEnd][i] ==null)
                        if (this.hashBoard[w.getRow()+indexEnd][i] ==0)
                            untilTheEndOfTheRowEnd=false;
                        else
                            indexEnd++;
                    }
//                    if (indexEnd==1)
//                        indexEnd=0;
//                    if (indexStart==1)
//                        indexStart=0;
                    if (indexEnd+indexStart>2){
                        t=new Tile[indexStart+indexEnd-1];
                        int count =0;
                        for(int j=w.getRow()-indexStart+1; j<w.getRow(); j++) { //this loop is onlt because hashcode
                            for (int k = 0; k < 25; k++)
                                if (bag.arrayTile[k].hashCode() == this.hashBoard[j][i]) {
                                    t[count] = bag.arrayTile[k];
                                    count++;
                                }
                        }
                        t[count]= w.getTiles()[i-w.getCol()];
                        count++;
                        for(int j=w.getRow()+1; j<=w.getRow()+indexEnd-1; j++)
                            //this loop is onlt because hashcode
                            for (int k=0; k<25; k++)
                                if(bag.arrayTile[k].hashCode()==this.hashBoard[j][i]) {
                                    t[count] = bag.arrayTile[k];
                                    count++;
                                }
//                            t[j]= boardGame[i][j];
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
//                if (this.boardGame[w.getRow()][w.getCol()-indexUp-1] ==null)
                if (this.hashBoard[w.getRow()][w.getCol()-indexUp-1] ==0)
                    untilTheEndOfTheColStart=false;
                else
                    indexUp++;
            }
            while ((w.getTiles().length+w.getCol()+indexDown-1)<=14 && untilTheEndOfTheColEnd)
            {
//                if (this.boardGame[w.getRow()][w.getTiles().length+w.getCol()+indexDown-1] ==null)
                if (this.hashBoard[w.getRow()][w.getTiles().length+w.getCol()+indexDown-1] ==0)
                    untilTheEndOfTheColEnd=false;
                else
                    indexDown++;
            }
            if (indexDown+indexUp>0){
                int count=0;
                t=new Tile[indexUp+w.getTiles().length+indexDown];
                for(int i=w.getCol()-indexUp; i<w.getCol(); i++)
//                for(int i=w.getCol()-indexUp; i<w.getTiles().length+w.getCol()+indexDown; i++)
                    //this loop is only because hashcode
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
//                    t[i]=boardGame[i][w.getCol()];
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
//            for (int row = 0; row < w.getTiles().length; row++) {
//                Pi = this.PiBoard[row+w.getRow()][w.getCol()];
//                if (Pi == 2 || Pi == 3)
//                    sum += (w.getTiles()[row].getScore() * Pi);
//                else
//                    sum += w.getTiles()[row].getScore();
//                if ( Pi == 4)
//                    sumPiToWord += 2;
//                if (Pi==1 && tileOnBoard==0)
//                    sumPiToWord+=2;
//                if (Pi == 5)
//                    sumPiToWord += 3;
//            }
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
//                if (Pi == 1 || Pi == 4)
//                    sumPiToWord += 2;
//                if (Pi == 5)
//                    sumPiToWord += 3;
                if ( Pi == 4)
                    sumPi2ToWord++;
                if (Pi==1 && tileOnBoard==0)
                    sumPi2ToWord++;
                if (Pi == 5)
                    sumPi3ToWord++;
            }
        }
//            if (sumPiToWord!=0)
//                sum *= sumPiToWord;
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

        //sum the word



//        for (int i=0)
//        {
//            Pi=this.PiBoard[w.getRow()][w.getCol()];
//            if (Pi==2 || Pi==3)
//                sum+=(t.getScore()*Pi);
//            else if (Pi==1 || Pi==4)
//                sumPiToWord+=2;
//            else if (Pi==5)
//                sumPiToWord+=3;
//            else
//                sum+=t.getScore();
//        }
//        sum*=sumPiToWord;
//        return sum;
//    }
    public int tryPlaceWord(Word word){
        int sum =0;
        Word wWithOutNull = word;
        if (word.isVertical())
        {
            for(int i=0; i<wWithOutNull.getTiles().length;i++)
            {
//                if(wWithOutNull.getTiles()[i]==null && this.boardGame[i+wWithOutNull.getRow()][wWithOutNull.getCol()]==null)
                if(wWithOutNull.getTiles()[i]==null && this.hashBoard[i+wWithOutNull.getRow()][wWithOutNull.getCol()]==0)
                    return 0;
//                else if (wWithOutNull.getTiles()[i]==null && this.boardGame[i+wWithOutNull.getRow()][wWithOutNull.getCol()]!=null)
//                    wWithOutNull.getTiles()[i]=this.boardGame[i+wWithOutNull.getRow()][wWithOutNull.getCol()];

                else if (wWithOutNull.getTiles()[i]==null && this.hashBoard[i+wWithOutNull.getRow()][wWithOutNull.getCol()]!=0)
                {
                    //this loop is only because hashcode
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
//                if(wWithOutNull.getTiles()[i]==null && this.boardGame[wWithOutNull.getRow()][i+wWithOutNull.getCol()]==null)
//                    return 0;
//                else if (wWithOutNull.getTiles()[i]==null && this.boardGame[wWithOutNull.getRow()][i+wWithOutNull.getCol()]!=null)
//                    wWithOutNull.getTiles()[i]=this.boardGame[wWithOutNull.getRow()][i+wWithOutNull.getCol()];
                if(wWithOutNull.getTiles()[i]==null && this.hashBoard[wWithOutNull.getRow()][i+wWithOutNull.getCol()]==0)
                    return 0;
                else if (wWithOutNull.getTiles()[i]==null && this.hashBoard[wWithOutNull.getRow()][i+wWithOutNull.getCol()]!=0)
                {
                    //this loop is only because hashcode
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
//            for(int i=w.getRow();i<w.getTiles().length+w.getRow()-1;i++ )
//                if (this.boardGame[i][w.getCol()]==null)
//                    this.boardGame[i][w.getCol()]=w.getTiles()[i];
            for(int i=w.getRow();i<=w.getTiles().length+w.getRow()-1;i++ )
                if (this.hashBoard[i][w.getCol()]==0)
                    this.hashBoard[i][w.getCol()]=w.getTiles()[i-w.getRow()].hashCode();
        }
        else{
//            for(int i=w.getCol(); i<w.getTiles().length+w.getCol()-1; i++)
//                if (this.boardGame[w.getRow()][i]==null)
//                    this.boardGame[w.getRow()][i]=w.getTiles()[i];
            for(int i=w.getCol(); i<=w.getTiles().length+w.getCol()-1; i++)
                if (this.hashBoard[w.getRow()][i]==0)
                    this.hashBoard[w.getRow()][i]=w.getTiles()[i-w.getCol()].hashCode();
        }
        tileOnBoard+=w.getTiles().length;
    }

    public void print(){
        for (int row=0; row<15; row++)
        {
            for(int col=0; col<15; col++)
                System.out.print(this.hashBoard[row][col]);
            System.out.println();
        }
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
















