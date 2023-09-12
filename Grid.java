public class Grid{
    private Cell[][] cells;
    private int rows;
    private int cols;

    Grid(int row, int col){
        cells = new Cell[row*col][row*col];
        rows = row;
        cols = col;
    }

    public void addCell(Cell cell, int i, int j){
        cells[i][j] = cell;
    }

    public void linkCells(){
        for(int i = 0; i < rows*cols; i++){
            for(int j = 0; j < rows*cols; j++){
                if (j == rows*cols - 1 && i != rows*cols - 1) {
                    cells[i][j].next = cells[i + 1][0];
                } else if (j < rows*cols - 1) {
                    cells[i][j].next = cells[i][j + 1];
                }
            }
        }
    }
    
    public void addCellArray(Cell[] oneArray, int index){
        cells[index] = oneArray;
    }

    public Cell getCell(int i, int j){
        if (i >= 0 && i < rows*cols && j >= 0 && j < rows*cols) {
            return cells[i][j];
        } else {
            throw new IndexOutOfBoundsException("Invalid cell indices: " + i + ", " + j);
        }
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < rows*cols; i++){
            for(int j = 0; j < rows*cols; j++){
                if(j % cols == 0 && j != 0) sb.append("|");
                sb.append(cells[i][j]);
            }
            sb.append("\n");
            if((i+1) % rows == 0 && i != 0){
                for (int j = 0; j < rows - 1; j++) {
                    sb.append("-".repeat(cols)).append("+");
                    }
                sb.append("-".repeat(cols));
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}