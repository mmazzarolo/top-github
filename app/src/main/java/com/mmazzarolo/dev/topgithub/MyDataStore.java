package com.mmazzarolo.dev.topgithub;

import com.lacronicus.easydatastorelib.BooleanEntry;
import com.lacronicus.easydatastorelib.ObjectEntry;
import com.lacronicus.easydatastorelib.Preference;
import com.lacronicus.easydatastorelib.StringEntry;
import com.mmazzarolo.dev.topgithub.model.LanguageList;

/**
 * Created by Matteo on 03/09/2015.
 */
public interface MyDataStore {

    @Preference("PREF_SELECTED_LANGUAGE")
    StringEntry selectedLanguage();

    @Preference("PREF_SELECTED_PERIOD")
    StringEntry selectedPeriod();

    @Preference("PREF_IS_FIRST_RUN")
    BooleanEntry isFirstRun();

    @Preference("PREF_LANGUAGES")
    ObjectEntry<LanguageList> languages();

}
