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

    @Override
    public String toString(){
        String s = "" + value;
        return s;
    }
}