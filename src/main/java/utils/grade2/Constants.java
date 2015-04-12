package utils.grade2;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by hosainfathelbab on 4/11/15.
 */
public class Constants {
    public static Set<Character> punctuation = new HashSet<Character>();
    public static Set<Character> tashkeel = new HashSet<Character>();
    public static Set<Character> symbols123 = new HashSet<Character>();

    static {
        tashkeel.add('َ');
        tashkeel.add('ِ');
        tashkeel.add('ُ');
        tashkeel.add('ً');
        tashkeel.add('ٍ');
        tashkeel.add('ٌ');
        tashkeel.add('ّ');
        tashkeel.add('ْ');

        punctuation.add(',');
        punctuation.add('[');
        punctuation.add(']');
        punctuation.add('{');
        punctuation.add('}');
        punctuation.add('(');
        punctuation.add(')');
        punctuation.add('<');
        punctuation.add('>');
        punctuation.add(':');
        punctuation.add('-');
        punctuation.add('_');
        punctuation.add('.');
        punctuation.add('!');
        punctuation.add('?');
        punctuation.add('\'');
        punctuation.add('\"');
        punctuation.add(';');
        punctuation.add('.');
        punctuation.add('،');
        punctuation.add(' ');
        punctuation.add('!');
        punctuation.add('\n');
    }
}
