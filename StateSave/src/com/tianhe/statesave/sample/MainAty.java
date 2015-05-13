package com.tianhe.statesave.sample;


import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.tianhe.statesave.R;
import com.tianhe.statesave.master.SaveState;
import com.tianhe.statesave.master.StateAty;

public class MainAty extends StateAty {

	private MainAty context=this;
	
	@SaveState
	private int number;
	
	private TextView txtNumber;
	private Button btnAdd;
	
	@SaveState
	private Obj obj=new Obj();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		this.initViews();
		this.setListeners();
		this.updateUi();
	}
	
	private void initViews(){
		this.txtNumber=(TextView) this.findViewById(R.id.main_txt);
		this.btnAdd=(Button) this.findViewById(R.id.main_btn);
	}
	
	private void setListeners(){
		this.btnAdd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				context.number++;
				context.updateUi();
				context.obj.setId(number);
				context.obj.setName("  name:"+number);
			}
		});
	}
	
	private void updateUi(){
		this.txtNumber.setText(obj.getName()+"  value:"+obj.getId());
	}

	@Override
	public Object getContext() {
		return this;
	}
	

}
