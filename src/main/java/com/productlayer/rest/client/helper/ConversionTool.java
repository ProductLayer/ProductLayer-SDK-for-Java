package com.productlayer.rest.client.helper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.productlayer.core.beans.BaseObject;

/**
 * Builds the (de)serializers for JSON and byte arrays in use by the
 * {@code PLYRestClient}.
 */
public class ConversionTool {

    private static final ObjectMapper objectMapper;

    private static final ByteArrayHttpMessageConverter byteArrayConverter;

    private static final MappingJackson2HttpMessageConverter jacksonConverter;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.setSerializationInclusion(Include.NON_EMPTY);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        byteArrayConverter = new ByteArrayHttpMessageConverter();
        List<MediaType> supportedMediaTypesImages = new ArrayList<MediaType>();
        supportedMediaTypesImages.add(MediaType.IMAGE_JPEG);
        supportedMediaTypesImages.add(MediaType.IMAGE_GIF);
        supportedMediaTypesImages.add(MediaType.IMAGE_PNG);
        byteArrayConverter.setSupportedMediaTypes(supportedMediaTypesImages);

        jacksonConverter = new MappingJackson2HttpMessageConverter();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(BaseObject.class, new ItemDeserializer());
        objectMapper.registerModule(module);
        jacksonConverter.setPrettyPrint(false);
        jacksonConverter.setObjectMapper(objectMapper);
        List<MediaType> supportedMediaTypesJson = new ArrayList<MediaType>();
        supportedMediaTypesJson.add(MediaType.APPLICATION_JSON);
        jacksonConverter.setSupportedMediaTypes(supportedMediaTypesJson);
    }

    /**
     * @return the Jackson JSON parser
     */
    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    /**
     * @return the Spring byte array converter
     */
    public static ByteArrayHttpMessageConverter getByteArrayConverter() {
        return byteArrayConverter;
    }

    /**
     * @return the Spring JSON converter internally using the Jackson parser
     */
    public static MappingJackson2HttpMessageConverter getJacksonConverter() {
        return jacksonConverter;
    }

    /**
     * Reads the string representation of {@code object} and attempts to convert
     * it to an instance of class {@code objectType}.
     * 
     * @param object
     *            the object to read the string representation of
     * @param objectType
     *            the class to deserialize the string to
     * @param <T>
     *            the type to convert to
     * @return the converted object as instance of {@code objectType}, or null
     *         if a deserialization is not possible
     */
    public static <T> T parseObject(Object object, Class<T> objectType) {
        ObjectMapper mapper = getObjectMapper();

        try {
            return mapper.readValue(object.toString(), objectType);
        } catch (JsonParseException e) {
        } catch (JsonMappingException e) {
        } catch (IOException e) {
        }

        return null;
    }
}
