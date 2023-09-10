import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.ArrayList;

public class Sudoku {
    Grid grid;
    Row[] rows;
    Column[] columns;
    Box[] boxes;
    int numRows, numColumns;


    public void readFile(String filename) throws CharCountMismatchException{
        Scanner scanner = null;
        File file = new File(filename);
        String content = null;

        try{
            scanner = new Scanner(file);
        }catch (FileNotFoundException e){
            System.err.println(e);
            System.exit(1);
        }

        numRows = Integer.parseInt(scanner.nextLine());
        numColumns = Integer.parseInt(scanner.nextLine());

        int expChars = numRows*numRows*numColumns*numColumns + 2 + (numRows*numColumns+1);
        try{
            content = new String(Files.readAllBytes(Paths.get(filename)));
            // System.out.println("Characters in file: " + content.length());
            if(content.length() != expChars){
                throw new CharCountMismatchException(numRows*numColumns);
            }
        }catch (Exception e) {
            System.err.println(e);
            System.exit(1);
        }
        
        grid = new Grid(numRows,numColumns);

        int index = 0;
        rows = new Row[numRows*numColumns];

        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            char[] row = line.toCharArray();
            Cell[] cellRow = new Cell[row.length];
            rows[index] = new Row();
            for(int i = 0; i < row.length; i++){    //setting rows
                //check if value is valid 

                cellRow[i] = new Cell(row[i]);
                cellRow[i].setRow(rows[index]);     
                rows[index].addCell(cellRow[i].getValue());
            }
            grid.addCellArray(cellRow, index++);
        }
        scanner.close();
    }

    public void setColumns() {
        columns = new Column[numRows * numColumns];
        System.out.println("Columns:");
        
        for(int i = 0; i < numRows*numColumns; i++){
            columns[i] = new Column();
            for(int j = 0; j < numRows*numColumns; j++){
                Cell oneCell = grid.getCell(j, i);          //get i'th cell from each array
                oneCell.setColumn(columns[i]);
                columns[i].addCell(oneCell.getValue());
            }
            System.out.println(i + ": " + columns[i].toString());
        }
    }

    public void setBoxes() {
        boxes = new Box[numRows * numColumns];
        for (int boxNum = 0; boxNum < numRows * numColumns; boxNum++) {
            boxes[boxNum] = new Box();
            
            int boxRow = (boxNum / numRows) * numRows; 
            int boxCol = (boxNum % numRows) * numColumns;

            for(int i = 0; i < numRows; i++){
                for(int j = 0; j < numColumns; j++){
                    Cell oneCell = grid.getCell(boxRow + i, boxCol + j);
                    oneCell.setBox(boxes[boxNum]);
                    boxes[boxNum].addCell(oneCell.getValue());
                }
            }
            
            System.out.println(boxes[boxNum].toString());
        }
    }

    public void createDataStructure(){
        // setColumns();
        setBoxes();
    }

    public static void main(String[] args){
        Sudoku sudoku = new Sudoku();
        try {
            sudoku.readFile(args[0]);
        } catch (CharCountMismatchException e) {
            e.printStackTrace();
        }
        System.out.println(sudoku.grid);
        sudoku.createDataStructure();
    }
}
