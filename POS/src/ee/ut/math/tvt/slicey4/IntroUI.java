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

	public static ImageIcon Team_logo;
	public static JFrame frame;
	public static JLabel Imagelabel;
	public static JPanel panel;
	public static JLabel Team_name, Team_leader, Leader_mail, members,
			software;

	IntroUI() {

		Map<String, String> andmed = loeFail("application.properties");
		Map<String, String> versioon = loeFail("version.properties");
		andmed.put(
				"versioon",
				versioon.get("build.major.number") + "."
						+ versioon.get("build.minor.number") + "."
						+ versioon.get("build.revision.number"));

		setTitle("Intro");

		setLayout(new GridLayout(0, 2));
		setSize(950, 400);

		Team_logo = new ImageIcon(getClass().getClassLoader().getResource("logo.png"));

		Imagelabel = new JLabel(Team_logo);

		add(Imagelabel);

		panel = new JPanel(new GridLayout(5, 1));
		panel.setBackground(Color.white);

		Team_name = new JLabel("Team name: " + andmed.get("team.name"));
		Team_leader = new JLabel("Team leader: "
				+ andmed.get("team.leader.name"));
		Leader_mail = new JLabel("Team leader email: "
				+ andmed.get("leader.email"));
		members = new JLabel("Team members: " + andmed.get("team.members"));
		software = new JLabel("Software version number: "
				+ andmed.get("versioon"));

		panel.add(Team_name);
		panel.add(Team_leader);
		panel.add(Leader_mail);
		panel.add(members);
		panel.add(software);

		getContentPane().setBackground(Color.white);
		add(panel);

		setVisible(true);

	};

	static Map<String, String> loeFail(String fail) {
		
		Map<String, String> d = new HashMap<String, String>();
		try {
			File file = new File(fail);
			//FileInputStream fileInput = new FileInputStream(file);
			InputStream fileInput = IntroUI.class.getClassLoader().getResourceAsStream(fail);
			Properties properties = new Properties();
			properties.load(fileInput);

			Enumeration<Object> enuKeys = properties.keys();
			while (enuKeys.hasMoreElements()) {
				String key = (String) enuKeys.nextElement();
				String value = properties.getProperty(key);
				d.put(key, value);
			}
			fileInput.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// System.out.println(d);
		return d;
	}

}
