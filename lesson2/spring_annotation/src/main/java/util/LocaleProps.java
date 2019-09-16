package util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class LocaleProps {
    private final String localeProperty;
    private final Locale locale;
    private String resourceLocation = "Questions.csv";

    public LocaleProps(@Value("${language}") String localeProperty) {
        this.localeProperty = localeProperty;
        this.locale = new Locale(localeProperty);
    }

    public Locale getLocale() {
        return locale;
    }

    public String getLocaleProperty() {
        return localeProperty;
    }

    public String getNameFile() {
       return this.getLocaleProperty() + resourceLocation;
    }
}