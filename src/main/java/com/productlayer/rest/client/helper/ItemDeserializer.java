package com.productlayer.rest.client.helper;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.productlayer.core.beans.BaseObject;
import com.productlayer.core.beans.Opine;
import com.productlayer.core.beans.Product;
import com.productlayer.core.beans.ProductImage;
import com.productlayer.core.beans.Review;

/**
 * The JSON deserializer for ProductLayer beans implementing {@link BaseObject}.
 */
public class ItemDeserializer extends JsonDeserializer<BaseObject> {

    @Override
    public BaseObject deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException,
            JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);

        String jsonString = node.toString();

        if (node == null || node.get("pl-class") == null) {
            return null;
        }

        String _class = node.get("pl-class").asText();

        if (_class.equals("com.productlayer.Review")) {
            return ConversionTool.getObjectMapper().readValue(jsonString, Review.class);
        } else if (_class.equals("com.productlayer.Image")) {
            return ConversionTool.getObjectMapper().readValue(jsonString, ProductImage.class);
        } else if (_class.equals("com.productlayer.Product")) {
            return ConversionTool.getObjectMapper().readValue(jsonString, Product.class);
        } else if (_class.equals("com.productlayer.Opine")) {
            return ConversionTool.getObjectMapper().readValue(jsonString, Opine.class);
        }

        return null;
    }
}
