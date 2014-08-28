package org.xwalk.demo.multiprofile;

import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity implements OnClickListener {

	private EditText mInputAcc;
	private Button mBtnLogin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		mInputAcc = (EditText) findViewById(R.id.input_acc);
		mBtnLogin = (Button) findViewById(R.id.btn_login);
		mBtnLogin.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		String acc = mInputAcc.getText().toString();
		if (acc.isEmpty()) {
			Toast.makeText(this, "Account is empty!", Toast.LENGTH_LONG).show();
			return;
		}
		ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
		List<RunningAppProcessInfo> processes = am.getRunningAppProcesses();
		for (RunningAppProcessInfo process : processes) {
			if (XWalkActivity.PROCESS_NAME.equals(process.processName)) {
				android.os.Process.killProcess(process.pid);
				break;
			}
		}
		Intent intent = new Intent(this, XWalkActivity.class);
		intent.putExtra(XWalkActivity.PROFILE_NAME, acc);
		startActivity(intent);
	}
}
