package com.wksjava.tut.apachehttpclient.examples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;

public class SSLExample {
	public static void main(String[] args) {
		String apiKey = args[0];
		String url = "https://dapi.kakao.com";
		String path = "/v2/local/search/address.json";
		
		Map<String, String> header = new HashMap<String, String>();
		header.put("Authorization", "KakaoAK " + apiKey);

		Map<String, String> queryString = new HashMap<String, String>();
		queryString.put("query", "서울시 강남구 남부순환로 2748");
		
		System.out.println(RequestGetSSL5(url, path, header, queryString));
		

		System.out.println(RequestGetSSL8(url, path, header, queryString));
		RequestGetSSL8();
	}
	
	

	/**
	 * This class is used in RequestGetSSL5 in order to accept all 
	 * @author its174
	 *
	 */
	public static class HttpsTrustManager implements X509TrustManager {
		public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			// TODO Auto-generated method stub
			
		}
		public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			// TODO Auto-generated method stub
			
		}
		public X509Certificate[] getAcceptedIssuers() {
			// TODO Auto-generated method stub
			return new X509Certificate[]{};
		}

	}
	
	/**
	 * For java >= 1.5
	 * @param url
	 * @param path
	 * @param header
	 * @param queryString
	 * @return response body
	 */
	public static String RequestGetSSL5(String url, String path, Map<String, String> header, Map<String, String> queryString) {

        SSLContext sslcontext = null;
		try {
			sslcontext = SSLContexts.custom().useSSL().build();
			sslcontext.init(null, new X509TrustManager[]{new HttpsTrustManager()}, new SecureRandom());
		} catch (Exception e) {
			e.printStackTrace();
		} 

        SSLConnectionSocketFactory factory = new SSLConnectionSocketFactory(sslcontext,
//                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER
                SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER
                );
        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(factory).build();
        
		
        // build url and its query string
		URIBuilder builder = null;
		HttpGet httpGet = null;
		try {
			builder = new URIBuilder(url + path);
			
			if (queryString != null) {
				Iterator<String> keys = queryString.keySet().iterator();
				while(keys.hasNext()) {
					String key = keys.next();
					builder.setParameter(key, queryString.get(key));
				}
			}
			httpGet = new HttpGet(builder.build());
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
        // add header
		if (header != null) {
			Iterator<String> keys = header.keySet().iterator();
			while (keys.hasNext()) {
				String key = keys.next();
				httpGet.setHeader(key, header.get(key));
			}
		}
        
        // request GET
        CloseableHttpResponse httpResponse = null;

        BufferedReader reader = null;
        try {
        	httpResponse = httpClient.execute(httpGet);
            
            reader = new BufferedReader(new InputStreamReader(
                    httpResponse.getEntity().getContent()));

            String inputLine = null;
            StringBuffer response = new StringBuffer();

            while ((inputLine = reader.readLine()) != null) {
                response.append(inputLine);
            }
            return response.toString();
        }catch(Exception e) {
        	e.printStackTrace();
        	return null;
        }finally {
        	if (reader != null) {
                try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
        	if (httpClient != null) {
                try {
					httpClient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}        
        	}
        }
	}
	
	

	/**
	 * For Java >= 1.8
	 * Accept all host
	 */
	public static String RequestGetSSL8(String url, String path, Map<String, String> header, Map<String, String> queryString) {
		SSLContext sslContext = null;
		try {
			sslContext = new SSLContextBuilder().loadTrustMaterial(null, (certificate, authType) -> true)
					.build();
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		CloseableHttpClient httpClient = HttpClients.custom().setSSLContext(sslContext)
				.setSSLHostnameVerifier(new NoopHostnameVerifier()).build();

        // build url and its query string
		URIBuilder builder = null;
		HttpGet httpGet = null;
		try {
			builder = new URIBuilder(url + path);
			
			if (queryString != null) {
				Iterator<String> keys = queryString.keySet().iterator();
				while(keys.hasNext()) {
					String key = keys.next();
					builder.setParameter(key, queryString.get(key));
				}
			}
			httpGet = new HttpGet(builder.build());
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
        // add header
		if (header != null) {
			Iterator<String> keys = header.keySet().iterator();
			while (keys.hasNext()) {
				String key = keys.next();
				httpGet.setHeader(key, header.get(key));
			}
		}
		
		
		// request GET
        CloseableHttpResponse httpResponse = null;

        BufferedReader reader = null;
        try {
        	httpResponse = httpClient.execute(httpGet);
            
            reader = new BufferedReader(new InputStreamReader(
                    httpResponse.getEntity().getContent()));

            String inputLine = null;
            StringBuffer response = new StringBuffer();

            while ((inputLine = reader.readLine()) != null) {
                response.append(inputLine);
            }
            return response.toString();
        }catch(Exception e) {
        	e.printStackTrace();
        	return null;
        }finally {
        	if (reader != null) {
                try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
        	if (httpClient != null) {
                try {
					httpClient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}        
        	}
        }
	}
	
	public static void RequestGetSSL8() {
		SSLContext sslContext = null;
		try {
			sslContext = new SSLContextBuilder().loadTrustMaterial(null, (certificate, authType) -> true)
					.build();
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		CloseableHttpClient httpClient = HttpClients.custom().setSSLContext(sslContext)
				.setSSLHostnameVerifier(new NoopHostnameVerifier()).build();
		HttpGet httpGet = new HttpGet("https://www.google.com");

		BufferedReader reader = null;
		try {
			CloseableHttpResponse httpResponse = httpClient.execute(httpGet);

			reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));

			String inputLine = null;
			StringBuffer response = new StringBuffer();

			while ((inputLine = reader.readLine()) != null) {
				response.append(inputLine);
			}

			System.out.println(response.toString());
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
