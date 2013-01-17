/**
 * 
 */
package com.mrsjstudios.twitterstream2rdf.rdf.mapping;

import org.openrdf.model.URI;

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

	public URI getCreatedAtURI();

	public URI getTweetIdURI();

	public URI getTextURI();

	public URI getUserIdURI();

}
