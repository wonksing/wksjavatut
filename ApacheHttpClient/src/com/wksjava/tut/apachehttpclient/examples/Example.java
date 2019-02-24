package com.wksjava.tut.apachehttpclient.examples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class Example {
	public static void main(String[] args) {
		requestPost();
	}
	
	public static void requestGet() {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet("https://www.google.com");
		
        // add header
        httpGet.addHeader("User-Agent", "Mozila/5.0");
        
        // request GET
        CloseableHttpResponse httpResponse = null;

        BufferedReader reader = null;
        try {
        	httpResponse = httpClient.execute(httpGet);
            System.out.println("::GET Response Status::");
            System.out.println(httpResponse.getStatusLine().getStatusCode());
            
            reader = new BufferedReader(new InputStreamReader(
                    httpResponse.getEntity().getContent()));

            String inputLine = null;
            StringBuffer response = new StringBuffer();

            while ((inputLine = reader.readLine()) != null) {
                response.append(inputLine);
            }
            //Print result
            System.out.println(response.toString());
        }catch(Exception e) {
        	
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
	
	public static void requestPost() {

	    CloseableHttpClient httpClient = null;
        BufferedReader reader = null;
		try {
			httpClient = HttpClients.createDefault();
		    HttpPost httpPost = new HttpPost("http://www.example.com");
		 
		    String json = "{\"id\":1,\"name\":\"John\"}";
		    StringEntity entity = new StringEntity(json);
		    
		    httpPost.setEntity(entity);
		    httpPost.setHeader("Accept", "application/json");
		    httpPost.setHeader("Content-type", "application/json");
		 
		    CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
		    
            reader = new BufferedReader(new InputStreamReader(
                    httpResponse.getEntity().getContent()));

            String inputLine = null;
            StringBuffer response = new StringBuffer();

            while ((inputLine = reader.readLine()) != null) {
                response.append(inputLine);
            }

            System.out.println(response.toString());
		}catch(Exception e) {
			
		}finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(httpClient != null) {
			    try {
					httpClient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
