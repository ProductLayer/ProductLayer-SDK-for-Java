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
package com.productlayer.core.beans.ranking;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.productlayer.core.beans.BaseObject;

/**
 * @author sorien
 *
 * 
 *         The entity encapsulating the ranked object with additional ranking
 *         information.
 */
@SuppressWarnings("serial")
public class RankingEntry<T extends BaseObject> implements Serializable {
    /**
     * The rank of the object.
     */
    @JsonProperty("pl-rank")
    private Long rank;

    /**
     * The score of the object.
     */
    @JsonProperty("pl-score")
    private Long score;

    /**
     * The entity for the rank. e.g.: Product, Opine, Review, User, Image
     */
    @JsonProperty("pl-entity")
    private T entity;

    public RankingEntry() {
        super();
    }

    /**
     * @return The rank of the object.
     */
    public Long getRank() {
        return this.rank;
    }

    /**
     * @param rank
     *            The rank of the object.
     */
    public void setRank(Long rank) {
        this.rank = rank;
    }

    /**
     * @return The score of the object.
     */
    public Long getScore() {
        return this.score;
    }

    /**
     * @param score
     *            The score of the object.
     */
    public void setScore(Long score) {
        this.score = score;
    }

    /**
     * @return The entity for the rank. e.g.: Product, Opine, Review, User,
     *         Image
     */
    public T getEntity() {
        return this.entity;
    }

    /**
     * @param entity
     *            The entity for the rank. e.g.: Product, Opine, Review, User,
     *            Image
     */
    public void setEntity(T entity) {
        this.entity = entity;
    }

}