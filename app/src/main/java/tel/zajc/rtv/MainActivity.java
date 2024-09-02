package tel.zajc.rtv;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.*;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.*;
import android.webkit.*;

public class MainActivity extends Activity {

	private WebView webView;

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		webView = (WebView) findViewById(R.id.web);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setSupportZoom(false);
		webView.getSettings().setDomStorageEnabled(true);
		webView.setBackgroundColor(Color.BLACK);
		webView.setWebViewClient(new WebViewClient() {
			@Override
			@SuppressLint("WebViewClientOnReceivedSslError")
			public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
				handler.proceed();
			}
		});

		webView.setWebChromeClient(new WebChromeClient() {
			@Override
			public Bitmap getDefaultVideoPoster() {
				Bitmap bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.RGB_565);
				Canvas canvas = new Canvas(bitmap);
				// Use whatever color you want here. You could probably use a transparent color
				canvas.drawARGB(255, 0, 0, 0);
				return bitmap;
			}
		});

		webView.loadUrl("https://androidtv.rtvslo.si/?emulator=true");
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
			webView.goBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

}
