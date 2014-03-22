package com.hackathon.damm.beermeup.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.glass.app.Card;
import com.google.android.glass.widget.CardScrollAdapter;
import com.google.android.glass.widget.CardScrollView;
import com.hackathon.damm.beermeup.R;

public class DrinkNowActivity extends Activity {

	final static String  TAG ="beermeup";
	
	private List<Card> mCards;
	private CardScrollView mCardScrollView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Card card1 = new Card(this);
        card1.setText("Cargando actividad actual..");
        card1.setFootnote("Estrella Damm");
		
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
				showActivities();
			}			
		}).start();
	}
	Context context;
	private void showActivities() {
		mCards = new ArrayList<Card>(3);

			Card card = new Card(this);
	        card.setText("MASSIVE ATTACK\nEscenario Estrella Dam\nViernes 21\n20:00-21:30");
	        card.setFootnote("Estrella Damm");
	        card.addImage(R.drawable.massiveattack);
	        mCards.add(card);
	        
	        Card card1 = new Card(this);
	        card1.setText("WOOD KID\nEscenario Sonar\nViernes 21\n20:00-21:15");
	        card1.setFootnote("Estrella Damm");
	        card1.addImage(R.drawable.jused);
	        mCards.add(card1);
	        
	        Card card2 = new Card(this);
	        card2.setText("RICHIE HAWTIN\nEscenario DJ\nViernes 21\n20:00-22:00");
	        card2.setFootnote("Estrella Damm");
	        card2.addImage(R.drawable.richiehawkin);
	        mCards.add(card2);
	        context = getApplicationContext();
		runOnUiThread(new Runnable() {
			
			@Override
			public void run() {

				mCardScrollView = new CardScrollView(context);
		        ExampleCardScrollAdapter adapter = new ExampleCardScrollAdapter();
		        mCardScrollView.setAdapter(adapter);
		        mCardScrollView.activate();
		        setContentView(mCardScrollView);
		        
		        mCardScrollView.setClickable(Boolean.TRUE);
			}
		});
        

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
