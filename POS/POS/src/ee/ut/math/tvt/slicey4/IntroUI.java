package ee.ut.math.tvt.slicey4;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class IntroUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static ImageIcon Team_logo;

	public JFrame window;
	public static JLabel Imagelabel;
	public static JPanel panel;

	void Create(Map<String, String> m) {

		JFrame frame = new JFrame("Intro");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(0, 2));
		frame.setSize(950, 400);

		Team_logo = new ImageIcon("logo.png");

		Imagelabel = new JLabel(Team_logo);

		frame.add(Imagelabel);

		panel = new JPanel(new GridLayout(5, 1));
		panel.setBackground(Color.white);

		JLabel text1 = new JLabel("Team name: " + m.get("team.name"));
		JLabel text2 = new JLabel("Team leader: " + m.get("team.leader.name"));
		JLabel text3 = new JLabel("Team leader email: " + m.get("leader.email"));
		JLabel text4 = new JLabel("Team members: " + m.get("team.members"));
		JLabel text5 = new JLabel("Software version number: "
				+ m.get("versioon"));

		panel.add(text1);
		panel.add(text2);
		panel.add(text3);
		panel.add(text4);
		panel.add(text5);

		frame.getContentPane().setBackground(Color.white);
		frame.add(panel);
		frame.setVisible(true);

	}

}
