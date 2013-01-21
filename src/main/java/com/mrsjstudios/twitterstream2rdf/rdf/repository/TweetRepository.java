/**
 * 
 */
package com.mrsjstudios.twitterstream2rdf.rdf.repository;

import java.io.File;

import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.rio.RDFFormat;

import com.mrsjstudios.twitterstream2rdf.rdf.mapping.RDFMapping;

import twitter4j.Status;

/**
 * Base interface for implementing RDF graph and repository of streaming tweets.
 * 
 * @author Sean Policarpio
 * 
 */
public interface TweetRepository {

	/**
	 * Initalises the Sesame based RDF repository so that new triples may be
	 * appended to it.
	 * 
	 * @throws RepositoryException
	 */
	void initialiseRepository() throws RepositoryException;

	/**
	 * Essentially shutsdown the {@link TweetRepository}.
	 * 
	 * @throws RepositoryException
	 */
	void deinitialiseRepository() throws RepositoryException;

	/**
	 * Sets the mapping that defines the transformation of a Tweet (
	 * {@link Status}) into a triple.
	 * 
	 * @param mapping
	 */
	void setRDFMapping(RDFMapping mapping);

	/**
	 * Adds a single {@link Status} Tweet to the repository.
	 * 
	 * @param tweet
	 */
	void addTweet(Status tweet);

	/**
	 * Dumps the complete repository - i.e. all the graphs within it - to a
	 * file.
	 * 
	 * @param dumpFile
	 * @param format
	 */
	void dumpRepositoryToFile(File dumpFile, RDFFormat format);

	/**
	 * Returns a Sesame {@link RepositoryConnection} to the RDF database.
	 * 
	 * @return
	 * @throws RepositoryException 
	 */
	RepositoryConnection getConnectionToRepository() throws RepositoryException;

}
