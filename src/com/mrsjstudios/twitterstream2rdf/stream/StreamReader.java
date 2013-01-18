/**
 * 
 */
package com.mrsjstudios.twitterstream2rdf.stream;

import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.conf.Configuration;

import com.mrsjstudios.twitterstream2rdf.generator.Generator;
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
	 * Sets the StreamListener that is executed when the stream is active. It is
	 * expected that it should return all filtered statuses up to some
	 * implementation of a {@link Generator}.
	 * 
	 * TODO The {@link StatusListener} parameter is too restrictive, but for
	 * some reason, I don't have access to the parent interface, StreamListener.
	 */
	void setListener(StatusListener listener);

	/**
	 * Starts the stream reader.
	 */
	void start();

	/**
	 * Stops and shuts down the stream reader.
	 */
	void stop();

}
