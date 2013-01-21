/**
 * 
 */
package com.mrsjstudios.twitterstream2rdf.generator;

import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;

import com.mrsjstudios.twitterstream2rdf.rdf.repository.TweetRepository;
import com.mrsjstudios.twitterstream2rdf.stream.StreamReader;

/**
 * @author Sean Policarpio
 * 
 */
public class StatusFilterGenerator extends Generator {

	/**
	 * Class logger
	 */
	private Logger logger = LoggerFactory.getLogger(StatusFilterGenerator.class);

	public StatusFilterGenerator(TweetRepository repo, StreamReader reader) {
		setRepository(repo);
		setStreamInput(reader);
		setStreamListener();
	}

	@Override
	public void setRepository(TweetRepository repo) {
		this.repo = repo;
	}

	@Override
	public void setStreamInput(StreamReader reader) {
		this.reader = reader;
	}

	@Override
	protected void setStreamListener() {

		StatusListener listener = new StatusListener() {

			@Override
			public void onException(Exception ex) {
				logger.error("Exception caught with StatusListener for StatusFilterGenerator");
				ex.printStackTrace();
			}

			@Override
			public void onStatus(Status status) {
				send(status);
			}

			@Override
			public void onStallWarning(StallWarning warning) {
			}

			@Override
			public void onScrubGeo(long userId, long upToStatusId) {
			}

			@Override
			public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
			}

			@Override
			public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
			}
		};

		this.reader.setListener(listener);

	}

	@Override
	protected void send(Status tweet) {
		this.repo.addTweet(tweet);
	}

	@Override
	public void start() throws Exception {
		try {

			repo.initialiseRepository();
			reader.start();

		} catch (RepositoryException e) {
			e.printStackTrace();
			throw new Exception("Could not initialise RDF repository. Generator is not started.");			
		}

	}

	@Override
	public void stop() throws Exception {
		try {
			
			reader.stop();
			repo.deinitialiseRepository();
			
		} catch (RepositoryException e) {
			e.printStackTrace();
			throw new Exception("Could not deinitialise/shutdown RDF repository. Generator is stopping nonetheless.");
		}
	}

	@Override
	public RepositoryConnection getConnectionToRDFRepository() throws RepositoryException {
		return this.repo.getConnectionToRepository();
	}

}
