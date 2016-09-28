import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by ross on 27/09/16.
 */
public class DartsMatch extends JFrame
{
    private JPanel displayPanel;
    private final int NUMBER_OF_DISPLAY_LABELS = 12;
    private String[] displayScores;
    private JLabel[] displayLabels;

    private JPanel scorePanel;
    private JLabel playersTurn;
    private JLabel currentPlayerScore;
    private JLabel scoreToEnter;

    private JPanel keypadPanel;
    private final int NUMBER_KEYPAD_BUTTONS = 9;
    private JButton[] keypadButtons;
    private JButton enterButton;
    private JButton zeroButton;
    private JButton deleteButton;

    Player player1;
    Player player2;
    Player currentPlayersTurn;



    Container cp;

    public DartsMatch(String title)
    {
        setSize(500, 300);
        setTitle(title);
        JOptionPane.showMessageDialog(null, "Throw for the bull");
        player1 = new Player((String)JOptionPane.showInputDialog(
                null,
                "Enter player name",
                JOptionPane.PLAIN_MESSAGE));
        player2 = new Player((String)JOptionPane.showInputDialog(
                null,
                "Enter player name",
                JOptionPane.PLAIN_MESSAGE));
        currentPlayersTurn = player1;

        //set up dislay panel
        displayPanel = new JPanel();
        displayPanel.setLayout(new GridLayout(3, 4));
        displayScores = new String[]{"First to 5 sets", "sets", "legs", "points", player1.getName(), Integer.toString(player1.getSets()), Integer.toString(player1.getLegs()), Integer.toString(player1.getPoints()), player2.getName(), Integer.toString(player2.getSets()), Integer.toString(player2.getLegs()), Integer.toString(player2.getPoints())};
        displayLabels = new JLabel[NUMBER_OF_DISPLAY_LABELS];
        for(int i = 0; i < NUMBER_OF_DISPLAY_LABELS; i++)
        {
            displayLabels[i] = new JLabel(displayScores[i]);
            displayPanel.add(displayLabels[i]);
        }

        //set up score panel
        scorePanel = new JPanel();
        scorePanel.setLayout(new GridLayout(1,3));
        playersTurn = new JLabel(currentPlayersTurn.getName() + " to throw");
        currentPlayerScore = new JLabel(Integer.toString(currentPlayersTurn.getPoints()));
        scoreToEnter = new JLabel("");
        scorePanel.add(playersTurn);
        scorePanel.add(currentPlayerScore);
        scorePanel.add(scoreToEnter);

        //set up keypad panel
        keypadPanel = new JPanel();
        keypadPanel.setLayout(new GridLayout(4, 3));
        keypadButtons = new JButton[NUMBER_KEYPAD_BUTTONS];
        for(int i = 0; i < NUMBER_KEYPAD_BUTTONS; i++)
        {
            keypadButtons[i] = new JButton("" + (i + 1));
            keypadPanel.add(keypadButtons[i]);
            keypadButtons[i].addActionListener(new KeypadButtonWatcher());
        }
        enterButton = new JButton("Ent");
        keypadPanel.add(enterButton);
        enterButton.addActionListener(new KeypadButtonWatcher());
        zeroButton = new JButton("0");
        keypadPanel.add(zeroButton);
        zeroButton.addActionListener(new KeypadButtonWatcher());
        deleteButton = new JButton("Del");
        keypadPanel.add(deleteButton);
        deleteButton.addActionListener(new KeypadButtonWatcher());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cp = getContentPane();
        cp.add(displayPanel, BorderLayout.NORTH);
        cp.add(scorePanel, BorderLayout.CENTER);
        cp.add(keypadPanel, BorderLayout.EAST);
    }

    public boolean testScore(String enteredScore)
    {
        int score = Integer.parseInt(enteredScore);
        if(score > Integer.parseInt(currentPlayerScore.getText()) || enteredScore.length() > 3 || score > 180 || scoreToEnter.getText().equals(""))
        {
            return false;
        }
        return true;
    }

    public void updateCurrentPlayerScores()
    {
        if(currentPlayersTurn.updatePoints(Integer.parseInt(scoreToEnter.getText())))
        {
            resetPlayerScores();
        }
    }

    public void updateScores()
    {
        displayLabels[5].setText(Integer.toString(player1.getSets()));
        displayLabels[6].setText(Integer.toString(player1.getLegs()));
        displayLabels[7].setText(Integer.toString(player1.getPoints()));
        displayLabels[9].setText(Integer.toString(player2.getSets()));
        displayLabels[10].setText(Integer.toString(player2.getLegs()));
        displayLabels[11].setText(Integer.toString(player2.getPoints()));
    }

    public void checkForWin()
    {
        if(!(currentPlayersTurn.getSets() == 3))
        {
            changePlayer();
        }
        else
        {
            playersTurn.setText(currentPlayersTurn.getName() + " wins!");
        }
    }


    public void changePlayer()
    {
        if(currentPlayersTurn.getName().equals(player1.getName()))
        {
            currentPlayersTurn = player2;
            playersTurn.setText(currentPlayersTurn.getName() + " to throw");
            currentPlayerScore.setText(Integer.toString(currentPlayersTurn.getPoints()));
            scoreToEnter.setText("");
        }
        else
        {
            currentPlayersTurn = player1;
            playersTurn.setText(currentPlayersTurn.getName() + " to throw");
            currentPlayerScore.setText(Integer.toString(currentPlayersTurn.getPoints()));
            scoreToEnter.setText("");
        }
    }

    public void resetPlayerScores()
    {
        player1.setPoints(501);
        player2.setPoints(501);
        if(currentPlayersTurn.getLegs() == 0)
        {
            player1.setLegs(0);
            player2.setLegs(0);
        }
    }

    public void resetScoreToEnter()
    {
        scoreToEnter.setText("");
    }


    private class KeypadButtonWatcher implements ActionListener
    {
        public void actionPerformed(ActionEvent a)
        {
            Object buttonPressed = a.getSource();
            if(buttonPressed.equals(keypadButtons[0]))
            {
                scoreToEnter.setText(scoreToEnter.getText() + "1");
            }
            if(buttonPressed.equals(keypadButtons[1]))
            {
                scoreToEnter.setText(scoreToEnter.getText() + "2");
            }
            if(buttonPressed.equals(keypadButtons[2]))
            {
                scoreToEnter.setText(scoreToEnter.getText() + "3");
            }
            if(buttonPressed.equals(keypadButtons[3]))
            {
                scoreToEnter.setText(scoreToEnter.getText() + "4");
            }
            if(buttonPressed.equals(keypadButtons[4]))
            {
                scoreToEnter.setText(scoreToEnter.getText() + "5");
            }
            if(buttonPressed.equals(keypadButtons[5]))
            {
                scoreToEnter.setText(scoreToEnter.getText() + "6");
            }
            if(buttonPressed.equals(keypadButtons[6]))
            {
                scoreToEnter.setText(scoreToEnter.getText() + "7");
            }
            if(buttonPressed.equals(keypadButtons[7]))
            {
                scoreToEnter.setText(scoreToEnter.getText() + "8");
            }
            if(buttonPressed.equals(keypadButtons[8]))
            {
                scoreToEnter.setText(scoreToEnter.getText() + "9");
            }
            if(buttonPressed.equals(enterButton))
            {
                if(!testScore(scoreToEnter.getText()))
                {
                    JOptionPane.showMessageDialog(null, "Please check entered score.");
                }
                else
                {
                    updateCurrentPlayerScores();
                    updateScores();
                    checkForWin();
                }
            }
            if(buttonPressed.equals(zeroButton))
            {
                scoreToEnter.setText(scoreToEnter.getText() + "0");
            }
            if(buttonPressed.equals(deleteButton))
            {
                if(scoreToEnter.getText().length() > 0)
                {
                    scoreToEnter.setText(scoreToEnter.getText().substring(0, scoreToEnter.getText().length() - 1));
                }
            }
        }
    }

}
