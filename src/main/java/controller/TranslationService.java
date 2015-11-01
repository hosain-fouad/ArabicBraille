package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import translators.TranslationResult;
import translators.Translator;
import utils.grade2.LineBreaker;
import utils.grade2.Tashkeel;

import java.io.UnsupportedEncodingException;

import static java.net.URLDecoder.decode;

@RestController
public class TranslationService {

    @Autowired
    private Translator grade1Translator;

    @Autowired
    private Translator grade2Translator;

    @RequestMapping(value = "/translate", produces = "text/html; charset=UTF-8")
    public
    @ResponseBody
    String translate(@RequestParam(value = "input") String input, @RequestParam(value = "grade") String grade) throws UnsupportedEncodingException {
        TranslationResult translation = null;
        switch (Integer.parseInt(grade)){
            case 1:
                translation = grade1Translator.translate(input, Tashkeel.ksa);
                break;
            case 2:
                translation = grade2Translator.translate(input, Tashkeel.ksa);
                break;
        }

        return translation.toString();
    }

    @RequestMapping(value = "/translateEncoded", produces = "text/html; charset=UTF-8")
    public
    @ResponseBody
    String translateEnocoded(@RequestParam(value = "input") String input, @RequestParam(value = "grade") String grade) throws UnsupportedEncodingException {
        input = decode(input, "ASCII");
        return translate(input, grade);
    }

}
