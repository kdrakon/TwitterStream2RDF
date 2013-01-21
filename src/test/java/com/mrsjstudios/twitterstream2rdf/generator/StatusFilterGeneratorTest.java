/**
 * 
 */
package com.mrsjstudios.twitterstream2rdf.generator;

import java.io.File;

import org.junit.Test;

import twitter4j.FilterQuery;
import twitter4j.conf.Configuration;

import com.mrsjstudios.twitterstream2rdf.config.TwitterConfig;
import com.mrsjstudios.twitterstream2rdf.rdf.mapping.RDFMapping;
import com.mrsjstudios.twitterstream2rdf.rdf.mapping.SimpleRDFMappingFactory;
import com.mrsjstudios.twitterstream2rdf.rdf.repository.InMemoryTweetRepository;
import com.mrsjstudios.twitterstream2rdf.rdf.repository.TweetRepository;
import com.mrsjstudios.twitterstream2rdf.stream.StatusFilterStreamReader;
import com.mrsjstudios.twitterstream2rdf.stream.StreamReader;

/**
 * @author Sean Policarpio
 * 
 */
public class StatusFilterGeneratorTest {

	private final static String FILTER_QUERY = "#quote";

	/**
	 * Use this main to execute tests outside of the JUnit environment.
	 * 
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		testStatusFilterWithInMemTweetRepo();
	}

	/**
	 * Tests {@link StatusFilterGenerator} with the
	 * {@link InMemoryTweetRepository}, {@link StatusFilterStreamReader}, and
	 * the {@link SimpleRDFMappingFactory}.
	 * 
	 * @throws Exception
	 */
	@Test
	public static void testStatusFilterWithInMemTweetRepo() throws Exception {

		RDFMapping mapping = new SimpleRDFMappingFactory().build();

		TweetRepository repo = new InMemoryTweetRepository(mapping);

		Configuration conf = TwitterConfig.readConfigFile(new File(
				"/home/kdrakon/git/TwitterStream2RDF/src/test/resources/twitterconfig"));

		String[] track = { FILTER_QUERY };
		FilterQuery filter = new FilterQuery().track(track);

		StreamReader reader = new StatusFilterStreamReader(conf, filter);

		Generator gen = new StatusFilterGenerator(repo, reader);

		gen.start();

	}
}
