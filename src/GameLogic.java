import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameLogic {

    //    Button
    private JButton btn_upperLeft;
    private JButton btn_upperMiddle;
    private JButton btn_upperRight;
    private JButton btn_lowerLeft;
    private JButton btn_lowerMiddle;
    private JButton btn_lowerRight;
    private JButton btn_middleLeft;
    private JButton btn_middleCenter;
    private JButton btn_middleRight;

    //    ButtonListener
    private ButtonListener upperLeft;
    private ButtonListener upperMiddle;
    private ButtonListener upperRight;
    private ButtonListener lowerLeft;
    private ButtonListener lowerMiddle;
    private ButtonListener lowerRight;
    private ButtonListener middleLeft;
    private ButtonListener middleCenter;
    private ButtonListener middleRight;

    private JPanel boardpanel;
    private JPanel mainpanel;
    private JButton []button = new JButton[]{btn_upperLeft, btn_upperMiddle, btn_upperRight, btn_lowerLeft, btn_lowerMiddle, btn_lowerRight, btn_middleLeft, btn_middleCenter, btn_middleRight};
    private String startgame = "X";

    //    Modified
    public ButtonListener buttonListener;

    String [][]board = new String[3][3];

    Font f1  = new Font("Arial", Font.BOLD, 30);


    public GameLogic(JPanel mainpanel, JPanel boardpanel, JButton[] button, JFrame frame){
        this.mainpanel = mainpanel;
        this.boardpanel = boardpanel;
        this.button = button;

//        Debug
//        System.out.println("Button 1 "+button1);
            upperLeft = new ButtonListener(button[0]);
            upperMiddle = new ButtonListener(button[1]);;
            upperRight = new ButtonListener(button[2]);;
            lowerLeft = new ButtonListener(button[3]);;
            lowerMiddle = new ButtonListener(button[4]);;
            lowerRight = new ButtonListener(button[5]);;
            middleLeft = new ButtonListener(button[6]);;
            middleCenter = new ButtonListener(button[7]);;
            middleRight = new ButtonListener(button[8]);;

//          buttonListener = new ButtonListener(button[8]);
    }

    private void initButton(JButton button,boolean enableStatus, Font font, String start)
    {
                  button.setEnabled(enableStatus);
                  button.setText(start);
                  button.setFont(font);
    }

    public class ButtonListener
    {
        public ButtonListener(JButton button)
        {
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    initButton(button,false,f1,startgame);

                    choose_player();
                    String c = button.getText();
                    board[2][2] = c;
                    if (winner_player("X")){
                        lockBoard();
                        JOptionPane.showMessageDialog(GameLogic.this.mainpanel,"X has won");
                        unlockBoard();
                    }
                    else if(winner_player("O")){
                        lockBoard();
                        JOptionPane.showMessageDialog(GameLogic.this.mainpanel,"O has won");
                        unlockBoard();
                    }
                }
            });
        }
    }
    private void lockBoard(){
        for (int i =0; i<9 ;i++){
            boardpanel.getComponent(i).setEnabled(false);
        }
    }
    private void unlockBoard(){
        for (int i =0; i<9 ;i++){
            boardpanel.getComponent(i).setEnabled(true);
        }

        //reset
        button[0].setText("");
        button[1].setText("");
        button[2].setText("");
        button[3].setText("");
        button[4].setText("");
        button[5].setText("");
        button[6].setText("");
        button[7].setText("");
        button[8].setText("");
        for ( int i = 0; i<3; i++){
            for (int j = 0; j<3;j++){
                board[i][j] = " ";
            }
        }
    }
    private void choose_player(){
        if (startgame.equalsIgnoreCase("X")){
            startgame = "O";
        }
        else {
            startgame = "X";
        }
    }
    private boolean winner_player( String player){
        for (int i = 0; i<3 ; i++) {
            //Row
            if ((board[i][0] == player) && (board[i][1] == player) && (board[i][2] == player)) {
                return true;
            }
            //Column
            if ((board[0][i] == player) && (board[1][i] == player) && (board[2][i] == player)) {
                return true;
            }
        }

        //Diagonal left
        if ((board[0][0]== player) && (board[1][1]== player) && (board[2][2]== player)){
            return true;
        }
        //Diagonal right
        if ((board[0][2]== player) && (board[2][1]== player) && (board[2][0]== player)){
            return true;
        }
        return false;
    }

}


