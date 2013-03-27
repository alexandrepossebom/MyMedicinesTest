package com.possebom.mymedicines.test;

import android.graphics.Point;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.view.Display;

import com.jayway.android.robotium.solo.Solo;
import com.possebom.mymedicines.MedicineListActivity;
import com.possebom.mymedicines.R;


public class TestMain extends ActivityInstrumentationTestCase2<MedicineListActivity> {

	private Solo solo;
	private String item;

	public TestMain() {
		super(MedicineListActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
		item = String.format("Item %03d", 1);
	}

	public void testAddItem(){
		Log.d("Teste","Creating item : "+item);
		solo.clickOnActionBarItem(R.id.menu_add);
		solo.enterText(0, item);
		solo.clickOnActionBarItem(R.id.menu_save);
		assertTrue(solo.searchText(item));
	}

	public void testRemoveItem(){
		Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int screenWidth = size.x;
		int screenHeight = size.y;
		
		int fromX = (screenWidth/2) - (screenWidth/3);
		int toX = (screenWidth/2) + (screenWidth/3);

		int fromY = screenHeight/5;
		int toY = fromY;
		
		solo.drag(fromX, toX, fromY, toY, 1);
		assertFalse(solo.searchText(item));
	}

	@Override
	protected void tearDown() throws Exception{
		solo.finishOpenedActivities();
	}

}
