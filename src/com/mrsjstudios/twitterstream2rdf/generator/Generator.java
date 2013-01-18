/**
 * 
 */
package com.mrsjstudios.twitterstream2rdf.generator;

import twitter4j.Status;

import com.mrsjstudios.twitterstream2rdf.rdf.mapping.RDFMapping;
import com.mrsjstudios.twitterstream2rdf.rdf.repository.TweetRepository;
import com.mrsjstudios.twitterstream2rdf.stream.StreamReader;

/**
 * 
 * The core. Brings together the {@link StreamReader}'s output with the
 * implementation of a {@link TweetRepository} and its {@link RDFMapping}.
 * 
 * @author Sean Policarpio
 * 
 */
public abstract class Generator {

	/**
	 * Set the RDF repository that this generator should store its tweets in.
	 * 
	 * @param repo
	 */
	public abstract void setRepository(TweetRepository repo);

	/**
	 * Specify the source of the tweets.
	 * 
	 * @param reader
	 */
	public abstract void setStreamInput(StreamReader reader);

	/**
	 * This method creates the stream listener for the {@link StreamReader}
	 * which reveals tweets to the send() method.
	 */
	protected abstract void setStreamListener();

	/**
	 * Sends the data from the {@link StreamReader} to the
	 * {@link TweetRepository}.
	 */
	protected abstract void send(Status tweet);

}
