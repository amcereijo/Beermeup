package com.hackathon.damm.beermeup.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
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
import com.hackathon.damm.beermeup.dto.EventDto;

public class EventDetailActivity extends Activity{
	final static String TAG ="beermeup";
	private List<Card> mCards;
	private List<EventDto> eventList;
	
	private CardScrollView mCardScrollView;
	private final String footText = "Detalles del concierto";
	private GestureDetector mGestureDetector;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
//		android.os.Debug.waitForDebugger();
		
		loadEventsData();
		createCards();
		
		mCardScrollView = new CardScrollView(this);
        ExampleCardScrollAdapter adapter = new ExampleCardScrollAdapter();
        mCardScrollView.setAdapter(adapter);
        mCardScrollView.activate();
        setContentView(mCardScrollView);
        
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
        EventDto dailyInfoDto = eventList.get(cardPos);
        
//        Intent dailyProjectIntent = new Intent(this, DailyProjectActivity.class);
//        dailyProjectIntent.putExtra(EventDto.DAYLI_INFO_KEY, dailyInfoDto);	
//        
//        startActivity(dailyProjectIntent);
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
	
	private void loadEventsData() {
		eventList = new ArrayList<EventDto>();
		
		EventDto eventDto = new EventDto();
		eventDto.setTitle("Concierto1");
		List<String> bands = new ArrayList<String>(3);
		bands.add("Grupo 1");
		bands.add("Grupo 2");
		eventDto.setBands(bands);
		eventList.add(eventDto);
		
		eventDto = new EventDto();
		eventDto.setTitle("Concierto2");
		bands = new ArrayList<String>(3);
		bands.add("Grupo 1");
		bands.add("Grupo 2");
		eventDto.setBands(bands);
		eventList.add(eventDto);
		
		eventDto = new EventDto();
		eventDto.setTitle("Concierto3");
		bands = new ArrayList<String>(3);
		bands.add("Grupo 1");
		bands.add("Grupo 2");
			eventDto.setBands(bands);
			eventList.add(eventDto);
		
		eventDto = new EventDto();
		eventDto.setTitle("Concierto4");
		bands = new ArrayList<String>(3);
		bands.add("Grupo 1");
		bands.add("Grupo 2");
		eventDto.setBands(bands);
		eventList.add(eventDto);
	}


	private void createCards() {
        mCards = new ArrayList<Card>(eventList.size());
        for(EventDto daily : eventList){
	        Card card = new Card(this);
	        card.setText(daily.getTitle());
	        card.setFootnote(footText);
	        mCards.add(card);
        }
    }
	
}
