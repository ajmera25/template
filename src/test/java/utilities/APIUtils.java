package utilities;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class APIUtils {

	
	public String getMaxImpressionTweetId() {
		int MaxImpression =0;
		String tweetId = null;
		try {
			OkHttpClient client = new OkHttpClient();
			
			HttpUrl url = new HttpUrl.Builder()
					.scheme("https")
					.host("api.twitter.com")
					.addPathSegments("/1.1/statuses/user_timeline.json")
					.addQueryParameter("screen_name","stepin_forum")
					.build();
			
			Request request = new Request.Builder()
				      .url(url)
				      .build();
			
			 Response response = client.newCall(request).execute();
			// JSONObject object = new JSONObject(response.body().string());
			 JSONArray array = new JSONArray(response.body().string());
			 HashMap<String,Integer> tweetImpressions = new HashMap<String,Integer>();
			 System.out.println(array);
			 for(int i=0;i<array.length();i++) {
				 JSONObject object = array.getJSONObject(i);
				 tweetImpressions.put(object.get("id").toString(), object.getInt("retweet_count") + object.getInt("favorite_count"));
			 }
			 System.out.println(tweetImpressions);
			 
			 for(String str :tweetImpressions.keySet()) {
				 int currentImpression = tweetImpressions.get(str);
				 if(MaxImpression<currentImpression) {
					 MaxImpression = currentImpression;
					 tweetId = str;
					 
				 }
			 }
			 System.out.println(tweetId  +  " " + MaxImpression);
	}catch(Exception e){
		e.printStackTrace();
	}
		return tweetId;
	}
	}
