package ee.ut.math.tvt.slicey4;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class IntroUI extends JFrame{

	public static Icon Team_logo;
	
	
	public JFrame window;
	public JLabel Imagelabel;
	public JPanel panel;

	
				
	
	void Create(Map<String, String> m){
		
		JFrame frame = new JFrame("Intro");
		frame.setLayout(new GridLayout(1,2));
    	frame.setSize(1400,600);
    	frame.setDefaultCloseOperation(window.EXIT_ON_CLOSE);
    	
    	Team_logo = new ImageIcon("logo.png");
    	
        Imagelabel = new JLabel(Team_logo);
          	
        frame.add(Imagelabel);
        
		panel = new JPanel(new GridLayout(5,1));
		
		JLabel text1 = new JLabel("Team name:" + m.get("team.name"));
		JLabel text2 = new JLabel("Team leader: " + m.get("team.leader"));
		JLabel text3 = new JLabel("Team leader email: " + m.get("leader.email"));
		JLabel text4 = new JLabel("Team members: " + m.get("team.members"));
		JLabel text5 = new JLabel("Software version number: " + m.get("versioon"));
		
		panel.add(text1);
		panel.add(text2);
		panel.add(text3);
		panel.add(text4);
		panel.add(text5);

		
		
	
	}
	
}
