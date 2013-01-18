/**
 * 
 */
package com.mrsjstudios.twitterstream2rdf.stream;

import twitter4j.FilterQuery;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.Configuration;

/**
 * 
 * Implementation of a {@link StreamReader} which works solely on the Twitter
 * Stream API's filter functionality. This particular class will utilise the
 * filter() method of the {@link TwitterStream}.
 * 
 * @author Sean Policarpio
 * 
 */
public class StatusFilterStreamReader implements StreamReader {

	/**
	 * The Twitter4j Stream.
	 */
	private TwitterStream stream;

	/**
	 * The listener that is executed when results are returned from the stream.
	 */
	private StatusListener listener;

	/**
	 * The Twitter4j configuration for this stream reader.
	 */
	private Configuration conf;

	/**
	 * The filter query to apply to the stream.
	 */
	private FilterQuery filter;

	public StatusFilterStreamReader(Configuration conf, FilterQuery filter) {
		setTwitterConfiguration(conf);
		this.filter = filter;
	}

	@Override
	public void setTwitterConfiguration(Configuration conf) {
		this.conf = conf;
	}

	@Override
	public void setListener(StatusListener listener) {
		this.listener = listener;
	}

	/**
	 * Configures the {@link TwitterStream} just before it can start listening.
	 */
	private void configureTwitterStream() {
		stream = new TwitterStreamFactory(conf).getInstance();
		stream.addListener(listener);
	}

	@Override
	public void start() {
		configureTwitterStream();
		stream.filter(filter);
	}

	@Override
	public void stop() {
		stream.shutdown();
	}

}
