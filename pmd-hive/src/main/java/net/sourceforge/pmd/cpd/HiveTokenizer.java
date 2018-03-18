/**
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */

package net.sourceforge.pmd.cpd;

import net.sourceforge.pmd.lang.ast.TokenMgrError;
import org.apache.hadoop.hive.ql.lib.Node;
import org.apache.hadoop.hive.ql.parse.ASTNode;
import org.apache.hadoop.hive.ql.parse.ParseDriver;
import org.apache.hadoop.hive.ql.parse.ParseException;

import java.io.IOException;

public class HiveTokenizer implements Tokenizer {

    public void tokenize(SourceCode sourceCode, Tokens tokenEntries) throws IOException {

        ParseDriver driver = new ParseDriver();

        try {
            ASTNode astNode = driver.parse(sourceCode.getCodeBuffer().toString());
            nodeToTokens(astNode, tokenEntries);
        } catch (ParseException e) {
            throw new TokenMgrError("Lexical error while parsing hive: " + e.toString(),
                    TokenMgrError.LEXICAL_ERROR);
        } finally {
            tokenEntries.add(TokenEntry.getEOF());
        }
    }

    private void nodeToTokens(ASTNode node, Tokens tokens) {
        if(node.token != null) {
            tokens.add(nodeToTokenEntry(node));
        }
        if(node.getChildren() != null) {
            for(Node c : node.getChildren()) {
                if(c instanceof ASTNode) {
//                    tokens.add(nodeToTokenEntry((ASTNode) c));
                    nodeToTokens((ASTNode) c, tokens);
                }
            }
        };

    }

    private TokenEntry nodeToTokenEntry(ASTNode n) {
        return new TokenEntry(n.token.getText(), String.format("%s",n.token.getType()), n.token.getLine());
    }
}
