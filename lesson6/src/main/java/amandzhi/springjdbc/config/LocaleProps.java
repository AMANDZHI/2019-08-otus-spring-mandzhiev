package amandzhi.springjdbc.config;

import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class LocaleProps {
    private final String localeProperty;
    private final Locale locale;

    public LocaleProps(ApplicationSettings applicationSettings) {
        this.localeProperty = applicationSettings.getLanguage();
        this.locale = new Locale(localeProperty);
    }

    public String getLocaleProperty() {
        return localeProperty;
    }

    public Locale getLocale() {
        return locale;
    }
}
