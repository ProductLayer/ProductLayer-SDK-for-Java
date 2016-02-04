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
package com.productlayer.core.beans.activities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.productlayer.core.beans.BaseObject;
import com.productlayer.core.beans.User;

/**
 * @author sorien
 *
 */
@SuppressWarnings("serial")
public class RichActivity extends BaseObject {
    /**
     * The type of the activtiy: Created, Updated, UnlockedAchievement, UpVoted,
     * DownVoted
     */
    @JsonProperty("pl-act-type")
    private ActivityType type;

    /**
     * List of users, who performed the type of activity.
     */
    @JsonProperty("pl-act-usr_list")
    private List<User> usersForActivity;

    /**
     * The parent base object of the activity. <br>
     * Types: WishList, User, Product, Opine, Review, Image. <br>
     * e.g.:<br>
     * The user likes a product, so the product he likes would be the
     * pl-act-parent_obj.
     */
    @JsonProperty("pl-act-parent_obj")
    private BaseObject parent;

    /**
     * The base object for the activity. <br>
     * Types: Opine, Review, Image <br>
     * <br>
     * e.g.: <br>
     * The user replies to an opine with an own opine, so the own opine from the
     * user would be the pl-act-obj.
     */
    @JsonProperty("pl-act-obj")
    private BaseObject object;

    /**
     * The status of the activity. Read, Unread
     */
    @JsonProperty("pl-act-status")
    private ActivityStatus status;

    public RichActivity() {
        super();
    }

    /**
     * @return The type of the activtiy: Created, Updated, UnlockedAchievement,
     *         UpVoted, DownVoted
     */
    public ActivityType getType() {
        return this.type;
    }

    /**
     * @param type
     *            The type of the activtiy: Created, Updated,
     *            UnlockedAchievement, UpVoted, DownVoted
     */
    public void setType(ActivityType type) {
        this.type = type;
    }

    /**
     * @return List of users, who performed the type of activity.
     */
    public List<User> getUsersForActivity() {
        return this.usersForActivity;
    }

    /**
     * @param usersForActivity
     *            List of users, who performed the type of activity.
     */
    public void setUsersForActivity(List<User> usersForActivity) {
        this.usersForActivity = usersForActivity;
    }

    /**
     * @return The parent base object of the activity. <br>
     *         Types: WishList, User, Product, Opine, Review, Image. <br>
     *         e.g.:<br>
     *         The user likes a product, so the product he likes would be the
     *         pl-act-parent_obj.
     */
    public BaseObject getParent() {
        return this.parent;
    }

    /**
     * @param parent
     *            The parent base object of the activity. <br>
     *            Types: WishList, User, Product, Opine, Review, Image. <br>
     *            e.g.:<br>
     *            The user likes a product, so the product he likes would be the
     *            pl-act-parent_obj.
     */
    public void setParent(BaseObject parent) {
        this.parent = parent;
    }

    /**
     * @return The base object for the activity. <br>
     *         Types: Opine, Review, Image <br>
     * <br>
     *         e.g.: <br>
     *         The user replies to an opine with an own opine, so the own opine
     *         from the user would be the pl-act-obj.
     */
    public BaseObject getObject() {
        return this.object;
    }

    /**
     * @param object
     *            The base object for the activity. <br>
     *            Types: Opine, Review, Image <br>
     * <br>
     *            e.g.: <br>
     *            The user replies to an opine with an own opine, so the own
     *            opine from the user would be the pl-act-obj.
     */
    public void setObject(BaseObject object) {
        this.object = object;
    }

    /**
     * @return The status of the activity. Read, Unread
     */
    public ActivityStatus getStatus() {
        return this.status;
    }

    /**
     * @param status
     *            The status of the activity. Read, Unread
     */
    public void setStatus(ActivityStatus status) {
        this.status = status;
    }

}