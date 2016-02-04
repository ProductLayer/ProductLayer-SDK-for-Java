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

import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.productlayer.core.utils.StringUtils;

/**
 * @author sorien
 *
 */
@SuppressWarnings("serial")
public class UserSettings extends HashMap<String, String> {
    /*
     * The key for the email newsletter. Check the email before allowing to enable this settings.
     * This should be synced with mailchimp.
     */
    @JsonIgnore
    private static String emailNewsletterKey = "pl-usr-setting-email_newsletter";
    
    /* 
     * The key for the email notifications. Check the email before allowing to enable this settings.
     * If email notifications are false then the user wont get any recent activities from prodly via email. 
     * This has nothing to do with push notifications. If push notifications are enabled he will receive it on his phone.
     */
    @JsonIgnore
    private static String emailNotificationKey = "pl-usr-setting-email_notification";
    
    @JsonIgnore
    public Boolean shouldSendEmailNewsletter(){
        String newsletter = this.get(emailNewsletterKey);
        if(StringUtils.isEmpty(newsletter) || newsletter.equalsIgnoreCase("true")) {
            sendEmailNewsletter(true);
            
            return true;
        }
        
        sendEmailNewsletter(false);
        return false;
    }
    
    @JsonIgnore
    public void sendEmailNewsletter(Boolean send) {
        this.put(emailNewsletterKey, send.toString());
    }
    
    @JsonIgnore
    public Boolean shouldSendEmailNotifications(){
        String notification = this.get(emailNotificationKey);
        if(StringUtils.isEmpty(notification) || notification.equalsIgnoreCase("true")) {
            sendEmailNotification(true);
            return true;
        }
        
        sendEmailNotification(false);
        return false;
    }
    
    @JsonIgnore
    public void sendEmailNotification(Boolean send) {
        this.put(emailNotificationKey, send.toString());
    }
}