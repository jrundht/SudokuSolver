public class Grid{
    private Cell[][] cells;
    private static Row[] allRows;
    private static Column[] allColumns;
    private static Box[] allBoxes;
    private int rows;
    private int cols;

    Grid(int row, int col){
        cells = new Cell[row*col][row*col];
        rows = row;
        cols = col;
    }

    public void addCell(Cell cell, int i, int j)throws NumberOutsideRangeException{
        if((int) cell.getValue() - 56 > rows*cols) throw new NumberOutsideRangeException(cell.getValue(), rows*cols);
        cells[i][j] = cell;
    }

    public void createDataStructure(){
        linkCells();
        setRowsAndColumns();
        setBoxes();
    }

    private void linkCells(){
        for(int i = 0; i < rows*cols; i++){
            for(int j = 0; j < rows*cols; j++){
                if (j == rows*cols - 1 && i != rows*cols - 1) {
                    cells[i][j].setNext(cells[i + 1][0]);
                } else if (j < rows*cols - 1) {
                    cells[i][j].setNext(cells[i][j + 1]);
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

    private void setRowsAndColumns(){
        allColumns = new Column[rows * cols];
        allRows = new Row[rows*cols];

        for(int i = 0; i < rows*cols; i++){
            allColumns[i] = new Column();
            allRows[i] = new Row();
            for(int j = 0; j < rows*cols; j++){
                Cell columnCell = getCell(j, i);          //get i'th cell from each array
                columnCell.setColumn(allColumns[i]);
                allColumns[i].addCell(columnCell);

                Cell rowCell = getCell(i, j);
                rowCell.setRow(allRows[i]);
                allRows[i].addCell(rowCell);
            }
        }
    }

    public void setBoxes(){
        allBoxes = new Box[rows * cols];
        for (int boxNum = 0; boxNum < rows * cols; boxNum++) {
            allBoxes[boxNum] = new Box();

            //find the top left corner of each box
            int boxRow = (boxNum / rows) * rows;
            int boxCol = (boxNum % rows) * cols;

            for(int i = 0; i < rows; i++){
                for(int j = 0; j < cols; j++){
                    Cell oneCell = getCell(boxRow + i, boxCol + j);
                    oneCell.setBox(allBoxes[boxNum]);
                    allBoxes[boxNum].addCell(oneCell);
                }
            }
        }
    }

    public void solve(){
        getCell(0,0).fillThisCellAndTheRest();
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < rows*cols; i++){
            for(int j = 0; j < rows*cols; j++){
                if(j % cols == 0 && j != 0) sb.append("| ");
                sb.append(cells[i][j]).append(" ");
            }
            sb.append("\n");
            if((i+1) % rows == 0 && i != 0){
                for (int j = 0; j < rows - 1; j++) {
                    sb.append("- ".repeat(cols)).append("+ ");
                    }
                sb.append("- ".repeat(cols));
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}