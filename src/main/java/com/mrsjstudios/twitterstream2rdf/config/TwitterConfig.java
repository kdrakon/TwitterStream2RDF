/**
 * 
 */
package com.mrsjstudios.twitterstream2rdf.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

/**
 * 
 * Helper class that can generate a Twitter4J {@link Configuration} from a text
 * file.
 * 
 * @author Sean Policarpio
 * 
 */
public class TwitterConfig {

	/**
	 * Class logger.
	 */
	private static Logger logger = LoggerFactory.getLogger(TwitterConfig.class);

	/**
	 * Returns a {@link Configuration} based on the inputs from a file. If
	 * reading the file fails, a default/empty {@link Configuration} will be
	 * returned.
	 * 
	 * @param configFile
	 * @return
	 */
	public static Configuration readConfigFile(File configFile) {

		Map<String, String> configParameters = new HashMap<String, String>();
		BufferedReader reader;
		Configuration conf;

		try {
			reader = new BufferedReader(new FileReader(configFile));

			while (reader.ready()) {
				String line = reader.readLine();
				String tuple[] = line.split("=");
				configParameters.put(tuple[0], tuple[1]);
			}

			reader.close();

			conf = new ConfigurationBuilder().setUser(configParameters.get("user"))
					.setOAuthConsumerKey(configParameters.get("consumerKey"))
					.setOAuthConsumerSecret(configParameters.get("consumerSecret"))
					.setOAuthAccessToken(configParameters.get("accessToken"))
					.setOAuthAccessTokenSecret(configParameters.get("accessTokenSecret")).build();

		} catch (FileNotFoundException e) {
			logger.error("Could not open or find the specified configuration file. An empty Configuration is being returned.");
			e.printStackTrace();
			conf = new ConfigurationBuilder().build();

		} catch (IOException e) {
			logger.error("Could not read from the specified configuration file. An empty Configuration is being returned.");
			e.printStackTrace();
			conf = new ConfigurationBuilder().build();

		}

		return conf;
	}

}
