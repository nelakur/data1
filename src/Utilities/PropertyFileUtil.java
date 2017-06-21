package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileUtil 
{
	public static String getValueForKey(String key) throws Exception, Exception
	{
		Properties configProperties = new Properties();
		configProperties.load(new FileInputStream(new File("./PropertiesFile/Environment.properties")));
		
		return configProperties.getProperty(key);
	}
	
	
}
