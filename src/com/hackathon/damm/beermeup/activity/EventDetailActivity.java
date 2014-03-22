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
import com.google.glass.input.VoiceInputHelper;
import com.google.glass.input.VoiceListener;
import com.google.glass.logging.FormattingLogger;
import com.google.glass.logging.FormattingLoggers;
import com.google.glass.voice.VoiceCommand;
import com.google.glass.voice.VoiceConfig;
import com.hackathon.damm.beermeup.R;
import com.hackathon.damm.beermeup.dto.EventDto;

public class EventDetailActivity extends Activity{
	final static String TAG ="beermeup";
	private List<Card> mCards;
	private List<EventDto> eventList;
	
	private CardScrollView mCardScrollView;
	private final String footText = "Detalles del concierto";
	private GestureDetector mGestureDetector;
	
	private VoiceInputHelper mVoiceInputHelper;
    private VoiceConfig mVoiceConfig;
    
    EventDto eventDetailDto;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		eventDetailDto = (EventDto) getIntent().getExtras().get(EventDto.EVENT_INFO_KEY);
		
//		Card card1 = new Card(this);
//		card1.addImage(eventDetailDto.getEventImageId());
//        card1.setText(eventDetailDto.getBands().get(0) + "\n" + eventDetailDto.getBands().get(1));
//        card1.setFootnote(eventDetailDto.getTitle());
        
//        View card1View = card1.toView();
//        setContentView(card1View);
        
        String[] items = {"back","next"};
        mVoiceConfig = new VoiceConfig("MyVoiceConfig", items);
        mVoiceInputHelper = new VoiceInputHelper(this, new MyVoiceListener(mVoiceConfig),
                VoiceInputHelper.newUserActivityObserver(this));
		
		loadEventsData(eventDetailDto.getTitle());
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
	private ArrayList<String> getGrupos(String evento){
		if("Sonar".equals(evento)){
			return new ArrayList<String>(){{add("Massive Attack"); add("Richie Hawtin"); add("Plastikman");}};
		}
		if("POPARB".equals(evento)){
			return new ArrayList<String>(){{add("Espaldamaceta"); add("Pau Vallve"); add("Za!");}};
		}
		if("PICNICK ELECTRONIK".equals(evento)){
			return new ArrayList<String>(){{add("DERRICK CARTER"); add("EL GUINCHO"); add("JUS-ED");}};
		}
		return new ArrayList<String>();
	}
	
	private int getLogo(String grupo){
		if("Massive Attack".equals(grupo)) return R.drawable.massiveattack;
		if("Richie Hawtin".equals(grupo)) return R.drawable.richiehawkin;
		if("Plastikman".equals(grupo)) return R.drawable.plastikman;
		if("Espaldamaceta".equals(grupo)) return R.drawable.espaldamaceta;
		if("Pau Vallve".equals(grupo)) return R.drawable.pauvallve;
		if("Za!".equals(grupo)) return R.drawable.za;
		if("DERRICK CARTER".equals(grupo)) return R.drawable.derrickcarter;
		if("EL GUINCHO".equals(grupo)) return R.drawable.elguincho;
		if("JUS-ED".equals(grupo)) return R.drawable.jused;
		return R.drawable.jused;
	}
	
	private void loadEventsData(String title) {
		ArrayList<String> grupos = getGrupos(title);
		eventList = new ArrayList<EventDto>();
		
		EventDto eventDto = new EventDto();
		eventDto.setTitle(grupos.get(0));
		eventDto.setEventImageId(getLogo(grupos.get(0)));
		eventDto.setFooter(title);
		eventList.add(eventDto);
		
		eventDto = new EventDto();
		eventDto.setTitle(grupos.get(1));
		eventDto.setEventImageId(getLogo(grupos.get(1)));
		eventDto.setFooter(title);
		eventList.add(eventDto);
		
		eventDto = new EventDto();
		eventDto.setTitle(grupos.get(2));
		eventDto.setEventImageId(getLogo(grupos.get(2)));
		eventDto.setFooter(title);
		eventList.add(eventDto);
		
		eventDto = new EventDto();
		eventDto.setTitle("Concierto4");
		eventDto.setFooter(title);
		eventList.add(eventDto);
	}


	private void createCards() {
        mCards = new ArrayList<Card>(eventList.size());
        for(EventDto daily : eventList){
	        Card card = new Card(this);
	        card.addImage(daily.getEventImageId());
	        card.setText(daily.getTitle());
	        card.setFootnote(footText);
	        mCards.add(card);
        }
    }
	
	@Override
    protected void onResume() {
        super.onResume();
        mVoiceInputHelper.addVoiceServiceListener();
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
	
}
