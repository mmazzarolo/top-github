package com.mmazzarolo.dev.topgithub.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by Matteo on 31/08/2015.
 */
@Parcel
public class User {
    @Expose
    String login;

    @Expose
    Integer id;

    @SerializedName("avatar_url")
    @Expose
    String avatarUrl;

    @SerializedName("gravatar_id")
    @Expose
    String gravatarId;
    @Expose
    String url;

    @SerializedName("html_url")
    @Expose
    String htmlUrl;

    @SerializedName("followers_url")
    @Expose
    String followersUrl;

    @SerializedName("following_url")
    @Expose
    String followingUrl;

    @SerializedName("gists_url")
    @Expose
    String gistsUrl;

    @SerializedName("starred_url")
    @Expose
    String starredUrl;

    @SerializedName("subscriptions_url")
    @Expose
    String subscriptionsUrl;

    @SerializedName("organizations_url")
    @Expose
    String organizationsUrl;

    @SerializedName("repos_url")
    @Expose
    String reposUrl;

    @SerializedName("events_url")
    @Expose
    String eventsUrl;

    @SerializedName("received_events_url")
    @Expose
    String receivedEventsUrl;

    @Expose
    String type;

    @SerializedName("site_admin")
    @Expose
    Boolean siteAdmin;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getGravatarId() {
        return gravatarId;
    }

    public void setGravatarId(String gravatarId) {
        this.gravatarId = gravatarId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public String getFollowersUrl() {
        return followersUrl;
    }

    public void setFollowersUrl(String followersUrl) {
        this.followersUrl = followersUrl;
    }

    public String getFollowingUrl() {
        return followingUrl;
    }

    public void setFollowingUrl(String followingUrl) {
        this.followingUrl = followingUrl;
    }

    public String getGistsUrl() {
        return gistsUrl;
    }

    public void setGistsUrl(String gistsUrl) {
        this.gistsUrl = gistsUrl;
    }

    public String getStarredUrl() {
        return starredUrl;
    }

    public void setStarredUrl(String starredUrl) {
        this.starredUrl = starredUrl;
    }

    public String getSubscriptionsUrl() {
        return subscriptionsUrl;
    }

    public void setSubscriptionsUrl(String subscriptionsUrl) {
        this.subscriptionsUrl = subscriptionsUrl;
    }

    public String getOrganizationsUrl() {
        return organizationsUrl;
    }

    public void setOrganizationsUrl(String organizationsUrl) {
        this.organizationsUrl = organizationsUrl;
    }

    public String getReposUrl() {
        return reposUrl;
    }

    public void setReposUrl(String reposUrl) {
        this.reposUrl = reposUrl;
    }

    public String getEventsUrl() {
        return eventsUrl;
    }

    public void setEventsUrl(String eventsUrl) {
        this.eventsUrl = eventsUrl;
    }

    public String getReceivedEventsUrl() {
        return receivedEventsUrl;
    }

    public void setReceivedEventsUrl(String receivedEventsUrl) {
        this.receivedEventsUrl = receivedEventsUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getSiteAdmin() {
        return siteAdmin;
    }

    public void setSiteAdmin(Boolean siteAdmin) {
        this.siteAdmin = siteAdmin;
    }
}
