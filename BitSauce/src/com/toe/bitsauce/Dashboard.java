package com.toe.bitsauce;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;
import com.viewpagerindicator.PageIndicator;
import com.viewpagerindicator.TitlePageIndicator;

public class Dashboard extends SherlockFragmentActivity {

	DashboardFragmentAdapter mAdapter;
	ViewPager mPager;
	Intent i;
	PageIndicator mIndicator;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_pager);
		getSupportActionBar().setHomeButtonEnabled(true);
		setAdapter();

		getExchangeRates();
	}

	private void getExchangeRates() {
		// TODO Auto-generated method stub

	}

	public void initPagerView(int position, View view) {
		ListItemAdapter adapter;
		ListView listView;
		switch (position) {
		case 0:
			ArrayList<ListItem> setup = new ArrayList<ListItem>();
			setup.add(new ListItem("About Bitcoin", "Know a more about it"));
			setup.add(new ListItem("Get a Bitcoin wallet",
					"Where you'll store your Bitcoins"));
			setup.add(new ListItem("Get a Bitcoin address",
					"It's just like your account number"));
			adapter = new ListItemAdapter(getApplicationContext(),
					R.layout.list_item, setup);
			listView = (ListView) findViewById(R.id.lvListItems);
			listView.setAdapter(adapter);
			listView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					switch (arg2) {
					case 0:
						i = new Intent(Intent.ACTION_VIEW);
						i.setData(Uri.parse("https://bitcoin.org"));
						startActivity(i);
						break;
					case 1:
						i = new Intent(Intent.ACTION_VIEW);
						i.setData(Uri
								.parse("https://play.google.com/store/apps/details?id=de.shildbach.wallet"));
						startActivity(i);
						break;
					case 2:
						new AlertDialog.Builder(Dashboard.this)
								.setTitle("Getting a Bitcoin address")
								.setMessage(
										"Once you've created a bitcoin wallet, open it and there will be a 'bitcoin address' which is 34-36 characters long. This address is all you need to receive payments.")
								.setPositiveButton("Got it",
										new DialogInterface.OnClickListener() {

											@Override
											public void onClick(
													DialogInterface arg0,
													int arg1) {
												arg0.dismiss();
											}
										}).show();
						break;
					}
				}
			});
			break;
		case 1:
			Typeface font = Typeface.createFromAsset(getAssets(),
					"fonts/aspergit_bold.otf");
			final TextView tvExchangeRate = (TextView) findViewById(R.id.tvExchangeRate);
			tvExchangeRate.setTypeface(font);
			final EditText etCashToConvert = (EditText) findViewById(R.id.etCashToConvert);
			etCashToConvert.setTypeface(font);
			final EditText etBitcoinAddress = (EditText) findViewById(R.id.etBitcoinAddress);
			etBitcoinAddress.setTypeface(font);
			final TextView tvNumberOfBitcoins = (TextView) findViewById(R.id.tvNumberOfBitcoins);
			tvNumberOfBitcoins.setTypeface(font);
			final TextView tvTransactionFee = (TextView) findViewById(R.id.tvTransactionFee);
			tvTransactionFee.setTypeface(font);
			final TextView tvAmountToSend = (TextView) findViewById(R.id.tvAmountToSend);
			tvAmountToSend.setTypeface(font);
			Button bProcess = (Button) findViewById(R.id.bProcess);
			bProcess.setTypeface(font);
			bProcess.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					String cashToConvert = etCashToConvert.getText().toString()
							.trim();
					float cash = Float.parseFloat(cashToConvert);
					float transactionFee = (cash / 100) * 15;
					String bitCoinAddress = etBitcoinAddress.getText()
							.toString();
					float amountToSend = cash + transactionFee;
					tvTransactionFee.setText("Transaction fee (15%):"
							+ transactionFee);
					tvAmountToSend.setText("Total amount to send: "
							+ amountToSend);
				}
			});
			Button bBuyBitcoins = (Button) findViewById(R.id.bBuyBitcoins);
			bBuyBitcoins.setTypeface(font);
			bBuyBitcoins.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub

				}
			});
			break;
		case 2:
			ArrayList<ListItem> spend = new ArrayList<ListItem>();
			spend.add(new ListItem("Games", "Buy games"));
			spend.add(new ListItem("Books", "Buy books"));
			spend.add(new ListItem("Food", "Order food"));
			spend.add(new ListItem("Electronics", "Get electronics"));
			adapter = new ListItemAdapter(getApplicationContext(),
					R.layout.list_item, spend);
			listView = (ListView) findViewById(R.id.lvListItems);
			listView.setAdapter(adapter);
			listView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					Toast.makeText(getApplicationContext(),
							"Tag selected: " + arg2, Toast.LENGTH_SHORT).show();
				}
			});
			break;
		}
	}

	private void setAdapter() {
		// TODO Auto-generated method stub
		DashboardFragmentAdapter adapter = new DashboardFragmentAdapter(
				Dashboard.this);

		mPager = (ViewPager) findViewById(R.id.pager);
		mPager.setAdapter(adapter);
		mPager.setCurrentItem(1);

		mIndicator = (TitlePageIndicator) findViewById(R.id.indicator);
		mIndicator.setViewPager(mPager);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case android.R.id.home:
			i = new Intent(getApplicationContext(), About.class);
			startActivity(i);
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		finish();
	}

}