package com.example.json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;

import android.widget.TextView;

public class MainActivity extends Activity {
	
	
	TextView a;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		a=(TextView)findViewById(R.id.tv);
		new ReadJSONFeedTask()
				.execute("http://extjs.org.cn/extjs/examples/grid/survey.html");
		}


	private class ReadJSONFeedTask extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... urls) {
			// TODO Auto-generated method stub
			return readJSONFeed(urls[0]);
		}
		
		

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			try{
				JSONArray ja = new  JSONArray(result);
				for(int i=0;i<ja.length();i++){
					JSONObject jo = ja.getJSONObject(i);
					String app;
					app = jo.getString("appeId")+ " "+ "-"+" " + jo.getString("inputTime")+ " "+ "-"+" " + jo.getString("appeId")+"\n";
					a.append(app);
				}
			}catch(Exception e){
			e.printStackTrace();
			}
			
			
			
		}



		private String readJSONFeed(String string) {
			StringBuilder sb = new StringBuilder();
			HttpClient hc  = new DefaultHttpClient();
			HttpGet hg = new HttpGet(string);
			try{
				
				HttpResponse hr = hc.execute(hg);
				StatusLine sl = hr.getStatusLine();
				int statuscode  = sl.getStatusCode();
				if(statuscode ==200)
				{
					HttpEntity he = hr.getEntity();
					InputStream is = he.getContent();
					BufferedReader br = new  BufferedReader(new InputStreamReader(is));
					String line;
					while((line =br.readLine())!=null){
						sb.append(line);
					}
				}
				else
				{
					Log.e("mmmmmmm","ssss");
				}
			}catch(ClientProtocolException e)
			{
				e.printStackTrace();
			}catch(IOException e)
			{
				e.printStackTrace();
			
			
			
		}return sb.toString();
	}
	}
}

	


