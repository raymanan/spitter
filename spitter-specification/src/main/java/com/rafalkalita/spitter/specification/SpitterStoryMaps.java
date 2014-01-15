package com.rafalkalita.spitter.specification;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStoryMaps;
import org.jbehave.core.model.ExamplesTableFactory;
import org.jbehave.core.parsers.RegexStoryParser;
import org.jbehave.core.reporters.StoryReporterBuilder;

import java.util.List;

import static java.util.Arrays.asList;
import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;

/**
 * User: rafalkalita
 * Date: 15/01/2014
 * Time: 22:19
 */
public class SpitterStoryMaps extends JUnitStoryMaps {

    public SpitterStoryMaps() {
        configuredEmbedder().useMetaFilters(metaFilters());
    }

    @Override
    public Configuration configuration() {
        return new MostUsefulConfiguration()
                .useStoryParser(new RegexStoryParser(new ExamplesTableFactory(new LoadFromClasspath(this.getClass()))))
                .useStoryReporterBuilder(new StoryReporterBuilder()
                        .withCodeLocation(codeLocationFromClass(this.getClass())));
    }

    @Override
    protected List<String> metaFilters() {
        return asList(); // will be specified by values in the pom.xml file when run from Maven
    }

    @Override
    protected List<String> storyPaths() {
        return new StoryFinder().findPaths(codeLocationFromClass(this.getClass()), "**/stories/*.story", "");

    }
}
