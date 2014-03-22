package com.hackathon.damm.beermeup.locate;

import java.util.ArrayList;
import java.util.List;

import com.google.android.glass.app.Card;
import com.google.android.glass.app.Card.ImageLayout;
import com.google.android.glass.touchpad.Gesture;
import com.google.android.glass.touchpad.GestureDetector;
import com.google.android.glass.widget.CardScrollAdapter;
import com.google.android.glass.widget.CardScrollView;
import com.hackathon.damm.beermeup.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public class ShowBeerCards extends Activity{
	final static String  TAG ="beermeup";
	private List<Card> mCards;
	private List<Beer> beerList;
	private List<BeerMan> beerManList;
	private CardScrollView mCardScrollView;
	
	private GestureDetector mGestureDetector;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initBeerList();
		initBeerManList();
		createCards();
		
		mCardScrollView = new CardScrollView(this);
        ExampleCardScrollAdapter adapter = new ExampleCardScrollAdapter();
        mCardScrollView.setAdapter(adapter);
        mCardScrollView.activate();
        setContentView(mCardScrollView);
        
        mCardScrollView.setClickable(Boolean.TRUE);
        mGestureDetector = createGestureDetector(this);
		
	}
	
	

	private void initBeerList(){
		Beer normal = new Beer(BeerType.NORMAL, "Normal", R.drawable.cerveza_normal);
		beerList.add(normal);
		
		Beer sinAlcohol = new Beer(BeerType.FREE, "Sin alcohol", R.drawable.cerveza_sin_alcohol);
		beerList.add(sinAlcohol);
		
		Beer paraCeliacos = new Beer(BeerType.DAURA, "Apta para celiacos", R.drawable.cerveza_para_celiacos);
		beerList.add(paraCeliacos);
	}
	
	private void initBeerManList() {
		List<BeerType> beerTypeListWithAlcohol = new ArrayList<BeerType>();
		beerTypeListWithAlcohol.add(BeerType.NORMAL);

		List<BeerType> beerTypeListWithNoAlcohol = new ArrayList<BeerType>();
		beerTypeListWithNoAlcohol.add(BeerType.FREE);
		
		List<BeerType> beerTypeListDaura = new ArrayList<BeerType>();
		beerTypeListDaura.add(BeerType.DAURA);
		
		BeerMan beerMan1 = new BeerMan(new Position(40.386893,-3.630135), beerTypeListWithAlcohol);
		beerManList.add(beerMan1);
		
		BeerMan beerMan2 = new BeerMan(new Position(40.390795,-3.627318), beerTypeListWithNoAlcohol);
		beerManList.add(beerMan2);
		
		BeerMan beerMan3 = new BeerMan(new Position(40.389819,-3.625275), beerTypeListDaura);
		beerManList.add(beerMan3);
	}
	
	private void createCards(){
		mCards = new ArrayList<Card>(beerList.size());
		for (Beer beer: beerList){
			Card card = new Card(this);
			card.setImageLayout(ImageLayout.LEFT);
			card.addImage(beer.getBeerImageId());
			card.setText(beer.getName());
	        mCards.add(card);
		}
	}
	
	private void processTAP(){
		int cardPos = mCardScrollView.getSelectedItemPosition();
		Beer beer = beerList.get(cardPos);
		BeerMan beerMan = findBeerManForBeer(beer);

		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setData(Uri.parse("google.navigation:q=" + beerMan.getPosition().x + "," + beerMan.getPosition().y));
		startActivity(intent);
	}
	
	private BeerMan findBeerManForBeer(Beer beer) {
		for (BeerMan beerMan: beerManList){
			for (BeerType beerType: beerMan.getBeerTypes()){
				if (beerType.equals(beer.getBeerType())){
					return beerMan;
				}
			}
		}
		return null;
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
