/**
 * 
 */
package com.mrsjstudios.twitterstream2rdf.generator;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import twitter4j.FilterQuery;
import twitter4j.conf.Configuration;

import com.mrsjstudios.twitterstream2rdf.config.TwitterConfig;
import com.mrsjstudios.twitterstream2rdf.rdf.mapping.RDFMapping;
import com.mrsjstudios.twitterstream2rdf.rdf.mapping.SimpleRDFMappingFactory;
import com.mrsjstudios.twitterstream2rdf.rdf.repository.InMemoryTweetRepository;
import com.mrsjstudios.twitterstream2rdf.stream.StatusFilterStreamReader;

/**
 * @author Sean Policarpio
 * 
 */
public class StatusFilterGeneratorTest {

	private final String FILTER_QUERY = "#test";

	/**
	 * Tests {@link StatusFilterGenerator} with the
	 * {@link InMemoryTweetRepository}, {@link StatusFilterStreamReader}, and
	 * the {@link SimpleRDFMappingFactory}.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testStatusFilterWithInMemTweetRepo() throws Exception {

		RDFMapping mapping = new SimpleRDFMappingFactory().build();

		InMemoryTweetRepository repo = new InMemoryTweetRepository(mapping);

		Configuration conf = TwitterConfig.readConfigFile(new File("resources/twitterconfig"));

		String[] track = { FILTER_QUERY };
		FilterQuery filter = new FilterQuery().track(track);

		StatusFilterStreamReader reader = new StatusFilterStreamReader(conf, filter);

		StatusFilterGenerator gen = new StatusFilterGenerator(repo, reader);

		gen.start();

	}
}
