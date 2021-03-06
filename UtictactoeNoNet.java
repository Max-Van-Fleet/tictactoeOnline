import java.awt.Color;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.scene.control.TextArea;


public class UtictactoeNoNet extends Application
{
    private ArrayList<ArrayList<Integer>> winList;
    private GridPane BigGrid;
    private int gridPlayed;
    private ArrayList<GridPane> smallGrids;
    public boolean mover;
    public boolean freeze;
    private BorderPane bp;
    private ArrayList<ArrayList<ArrayList<Boolean>>> currentgrid;
    public UtictactoeNoNet()
    {
        winList=new ArrayList<ArrayList<Integer>>();
        currentgrid = new ArrayList<ArrayList<ArrayList<Boolean>>>();
        gridPlayed = 9;
        smallGrids = new ArrayList<GridPane>();
        bp = new BorderPane();
        freeze=false;
        BigGrid = new GridPane();
        mover=true;
        winList.add(new ArrayList<Integer>());
        winList.add(new ArrayList<Integer>());
        winList.add(new ArrayList<Integer>());
        for(int i =0; i<9; i++)
        {
            winList.get(i%3).add(0);
        }
    }
    public static void main(String[] args) throws Exception {
        launch(args);
    }
    
    @Override
    public void start(Stage primary)
    {
        makegrid();
        bp.setCenter(BigGrid);
        Scene scene = new Scene(bp, 800, 500);
        scene.getStylesheets().add("stylesheet.css");
        primary.setScene(scene);
        primary.show();
    }
    private void makegrid()
    {
        for(int i = 0 ; i<9; i++)
        {
            currentgrid.add(new ArrayList<ArrayList<Boolean>>());
            smallGrids.add(new GridPane());
        }
        for(int i = 0; i<9;i++)   
        {
            currentgrid.get(i).add(new ArrayList<Boolean>());
            currentgrid.get(i).add(new ArrayList<Boolean>());
            currentgrid.get(i).add(new ArrayList<Boolean>());
            for(int j = 0; j<9;j++)
            {
                currentgrid.get(i).get(j%3).add(null);
                tile x = new tile("", j%3, j/3, false, i );
                smallGrids.get(i).add(x, j%3, j/3);
            }
            smallGrids.get(i).setHgap(5); 
            smallGrids.get(i).setVgap(5); 
        }
        for(int i= 0; i <9; i++)
        {
            BigGrid.add(smallGrids.get(i), i%3, i/3);
        }
        BigGrid.setHgap(20); 
        BigGrid.setVgap(20);
    }
    public void check2()
    {
        int winner =0;
        ArrayList<Integer> xWins= new ArrayList<Integer>();
        xWins.add(1);
        xWins.add(1);
        xWins.add(1);
        ArrayList<Integer> oWins= new ArrayList<Integer>();
        oWins.add(2);
        oWins.add(2);
        oWins.add(2);
        if(!winList.get(0).contains(0)&&!winList.get(1).contains(0)&&!winList.get(2).contains(0))
        {
            winner = 3;
            freeze = true;
        }
        for(int i =0; i<3; i++)
        {
            if (winList.get(i).equals(xWins)==true)
            {
                winner =1;
                freeze = true;
            }
            if (winList.get(i).equals(oWins)==true)
            {
                winner = 2;
                freeze = true;
            }
        }
        for(int i =0; i<3; i++)
        {
            ArrayList<Integer> col= new ArrayList<Integer>();
            col.add(winList.get(0).get(i));
            col.add(winList.get(1).get(i));
            col.add(winList.get(2).get(i));
            if (col.equals(xWins)==true)
            {
                winner = 1;
                //freeze = true;
            }
            if (col.equals(oWins)==true)
            {
                winner =2;
                //freeze = true;
            }
        }
        ArrayList<Integer> diag1= new ArrayList<Integer>();
        diag1.add(winList.get(0).get(0));
        diag1.add(winList.get(1).get(1));
        diag1.add(winList.get(2).get(2));
        if (diag1.equals(xWins)==true)
        {
            winner =1;
            //freeze = true;
        }
        if (diag1.equals(oWins)==true)
        {
            winner =2;
            //freeze = true;
        }
        ArrayList<Integer> diag2= new ArrayList<Integer>();
        diag2.add(winList.get(0).get(2));
        diag2.add(winList.get(1).get(1));
        diag2.add(winList.get(2).get(0));
        if (diag2.equals(xWins)==true)
        {
            winner = 1;
            //freeze = true;
        }
        if (diag2.equals(oWins)==true)
        {
            winner = 2;
            //freeze = true;
        }
        if(winner == 1)
        {
            Text Win = new Text();
            String x="X Wins!";
            Win.setText(x);
            Win.setId("fancytext");
            bp.setRight(Win);
        }
        else if(winner == 2)
        {
            Text Win = new Text();
            String x="O Wins!";
            Win.setText(x);
            Win.setId("fancytext");
            bp.setRight(Win);
        }
        else if(winner == 3)
        {
            Text Win = new Text();
            String x="Draw";
            Win.setText(x);
            Win.setId("fancytext");
            bp.setRight(Win);
        }
        else{
            Text Win = new Text();
            String x="";
            Win.setText(x);
            Win.setId("fancytext");
            bp.setRight(Win);
        }
    }
    public void check(int number)
    {
        int winner=0;
        ArrayList<ArrayList<Boolean>> t = currentgrid.get(number);
        ArrayList<Boolean> xWins= new ArrayList<Boolean>();
        xWins.add(true);
        xWins.add(true);
        xWins.add(true);
        ArrayList<Boolean> oWins= new ArrayList<Boolean>();
        oWins.add(false);
        oWins.add(false);
        oWins.add(false);
        if(!t.get(0).contains(null)&&!t.get(1).contains(null)&&!t.get(2).contains(null))
        {
            winner = 3;
            //freeze = true;
        }
        for(int i =0; i<3; i++)
        {
            if (t.get(i).equals(xWins)==true)
            {
                winner =1;
                //freeze = true;
            }
            if (t.get(i).equals(oWins)==true)
            {
                winner = 2;
                //freeze = true;
            }
        }
        for(int i =0; i<3; i++)
        {
            ArrayList<Boolean> col= new ArrayList<Boolean>();
            col.add(t.get(0).get(i));
            col.add(t.get(1).get(i));
            col.add(t.get(2).get(i));
            if (col.equals(xWins)==true)
            {
                winner = 1;
                //freeze = true;
            }
            if (col.equals(oWins)==true)
            {
                winner =2;
                //freeze = true;
            }
        }
        ArrayList<Boolean> diag1= new ArrayList<Boolean>();
        diag1.add(t.get(0).get(0));
        diag1.add(t.get(1).get(1));
        diag1.add(t.get(2).get(2));
        if (diag1.equals(xWins)==true)
        {
            winner =1;
            //freeze = true;
        }
        if (diag1.equals(oWins)==true)
        {
            winner =2;
            //freeze = true;
        }
        ArrayList<Boolean> diag2= new ArrayList<Boolean>();
        diag2.add(t.get(0).get(2));
        diag2.add(t.get(1).get(1));
        diag2.add(t.get(2).get(0));
        if (diag2.equals(xWins)==true)
        {
            winner = 1;
            //freeze = true;
        }
        if (diag2.equals(oWins)==true)
        {
            winner = 2;
            //freeze = true;
        }
        
        ObservableList<Node> childrens = BigGrid.getChildren();
        Node result = null;
        for (Node node : childrens) {
          if(BigGrid.getRowIndex(node) == number/3 && BigGrid.getColumnIndex(node) == number%3) {
             result = node;
            break;
          }
        }
        
        if(winner == 1)
        {
            winList.get(number%3).set(number/3,1);
            TextArea Win = new TextArea();
            String x="X";
            Win.setText(x);
            Win.setPrefHeight(160);
            Win.setPrefWidth(160);
            Win.setEditable(false);
            Win.setId("fancytext");
            BigGrid.getChildren().remove(result);
            BigGrid.add(Win,number%3,number/3);
            System.out.println(winList);
            winner=0;
        }
        else if(winner == 2)
        {
            winList.get(number%3).set(number/3,2);

            TextArea Win = new TextArea();
            String x="O";
            Win.setText(x);
            Win.setId("fancytext");
            Win.setPrefHeight(160);
            Win.setPrefWidth(160);
            Win.setEditable(false);
            BigGrid.getChildren().remove(result);
            BigGrid.add(Win,number%3,number/3);
            System.out.println(winList);
            winner=0;

        }
        else if(winner == 3)
        {
            winList.get(number%3).set(number/3,3);
            Text Win = new Text();
            String x="";
            Win.setText(x);
            Win.setId("fancytext");
            BigGrid.getChildren().remove(result);
            BigGrid.add(Win,number%3,number/3);
            System.out.println(winList);
            winner=0;

        }
        check2();
    }
    class tile extends Button
    {
        private String ch;
        private int x;
        private int y;
        private boolean clicked;
        private int number;
        public tile(String ch, int x,int y, boolean clicked, int number)
        {
            super(ch);
            this.x=x;
            this.y=y;
            this.clicked= clicked;
            setPrefSize(50,50);
            setOnAction(e->{
                if(gridPlayed == 9 ||winList.get(gridPlayed%3).get(gridPlayed/3)>0)
                {
                    gridPlayed=9;
                }
                if(gridPlayed == 9 || gridPlayed == number)
                {
                if(freeze== false)
                {
                    if (clicked ==false)
                    {
                        if (mover==true)
                        {
                            smallGrids.get(number).getChildren().remove(this);
                            smallGrids.get(number).add(new tile("X", x, y, true, number),x, y);
                            currentgrid.get(number).get(y).set(x,true);
                            mover= false;
                            gridPlayed = (y*3)+x;
                            check(number);
                        }
                        else
                        {
                            smallGrids.get(number).getChildren().remove(this);
                            smallGrids.get(number).add(new tile("O", x, y, true, number),x, y);
                            currentgrid.get(number).get(y).set(x,false);
                            gridPlayed = (y*3)+x;
                            mover= true;
                            check(number);
                        }
                    }
                }
            }
            });
        }
    }
}
