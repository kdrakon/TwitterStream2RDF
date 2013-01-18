/**
 * 
 */
package com.mrsjstudios.twitterstream2rdf.generator;

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
public interface Generator {

	/**
	 * Set the RDF repository that this generator should store its tweets in.
	 * 
	 * @param repo
	 */
	void setRepository(TweetRepository repo);

	/**
	 * Specify the source of the tweets.
	 * 
	 * @param reader
	 */
	void setStreamInput(StreamReader reader);

	/**
	 * Sends the data from the {@link StreamReader} to the
	 * {@link TweetRepository}.
	 */
	void send();

}
