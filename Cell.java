import java.util.ArrayList;
import java.util.HashSet;

public class Cell{
    private char value;
    private Cell next = null;
    private Row thisRow = null;
    private Column thisColumn = null;
    private Box thisBox = null;

    Cell(char v) {

        value = v;
    }

    public char getValue(){
        return value;
    }

    public void setValue(char v){
        value = v;
    }

    public void setNext(Cell nextCell) { next = nextCell; }

    public Cell next(){ return next; }

    public void setRow(Row r){
        thisRow = r;
    }
    public void setColumn(Column c){
        thisColumn = c;
    }
    public void setBox(Box b){
        thisBox = b;
    }

    public boolean charNotInSection(char v){
        return !thisBox.valueInSection(v) &&
            !thisRow.valueInSection(v) &&
            !thisColumn.valueInSection(v);
    }

    public HashSet<Character> findAllPossibilities(){
        HashSet<Character> possibilities = new HashSet <>();
        if(value != '.') return possibilities;
        int gridDim = thisBox.getSize();

        String legalChars = "123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String validCharsForGrid = legalChars.substring(0, gridDim);

        for(char v : validCharsForGrid.toCharArray()){
            if(charNotInSection(v)){
                possibilities.add(v);
            }
        }
        return possibilities;
    }

    void printSections(){
        System.out.println(thisRow.cells);
        System.out.println(thisColumn.cells);
        System.out.println(thisBox.cells);
    }

    //Brute force method
    public boolean fillThisCellAndTheRest(){
        var possibilities = findAllPossibilities();
        
        if(value != '.'){
            if(next == null) return true;
            return next.fillThisCellAndTheRest();
        }

        for(char v : possibilities){
            if(charNotInSection(v)){
                setValue(v);
                if(next == null){
                    return true;
                }

                if(next.fillThisCellAndTheRest()){
                    return true;
                }else{
                    value = '.';
                }
            }
        }
        return false;
    }

    @Override
    public String toString(){
        String s = "" + value;
        return s;
    }
}