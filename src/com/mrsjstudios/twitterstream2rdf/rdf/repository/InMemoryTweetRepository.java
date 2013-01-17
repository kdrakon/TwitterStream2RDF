/**
 * 
 */
package com.mrsjstudios.twitterstream2rdf.rdf.repository;

import java.util.List;

import org.openrdf.model.Statement;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.sail.SailRepository;
import org.openrdf.sail.memory.MemoryStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import twitter4j.Status;

import com.mrsjstudios.twitterstream2rdf.rdf.mapping.RDFMappingFactory.RDFMappingImpl;

/**
 * 
 * Creates an in-memory RDF repository
 * 
 * @author Sean Policarpio
 * 
 */
public class InMemoryTweetRepository implements TweetRepository {

	/**
	 * Local class logger instance.
	 */
	Logger logger = LoggerFactory.getLogger(InMemoryTweetRepository.class);

	/**
	 * The local in-memory RDF repository.
	 */
	private Repository tweetRepository;

	/**
	 * The mapping definition for transforming the Tweet into RDF.
	 */
	private RDFMappingImpl mapping;

	@Override
	public void initialiseRepository() throws RepositoryException {
		tweetRepository = new SailRepository(new MemoryStore());
		tweetRepository.initialize();
	}

	@Override
	public void setRDFMapping(RDFMappingImpl mapping) {
		this.mapping = mapping;
	}

	@Override
	public void addTweet(Status tweet) {
		try {
			appendTweetToRepository(mapping.map(tweet));
		} catch (RepositoryException e) {
			logger.error("Could not add tweet to in-memory repository. The tweet will be skipped.");
			e.printStackTrace();
		}
	}

	/**
	 * Appends the RDF tweet to the local repository. This method is
	 * synchronized due to access to the shared repository.
	 * 
	 * @throws RepositoryException
	 */
	private synchronized void appendTweetToRepository(List<Statement> triple) throws RepositoryException {
		RepositoryConnection con = tweetRepository.getConnection();
		con.add(triple);
		con.close();
	}

}
