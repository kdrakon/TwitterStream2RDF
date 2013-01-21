/**
 * 
 */
package com.mrsjstudios.twitterstream2rdf.rdf.mapping;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.openrdf.model.Statement;
import org.openrdf.model.URI;
import org.openrdf.model.impl.LiteralImpl;
import org.openrdf.model.impl.StatementImpl;
import org.openrdf.model.impl.URIImpl;
import org.openrdf.model.vocabulary.RDFS;
import org.openrdf.model.vocabulary.XMLSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import twitter4j.Status;

/**
 * Factory that generates a simple RDF mapping for use in a Tweet repository.
 * The returned {@link RDFMapping} contains the map() method that defines the
 * actual transformation of a Tweet into RDF triples.
 * 
 * @author Sean Policarpio
 * 
 */
public class SimpleRDFMappingFactory {

	private Logger logger = LoggerFactory.getLogger(SimpleRDFMappingFactory.class);

	private URI namespaceURI = new URIImpl("http://blank");
	private URI createdAtURI = new URIImpl("http://blank/createdAtURI");
	private URI tweetIdURI = new URIImpl("http://blank/tweetIdURI");
	private URI textURI = new URIImpl("http://blank/textURI");
	private URI userIdURI = new URIImpl("http://blank/userIdURI");
	private URI tweetClassURI = new URIImpl("http://blank/tweetClassURI");

	public SimpleRDFMappingFactory namespaceURI(URI namespaceURI) {
		this.namespaceURI = namespaceURI;
		return this;
	}

	public SimpleRDFMappingFactory createdAtURI(URI createdAtURI) {
		this.createdAtURI = createdAtURI;
		return this;
	}

	public SimpleRDFMappingFactory tweetIdURI(URI tweetIdURI) {
		this.tweetIdURI = tweetIdURI;
		return this;
	}

	public SimpleRDFMappingFactory textURI(URI testURI) {
		this.textURI = testURI;
		return this;
	}

	public SimpleRDFMappingFactory userIdURI(URI userIdURI) {
		this.userIdURI = userIdURI;
		return this;
	}

	public SimpleRDFMappingFactory tweetObjectURI(URI tweetClassURI) {
		this.tweetClassURI = tweetClassURI;
		return this;
	}

	/**
	 * Builds the {@link RDFMapping} with the associated values from the
	 * factory.
	 * 
	 * @return
	 */
	public RDFMapping build() {
		return new RDFMappingImpl();
	}

	public class RDFMappingImpl implements RDFMapping {

		/**
		 * Creates a set of RDF triples to describe this single tweet.
		 */
		@Override
		public List<Statement> map(Status tweet) {

			List<Statement> statements = new ArrayList<Statement>();

			URI tweetURI = new URIImpl(getNamespaceURI().toString() + "/" + getTweetIdURI().toString());

			Statement classTriple = new StatementImpl(tweetURI, RDFS.CLASS, getTweetClassURI());
			statements.add(classTriple);

			Statement tweeterTriple = new StatementImpl(tweetURI, getUserIdURI(), new LiteralImpl(tweet.getUser().getName()));
			statements.add(tweeterTriple);

			Statement tweetTextTriple = new StatementImpl(tweetURI, getTextURI(), new LiteralImpl(tweet.getText()));
			statements.add(tweetTextTriple);

			GregorianCalendar c = new GregorianCalendar();
			c.setTime(tweet.getCreatedAt());
			try {
				XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
				Statement creationTweet = new StatementImpl(tweetURI, getCreatedAtURI(), new LiteralImpl(
						xmlDate.toXMLFormat(), XMLSchema.DATE));
				statements.add(creationTweet);

			} catch (DatatypeConfigurationException e) {
				logger.error("Could not create XML Schema date for tweet.");
				e.printStackTrace();
			}

			return statements;
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

		@Override
		public URI getTweetClassURI() {
			return tweetClassURI;
		}

	}

}
