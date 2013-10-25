package ee.ut.math.tvt.slicey4;

import java.awt.Color;
import java.awt.GridLayout;
import java.io.*;
import java.util.*;

import javax.swing.*;

public class IntroUI extends JFrame {

	/**
  * 
  */
	private static final long serialVersionUID = 1L;

	public static JLabel Team_logo;
	public static JFrame frame;
	// public static JLabel Imagelabel;
	public static JPanel panel;
	public static JLabel Team_name, Team_leader, Leader_mail, members,
			software;

	IntroUI() throws IOException {
		setTitle("Intro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(950, 400);

		Properties appProps = new Properties();
		FileInputStream in = new FileInputStream("application.properties");
		appProps.load(in);
		in.close();

		Properties verProps = new Properties();
		FileInputStream verIn = new FileInputStream("version.properties");
		verProps.load(verIn);
		verIn.close();
		

		setLayout(new GridLayout(0, 2));

		JLabel Team_logo = new JLabel(new ImageIcon("logo.png"));

		add(Team_logo);

		panel = new JPanel(new GridLayout(5, 1));
		panel.setBackground(Color.white);
		/*
		 * Team_name = new JLabel("Team name: " + andmed.get("team.name"));
		 * Team_leader = new JLabel("Team leader: " +
		 * andmed.get("team.leader.name")); Leader_mail = new
		 * JLabel("Team leader email: " + andmed.get("leader.email")); members =
		 * new JLabel("Team members: " + andmed.get("team.members")); software =
		 * new JLabel("Software version number: " + andmed.get("versioon"));
		 */

		// String[] data = {
		Team_name = new JLabel("Team name: "
				+ appProps.getProperty("team.name"));
		Team_leader = new JLabel("Team leader: "
				+ appProps.getProperty("team.leader.name"));
		Leader_mail = new JLabel("Team leader email: "
				+ appProps.getProperty("leader.email"));
		members = new JLabel("Team members: "
				+ appProps.getProperty("team.members"));
		software = new JLabel("Build number: "
				+ verProps.getProperty("build.major.number") + "."
				+ verProps.getProperty("build.minor.number") + "."
				+ verProps.getProperty("build.revision.number"));

		panel.add(Team_name);
		panel.add(Team_leader);
		panel.add(Leader_mail);
		panel.add(members);
		panel.add(software);

		getContentPane().setBackground(Color.white);
		add(panel);

		setVisible(true);

	};

}