package com.mmazzarolo.dev.topgithub.model;

/**
 * Created by Matteo on 03/09/2015.
 */
public class Language {

    String name;
    boolean isSelected;

    public Language(String name, boolean isSelected) {
        this.name = name;
        this.isSelected = isSelected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public void toggleSelection() {
        isSelected = !isSelected;
    }
}
