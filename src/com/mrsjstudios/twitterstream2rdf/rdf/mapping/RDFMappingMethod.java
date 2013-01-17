/**
 * 
 */
package com.mrsjstudios.twitterstream2rdf.rdf.mapping;

import java.util.List;

import org.openrdf.model.Statement;

import twitter4j.Status;

/**
 * 
 * This methods define how a {@link RDFMapping} should be implemented upon a
 * tweet.
 * 
 * @author Sean Policarpio
 * 
 */
public interface RDFMappingMethod {
	
	/**
	 * Transforms a single tweet into a List of Sesame {@link Statement}'s (triples)
	 * based on a {@link RDFMapping}.
	 * 
	 * @param tweet
	 * @return
	 */
	public List<Statement> map(Status tweet);

}
