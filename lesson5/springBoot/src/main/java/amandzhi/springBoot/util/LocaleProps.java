package amandzhi.springBoot.util;

import amandzhi.springBoot.config.ApplicationSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class LocaleProps {
    private final String localeProperty;
    private final Locale locale;
    private final String resourceLocation = "Questions.csv";

    @Autowired
    public LocaleProps(ApplicationSettings settings) {
        this.localeProperty = settings.getLanguage();
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