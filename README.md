ProductLayer SDK for Java
========================

<div style="text-align:center"><img src="https://dl.dropboxusercontent.com/u/768123/logo.png" height="256" width="256" ></div>

The ultimate product information API, enabling a new breed of [product-centric apps](http://www.cocoanetics.com/2014/02/from-barcodes-to-productlayer/). This is the Java SDK for it (iOS SDK can be found [here](https://github.com/ProductLayer/ProductLayer-SDK-for-iOS)). It is Open Source so that you can see how easy it is to use it in your own apps.

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
#### Get a api-key
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

 - ConsoleClient - A simple console client to request product infos from the productlayer API


Apps using this SDK
-------------------

We love to highlight apps using the ProductLayer SDK. The first app using it is prod.ly the [official client app for the prod.ly social network](https://prod.ly/app/).