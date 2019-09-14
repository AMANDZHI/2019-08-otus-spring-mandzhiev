package service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class LocaleService {
    private final String localeProperty;
    private final Locale locale;

    public LocaleService(@Value("${language}") String localeProperty) {
        this.localeProperty = localeProperty;
        this.locale = new Locale(localeProperty);
    }

    public Locale getLocale() {
        return locale;
    }

    public String getLocaleProperty() {
        return localeProperty;
    }
}