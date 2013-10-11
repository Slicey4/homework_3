package ee.ut.math.tvt.slicey4;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class IntroUI extends Intro{
	public static String Team_name="Slicey4";
	public static String Team_leader= "Kätlin Viilukas";
	public static String Team_leader_email= "katlinviilukas@gmail.com";
	public static String[] Team_members= {"Kätlin Viilukas", "Jane Jürgenson", "Kristiina Pokk", "Linda Liis Eek"};
	public static Icon Team_logo;
	public static int software_version_number;

	
	void Loo(){
	JFrame frame = new JFrame("no image");
	ImageIcon image = new ImageIcon("logo.png");
	JLabel imagelabel = new JLabel(image);
	frame.add(imagelabel);
	
	}
	
}
