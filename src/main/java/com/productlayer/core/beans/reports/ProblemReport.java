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
package com.productlayer.core.beans.reports;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.productlayer.core.beans.BaseObject;
import com.productlayer.core.beans.ObjectReference;

/**
 * @author sorien
 *
 * 
 *         Problem reports are for reporting copyright infringements or any
 *         other problems with the content.
 */
@SuppressWarnings("serial")
public class ProblemReport extends BaseObject {
    /**
     * A reference to the object causing the reported problem. <br>
     * <b>This reference will be set by the api and will be overwritten if
     * present.</b>
     */
    @JsonProperty("pl-report-obj_ref")
    private ObjectReference object;

    /**
     * The email of the reporter.
     */
    @JsonProperty("pl-report-email")
    private String email;

    /**
     * The problem description of the report.
     */
    @JsonProperty("pl-report-description")
    private String text;

    /**
     * The status for the report. [pl-status-created, pl-status-processing,
     * pl-status-waiting_for_info, pl-status-closed]
     */
    @JsonProperty("pl-report-status")
    private ReportStatus status;

    public ProblemReport() {
        super();
    }

    /**
     * @return A reference to the object causing the reported problem. <br>
     *         <b>This reference will be set by the api and will be overwritten
     *         if present.</b>
     */
    public ObjectReference getObject() {
        return this.object;
    }

    /**
     * @param object
     *            A reference to the object causing the reported problem. <br>
     *            <b>This reference will be set by the api and will be
     *            overwritten if present.</b>
     */
    public void setObject(ObjectReference object) {
        this.object = object;
    }

    /**
     * @return The email of the reporter.
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * @param email
     *            The email of the reporter.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return The problem description of the report.
     */
    public String getText() {
        return this.text;
    }

    /**
     * @param text
     *            The problem description of the report.
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return The status for the report. [pl-status-created,
     *         pl-status-processing, pl-status-waiting_for_info,
     *         pl-status-closed]
     */
    public ReportStatus getStatus() {
        return this.status;
    }

    /**
     * @param status
     *            The status for the report. [pl-status-created,
     *            pl-status-processing, pl-status-waiting_for_info,
     *            pl-status-closed]
     */
    public void setStatus(ReportStatus status) {
        this.status = status;
    }

}