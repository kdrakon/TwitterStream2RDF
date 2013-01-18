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

	public URI getNamespaceURI();

	public URI getTweetClassURI();

	public URI getCreatedAtURI();

	public URI getTweetIdURI();

	public URI getTextURI();

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
