package uk.co.joestrong.speakingclock;

import java.util.Calendar;

import uk.co.joestrong.speakingclock.R;

import android.R.string;
import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.format.Time;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void tellTime(View view){
    	MediaPlayer mp = MediaPlayer.create(this, R.raw.intro);
    	mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
    		public void onCompletion(MediaPlayer mp){
    			tellHour();
    		}
    	});
    	mp.start();
    }
    
    protected void tellHour(){
    	Time time = new Time();
    	time.setToNow();
    	int hours = time.hour;
    	final int minutes = time.minute;
    	if(hours >= 12){
    		hours -= 12;
    	}
    	int clip = getNumberClip(hours);
    	MediaPlayer mp = MediaPlayer.create(this, clip);
    	mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
    		public void onCompletion(MediaPlayer mp){
    			if(minutes == 0){
    				tellOclock();
    			}else{
    				tellMinute(minutes);
    			}
    		}
    	});
    	mp.start();
    }
    
    protected void tellMinute(Integer minutes){
    	if(minutes < 20){
    		int clip = 0;
    		clip = getNumberClip(minutes);
    		MediaPlayer mp = MediaPlayer.create(this, clip);
        	mp.start();
    	}else{
    		int clip = 0;
    		Integer tens = Integer.parseInt(minutes.toString().substring(0, 1) + "0");
    		final Integer singles = minutes - tens;
    		clip = getNumberClip(tens);
    		MediaPlayer mp = MediaPlayer.create(this, clip);
    		mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
    			public void onCompletion(MediaPlayer mp){
    				if(singles > 0){
    					tellMinute(singles);
    				}
    			}
    		});
        	mp.start();
    	}
    }
    
    protected void tellOclock(){
    	MediaPlayer mp = MediaPlayer.create(this, R.raw.oclock);
    	mp.start();
    }
    
    protected int getNumberClip(int hours){
    	int clip = 0;
    	switch(hours){
    	case 0:
    		clip = R.raw.twelve;
    		break;
    	case 1:
    		clip = R.raw.one;
    		break;
    	case 2:
    		clip = R.raw.two;
    		break;
    	case 3:
    		clip = R.raw.three;
    		break;
    	case 4:
    		clip = R.raw.four;
    		break;
    	case 5:
    		clip = R.raw.five;
    		break;
    	case 6:
    		clip = R.raw.six;
    		break;
    	case 7:
    		clip = R.raw.seven;
    		break;
    	case 8:
    		clip = R.raw.eight;
    		break;
    	case 9:
    		clip = R.raw.nine;
    		break;
    	case 10:
    		clip = R.raw.ten;
    		break;
    	case 11:
    		clip = R.raw.eleven;
    		break;
    	case 12:
    		clip = R.raw.twelve;
    		break;
    	case 13:
    		clip = R.raw.thirteen;
    		break;
    	case 14:
    		clip = R.raw.fourteen;
    		break;
    	case 15:
    		clip = R.raw.fifteen;
    		break;
    	case 16:
    		clip = R.raw.sixteen;
    		break;
    	case 17:
    		clip = R.raw.seventeen;
    		break;
    	case 18:
    		clip = R.raw.eighteen;
    		break;
    	case 19:
    		clip = R.raw.nineteen;
    		break;
    	case 20:
    		clip = R.raw.twenty;
    		break;
    	case 30:
    		clip = R.raw.thirty;
    		break;
    	case 40:
    		clip = R.raw.fourty;
    		break;
    	case 50:
    		clip = R.raw.fifty;
    		break;
    	}
    	return clip;
    }
    
}
