/**
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */

package net.sourceforge.pmd.cpd;

public class HiveLanguage extends AbstractLanguage {
    public HiveLanguage() {
        super("Hive", "hive", new HiveTokenizer(), ".hive", ".hql");
    }
}
