public class Cell{
    private char value;

    private Row thisRow;
    private Column thisColumn;
    private Box thisBox;

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

    @Override
    public String toString(){
        // if(value == '.') value = ' ';
        String s = "" + value;
        return s;
    }
}