/**
 * 
 */
package com.mrsjstudios.twitterstream2rdf.generator;

import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;

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
	 * This generators RDF repository for tweets.
	 */
	protected TweetRepository repo;

	/**
	 * This generators Twitter stream reader.
	 */
	protected StreamReader reader;

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

	/**
	 * Starts the generators stream and begins outputting it to the repository.
	 * This will throw an Exception if the Generator can not start.
	 * 
	 * @throws Exception
	 */
	public abstract void start() throws Exception;

	/**
	 * Stops the generator and attempts to shutdown both the stream and
	 * repository. This will throw an Exception if shutdown is unsuccessful.
	 * 
	 * @throws Exception
	 */
	public abstract void stop() throws Exception;

	/**
	 * Returns a Sesame RDF repository connection the RDF tweet repository. This
	 * can be used to query the RDF tweets.
	 * 
	 * @return
	 * @throws RepositoryException 
	 */
	public abstract RepositoryConnection getConnectionToRDFRepository() throws RepositoryException;

}
