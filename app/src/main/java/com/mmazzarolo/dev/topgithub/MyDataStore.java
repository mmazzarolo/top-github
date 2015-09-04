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

    // Selected programming language
    @Preference("PREF_SELECTED_LANGUAGE")
    StringEntry selectedLanguage();

    // Selected period, can be:
    // - action_today
    // - action_this_week
    // - action_this_month
    @Preference("PREF_SELECTED_PERIOD")
    StringEntry selectedPeriod();

    // Is this the first time the app runs?
    @Preference("PREF_IS_FIRST_RUN")
    BooleanEntry isFirstRun();

    // User saved programming languages
    // They are saved on SharedPreference converting LanguageList to GSON
    @Preference("PREF_LANGUAGES")
    ObjectEntry<LanguageList> languages();

}
