/**
 * 
 */
package com.mrsjstudios.twitterstream2rdf.stream;

import twitter4j.TwitterStream;
import twitter4j.conf.Configuration;

import com.mrsjstudios.twitterstream2rdf.rdf.repository.TweetRepository;

/**
 * 
 * This interface contains methods for reading the Twitter Stream API and
 * outputting them for the {@link TweetRepository}. It is mainly a wrapper to
 * the {@link TwitterStream} interface.
 * 
 * @author Sean Policarpio
 * 
 */
public interface StreamReader {

	/**
	 * Set the Twitter configuration (e.g OAuth keys).
	 * 
	 * @param conf
	 */
	void setTwitterConfiguration(Configuration conf);

	/**
	 * Starts the stream reader.
	 */
	void start();

	/**
	 * Stops and shuts down the stream reader.
	 */
	void stop();

}
