package com.possebom.mymedicines.test;

import java.util.concurrent.CountDownLatch;

import android.test.AndroidTestCase;
import android.util.Log;

import com.possebom.mymedicines.GetMedicine;
import com.possebom.mymedicines.GetMedicine.GetMedicineListener;
import com.possebom.mymedicines.model.Medicine;


public class TestGetMedicine extends AndroidTestCase implements GetMedicineListener{
	private final CountDownLatch signal = new CountDownLatch(1);
	private Medicine	medicine;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testInvalidMedicine() throws InterruptedException {
		boolean result = false;
		GetMedicine gm = new GetMedicine(this, "foo", "bar");
		gm.execute();

		signal.await();

		if(medicine.getBrandName()  == null)
			result = true;

		assertTrue(result);
	}


	public void testValidMedicine() throws InterruptedException {
		boolean result = false;
		GetMedicine gm = new GetMedicine(this, "7891010504779", "BR");
		gm.execute();

		signal.await();

		if(medicine.getBrandName().equals("Band-Aid") && medicine.getCountry().equals("BR"))
			result = true;
		
		Log.d("TESTE","Mecicine is : "+medicine);

		assertTrue(result);

	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	@Override
	public void onRemoteCallComplete(Medicine medicine) {
		this.medicine = medicine;
		signal.countDown();
	}

}
