package com.possebom.mymedicines.test;

import java.util.concurrent.CountDownLatch;

import android.content.Context;
import android.test.AndroidTestCase;

import com.possebom.mymedicines.GetMedicine;
import com.possebom.mymedicines.GetMedicine.GetMedicineListener;
import com.possebom.mymedicines.SendMedicine;
import com.possebom.mymedicines.SendMedicine.SetMedicineListener;
import com.possebom.mymedicines.model.Medicine;


public class TestSendMedicine extends AndroidTestCase implements SetMedicineListener, GetMedicineListener{
	private final CountDownLatch signalSet = new CountDownLatch(1);
	private final CountDownLatch signalGet = new CountDownLatch(1);
	private Medicine	medicine;
	private Medicine	medicineGet;
	private Context	context;
	private long now;
	boolean result = false;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		now = System.currentTimeMillis();
		medicine = new Medicine();
		medicine.setBrandName(String.valueOf(now));
		medicine.setBarcode("1");
		medicine.setCountry("BR");
	}

	public void testInvalidMedicine() throws InterruptedException {
		
		context = getContext();
		SendMedicine sm = new SendMedicine(context, this, medicine);
		sm.execute();

		signalSet.await();
		
		GetMedicine gm = new GetMedicine(this, medicine.getBarcode(), medicine.getCountry());
		gm.execute();

		signalGet.await();

		if(medicineGet.getBrandName().equals(medicine.getBrandName()))
			result = true;

		assertTrue(result);
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	@Override
	public void onSaveCallComplete() {
		signalSet.countDown();
	}

	@Override
	public void onRemoteCallComplete(Medicine medicine) {
		this.medicineGet = medicine;
		signalGet.countDown();
	}

}
