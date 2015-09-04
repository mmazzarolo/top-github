package com.mmazzarolo.dev.topgithub.event;



import com.mmazzarolo.dev.topgithub.model.Repository;
import com.mmazzarolo.dev.topgithub.model.SearchResult;

import java.util.List;

/**
 * Created by Matteo on 31/08/2015.
 *
 * Fired on succesful search.
 */
public class SearchSuccesEvent {

    SearchResult searchResult;

    public SearchSuccesEvent(SearchResult searchResult) {
        this.searchResult = searchResult;
    }

    public SearchResult getSearchResult() {
        return searchResult;
    }

    public void setSearchResult(SearchResult searchResult) {
        this.searchResult = searchResult;
    }

    public List<Repository> getRepositories() {
        return searchResult.getRepositories();
    }
}
