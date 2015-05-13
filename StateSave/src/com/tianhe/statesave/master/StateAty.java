package com.tianhe.statesave.master;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public abstract class StateAty extends FragmentActivity implements IContext{

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		StateUtils.onRestoreState(this.getContext(), savedInstanceState);
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		StateUtils.onSaveState(this.getContext(), outState);
		super.onSaveInstanceState(outState);
	}
	
	
}
