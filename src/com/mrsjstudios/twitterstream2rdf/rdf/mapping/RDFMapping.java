/**
 * 
 */
package com.mrsjstudios.twitterstream2rdf.rdf.mapping;

import java.util.List;

import org.openrdf.model.Statement;
import org.openrdf.model.URI;

import twitter4j.Status;

/**
 * 
 * Represents the URI mappings for the particular fields being turned into RDF
 * triples.
 * 
 * @author Sean Policarpio
 * 
 */
public interface RDFMapping {

	/**
	 * The namespace URI for identifying the tweet ID.
	 * 
	 * @return
	 */
	public URI getNamespaceURI();

	/**
	 * The URI of the RDF class for the tweet.
	 * 
	 * @return
	 */
	public URI getTweetClassURI();

	/**
	 * The URI predicate for the creation date of the tweet.
	 * 
	 * @return
	 */
	public URI getCreatedAtURI();

	/**
	 * The URI predicate for the id of the tweet (long val).
	 * 
	 * @return
	 */
	public URI getTweetIdURI();

	/**
	 * The URI predicate for the actual tweets text.
	 * 
	 * @return
	 */
	public URI getTextURI();

	/**
	 * The URI predicate for the user (tweeter).
	 * 
	 * @return
	 */
	public URI getUserIdURI();

	/**
	 * Transforms a single tweet into a List of Sesame {@link Statement}'s
	 * (triples) based on a {@link RDFMapping}.
	 * 
	 * @param tweet
	 * @return
	 */
	public List<Statement> map(Status tweet);

}
