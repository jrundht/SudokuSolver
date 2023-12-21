import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View {
	Controll controller;
	JFrame frame;
	JPanel mainPanel;
	JPanel firstView;
	JPanel gridPanel;
	JPanel topPanel;

	JButton solveButton;
	JButton resetButton;
	JButton quitButton;

	JButton sixGrid;
	JButton nineGrid;
	JTextField[][] cells;

	View(Controll controll){
		this.controller = controll;

		frame = new JFrame("SudokuSolver");
		frame.setSize(750, 750);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		frame.add(mainPanel);

		choice();

		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	private void setTopPanel(){
		topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(1, 3));

		solveButton = new JButton("Solve");
		solveButton.addActionListener(new Button());
		topPanel.add(solveButton);
		resetButton = new JButton("Reset");
		resetButton.addActionListener(new Button());
		topPanel.add(resetButton);
		quitButton = new JButton("Quit");
		quitButton.addActionListener(new Button());
		topPanel.add(quitButton);
		mainPanel.add(topPanel, BorderLayout.NORTH);
	}

	class Button implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e){
			String choice = e.getActionCommand();
			if(choice.equals("6x6")){
				controller.createSixGrid();
				//TODO: call method that creates 6x6 grid in panel
				createGridPanel(6, 6);
			}else if(choice.equals("9x9")){
				controller.createNineGrid();
				//TODO: call method that creates 9x9 grid in panel
				createGridPanel(9, 9);
			}else if(choice.equals("Solve")){
				createAndSolveSudoku();
			}else if(choice.equals("Reset")){
				resetGrid();
			}else{
				System.exit(0);
			}

		}
	}
	private void choice(){
		sixGrid = new JButton("6x6");
		sixGrid.setSize(50,50);
		sixGrid.addActionListener(new Button());
		nineGrid = new JButton("9x9");
		nineGrid.setSize(50,50);
		nineGrid.addActionListener(new Button());
		firstView = new JPanel();
		firstView.setLayout(new GridLayout(1, 2)); // use GridLayout
		firstView.add(sixGrid);
		firstView.add(nineGrid);
		mainPanel.add(firstView, BorderLayout.CENTER);
	}

	private void createGridPanel(int rows, int cols){
		mainPanel.remove(firstView);
		setTopPanel();
		cells = new JTextField[rows][cols];
		gridPanel = new JPanel();
		gridPanel.setLayout(new GridLayout(rows, cols));
		Font font = new Font("Arial", Font.PLAIN, 20);
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < cols; j++){
				JTextField cell = new JTextField();
				cell.setHorizontalAlignment(JTextField.CENTER);
				cell.setFont(font);
				gridPanel.add(cell);
				cells[i][j] = cell;
			}
		}
		mainPanel.add(gridPanel, BorderLayout.CENTER);
		mainPanel.revalidate();
		mainPanel.repaint();
		frame.getContentPane().repaint();
	}

	private void createAndSolveSudoku(){
		for(int i = 0; i < cells.length; i++){
			for(int j = 0; j < cells[i].length; j++){
				JTextField cell = cells[i][j];
				String c = cell.getText();
				if(c.equals("")) c += ".";
				controller.addCell(c.charAt(0), i, j);
			}
		}
		controller.createDataStructure();
		controller.solve();
	}

	public void updateGrid(){
		for(int i = 0; i < cells.length; i++){
			for(int j = 0; j < cells[i].length; j++){
				JTextField cell = cells[i][j];
				if(cell.getText().equals("")){
					cell.setText(controller.getCell(i, j));
					cell.setForeground(Color.BLUE);
				}else{
					cell.setText(controller.getCell(i, j));
				}
				cell.repaint();
			}
		}
		frame.getContentPane().repaint();
	}

	private void resetGrid(){
		for(int i = 0; i < cells.length; i++){
			for(int j = 0; j < cells[i].length; j++){
				JTextField cell = cells[i][j];
				cell.setText("");
				cell.revalidate();
				cell.repaint();
			}
		}
		frame.getContentPane().repaint();
	}

}
