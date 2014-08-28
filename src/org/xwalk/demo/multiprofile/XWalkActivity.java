package org.xwalk.demo.multiprofile;

import org.xwalk.core.XWalkPreferences;
import org.xwalk.core.XWalkView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class XWalkActivity extends Activity {
	static final String PROFILE_NAME = "xwalk-extra-profile-name";
	static final String PROCESS_NAME = "org.xwalk.demo.multiprofile.xwalkview";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		if (intent.hasExtra(PROFILE_NAME)) {
			XWalkPreferences.setValue(XWalkPreferences.PROFILE_NAME, intent.getStringExtra(PROFILE_NAME));
		}
		setContentView(R.layout.activity_xwalk);
		XWalkView view = (XWalkView) findViewById(R.id.xwalkview);
		view.load("http://people.w3.org/mike/localstorage.html", null);
	}
}
