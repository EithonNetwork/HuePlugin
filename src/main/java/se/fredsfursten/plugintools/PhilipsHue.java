package se.fredsfursten.plugintools;

import org.json.simple.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;


public class PhilipsHue {

	public void setOn()
	{
		JSONObject json = new JSONObject();
		json.put("on", "true");
		json.put("sat", "255");
		json.put("bri", "255");
		json.put("hue", "10000");
	}
	
	public static void on()
	{
		try {
			 
			Client client = Client.create();
	 
			WebResource webResource = client
			   .resource("http://192.168.1.91/api/minecrafthueplugin/lights/3/state");
			
			JSONObject json = new JSONObject();
			json.put("on", true);
			
			String input = json.toString();
			System.out.println("Input to Server .... \n" + input);
			
			ClientResponse response = webResource.accept("application/json")
	                   .put(ClientResponse.class, input);
	 
			System.out.println("Status from Server .... \n");
			System.out.println(String.format("%d", response.getStatus()));
			
			/*
			if (response.getStatus() != 200) {
			   throw new RuntimeException("Failed : HTTP error code : "
				+ response.getStatus());
			}
			*/
	 
			String output = response.getEntity(String.class);
	 
			System.out.println("Output from Server .... \n");
			System.out.println(output);
	 
		  } catch (Exception e) {
	 
			e.printStackTrace();
	 
		  }
	}
	

	
	public static void off()
	{
		try {
			 
			Client client = Client.create();
	 
			WebResource webResource = client
			   .resource("http://192.168.1.91/api/minecrafthueplugin/lights/3/state");
			
			String input = "{\"on\":false}";
			
			ClientResponse response = webResource.accept("application/json")
	                   .put(ClientResponse.class, input);
	 
			System.out.println("Status from Server .... \n");
			System.out.println(String.format("%d", response.getStatus()));
			
			/*
			if (response.getStatus() != 200) {
			   throw new RuntimeException("Failed : HTTP error code : "
				+ response.getStatus());
			}
			*/
	 
			String output = response.getEntity(String.class);
	 
			System.out.println("Output from Server .... \n");
			System.out.println(output);
	 
		  } catch (Exception e) {
	 
			e.printStackTrace();
	 
		  }
	}
}
