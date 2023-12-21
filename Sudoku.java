import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.ArrayList;

public class Sudoku {
    private static Grid grid;
    private static Row[] rows;
    private static Column[] columns;
    private static Box[] boxes;
    private static int numRows, numColumns;


    public static void readFile(String filename) throws CharCountMismatchException, NumberOutsideRangeException{
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

        //num characters in the sudoku + the two dimension characters + newline characters
        int expChars = 2 + numRows*numRows*numColumns*numColumns + (numRows*numColumns+1);
        try{
            content = new String(Files.readAllBytes(Paths.get(filename)));
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
                if((int) row[i] - 56 > numRows*numColumns) throw new NumberOutsideRangeException(row[i], numRows*numColumns);
                cellRow[i] = new Cell(row[i]);
                //cellRow[i].setRow(rows[index]);
                //rows[index].addCell(cellRow[i]);
            }
            grid.addCellArray(cellRow, index++);
        }
        grid.createDataStructure();
        scanner.close();
    }

    public static void solveSudoku(){
        if(grid.getCell(0,0).fillThisCellAndTheRest()){
            System.out.println("Sudoku solved successfully:");
            System.out.println(grid);
        }else{
            System.out.println("Sudoku is not solvable :/");
        }
    }

    public static void printPointers(){
        Cell cell = grid.getCell(0,0);
        while (cell != null){
            System.out.print(cell);
            cell = cell.next();
        }
        System.out.println();

    }

    public static void main(String[] args){
        try {
            readFile(args[0]);
        } catch (Exception e){
            e.printStackTrace();
            System.exit(1);
        }
        System.out.println(grid);

        solveSudoku();
        System.out.println();
    }
}
