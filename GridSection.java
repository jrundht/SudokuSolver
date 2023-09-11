import java.util.ArrayList;

public class GridSection {
    ArrayList<Character> cells = new ArrayList<>();

    public int getSize(){
        return cells.size();
    }

    public boolean checkValue(char value){
        for(char v : cells){
            if(v == value) return true;
        }
        return false;
    }

    public void addCell(char value){
        boolean valueInSection = false;
        if(value != '.') valueInSection = checkValue(value);

        if(!valueInSection){
            cells.add(value);
        }else{
            cells.add('.');
        }
    }

    public boolean valueInSection(char value){
        for(char v : cells){
            if(v == value) return true;
        }
        return false;
    }

    @Override
    public String toString(){
        String s = "[";
        for(char c : cells){
            s += c + " ";
        }
        s += "]";
        return s;
    }
}
