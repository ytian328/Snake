import java.awt.*;
import java.awt.event.*;

public class SnakeControl extends Frame implements WindowListener, ActionListener {
	
	TextField text = new TextField(20);
    Button up;
    Button down;
    Button left;
    Button right;
    String direction = "R";


    public SnakeControl(String title) {

            super(title);
            setLayout(new FlowLayout());
            addWindowListener(this);
            up = new Button("Up");
            down = new Button("Down");
            left = new Button("Left");
            right = new Button("right");
            add(up);
            add(down);
            add(left);
            add(right);
            add(text);
            up.addActionListener(this);
            down.addActionListener(this);
            left.addActionListener(this);
            right.addActionListener(this);
            
            this.setSize(350, 200);
            this.setVisible(true);
            
            
    }

    public void actionPerformed(ActionEvent e) {
            if(e.getSource() == up) direction = "U";
            else if(e.getSource() == down) direction = "D";
            else if(e.getSource() == left) direction = "L";
            else if(e.getSource() == right) direction = "R"; 
            text.setText("Button Clicked is : " + direction);
    }

    public void windowClosing(WindowEvent e) {
            dispose();
            System.exit(0);
    }

    public void windowOpened(WindowEvent e) {}
    public void windowActivated(WindowEvent e) {}
    public void windowIconified(WindowEvent e) {}
    public void windowDeiconified(WindowEvent e) {}
    public void windowDeactivated(WindowEvent e) {}
    public void windowClosed(WindowEvent e) {}

}
