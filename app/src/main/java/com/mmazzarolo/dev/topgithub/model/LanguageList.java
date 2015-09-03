package com.mmazzarolo.dev.topgithub.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Matteo on 03/09/2015.
 */
public class LanguageList {
    List<Language> languages = new ArrayList<Language>();

    public LanguageList(List<Language> languages) {
        this.languages = languages;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }

    public List<String> getUserLanguages() {
        List<String> userLanguages = new ArrayList<String>();
        for (Language language : languages) {
            if (language.isSelected()) {
                userLanguages.add(language.getName());
            }
        }
        Collections.sort(userLanguages);
        return userLanguages;
    }
}
