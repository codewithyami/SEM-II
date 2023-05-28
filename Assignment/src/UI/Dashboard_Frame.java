package UI;

import java.awt.Color;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class Dashboard_Frame {
	protected JPanel Left_panel, Top_panel, Center_panel;
	Frame sharedFrame = Frame.getInstance();
	
	public Dashboard_Frame() {
		
		Left_panel = new JPanel();
		Left_panel.setSize(300,980);
		Left_panel.setBounds(0,0,300,980);
		Left_panel.setBackground(new Color(46,18,45)); // set the panel background color to red
        Left_panel.setVisible(true);
        Left_panel.setLayout(null);
        sharedFrame.add(Left_panel);
                
        Top_panel = new JPanel();
		Top_panel.setBounds(300,0,1460,198);
		Top_panel.setBackground(new Color(83,3,50)); // set the panel background color to red
        Top_panel.setVisible(true);
        Top_panel.setLayout(null);
        sharedFrame.add(Top_panel);
        
        Center_panel = new JPanel();
		Center_panel.setBounds(300,198,1460,782);
		Center_panel.setBackground(new Color(234,214,214)); // set the panel background color to red
        Center_panel.setVisible(true);
        Center_panel.setLayout(null);
        sharedFrame.add(Center_panel); 
	}
	
	public static void main(String[] args) {
		new Dashboard_Frame();
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
