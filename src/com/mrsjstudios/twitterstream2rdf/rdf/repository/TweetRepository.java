/**
 * 
 */
package com.mrsjstudios.twitterstream2rdf.rdf.repository;

import org.openrdf.repository.RepositoryException;

import com.mrsjstudios.twitterstream2rdf.rdf.mapping.RDFMappingFactory.RDFMappingImpl;

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
	 * Sets the mapping that defines the transformation of a Tweet (
	 * {@link Status}) into a triple.
	 * 
	 * @param mapping
	 */
	void setRDFMapping(RDFMappingImpl mapping);

	/**
	 * Adds a single {@link Status} Tweet to the repository.
	 * 
	 * @param tweet
	 */
	void addTweet(Status tweet);

}
