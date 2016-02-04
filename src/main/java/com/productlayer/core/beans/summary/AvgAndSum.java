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
package com.productlayer.core.beans.summary;

/**
 * @author sorien
 *
 */
public class AvgAndSum {
    /**
     * The ID.
     */
    private String _id;

    /**
     * The average.
     */
    private Float avg;

    /**
     * The sum.
     */
    private Integer sum;

    public AvgAndSum() {
        super();
    }

    public AvgAndSum(String _id, Float avg, Integer sum) {
        this._id = _id;
        this.avg = avg;
        this.sum = sum;
    }

    /**
     * @return The ID.
     */
    public String get_id() {
        return this._id;
    }

    /**
     * @param _id
     *            The ID.
     */
    public void set_id(String _id) {
        this._id = _id;
    }

    /**
     * @return The average.
     */
    public Float getAvg() {
        return this.avg;
    }

    /**
     * @param avg
     *            The average.
     */
    public void setAvg(Float avg) {
        this.avg = avg;
    }

    /**
     * @return The sum.
     */
    public Integer getSum() {
        return this.sum;
    }

    /**
     * @param sum
     *            The sum.
     */
    public void setSum(Integer sum) {
        this.sum = sum;
    }

}