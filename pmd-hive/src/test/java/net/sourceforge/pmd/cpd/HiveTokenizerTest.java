package net.sourceforge.pmd.cpd;

import net.sourceforge.pmd.testframework.AbstractTokenizerTest;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class HiveTokenizerTest extends AbstractTokenizerTest {

    @Test
    public void tokenize() throws IOException {
        expectedTokenCount = 21;
        tokenizeTest();
    }

    @Before
    public void buildTokenizer() throws IOException {
        tokenizer = new HiveTokenizer();
        sourceCode = new SourceCode(new SourceCode.StringCodeLoader(getSampleCode()));
    }

    public String getSampleCode() throws IOException {
        return "INSERT OVERWRITE TABLE event_test.hbase_counts\n" +
                "SELECT\n" +
                "  username,\n" +
                "  count\n" +
                "FROM\n" +
                "  event_test.event_counts";
    }
}
