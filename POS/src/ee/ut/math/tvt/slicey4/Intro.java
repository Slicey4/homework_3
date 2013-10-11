package ee.ut.math.tvt.slicey4;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.swing.JFrame;

public class Intro {

	static Map<String, String> loeFail(String fail) {
		Map<String, String> d = new HashMap<String, String>();
		try {
			File file = new File(fail);
			FileInputStream fileInput = new FileInputStream(file);
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

	public static void main(String[] args) {
		Map<String, String> andmed = loeFail("application.properties");
		Map<String, String> versioon = loeFail("version.properties");
		andmed.put("versioon", versioon.get("build.number"));
		System.out.println(andmed);
		IntroUI uus = new IntroUI();
		uus.Create(andmed);

	}
}
