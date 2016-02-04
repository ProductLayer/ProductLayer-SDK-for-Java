/**
 * Copyright (c) 2015, ProductLayer GmbH All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * - Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer. 
 * 
 * - Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.productlayer.core.beans;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Rene Swoboda on 29/12/13.
 * 
 * 
 */
@SuppressWarnings("serial")
public class User extends BaseObject {
    /**
     * The nickname of the user.
     */
    @JsonProperty("pl-usr-nickname")
    private String nickname;

    /**
     * The first name of the user.
     */
    @JsonProperty("pl-usr-fname")
    private String firstName;

    /**
     * The last name of the user.
     */
    @JsonProperty("pl-usr-lname")
    private String lastName;

    /**
     * The email of the user.
     */
    @JsonProperty("pl-usr-email")
    private String email;

    /**
     * The roles of the user. Roles: USER, DEVELOPER, ADMIN
     */
    @JsonProperty("pl-usr-roles")
    private List<String> roles;

    /**
     * The birthday of the user.
     */
    @JsonProperty("pl-usr-bday")
    private Date birthday;

    /**
     * The gender of the user.
     */
    @JsonProperty("pl-usr-gender")
    private String gender;

    /**
     * ProductListsDatabaseHandler no direct reference necessary. <b>DEPRECATED:
     * Use pl-usr-avatar instead!</b> <br>
     * The id of the avatar image of the user. If no image is defined
     * productlayer tries to get a gravatar image.
     */
    @JsonProperty("pl-usr-img_id")
    private String avatarId;

    /**
     * <b>DEPRECATED: Use pl-usr-avatar instead!</b> <br>
     * The url of the users avatar image. If no image is defined productlayer
     * returns a gravatar image url.
     */
    @JsonProperty("pl-usr-img")
    private String avatarUrl;

    /**
     * The overall score from the user. The gamification points of the user.
     */
    @JsonProperty("pl-usr-points")
    private Long points = 0L;

    /**
     * The unlocked achievements from the user. A list of unlocked achievements.
     */
    @JsonProperty("pl-usr-achv_unlocked")
    private List<String> unlockedAchievements;

    /**
     * This is only for users with the role "DEVELOPER". If i have the role
     * DEVELOPER i can create applications which can access the API.
     */
    @JsonProperty("pl-app")
    private List<Application> applications;

    /**
     * The counter for all user which are follower of this user.
     */
    @JsonProperty("pl-usr-follower_cnt")
    private Long followerCount = 0L;

    /**
     * The counter for all user this user is following.
     */
    @JsonProperty("pl-usr-following_cnt")
    private Long followingCount = 0L;

    /**
     * Is this user following the logged in user.
     */
    @JsonProperty("pl-usr-following")
    private Boolean following;

    /**
     * Is this user followed the logged in user.
     */
    @JsonProperty("pl-usr-followed")
    private Boolean followed;

    /**
     * A hashmap with all social connections. e.g.: facebook, twitter
     */
    @JsonProperty("pl-usr-social-connections")
    private HashMap<String, Boolean> socialConnections = new HashMap<String, Boolean>();

    /**
     * The avatar image metadata object.
     */
    @JsonProperty("pl-usr-avatar")
    private UserAvatarImage avatar;

    /**
     * The general settings for the user.
     */
    @JsonProperty("pl-usr-settings")
    private UserSettings settings = new UserSettings();

    public User() {
    }

    public User(String id) {
        this.id = id;
    }

    /**
     * @return The nickname of the user.
     */
    public String getNickname() {
        return this.nickname;
    }

    /**
     * @param nickname
     *            The nickname of the user.
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * @return The first name of the user.
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * @param firstName
     *            The first name of the user.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return The last name of the user.
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * @param lastName
     *            The last name of the user.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return The email of the user.
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * @param email
     *            The email of the user.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return The roles of the user. Roles: USER, DEVELOPER, ADMIN
     */
    public List<String> getRoles() {
        return this.roles;
    }

    /**
     * @param roles
     *            The roles of the user. Roles: USER, DEVELOPER, ADMIN
     */
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    /**
     * @return The birthday of the user.
     */
    public Date getBirthday() {
        return this.birthday;
    }

    /**
     * @param birthday
     *            The birthday of the user.
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * @return The gender of the user.
     */
    public String getGender() {
        return this.gender;
    }

    /**
     * @param gender
     *            The gender of the user.
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return <b>DEPRECATED: Use pl-usr-avatar instead!</b> <br>
     *         The id of the avatar image of the user. If no image is defined
     *         productlayer tries to get a gravatar image.
     */
    public String getAvatarId() {
        return this.avatarId;
    }

    /**
     * @param avatarId
     *            <b>DEPRECATED: Use pl-usr-avatar instead!</b> <br>
     *            The id of the avatar image of the user. If no image is defined
     *            productlayer tries to get a gravatar image.
     */
    public void setAvatarId(String avatarId) {
        this.avatarId = avatarId;
    }

    /**
     * @return <b>DEPRECATED: Use pl-usr-avatar instead!</b> <br>
     *         The url of the users avatar image. If no image is defined
     *         productlayer returns a gravatar image url.
     */
    public String getAvatarUrl() {
        return this.avatarUrl;
    }

    /**
     * @param avatarUrl
     *            <b>DEPRECATED: Use pl-usr-avatar instead!</b> <br>
     *            The url of the users avatar image. If no image is defined
     *            productlayer returns a gravatar image url.
     */
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    /**
     * @return The gamification points of the user.
     */
    public Long getPoints() {
        return this.points;
    }

    /**
     * @param points
     *            The gamification points of the user.
     */
    public void setPoints(Long points) {
        this.points = points;
    }

    /**
     * @return A list of unlocked achievements.
     */
    public List<String> getUnlockedAchievements() {
        return this.unlockedAchievements;
    }

    /**
     * @param unlockedAchievements
     *            A list of unlocked achievements.
     */
    public void setUnlockedAchievements(List<String> unlockedAchievements) {
        this.unlockedAchievements = unlockedAchievements;
    }

    /**
     * @return If i have the role DEVELOPER i can create applications which can
     *         access the API.
     */
    public List<Application> getApplications() {
        return this.applications;
    }

    /**
     * @param applications
     *            If i have the role DEVELOPER i can create applications which
     *            can access the API.
     */
    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }

    /**
     * @return The counter for all user which are follower of this user.
     */
    public Long getFollowerCount() {
        return this.followerCount;
    }

    /**
     * @param followerCount
     *            The counter for all user which are follower of this user.
     */
    public void setFollowerCount(Long followerCount) {
        this.followerCount = followerCount;
    }

    /**
     * @return The counter for all user this user is following.
     */
    public Long getFollowingCount() {
        return this.followingCount;
    }

    /**
     * @param followingCount
     *            The counter for all user this user is following.
     */
    public void setFollowingCount(Long followingCount) {
        this.followingCount = followingCount;
    }

    /**
     * @return Is this user following the logged in user.
     */
    public Boolean getFollowing() {
        return this.following;
    }

    /**
     * @param following
     *            Is this user following the logged in user.
     */
    public void setFollowing(Boolean following) {
        this.following = following;
    }

    /**
     * @return Is this user followed the logged in user.
     */
    public Boolean getFollowed() {
        return this.followed;
    }

    /**
     * @param followed
     *            Is this user followed the logged in user.
     */
    public void setFollowed(Boolean followed) {
        this.followed = followed;
    }

    /**
     * @return A hashmap with all social connections. e.g.: facebook, twitter
     */
    public HashMap<String, Boolean> getSocialConnections() {
        return this.socialConnections;
    }

    /**
     * @param socialConnections
     *            A hashmap with all social connections. e.g.: facebook, twitter
     */
    public void setSocialConnections(HashMap<String, Boolean> socialConnections) {
        this.socialConnections = socialConnections;
    }

    /**
     * @return The avatar image metadata object.
     */
    public UserAvatarImage getAvatar() {
        return this.avatar;
    }

    /**
     * @param avatar
     *            The avatar image metadata object.
     */
    public void setAvatar(UserAvatarImage avatar) {
        this.avatar = avatar;
    }

    /**
     * @return The general settings for the user.
     */
    public UserSettings getSettings() {
        return this.settings;
    }

    /**
     * @param settings
     *            The general settings for the user.
     */
    public void setSettings(UserSettings settings) {
        this.settings = settings;
    }

}