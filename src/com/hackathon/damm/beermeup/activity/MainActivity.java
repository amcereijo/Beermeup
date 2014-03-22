package com.hackathon.damm.beermeup.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.glass.app.Card;
import com.google.android.glass.touchpad.Gesture;
import com.google.android.glass.touchpad.GestureDetector;
import com.google.android.glass.widget.CardScrollAdapter;
import com.google.android.glass.widget.CardScrollView;
import com.google.glass.input.VoiceInputHelper;
import com.google.glass.input.VoiceListener;
import com.google.glass.logging.FormattingLogger;
import com.google.glass.logging.FormattingLoggers;
import com.google.glass.voice.VoiceCommand;
import com.google.glass.voice.VoiceConfig;
import com.hackathon.damm.beermeup.locate.ShowBeerCards;

public class MainActivity extends Activity {

	final static String TAG ="beermeup";
	
	private CardScrollView mCardScrollView;
	private final String footText = "Estrella Damm";
	private GestureDetector mGestureDetector;
	private List<Card> mCards;
	
	private VoiceInputHelper mVoiceInputHelper;
    private VoiceConfig mVoiceConfig;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		mCards = new ArrayList<Card>(3);
		
		Card cardNow = new Card(this);
		cardNow.setText("Pasando ahora");
		cardNow.setFootnote(footText);
        mCards.add(cardNow);
		
		Card cardPlay = new Card(this);
		cardPlay.setText("Juega");
		cardPlay.setFootnote(footText);
        mCards.add(cardPlay);
		
        Card cardInfo = new Card(this);
        cardInfo.setText("Información");
        cardInfo.setFootnote(footText);
        mCards.add(cardInfo);
        
        Card cardLocation = new Card(this);
        cardLocation.setText("Localiza Cerveza");
        cardLocation.setFootnote(footText);
        mCards.add(cardLocation);
        
		mCardScrollView = new CardScrollView(this);
        ExampleCardScrollAdapter adapter = new ExampleCardScrollAdapter();
        mCardScrollView.setAdapter(adapter);
        mCardScrollView.activate();
        setContentView(mCardScrollView);
        
        String[] items = {"back","next", "check", "parent"};
        mVoiceConfig = new VoiceConfig("MyVoiceConfig", items);
        mVoiceInputHelper = new VoiceInputHelper(this, new MyVoiceListener(mVoiceConfig),
                VoiceInputHelper.newUserActivityObserver(this));
        
        mCardScrollView.setClickable(Boolean.TRUE);
        
        mGestureDetector = createGestureDetector(this);
	}
	
	@Override
	public boolean onGenericMotionEvent(MotionEvent event) {
		
		if (mGestureDetector != null) {
            return mGestureDetector.onMotionEvent(event);
        }
        return false;
	}
	
	@Override
    protected void onResume() {
        super.onResume();
        mVoiceInputHelper.addVoiceServiceListener();
    }
	
	@Override
	protected void onPause() {
		super.onPause();
		mVoiceInputHelper.removeVoiceServiceListener();
	}
	
	public class MyVoiceListener implements VoiceListener {
        
		protected final VoiceConfig voiceConfig;

        public MyVoiceListener(VoiceConfig voiceConfig) {
            this.voiceConfig = voiceConfig;
        }

        @Override
        public void onVoiceServiceConnected() {
            mVoiceInputHelper.setVoiceConfig(mVoiceConfig, false);
        }

        @Override
        public void onVoiceServiceDisconnected() {

        }

        @Override
        public VoiceConfig onVoiceCommand(VoiceCommand vc) {
            String recognizedStr = vc.getLiteral();
            Log.i(TAG, "Recognized text: "+recognizedStr);
            
            if("exit".equals(recognizedStr)){
            	finish();
            }else if("next".equals(recognizedStr)){
            	int max = mCards.size();
            	int actual = mCardScrollView.getSelectedItemPosition();
            	if(actual<max-1){
            		mCardScrollView.setSelection(actual+1);
            	}
            }else if("back".equals(recognizedStr)){
            	int actual = mCardScrollView.getSelectedItemPosition();
            	if(actual>0){
            		mCardScrollView.setSelection(actual-1);
            	}
            }else if("check".equals(recognizedStr)){
            	processTAP();
            }else if("parent".equals(recognizedStr)){
            	finish();
            }
            
            return voiceConfig;
        }
        @Override
        public FormattingLogger getLogger() {
            return FormattingLoggers.getContextLogger();
        }

        @Override
        public boolean isRunning() {
            return true;
        }

        @Override
        public boolean onResampledAudioData(byte[] arg0, int arg1, int arg2) {
            return false;
        }

        @Override
        public boolean onVoiceAmplitudeChanged(double arg0) {
            return false;
        }

        @Override
        public void onVoiceConfigChanged(VoiceConfig arg0, boolean arg1) {

        }
    }
	
	
	private GestureDetector createGestureDetector(Context context) {
	    GestureDetector gestureDetector = new GestureDetector(context);
	        //Create a base listener for generic gestures
	        gestureDetector.setBaseListener( new GestureDetector.BaseListener() {
	            @Override
	            public boolean onGesture(Gesture gesture) {
	            	
	            	switch (gesture) {
	            	case TAP:
	                    Log.e(TAG,"TAP called.");
	                    processTAP();
	                    break;
	                case LONG_PRESS:
	                    Log.e(TAG,"LONG_PRESS called.");
	                    processTAP();
	                    return true;
	                case SWIPE_DOWN:
	                    Log.e(TAG,"SWIPE_DOWN called.");
	                    return true;
	                case SWIPE_LEFT:
	                    Log.e(TAG,"SWIPE_LEFT called.");
	                    return true;
	                case SWIPE_RIGHT:
	                    Log.e(TAG,"SWIPE_RIGHT called.");
	                    return true;
	                case SWIPE_UP:
	                    Log.e(TAG,"SWIPE_UP called.");
	                    return true;
	                case THREE_LONG_PRESS:
	                    Log.e(TAG,"THREE_LONG_PRESS called.");
	                    return true;
	                case THREE_TAP:
	                    Log.e(TAG,"THREE_TAP called.");
	                    return true;
	                case TWO_LONG_PRESS:
	                    Log.e(TAG,"TWO_LONG_PRESS called.");
	                    return true;
	                case TWO_SWIPE_DOWN:
	                    Log.e(TAG,"TWO_SWIPE_DOWN called.");
	                    return true;
	                case TWO_SWIPE_LEFT:
	                    Log.e(TAG,"TWO_SWIPE_LEFT called.");
	                    return true;
	                case TWO_SWIPE_RIGHT:
	                    Log.e(TAG,"TWO_SWIPE_RIGHT called.");
	                    return true;
	                case TWO_SWIPE_UP:
	                    Log.e(TAG,"TWO_SWIPE_UP called.");
	                    return true;
	                case TWO_TAP:
	                    Log.e(TAG,"TWO_TAP called.");
	                    return true;
	  
					}
	            	
	                return false;
	            }
	        });
	        gestureDetector.setFingerListener(new GestureDetector.FingerListener() {
	            @Override
	            public void onFingerCountChanged(int previousCount, int currentCount) {
	              // do something on finger count changes
	            }
	        });
	        gestureDetector.setScrollListener(new GestureDetector.ScrollListener() {
	            @Override
	            public boolean onScroll(float displacement, float delta, float velocity) {
	                // do something on scrolling
	            	return true;
	            }
	        });
	        return gestureDetector;
	    }
	
	protected void processTAP() {
		int cardPos = mCardScrollView.getSelectedItemPosition();
		Intent intent = null;
        switch (cardPos) {
        case 0:
			 intent = new Intent(this, DrinkNowActivity.class);
			break;
        case 1:
			 intent = new Intent(this, PlayActivity.class);
			break;
		case 3:
			intent = new Intent(this, ShowBeerCards.class);
			break;
		case 2: 
		intent = new Intent(this, BeerMeUpMainActivity.class);
		default:
			break;
		}
        startActivity(intent);
	}
	
	private class ExampleCardScrollAdapter extends CardScrollAdapter {

        @Override
        public int findIdPosition(Object id) {
        	Log.i(TAG, "findIdPosition");
            return -1;
        }

        @Override
        public int findItemPosition(Object item) {
            return mCards.indexOf(item);
        }

        @Override
        public int getCount() {
            return mCards.size();
        }

        @Override
        public Object getItem(int position) {
            return mCards.get(position);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return mCards.get(position).toView();
        }
    }

	
}
