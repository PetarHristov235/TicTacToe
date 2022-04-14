import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class tttGame extends JPanel {
     char playerMark='x';
    JButton[] buttons=new JButton[9];
    char emptyButton=' ';
    public tttGame(){
        setLayout(new GridLayout(3,3));
        initializeButtons();

        }

        public void initializeButtons() {
            for (int i = 0; i < 9; i++) {
                buttons[i] = new JButton();
                JButton currentButton =buttons[i] ;
                currentButton.setText(String.valueOf(emptyButton));
                currentButton.setFont(new Font("Arial", Font.BOLD,50));
                currentButton.setBackground(Color.white);
                currentButton.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JButton buttonClicked = (JButton) e.getSource();
                        char checkIfIsUsed = buttonClicked.getText().charAt(0);
                        if (checkIfIsUsed==emptyButton) {
                        buttonClicked.setText(String.valueOf(playerMark));



                            if (playerMark == 'x') {
                                playerMark = 'o';
                                buttonClicked.setBackground(new Color(185, 100,100));

                            } else {
                                playerMark = 'x';
                                buttonClicked.setBackground(new Color(100, 100, 185));
                            }
                            displayVictor();
                        }
                    }
                }) ;
                add(buttons[i]);
            }
        }

        public void displayVictor(){
        if (checkForWinner()){
            //reverse the player marks
            //because after we put x and we win, the game changes it to o
            if (playerMark=='x') playerMark='o';
            else playerMark='x';

            JOptionPane pane= new JOptionPane();
            int dialogResult= JOptionPane.showConfirmDialog(pane,"Game Over. "+ playerMark + " wins. Would you like to play again?", "Game Over."
                    , JOptionPane.YES_NO_OPTION);

            if (dialogResult==JOptionPane.YES_OPTION) resetTheButtons();
            else System.exit(0);
        }
        else if (checkDraw()){
            JOptionPane pane=new JOptionPane();
            int dialogResult=JOptionPane.showConfirmDialog(pane, "Draw. Play again?", "Game over.", JOptionPane.YES_NO_OPTION);
            if (dialogResult==JOptionPane.YES_OPTION) resetTheButtons();
            else System.exit(0);
        }
        }
        private void resetTheButtons(){
            for (int i = 0; i <9 ; i++) {
                playerMark='x';
                buttons[i].setText(String.valueOf(emptyButton));
                buttons[i].setBackground(Color.white);
            }
        }

    public boolean checkDraw(){
        boolean full=true;
            for (int i = 0; i <9 ; i++) {
                if(buttons[i].getText().charAt(0)==emptyButton){
                    full=false;
                }
            }

        return full;

    }
    public boolean checkForWinner() {
        if (checkRows() == true || checkColumns() == true || checkDiagonals() == true) {
            return true;


        } else {
            return false;
        }
    }
        public boolean checkRows(){
        int i = 0;
            for (int j = 0; j <3 ; j++) {
                if (buttons[i].getText().equals(buttons[i+1].getText())&& buttons[i].getText().equals(buttons[i+2].getText())
                        && buttons[i].getText().charAt(0)!= emptyButton) {
                    return true;
                }
                i=i+3;
                }
        return false;

    }

    public boolean checkColumns(){
        int i=0;
            for (int j = 0; j <3 ; j++) {
            if (buttons[i].getText().equals(buttons[i+3].getText())&& buttons[i].getText().equals(buttons[i+6].getText())
                    && buttons[i].getText().charAt(0) !=emptyButton){
                return true;
            }
            i++;
            }
            return false;
    }
    public boolean checkDiagonals(){
        if(buttons[0].getText().equals(buttons[4].getText())&& buttons[0].getText().equals(buttons[8].getText())
            && buttons[0].getText().charAt(0) !=emptyButton){
            return true;
        } else if(buttons[2].getText().equals(buttons[4].getText()) && buttons[2].getText().equals(buttons[6].getText())
            && buttons[2].getText().charAt(0) !=emptyButton) {
            return true;
        }        else {
            return false;
        }

    }

    public static void main(String[] args) {
        JFrame window= new JFrame("Tic Tac Toe by Petar Hristov♥");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().add(new tttGame());
        window.setBounds(500,500,500,500);
        window.setVisible(true);
        window.setLocationRelativeTo(null);
    }
    }


