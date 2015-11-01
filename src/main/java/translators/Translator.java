package translators;

import utils.grade2.Tashkeel;

/**
 * Created by hosainfathelbab on 4/4/15.
 */
public abstract class Translator {
    public abstract TranslationResult translate(String input, Tashkeel tashkeel);
}
