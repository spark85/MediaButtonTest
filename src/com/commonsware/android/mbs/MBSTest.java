/***
	Copyright (c) 2011 CommonsWare, LLC
	
	Licensed under the Apache License, Version 2.0 (the "License"); you may
	not use this file except in compliance with the License. You may obtain
	a copy of the License at
		http://www.apache.org/licenses/LICENSE-2.0
	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
*/

package com.commonsware.android.mbs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

public class MBSTest extends Activity {
	private View button=null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		button=findViewById(R.id.button);
	}
	
	public void startSending(View v) {
		v.postDelayed(onDown, 5000);
		v.setEnabled(false);
	}

	private Runnable onDown=new Runnable() {
		public void run() {
			Intent i=new Intent(Intent.ACTION_MEDIA_BUTTON);
				
			i.putExtra(Intent.EXTRA_KEY_EVENT,
								 new KeyEvent(KeyEvent.ACTION_DOWN,
															KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE));
				
			sendOrderedBroadcast(i, null);
			
			button.postDelayed(onUp, 100);
		}
	};

	private Runnable onUp=new Runnable() {
		public void run() {
			Intent i=new Intent(Intent.ACTION_MEDIA_BUTTON);
				
			i.putExtra(Intent.EXTRA_KEY_EVENT,
								 new KeyEvent(KeyEvent.ACTION_UP,
															KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE));
				
			sendOrderedBroadcast(i, null);
			
			button.postDelayed(onDown, 5000);
		}
	};
}
