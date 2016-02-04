/**
 * Copyright (c) 2015, ProductLayer GmbH All rights reserved.
 */
package com.productlayer.examples.console;

import java.util.Date;
import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.productlayer.core.beans.Product;
import com.productlayer.core.utils.GTINValidator;
import com.productlayer.rest.client.PLYRestClient;
import com.productlayer.rest.client.config.PLYRestClientConfig;
import com.productlayer.rest.client.services.ProductService;

/**
 * @author sorien
 *
 */
public class ConsoleClient {

    /**
     * @param args The console arguments
     * @throws JsonProcessingException Exception if the POJO couldn't be converted to a human readable JSON-String representation.
     */
    public static void main(String[] args) throws JsonProcessingException {
        
        // initialise the config
        PLYRestClientConfig config = new PLYRestClientConfig("https", "api.productlayer.com", 80, "0.5", "<API_KEY>", false, null, 0);
        
        HashMap<String, String> searchParameter = new HashMap<String, String>();
        
        // Parse arguments
        for(int i = 0; i < args.length; i++) {
            switch (args[i]) {
            case "--api_key":
                if(args.length > i+1 && !args[i+1].startsWith("-")) {
                    config.apiKey = args[i+1];
                    i++;
                } else {
                    System.out.println("You can't use the --api_key option without providing a key!");
                    return;
                }
                break;
            case "--gtin":
                // The GTIN (barcode) of the product
                if(args.length > i+1 && !args[i+1].startsWith("-")) {
                    String gtin = args[i+1];
                    if (GTINValidator.isValidGTIN(gtin)) {
                        searchParameter.put("gtin", args[i+1]);
                    } else {
                        System.out.println("The gtin you provided is not valid!");
                        return;
                    }
                    i++;
                } else {
                    System.out.println("You can't use the --gtin option without a valid gtin!");
                    return;
                }
                break;
            case "--query":
                // The query may contain the name, GTIN or brand of the product. ATTENTION: If the query is set all other url parameters will be ignored!
                if(args.length > i+1 && !args[i+1].startsWith("-")) {
                    searchParameter.put("query", args[i+1]);
                    i++;
                } else {
                    System.out.println("You can't use the --query option without providing a query!");
                    return;
                }
                break;
            case "--name":
                // The name of the product or a substring of it.
                if(args.length > i+1 && !args[i+1].startsWith("-")) {
                    searchParameter.put("name", args[i+1]);
                    i++;
                } else {
                    System.out.println("You can't use the --name option without providing a name!");
                    return;
                }
                break;
            case "--language":
                // The preferred language (e.g.: 'en' or 'de')
                if(args.length > i+1 && !args[i+1].startsWith("-")) {
                    searchParameter.put("language", args[i+1]);
                    i++;
                } else {
                    System.out.println("You can't use the --language option without providing a language!");
                    return;
                }
                break;
            case "--page":
                // The page to be displayed starting with 0 - if no page has been provided, the first page will be shown
                if(args.length > i+1 && !args[i+1].startsWith("-")) {
                    searchParameter.put("page", args[i+1]);
                    i++;
                } else {
                    System.out.println("You can't use the --page option without providing a page!");
                    return;
                }
                break;
            case "--recordsPerPage":
                // The amount of items to be displayed per page, default: '200'
                if(args.length > i+1 && !args[i+1].startsWith("-")) {
                    searchParameter.put("recordsPerPage", args[i+1]);
                    i++;
                } else {
                    System.out.println("You can't use the --recordsPerPage option without providing a recordsPerPage!");
                    return;
                }
                break;
            case "--brand":
                // The brand of the product
                if(args.length > i+1 && !args[i+1].startsWith("-")) {
                    searchParameter.put("brand", args[i+1]);
                    i++;
                } else {
                    System.out.println("You can't use the --brand option without providing a brand!");
                    return;
                }
                break;
            case "--brandOwner":
                // The brand owner of the product
                if(args.length > i+1 && !args[i+1].startsWith("-")) {
                    searchParameter.put("brandOwner", args[i+1]);
                    i++;
                } else {
                    System.out.println("You can't use the --brandOwner option without providing a brandOwner!");
                    return;
                }
                break;
            case "--categoryKey":
                // The category key starting with 'pl-prod-cat-', e.g.: pl-prod-cat-books
                if(args.length > i+1 && !args[i+1].startsWith("-")) {
                    searchParameter.put("categoryKey", args[i+1]);
                    i++;
                } else {
                    System.out.println("You can't use the --categoryKey option without providing a categoryKey!");
                    return;
                }
                break;
            case "--orderBy":
                // Used to sort the result-set by one or more columns. The order by parameters are separated by 
                // a semicolon. Also you need to provide a prefix asc for ascending or desc for descending order
                // Default: pl-prod-name_asc (Product names ascending)
                if(args.length > i+1 && !args[i+1].startsWith("-")) {
                    searchParameter.put("orderBy", args[i+1]);
                    i++;
                } else {
                    System.out.println("You can't use the --orderBy option without providing a orderBy!");
                    return;
                }
                break;
            default:
                printHelp(args[i]);
                return;
            }
        }
        
        // check if the api key is present
        if(config.apiKey.equalsIgnoreCase("<API_KEY>")) {
            printHelp("--api_key is missing");
            return;
        }
        
        // create the productlayer client
        PLYRestClient client = new PLYRestClient(config);
        
        long startTime = new Date().getTime();
        
        // request products matching the search criteria 
        Product[] products = ProductService.searchProducts(client, 
                searchParameter.get("query"), 
                (searchParameter.containsKey("page") ? Integer.getInteger(searchParameter.get("page")) : 0),
                (searchParameter.containsKey("recordsPerPage") ? Integer.getInteger(searchParameter.get("recordsPerPage")) : 200),
                searchParameter.get("gtin"), 
                searchParameter.get("brand"), 
                searchParameter.get("brandOwner"),
                searchParameter.get("language"),
                false,
                "pl-prod-gtin,pl-prod-name,pl-lng,pl-brand-name,pl-brand-own-name,pl-prod-cat",
                searchParameter.get("name"), 
                searchParameter.get("categoryKey"),
                searchParameter.get("orderBy"));
        
        long requestDuration = new Date().getTime() - startTime;
        
        // print the results to the console.
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(Include.NON_EMPTY);
        System.out.println("Found " + products.length + " products in " + convertToTimeString(requestDuration));
        System.out.println("---------------------------------------");
        System.out.println("");
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(products));
    }
    
    /**
     * Convert time in ms to a human readable string.
     * @param timeInMS The time in milliseconds.
     * @return a human readable time format
     */
    private static String convertToTimeString(long timeInMS) {
        long millis = timeInMS % 1000;
        long seconds = timeInMS / 1000;
        
        return seconds + "s " + millis + "ms";
    }
    
    /**
     * Print the allowed parameters and usage examples to the console.
     * @param illegalOption
     */
    private static void printHelp(String illegalOption){
        System.out.println("ConsoleClient illegal option " + illegalOption);
        System.out.println("Options:");
        System.out.println("   required:");
        System.out.println("\t--api_key        The key for the application. Needed for every request.");
        System.out.println("");
        System.out.println("   optional:");
        System.out.println("\t--gtin           The GTIN (barcode) of the product");
        System.out.println("\t--query          The query may contain the name, GTIN or brand of the product.");
        System.out.println("\t--name           The name of the product or a substring of it.");
        System.out.println("\t--language       The preferred language (e.g.: 'en' or 'de')");
        System.out.println("\t--page           The page to be displayed starting with 0 - if no page has been provided, the first page will be shown");
        System.out.println("\t--recordsPerPage The amount of items to be displayed per page, default: '200'");
        System.out.println("\t--brand          The brand of the product");
        System.out.println("\t--brandOwner     The brand owner of the product");
        System.out.println("\t--categoryKey    The category key starting with 'pl-prod-cat-', e.g.: pl-prod-cat-books");
        System.out.println("\t--orderBy        Used to sort the result-set by one or more columns. DEFAULT: pl-prod-name_asc (Product names ascending)");
        System.out.println("");
        System.out.println("Usage:");
        System.out.println("\tConsoleClient --api_key <API_KEY> --gtin 00888462563369");
        System.out.println("\tConsoleClient --api_key <API_KEY> --gtin 00888462563369 --language en --page 0 --recordsPerPage 1");
        System.out.println("\tConsoleClient --api_key <API_KEY> --name \"Apple iPhone 6S 64GB\"");
        System.out.println("\tConsoleClient --api_key <API_KEY> --query \"Apple iPhone 6S 64GB\"");
        System.out.println("");
        System.out.println("If you don't have an API_KEY you can create one at https://developer.productlayer.com");
    }

}
