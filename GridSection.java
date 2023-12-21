import java.util.ArrayList;

public class GridSection {
    ArrayList<Character> cells = new ArrayList<>();
    ArrayList<Cell> myCells = new ArrayList<>();

    public int getSize(){
        return cells.size();
    }

    public void addCell(Cell cell){
        cells.add(cell.getValue());
        myCells.add(cell);
    }

    public boolean valueInSection(char value){
        for(Cell c : myCells){
            if(c.getValue() == value) return true;
        }
        return false;
    }

    @Override
    public String toString(){
        String s = "[";
        for(Cell c : myCells){
            s += c.getValue() + " ";
        }
        s += "]";
        return s;
    }
}
