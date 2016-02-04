/**
 * Copyright (c) 2015, ProductLayer GmbH All rights reserved.
 */
package com.productlayer.core.beans.localization;

import java.util.HashSet;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.productlayer.core.beans.BaseObject;

/**
 * @author sorien
 *
 */
@SuppressWarnings("serial")
public class LocalizationGroup extends BaseObject{
    
    /**
     * @return string identifier of this class
     */
    @JsonIgnore
    @Override
    public String getSimpleObjectIdentifier() { return "LocalizationGroup"; };
    
    /**
     * @return full identifier of this class
     */
    @JsonIgnore
    @Override
    public String getDomainObjectIdentifier() { return "com.productlayer." + getSimpleObjectIdentifier(); }
    
    @JsonProperty("pl-locale-group-name")
    public String name;
    
    @JsonProperty("pl-locale-group-key_count")
    public Long keyCount;
    
    @JsonProperty("pl-locale-group-localized_key_count")
    public Long localizedKeyCount;
    
    @JsonProperty("pl-locale-group-lngs")
    public HashSet<String> languages;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the keyCount
     */
    public Long getKeyCount() {
        return keyCount;
    }

    /**
     * @param keyCount the keyCount to set
     */
    public void setKeyCount(Long keyCount) {
        this.keyCount = keyCount;
    }

    /**
     * @return the localizedKeyCount
     */
    public Long getLocalizedKeyCount() {
        return localizedKeyCount;
    }

    /**
     * @param localizedKeyCount the localizedKeyCount to set
     */
    public void setLocalizedKeyCount(Long localizedKeyCount) {
        this.localizedKeyCount = localizedKeyCount;
    }

    /**
     * @return the languages
     */
    public HashSet<String> getLanguages() {
        return languages;
    }

    /**
     * @param languages the languages to set
     */
    public void setLanguages(HashSet<String> languages) {
        this.languages = languages;
    }
}
