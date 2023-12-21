public class Controll {
	View view;
	Grid grid;

	public void createSixGrid(){
		grid = new Grid(2, 3);
	}
	public void createNineGrid(){
		grid = new Grid(3, 3);
	}

	public void addCell(char c, int i, int j){
		try{
			grid.addCell(new Cell(c), i , j);
		} catch (NumberOutsideRangeException e){
			System.err.println(e);
			System.exit(1);
		}
	}

	public String getCell(int i, int j){
		return "" + grid.getCell(i, j).getValue();
	}

	public void createDataStructure(){
		grid.createDataStructure();
	}

	public void solve(){
		grid.solve();
		view.updateGrid();
	}


}
