package com.productlayer.rest.client.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;

import com.productlayer.core.beans.ProductImage;
import com.productlayer.core.beans.UserAvatarImage;
import com.productlayer.core.beans.reports.ProblemReport;
import com.productlayer.core.error.PLYHttpException;
import com.productlayer.core.utils.StringUtils;
import com.productlayer.rest.client.PLYRestClient;
import com.productlayer.rest.client.helper.UrlHelper;

/**
 * Methods for managing images.
 */
public class ImageService {

    /**
     * Deletes a specific product image. Only the owner or an admin can delete
     * the image. If the user earns points for this operation
     * 'X-ProductLayer-User-Points' and 'X-ProductLayer-User-Points-Changed'
     * will be present in the response header.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param imageID
     *            The identifier of the image
     * @return The metadata of any deleted images
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static ProductImage[] deleteProductImage(PLYRestClient client, String imageID) {
        String url = "/image/" + imageID;

        ResponseEntity<ProductImage[]> response = client.exchange(url, HttpMethod.DELETE,
                ProductImage[].class, null);
        return response.getBody();
    }

    /**
     * Deletes the avatar image of a user.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param userID
     *            The identifier of the user
     * @return The metadata of the deleted user avatar image
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static UserAvatarImage deleteUserAvatar(PLYRestClient client, String userID) {
        String url = "/user/" + userID + "/avatar";

        ResponseEntity<UserAvatarImage> response = client.exchange(url, HttpMethod.DELETE,
                UserAvatarImage.class, null);
        return response.getBody();
    }

    /**
     * Downvotes a specific product image. If the user already up voted the
     * image the up-vote will be removed.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param imageID
     *            The identifier of the metadata object or the identifier of the
     *            file ({image_id}.jpg)
     * @return The image metadata with the new vote score
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static ProductImage downVoteProductImage(PLYRestClient client, String imageID) {
        String url = "/image/" + imageID + "/down_vote";

        ResponseEntity<ProductImage> response = client.exchange(url, HttpMethod.POST, ProductImage.class,
                null);
        return response.getBody();
    }

    /**
     * Gets the default image (highest voted image) of a product. For some
     * browsers you need to add the .jpg file extension
     * (/product/{gtin}/default_image.jpg) to the url.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param gtin
     *            The GTIN (barcode) of the product
     * @param maxWidth
     *            [Optional] The preferred maximum width
     * @param maxHeight
     *            [Optional] The preferred maximum height
     * @param crop
     *            [Optional] Whether the image should be cropped
     * @param quality
     *            [Optional] The quality of the image between 20 and 100
     * @return the URL to the requested data
     */
    public static String getDefaultProductImageForSizeURL(PLYRestClient client, String gtin,
            Integer maxWidth, Integer maxHeight, Boolean crop, Integer quality) {
        String url = client.getUrlForMethod("/product/" + gtin + "/default_image");

        Map<String, String> parameters = new HashMap<String, String>();
        if (!StringUtils.isEmpty(maxWidth)) {
            parameters.put("max_width", maxWidth.toString());
        }
        if (!StringUtils.isEmpty(maxHeight)) {
            parameters.put("max_height", maxHeight.toString());
        }
        if (!StringUtils.isEmpty(crop)) {
            parameters.put("crop", crop.toString());
        }
        if (!StringUtils.isEmpty(quality)) {
            parameters.put("quality", quality.toString());
        }
        url = UrlHelper.addQueryParameterToUrl(url, parameters);

        return url;
    }

    /**
     * Gets the metadata of the default image (highest voted image) of a
     * specific product.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param gtin
     *            The GTIN (barcode) of the product
     * @return The image metadata
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static ProductImage getDefaultProductImageMeta(PLYRestClient client, String gtin) {
        String url = "/product/" + gtin + "/default_image/meta";

        ResponseEntity<ProductImage> response = client
                .exchange(url, HttpMethod.GET, ProductImage.class, null);
        return response.getBody();
    }

    /**
     * Gets a specific image. For some browsers you need to add the .jpg file
     * extension (/image/{image_id}.jpg) to the url.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param imageID
     *            The identifier of the image
     * @param maxWidth
     *            [Optional] The preferred maximum width
     * @param maxHeight
     *            [Optional] The preferred maximum height
     * @param crop
     *            [Optional] Whether the image should be cropped
     * @param quality
     *            [Optional] The quality of the image between 20 and 100
     * @return the URL to the requested data
     */
    public static String getImageForSizeURL(PLYRestClient client, String imageID, Integer maxWidth,
            Integer maxHeight, Boolean crop, Integer quality) {
        String url = client.getUrlForMethod("/image/" + imageID);

        Map<String, String> parameters = new HashMap<String, String>();
        if (!StringUtils.isEmpty(maxWidth)) {
            parameters.put("max_width", maxWidth.toString());
        }
        if (!StringUtils.isEmpty(maxHeight)) {
            parameters.put("max_height", maxHeight.toString());
        }
        if (!StringUtils.isEmpty(crop)) {
            parameters.put("crop", crop.toString());
        }
        if (!StringUtils.isEmpty(quality)) {
            parameters.put("quality", quality.toString());
        }
        url = UrlHelper.addQueryParameterToUrl(url, parameters);

        return url;
    }

    /**
     * Gets a specific image meta information.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param imageID
     *            The identifier of the image
     * @return The image metadata
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static ProductImage getImageMeta(PLYRestClient client, String imageID) {
        String url = "/image/" + imageID + "/meta";

        ResponseEntity<ProductImage> response = client
                .exchange(url, HttpMethod.GET, ProductImage.class, null);
        return response.getBody();
    }

    /**
     * Gets all image metadata of a specific product. Use this to get all image
     * URLs of a product.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param gtin
     *            The GTIN (barcode) of the product
     * @return Any found product image metadata
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static ProductImage[] getProductImages(PLYRestClient client, String gtin) {
        String url = "/product/" + gtin + "/images";

        ResponseEntity<ProductImage[]> response = client.exchange(url, HttpMethod.GET, ProductImage[].class,
                null);
        return response.getBody();
    }

    /**
     * Gets the avatar of a specific user.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param userID
     *            The identifier of the user
     * @param size
     *            [Optional] The size of the avatar image in pixel. The avatar
     *            image is always a square image and the maximum size is 512
     *            pixel.
     * @return the URL to the requested data
     */
    public static String getUserAvatarURL(PLYRestClient client, String userID, Integer size) {
        String url = client.getUrlForMethod("/user/" + userID + "/avatar");

        Map<String, String> parameters = new HashMap<String, String>();
        if (!StringUtils.isEmpty(size)) {
            parameters.put("size", size.toString());
        }
        url = UrlHelper.addQueryParameterToUrl(url, parameters);

        return url;
    }

    /**
     * Sends a report about copyright infringements or any other problems with
     * the image.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param imageID
     *            The identifier of the image
     * @param report
     *            The report
     * @return The problem report object
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static ProblemReport reportImage(PLYRestClient client, String imageID, ProblemReport report) {
        String url = "/images/report_problem";

        Map<String, String> parameters = new HashMap<String, String>();
        if (!StringUtils.isEmpty(imageID)) {
            parameters.put("image_id", imageID);
        }
        url = UrlHelper.addQueryParameterPlaceholderToUrl(url, parameters);

        ResponseEntity<ProblemReport> response = client.exchangeWithObject(url, HttpMethod.POST, report,
                ProblemReport.class, parameters);
        return response.getBody();
    }

    /**
     * Rotates the image clockwise.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param imageID
     *            The identifier of the image
     * @param degrees
     *            [Optional] The degrees to rotate the image clockwise
     * @return The image metadata after the rotation
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static ProductImage rotateImage(PLYRestClient client, String imageID, Integer degrees) {
        String url = "/image/" + imageID + "/rotate";

        Map<String, String> parameters = new HashMap<String, String>();
        if (!StringUtils.isEmpty(degrees)) {
            parameters.put("degrees", degrees.toString());
        }
        url = UrlHelper.addQueryParameterPlaceholderToUrl(url, parameters);

        ResponseEntity<ProductImage> response = client.exchange(url, HttpMethod.PUT, ProductImage.class,
                parameters);
        return response.getBody();
    }

    /**
     * Upvotes a specific product image. If the user already down voted the
     * image the down-vote will be removed.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param imageID
     *            The identifier of the metadata object or the identifier of the
     *            file ({image_id}.jpg)
     * @return The image metadata with the new vote score
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static ProductImage upVoteProductImage(PLYRestClient client, String imageID) {
        String url = "/image/" + imageID + "/up_vote";

        ResponseEntity<ProductImage> response = client.exchange(url, HttpMethod.POST, ProductImage.class,
                null);
        return response.getBody();
    }

    /**
     * Updates the avatar image of a user.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param userID
     *            The identifier of the user
     * @param filePath
     *            the path to the file to upload
     * @return The metadata of the updated user avatar image
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static UserAvatarImage updateUserAvatar(PLYRestClient client, String userID, String filePath) {
        MultiValueMap<String, Object> mvm = new LinkedMultiValueMap<String, Object>();
        mvm.add("file", new FileSystemResource(filePath));

        String url = "/user/" + userID + "/avatar";

        ResponseEntity<UserAvatarImage> response = client.exchangeWithObjectAndContentType(url,
                HttpMethod.POST, mvm, MediaType.MULTIPART_FORM_DATA, UserAvatarImage.class, null);
        return response.getBody();
    }

    /**
     * Uploads an image for an opine. If the user earns points for this
     * operation 'X-ProductLayer-User-Points' and
     * 'X-ProductLayer-User-Points-Changed' will be present in the response
     * header.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param opineID
     *            The identifier of the opine
     * @param filePath
     *            the path to the file to upload
     * @return The metadata of the uploaded image
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static ProductImage uploadOpineImage(PLYRestClient client, String opineID, String filePath) {
        MultiValueMap<String, Object> mvm = new LinkedMultiValueMap<String, Object>();
        mvm.add("file", new FileSystemResource(filePath));

        String url = "/opine/" + opineID + "/images";

        ResponseEntity<ProductImage> response = client.exchangeWithObjectAndContentType(url, HttpMethod.POST,
                mvm, MediaType.MULTIPART_FORM_DATA, ProductImage.class, null);
        return response.getBody();
    }

    /**
     * Uploads a product image. If the user earns points for this operation
     * 'X-ProductLayer-User-Points' and 'X-ProductLayer-User-Points-Changed'
     * will be present in the response header.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param gtin
     *            The GTIN (barcode) of the product
     * @param filePath
     *            the path to the file to upload
     * @return The image metadata
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static ProductImage uploadProductImage(PLYRestClient client, String gtin, String filePath) {
        MultiValueMap<String, Object> mvm = new LinkedMultiValueMap<String, Object>();
        mvm.add("file", new FileSystemResource(filePath));

        String url = "/product/" + gtin + "/images";

        ResponseEntity<ProductImage> response = client.exchangeWithObjectAndContentType(url, HttpMethod.POST,
                mvm, MediaType.MULTIPART_FORM_DATA, ProductImage.class, null);
        return response.getBody();
    }

}
