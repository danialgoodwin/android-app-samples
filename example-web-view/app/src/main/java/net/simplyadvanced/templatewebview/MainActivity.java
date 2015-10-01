package net.simplyadvanced.templatewebview;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		setupWebView();
	}
	
	@SuppressLint("SetJavaScriptEnabled")
	private void setupWebView() {
		WebView wv = (WebView) findViewById(R.id.webview_main);
		
		WebSettings webSettings = wv.getSettings();
		webSettings.setBuiltInZoomControls(false); // If true, 
		webSettings.setJavaScriptEnabled(true); // Only use this if JavaScript is needed. By default, it is false.
//		webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
		wv.setWebViewClient(new MyWebViewClient()); // Ensures URL box doesn't show.
		wv.loadUrl("http://simplyadvanced.net/games/flappyfishes/");
	}
	
	private class MyWebViewClient extends WebViewClient {
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			return false;
		}
	}
			
	

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.main, menu);
//		return true;
//	}

}
