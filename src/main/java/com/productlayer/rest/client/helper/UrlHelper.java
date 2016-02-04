/**
 * 
 */
package com.productlayer.rest.client.helper;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Static methods to work on URLs.
 */
public class UrlHelper {
    
    /**
     * Adds query parameters to an URL.
     * 
     * @param url
     *            the URL to append the query parameters to
     * @param parameter
     *            a map of key-value pairs that are appended as "key=value"
     * @return the URL and appended parameters
     */
	public static String addQueryParameterToUrl(String url, Map<String, String> parameter) {
		if(parameter.size() == 0) return url;
		
		boolean first = true;
        for (Entry<String, String> entry : parameter.entrySet()) {
            
            url += (first) ? "?" : "&";
            url += entry.getKey() + "=" + entry.getValue();
            
            first = false;
        }
	    
	    return url;
	}
	
    /**
     * Adds query parameter placeholders to an URL.
     * 
     * @param url
     *            the URL to append the query parameter placeholders to
     * @param parameter
     *            a map of key-value pairs that are appended as "key={key}"
     * @return the URL and appended parameter placeholders
     */
	public static String addQueryParameterPlaceholderToUrl(String url, Map<String, String> parameter) {
        if(parameter.size() == 0) return url;
        
        boolean first = true;
        for (String key : parameter.keySet()) {
            
            url += (first) ? "?" : "&";
            url += key + "={" + key + "}";
            
            first = false;
        }
        
        return url;
    }

    /**
     * Replaces query parameters found in a URL string with specified
     * parameters.
     * 
     * @param fullUrl
     *            the URL including any old query parameters
     * @param newParameter
     *            the new parameters to put in the URL, replacing any existing
     *            one if it was already found in the URL
     * @return the URL including any previous query parameters and new ones
     *         overwriting existing ones
     */
    public static String replaceQueryParameterInUrl(String fullUrl, Map<String, String> newParameter) {
        Map<String, String> parameter = urlToQueryParameterMap(fullUrl);
        parameter.putAll(newParameter);
        String url = splitUrlParameter(fullUrl)[0];
        return addQueryParameterToUrl(url, parameter);
    }

    /**
     * Extracts any query parameters from a URL.
     * 
     * @param url
     *            the URL to work on
     * @return a key:value mapping of any query parameters found in the URL
     */
    public static Map<String, String> urlToQueryParameterMap(String url) {
        Map<String, String> paramMap = new HashMap<String, String>();
        String query = splitUrlParameter(url)[1];
        query = query.replace("&amp;", "&");
        String[] params = query.split("&");
        for (String param : params) {
            int equalsPos = param.indexOf("=");
            String key = equalsPos == -1 ? param : param.substring(0, equalsPos);
            String value = equalsPos == -1 ? "" : param.substring(equalsPos + 1);
            paramMap.put(key, value);
        }
        return paramMap;
    }

    /**
     * Splits a URL into the path and any query parameters.
     * 
     * @param fullUrl
     *            the URL to split
     * @return an array of {path, query}
     */
    public static String[] splitUrlParameter(String fullUrl) {
        int queryStart = fullUrl.indexOf("?");
        String url = queryStart == -1 ? fullUrl : fullUrl.substring(0, queryStart);
        String query = queryStart == -1 ? "" : fullUrl.substring(queryStart + 1);
        return new String[] { url, query };
    }

}
