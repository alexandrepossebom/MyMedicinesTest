package com.possebom.mymedicines.test;

import android.test.ActivityInstrumentationTestCase2;

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
		item = String.format("Item %d", System.currentTimeMillis());
	}
	
	public void testInsertEmpty(){
		String text = solo.getString(R.string.alert_title_medicine);
		solo.clickOnActionBarItem(R.id.menu_add);
		solo.clickOnActionBarItem(R.id.menu_save);
		assertTrue("Title not found",solo.waitForText(text));
	}
	
	public void testBarcode(){
		String text = solo.getString(R.string.title_alert_barcodescanner);
		solo.clickOnActionBarItem(R.id.menu_add);
		solo.clickOnActionBarItem(R.id.menu_barcode);
		assertTrue("Title not found",solo.waitForText(text));
	}

	public void testAddAndRemoveItem(){
		solo.clickOnActionBarItem(R.id.menu_add);
		solo.enterText(0, item);
		solo.clickOnActionBarItem(R.id.menu_save);
		assertTrue(solo.searchText(item));
		solo.clickOnText(item);
		assertTrue(solo.searchText(solo.getString(R.string.validity)));
		solo.clickOnActionBarHomeButton();
		solo.clickOnText(item);
		solo.clickOnActionBarItem(R.id.menu_delete);
		assertFalse(solo.searchText(item));
	}

	@Override
	protected void tearDown() throws Exception{
		solo.finishOpenedActivities();
	}

}
