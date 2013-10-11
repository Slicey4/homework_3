package ee.ut.math.tvt.slicey4;
import java.awt.GridLayout;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class IntroUI extends Intro{
	public static String Team_name;
	public static String Team_leader;
	public static String Team_leader_email;
	public static String[] Team_members;
	public static Icon Team_logo;
	public static int software_version_number;
	
	public JFrame window;
	public JLabel Imagelabel;
	public JPanel panel;

	
	void Create(){
		JFrame frame = new JFrame("Intro");
		frame.setLayout(new GridLayout(1,2));
    	frame.setSize(1400,600);
    	frame.setDefaultCloseOperation(window.EXIT_ON_CLOSE);
    	
    	Team_logo = new ImageIcon("logo.png");
    	
        Imagelabel = new JLabel(Team_logo);
          	
        frame.add(Imagelabel);
        
		panel = new JPanel(new GridLayout(5,1));
		
		JLabel text1 = new JLabel("Team name:" + Team_name);
		JLabel text2 = new JLabel("Team leader: " + Team_leader);
		JLabel text3 = new JLabel("Team leader email: " + Team_leader_email);
		JLabel text4 = new JLabel("Team members: " + Team_members.toString());
		JLabel text5 = new JLabel("Software version number: " + software_version_number);
		
		panel.add(text1);
		panel.add(text2);
		panel.add(text3);
		panel.add(text4);
		panel.add(text5);

		
		
	
	}
	
}
