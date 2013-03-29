package com.possebom.mymedicines.test;

import android.graphics.Point;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.view.Display;
import android.view.View;

import com.jayway.android.robotium.solo.Solo;
import com.possebom.mymedicines.MedicineListActivity;
import com.possebom.mymedicines.R;


public class TestMain extends ActivityInstrumentationTestCase2<MedicineListActivity> {

	private Solo solo;
	private String item;
	private View view;

	public TestMain() {
		super(MedicineListActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
		item = String.format("Item %03d", 1);
	}
	
	public void testInsertEmpty(){
		String text = solo.getString(R.string.alert_title_medicine);
		view = solo.getView(R.id.menu_add);
		solo.waitForView(view);
		solo.clickOnActionBarItem(R.id.menu_add);
		view = solo.getView(R.id.menu_save);
		solo.waitForView(view);
		solo.clickOnActionBarItem(R.id.menu_save);
		assertTrue("Title not found",solo.waitForText(text));
	}
	
	public void testBarcode(){
		String text = solo.getString(R.string.title_alert_barcodescanner);
		view = solo.getView(R.id.menu_add);
		solo.waitForView(view);
		solo.clickOnActionBarItem(R.id.menu_add);
		view = solo.getView(R.id.menu_barcode);
		solo.waitForView(view);
		solo.clickOnActionBarItem(R.id.menu_barcode);
		assertTrue("Title not found",solo.waitForText(text));
	}

	public void testAddItem(){
		view = solo.getView(R.id.menu_add);
		solo.waitForView(view);
		solo.clickOnActionBarItem(R.id.menu_add);
		view = solo.getView(R.id.menu_save);
		solo.waitForView(view);
		solo.enterText(0, item);
		solo.clickOnActionBarItem(R.id.menu_save);
		assertTrue(solo.searchText(item));
		solo.clickOnText(item);
		assertTrue(solo.searchText(solo.getString(R.string.validity)));
		solo.clickOnActionBarHomeButton();
		assertTrue(solo.searchText(item));
	}

	public void testRemoveItem(){
		solo.clickOnText(item);
		view = solo.getView(R.id.menu_delete);
		solo.waitForView(view);
		solo.clickOnActionBarItem(R.id.menu_delete);
		assertFalse(solo.searchText(item));
	}

	@Override
	protected void tearDown() throws Exception{
		solo.finishOpenedActivities();
	}

}
