package com.toefldictionary.tools;

import com.google.api.translate.Language;

import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Gor on 21-May-16.
 */
public final class Translate extends YandexTranslatorAPI {

    private static final String SERVICE_URL = "https://translate.yandex.net/api/v1.5/tr.json/translate?";
    private static final String TRANSLATION_LABEL = "text";

    //prevent instantiation
    private Translate() {}

    public static String execute(final String text, final Language from, final Language to) throws Exception {
        validateServiceState(text);
        final String params =
                PARAM_API_KEY + URLEncoder.encode(apiKey, ENCODING)
                        + PARAM_LANG_PAIR + URLEncoder.encode(from.toString(), ENCODING) + URLEncoder.encode("-", ENCODING) + URLEncoder.encode(to.toString(), ENCODING)
                        + PARAM_TEXT + URLEncoder.encode(text, ENCODING);
        final URL url = new URL(SERVICE_URL + params);
        return retrievePropArrString(url, TRANSLATION_LABEL).trim();
    }

    private static void validateServiceState(final String text) throws Exception {
        final int byteLength = text.getBytes(ENCODING).length;
        if (byteLength > 10240) { // TODO What is the maximum text length allowable for Yandex?
            throw new RuntimeException("TEXT_TOO_LARGE");
        }
        validateServiceState();
    }
}