/**
 * 
 */
package com.mrsjstudios.twitterstream2rdf.rdf.mapping;

import java.util.List;

import org.openrdf.model.Statement;
import org.openrdf.model.URI;

import twitter4j.Status;

/**
 * Factory that generates an RDF mapping for use in a Tweet repository. The
 * returned {@link RDFMappingImpl} contains the map() method that defines the
 * actual transformation of a Tweet into RDF triples.
 * 
 * @author Sean Policarpio
 * 
 */
public class RDFMappingFactory {

	private URI namespaceURI;
	private URI createdAtURI;
	private URI tweetIdURI;
	private URI textURI;
	private URI userIdURI;

	public RDFMappingFactory namespaceURI(URI namespaceURI) {
		this.namespaceURI = namespaceURI;
		return this;
	}

	public RDFMappingFactory createdAtURI(URI createdAtURI) {
		this.createdAtURI = createdAtURI;
		return this;
	}

	public RDFMappingFactory tweetIdURI(URI tweetIdURI) {
		this.tweetIdURI = tweetIdURI;
		return this;
	}

	public RDFMappingFactory textURI(URI testURI) {
		this.textURI = testURI;
		return this;
	}

	public RDFMappingFactory userIdURI(URI userIdURI) {
		this.userIdURI = userIdURI;
		return this;
	}

	/**
	 * Builds the {@link RDFMapping} with its associated
	 * {@link RDFMappingMethod}.
	 * 
	 * @return
	 */
	public RDFMapping build() {
		return new RDFMappingImpl();
	}

	public class RDFMappingImpl implements RDFMapping, RDFMappingMethod {

		@Override
		public List<Statement> map(Status tweet) {
			// TODO DEFINE MAPPING
			return null;
		}

		@Override
		public URI getUserIdURI() {
			return userIdURI;
		}

		@Override
		public URI getTweetIdURI() {
			return tweetIdURI;
		}

		@Override
		public URI getTextURI() {
			return textURI;
		}

		@Override
		public URI getNamespaceURI() {
			return namespaceURI;
		}

		@Override
		public URI getCreatedAtURI() {
			return createdAtURI;
		}

	}

}
