package com.example.synchrony.assignment.demo.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.example.synchrony.assignment.demo.model.userImages;
import com.example.synchrony.assignment.demo.model.userProfile;
import com.example.synchrony.assignment.demo.repository.ImageRepository;
import com.example.synchrony.assignment.demo.repository.UserRepository;
import org.apache.http.*;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;


@Service
public class imageService {
	@Autowired 
	private ImageRepository imgrepo;
	
	@Autowired 
	private UserRepository userRepository;
	@Autowired
	private RestTemplate resttemplate;
	public String uploadImage()
	{
		return "HI";
	}
	
/*	public  byte[] getImageWithMediaType(String id) throws IOException {
		String loginid=SecurityContextHolder.getContext().getAuthentication().getName();
		Optional<userImages> image=imgrepo.findByUserLoginIdAndId(loginid, id);
		if(image.isPresent())
		{
			CloseableHttpClient httpClient = HttpClients.createDefault();
	        HttpGet httpPostRequest = new HttpGet(image.get().getLink());
	    	HttpResponse response =httpClient.execute(httpPostRequest );
	    	System.out.println(response.getStatusLine().getReasonPhrase());
	    	System.out.println(response);
	    	return null;
		}
		else
		{
			return null;
		}
        
    }*/
	public  int  deleteImagewithMediaTyp(String id) throws IOException {
		String loginid=SecurityContextHolder.getContext().getAuthentication().getName();
	    Optional<userImages> img=imgrepo.findByUserLoginIdAndId(loginid, id);
        if(img.isPresent())
        {
        
        
        	CloseableHttpClient httpClient = HttpClients.createDefault();
        	HttpDelete httpPostRequest = new HttpDelete("https://api.imgur.com/3/image/" +img.get().getDeletehash());
        	httpPostRequest.setHeader("Authorization", "Client-ID 56b72718b07a454");
    		HttpResponse response =httpClient.execute(httpPostRequest );
        	int status=response.getStatusLine().getStatusCode();
        	System.out.println("returned status code is" +status);
        	imgrepo.delete(img.get());
    		return 200;
    	
        }
        else
        {
        	return 204;
        }

		

    }
	public Map<String,String> uploadimage(MultipartFile multipartFile) throws IOException {
	
			File file = new File("src/main/resources/targetFile.tmp");
			try (OutputStream os = new FileOutputStream(file)) {
		    os.write(multipartFile.getBytes());
			}
			byte[] fileContent = FileUtils.readFileToByteArray(file);
			String encodedString = Base64.getEncoder().encodeToString(fileContent);
		
			CloseableHttpClient httpClient = HttpClients.createDefault();
	        HttpPost httpPostRequest = new HttpPost("https://api.imgur.com/3/image");
	        httpPostRequest.setHeader("Authorization", "Client-ID 56b72718b07a454");
	        
	        List<NameValuePair> params = new ArrayList<NameValuePair>();
	
	        params.add(new BasicNameValuePair("image", encodedString));
	     
	        //int status = -1;
	        final Map<String, String> map = new HashMap();
	        map.put("status", "-1");
	        
	        try
	        {
	            httpPostRequest.setEntity(new UrlEncodedFormEntity(params));
	          
	            ResponseHandler response=   new ResponseHandler<String>() {

				@Override
				public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
					// TODO Auto-generated method stub
					int status=-1;
					status=response.getStatusLine().getStatusCode();
				
					map.put("status", String.valueOf(status));
					 if (status >= 200 && status < 300) {
					HttpEntity entity = response.getEntity();
		            Header headerEncoding = response.getEntity().getContentEncoding();
		            Charset enocodedCharset = (headerEncoding == null) ? StandardCharsets.UTF_8 : Charsets.toCharset(headerEncoding.toString());
		            String jsonResponse = EntityUtils.toString(entity, enocodedCharset);
		            JSONObject jsonObject;
					try {
						jsonObject = new JSONObject(jsonResponse);
						JSONObject dataObject = (JSONObject) jsonObject.get("data");
					
					    
					    Optional<userProfile> user=userRepository.findByLoginId(SecurityContextHolder.getContext().getAuthentication().getName());
						userImages userimg= new userImages();
						userimg.setUser(user.get());
						String filename=StringUtils.cleanPath(multipartFile.getOriginalFilename());
						userimg.setImageName(filename);
					    long millis=System.currentTimeMillis();  
					    java.sql.Date date=new java.sql.Date(millis); 
						userimg.setImageUploaded(date );
						userimg.setId((String) dataObject.get("id"));
						userimg.setLink((String) dataObject.get("link"));
						userimg.setDeletehash((String) dataObject.get("deletehash"));
						imgrepo.save(userimg);
						map.put("link", (String) dataObject.get("link"));

					} catch (Exception e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					}
					
					 
		            
					return null ;
				}
			};
	            
			httpClient.execute(httpPostRequest, response );
	            
	            
	        }
	        catch (Exception e)
	        {
	        	e.printStackTrace();
	        } 
	        
       return map;
	}
}
