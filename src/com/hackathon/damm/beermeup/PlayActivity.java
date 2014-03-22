package com.hackathon.damm.beermeup;

import android.app.Activity;
import android.os.Bundle;

import com.google.android.glass.app.Card;

public class PlayActivity extends Activity {

	private final String[] sonarResponses = new String[]{
			"Massive Attack",
			"Rudimental",
			"Bonobo",
			"James Holden"
	};
	private final String[] sonarSongs = new String[]{
			"In deepest hollow of our minds A system failure left behind And their necks crane As they turn to pray for rain And their necks crane",
			"You know I said it's true I can feel the love Can you feel it too I can feel it ah-ah I can feel it ah-ah",
			"We don’t need no truth, got planted now It grows on trees I don’t let it or 8 side loose There’s no saint and there’s no sinner Done more dirt than these",
			"Don't you know it's gone too wrong Early warning How could it have come to this We're dying trying  It's a long way down "
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Card card1 = new Card(this);
        card1.setText("");
        card1.setFootnote("Estrella Damm play");
        
        
        
	}
}
