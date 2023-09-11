import java.util.ArrayList;

public class Cell{
    private char value;

    private Row thisRow = null;
    private Column thisColumn = null;
    private Box thisBox = null;

    Cell(char v){
        value = v;
    }

    public char getValue(){
        return value;
    }

    public void setRow(Row r){
        thisRow = r;
    }
    public void setColumn(Column c){
        thisColumn = c;
    }
    public void setBox(Box b){
        thisBox = b;
    }

    public ArrayList<Character> findAllPossibilities(){
        ArrayList<Character> possibilities = new ArrayList<>();
        if(value != '.') return possibilities;
        int gridDim = thisBox.getSize();

        String allowedChars = "123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String validCharsForGrid = allowedChars.substring(0, gridDim);

        for(char value : validCharsForGrid.toCharArray()){
            if(!thisBox.valueInSection(value) && !thisRow.valueInSection(value) && !thisColumn.valueInSection(value)){
                possibilities.add(value);
            }
        }
        return possibilities;
    }

    public void fillThisCellAndTheRest(){
        ArrayList<Character> possibilities = findAllPossibilities();
        
    }

    @Override
    public String toString(){
        String s = "" + value;
        return s;
    }
}