package com.possebom.mymedicines.test;

import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;

import com.jayway.android.robotium.solo.Solo;
import com.possebom.mymedicines.MedicineListActivity;
import com.possebom.mymedicines.R;


public class TestMain extends ActivityInstrumentationTestCase2<MedicineListActivity> {

	private Solo solo;
	private String item;
	private final long timeout = 20000;
	private final String	TAG = "TESTE";

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
		solo.waitForActivity("MedicineListActivity" ,(int) timeout);
		String text = solo.getString(R.string.alert_title_medicine);
		solo.clickOnActionBarItem(R.id.menu_add);
		solo.waitForActivity("MedicineAddActivity", (int) timeout);
		solo.clickOnActionBarItem(R.id.menu_save);
		boolean result = solo.waitForText(text, 1, timeout);
		assertTrue("Title not found : " + text,result);
	}
	
	public void testBarcode(){
		solo.waitForActivity("MedicineListActivity" ,(int) timeout);
		String text = solo.getString(R.string.title_alert_barcodescanner);
		solo.clickOnActionBarItem(R.id.menu_add);
		solo.waitForActivity("MedicineAddActivity",(int) timeout);
		solo.clickOnActionBarItem(R.id.menu_barcode);
		boolean result = solo.waitForText(text, 1, timeout);
		assertTrue("Title not found : "+text,result);
	}

	public void testAddAndRemoveItem(){
		solo.waitForActivity("MedicineListActivity" ,(int) timeout);
		solo.clickOnActionBarItem(R.id.menu_add);
		solo.waitForActivity("MedicineAddActivity", (int) timeout);
		solo.enterText(0, item);
		solo.clickOnActionBarItem(R.id.menu_save);
		boolean result = solo.waitForText(item, 1, timeout);
		assertTrue(result);
		solo.clickOnText(item);
		String text = solo.getString(R.string.validity);
		result = solo.waitForText(text, 1, timeout);
		assertTrue(result);
		solo.clickOnActionBarHomeButton();
		solo.waitForText(item, 1, timeout);
		solo.clickOnText(item);
		solo.waitForText(text, 1, timeout);
		solo.clickOnActionBarItem(R.id.menu_delete);
		solo.waitForActivity("MedicineListActivity" ,(int) timeout);
		result = solo.waitForText(item, 1, 5000);
		assertFalse(result);
	}

	@Override
	protected void tearDown() throws Exception{
		solo.finishOpenedActivities();
	}

}
