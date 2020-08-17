package org.elasticsearch.index.analysis;

import org.apache.lucene.analysis.TokenStream;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.IndexSettings;

public class ConcatenateTokenFilterFactory extends AbstractTokenFilterFactory {

    private final String tokenSeparator;
    private final int incrementGap;

    public ConcatenateTokenFilterFactory(IndexSettings indexSettings, Environment environment, String name, Settings settings) {
        super(indexSettings, name, settings);
        // the token_separator is defined in the ES configuration file
        tokenSeparator = settings.get("token_separator");
        incrementGap = settings.getAsInt("increment_gap", 100);
    }

    @Override
    public TokenStream create(TokenStream tokenStream) {
        return new ConcatenateFilter(tokenStream, tokenSeparator, incrementGap);
    }

}
