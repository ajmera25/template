package utilities;

import java.io.File;
import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class APIUtils {

	public static String getCall(){
		String videoName = null;
		try {
			OkHttpClient client = new OkHttpClient();
			
			HttpUrl url = new HttpUrl.Builder()
					.scheme("https")
					.host("")
					.addPathSegments("")
					.build();
			
			Request request = new Request.Builder()
				      .url(url)
				      .build();
			
			 Response response = client.newCall(request).execute();
			 System.out.println(response.body().string());
	}catch(Exception e){
		e.printStackTrace();
	}
		return videoName;
}
	
	public static void postCall(String url){
	try{
		OkHttpClient client = new OkHttpClient();
		
	    RequestBody requestBody = null;

		Request request = new Request.Builder().url("")
	                .post(requestBody).build();
	 
	        Response response = client.newCall(request).execute();
	}catch(Exception e){
		e.printStackTrace();
		
	}
}
	public static void uploadFile() throws IOException{
	OkHttpClient client = new OkHttpClient();
	
	HttpUrl url = new HttpUrl.Builder()
			.scheme("https")
			.host("")
			.addPathSegment("")
			.build();
	
	
	File file = new File(System.getProperty("user.dir") + "/src/test/resources/testdata/" + "FileName");
	
	RequestBody requestBody = new MultipartBody.Builder()
			.setType(MultipartBody.FORM)
            .addFormDataPart("txt", file.getName(), RequestBody.create(MediaType.parse("application/json"), file))
            .build();
	
	Request request = new Request.Builder()
		      .url(url)
		      .post(requestBody)
		      .build();
	
	 Response response = client.newCall(request).execute();
	 System.out.println(response.body().string());
}
}
