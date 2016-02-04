ProductLayer SDK for Java
========================

![productlayer logo](https://prod.ly/images/logo_256x175.png)

The ultimate product information API, enabling a new breed of [product-centric apps](http://www.cocoanetics.com/2014/02/from-barcodes-to-productlayer/). This is the Java SDK. We also provide SDKs for [Android](https://github.com/ProductLayer/ProductLayer-SDK-for-Android) and [iOS](https://github.com/ProductLayer/ProductLayer-SDK-for-iOS).

 - [prod.ly web](https://prod.ly)
 - [prod.ly android](https://play.google.com/store/apps/details?id=com.productlayer.prodly)

You can sign up for a developer account at [developer.productlayer.com](https://developer.productlayer.com)

To clone the project execute the following command:
```
git clone https://github.com/ProductLayer/ProductLayer-SDK-for-Java.git
```

## Usage

#### Grade
	compile 'com.productlayer.ply-sdk-java:0.5.0'
	
#### Maven
	<dependency>
		<groupId>com.productlayer</groupId>
		<artifactId>ply-sdk-java</artifactId>
		<version>0.5.0</version>
	</dependency>

-----
#### Get an api-key
https://developer.productlayer.com

----

#### Example

```java
// initialise the config
PLYRestClientConfig config = new PLYRestClientConfig("https", "api.productlayer.com", 80, "0.5", "<API_KEY>", false, null, 0);
	
// create the productlayer client
PLYRestClient client = new PLYRestClient(config);

// Request a product via a global unique gtin
Product foundProduct = ProductService.getProductForGtin(client, "0888462563369", "de", false, null);
```

 - [ConsoleClient](https://github.com/ProductLayer/ProductLayer-SDK-for-Java/blob/develop/src/main/java/com/productlayer/examples/console/ConsoleClient.java) - A simple console client to request product infos from the productlayer API
