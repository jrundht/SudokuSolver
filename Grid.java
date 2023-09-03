import java.util.ArrayList;

public class Grid{
    ArrayList<Cell> allCells = new ArrayList<>();
    int rows;
    int cols;

    Grid(int row, int col){
        rows = row;
        cols = col;
    }

    public void addCell(char value){
        allCells.add(new Cell(value));
    }

    public Cell getCell(int i){
        return allCells.get(i);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int gridSz = rows * rows * cols * cols;
        
        for(int i = 0; i < allCells.size(); i++){
            sb.append(allCells.get(i));
            
            //Vertical separator
            if((i+1) % cols == 0 && (i+1) % (rows*cols) != 0){
                sb.append("|");
            }
            if ((i+1) % (cols * rows) == 0) {
                sb.append("\n");
                
                //Horizontal separators
                if ((i + 1) % (rows * rows * cols) == 0 && (i + 1) != gridSz) {
                    for (int j = 0; j < rows - 1; j++) {
                        sb.append("-".repeat(cols)).append("+");
                    }
                    sb.append("-".repeat(cols));
                    sb.append("\n");
                }
            }
        }
        return sb.toString();
    }
}