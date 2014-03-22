package com.hackathon.damm.beermeup.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
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

public class PlayActivity extends Activity {

	protected static final String TAG = "beermeup";
	private final String[] sonarResponses = new String[]{
			"Massive Attack",
			"Rudimental",
			"Bonobo",
			"James Holden"
	};
	private final String[] sonarSongs = new String[]{
			"In deepest hollow of our minds A system failure left behind And their necks crane As they turn to pray for rain And their necks crane...",
			"You know I said it's true I can feel the love Can you feel it too I can feel it ah-ah I can feel it ah-ah...",
			"We don’t need no truth, got planted now It grows on trees I don’t let it or 8 side loose There’s no saint and there’s no sinner...",
			"Don't you know it's gone too wrong Early warning How could it have come to this We're dying trying  It's a long way down... "
	};
	
	private Random random = new Random(System.currentTimeMillis());
	private int actual;
	private GestureDetector mGestureDetector;
	private final String footer = "Estrella Damm play";
	private CardScrollView mCardScrollView;
	private Context context;
	private List<Card> mCards; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		android.os.Debug.waitForDebugger();
		Log.i(TAG, "Entra a PLay");
	}
	
	
	@Override
	protected void onResume() {
		super.onResume();
		actual = random.nextInt(4);
		
		Card card1 = new Card(this);
        card1.setText(sonarSongs[actual]);
        card1.setFootnote(footer);
		
		View card1View = card1.toView();
        setContentView(card1View);
        Log.i(TAG, "SEt de vista principl");
        
        new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					Log.e(TAG, "error" ,e);
				}
				changeView();
			}
		});
	}
	
	private void changeView(){
		context = getApplicationContext();
		
		Log.i(TAG, "Cambiar vistas");
		runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				
				mCards = new ArrayList<Card>(4);
				for(String response : sonarResponses){
					Card card = new Card(context);
			        card.setText(response);
			        card.setFootnote(footer);
			        mCards.add(card);
				}
				
				
				mCardScrollView = new CardScrollView(context);
		        ExampleCardScrollAdapter adapter = new ExampleCardScrollAdapter();
		        mCardScrollView.setAdapter(adapter);
		        mCardScrollView.activate();
		        setContentView(mCardScrollView);
		        
		        mCardScrollView.setClickable(Boolean.TRUE);
		        
		        mGestureDetector = createGestureDetector(context);
		        
		        Log.i(TAG, "vistas cambiadas");
			}
		});
	}
	
	
	private void savePoints(int poinst){
		//getSharedPreferences("beermeup.play", )
	}
	
	private int readPoints(){
		int points = 0;
		return points;
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
	
	
	@Override
	public boolean onGenericMotionEvent(MotionEvent event) {
		Log.i(TAG, "onGenericMotionEvent with event:"+event.getAction());
		if (mGestureDetector != null) {
            return mGestureDetector.onMotionEvent(event);
        }
        return false;
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
		Log.i(TAG, "Tab processed in position:"+ cardPos);
		Card card = null;
        if(cardPos == actual){
        	card = new Card(context);
	        card.setText("Premio!! Código regalo: JXDHU");
	        card.setFootnote(footer+" Points:" +readPoints());
        }else{
        	card = new Card(context);
	        card.setText("Incorrecto!!");
	        card.setFootnote(footer+" Points:" +readPoints());
        }
        setContentView(card.toView());
        Log.i(TAG, "Setted view");
	}
}
