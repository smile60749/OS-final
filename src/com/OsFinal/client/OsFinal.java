package com.OsFinal.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;

public class OsFinal implements EntryPoint {
	private TextBox txt;
	private Label label,label_1,lblNewLabel,all;
	private Button fifo,lru,twice,add,clean;
	private int[] num=new int[100];
	private int count=0;
	String str="";
	//////////////////////////////////////////
	private FlexTable flexTable;
	//////////////////////////////////////////
	public void onModuleLoad() {
		RootPanel rp=RootPanel.get();
		
		label = new Label("\u8ACB\u8F38\u5165");
		rp.add(label, 10, 26);
		
		txt = new TextBox();
		rp.add(txt, 74, 20);
		txt.setSize("73px", "18px");
		
		
		label_1 = new Label("\u8ACB\u9078\u64C7\u6F14\u7B97\u6CD5");
		rp.add(label_1, 10, 121);
		
		all = new Label("    ");
		rp.add(all, 99, 80);

		add = new Button("New button");
		add.setText("\u65B0\u589E");
		add.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				num[count]=Integer.parseInt(txt.getText());
				count++;
				str=str+txt.getText()+" ";
				all.setText(str);
				txt.setText("");
			}
		});
		rp.add(add, 225, 20);
		
		clean = new Button("New button");
		clean.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				str="";
				all.setText(str);
				count=0;
				//////////////////清空表格/////////////////
				flexTable.removeAllRows();
				////////////////////////////////////////
			}
		});
		clean.setText("\u6E05\u9664");
		rp.add(clean, 285, 20);		
		
		fifo = new Button("New button");
		fifo.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				int[] N={0, 2, 1};	//分頁(行)被替換的優先順序，N[2]在下一次將第一個被替換
				int temp,line_temp=2;		//temp(N陣列的有用到的暫存器)，line_temp(上一次有錯誤發生的佇列)
				boolean if_correct;			//是否引起分頁錯誤
				//------------table設定-----------------
				flexTable.removeAllRows();
				flexTable.setBorderWidth(1);
				flexTable.setPixelSize(10, 10);
				for(int i=0;i<3;i++) //設定三行
					flexTable.setWidget(i,count-1,null);
				line_temp=0;
				//--------------------------------------
				//第一頁必發生錯誤
				flexTable.setWidget(0,0,new Label(""+num[0]));
				flexTable.setWidget(1,0,new Label("\u3000"));
				flexTable.setWidget(2,0,new Label("\u3000"));
				//
				for(int i=1;i<count;i++) {
					if_correct = false;			//預設會引起分頁錯誤
					for(int j=0;j<3;j++) 
						if(flexTable.getText(j, line_temp).equals(""+num[i])){//判斷對這個參考不會造成錯誤
							if_correct = true;	//不會引起分頁錯誤
							flexTable.setWidget(2,i,new Label("\u3000"));
						}
					if(!if_correct){
							flexTable.setWidget(N[2],i,new Label(""+num[i]));
							flexTable.setWidget(N[1],i,new Label(""+flexTable.getText(N[1], line_temp)));
							flexTable.setWidget(N[0],i,new Label(""+flexTable.getText(N[0], line_temp)));
						
							//改變下一次分頁被替換的優先順序
							temp=N[2];			
							N[2]=N[1];
							N[1]=N[0];
							N[0]=temp;
							//紀錄有錯誤發生的此佇列
							line_temp=i;
					}
				}
			}
		});
		fifo.setText("FIFO");
		rp.add(fifo, 10, 159);
		
		/////////////////LRU//////////////////////////
		lru = new Button("New button");
		lru.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
				int[] N={0, 2, 1};	//分頁(行)被替換的優先順序，N[2]在下一次將第一個被替換
				int temp,line_temp=2;		//temp(N陣列的有用到的暫存器)，line_temp(上一次有錯誤發生的佇列)
				boolean if_correct;			//是否引起分頁錯誤
				
				//------------table設定-----------------
				flexTable.removeAllRows();
				flexTable.setBorderWidth(1);
				flexTable.setPixelSize(10, 10);
				for(int i=0;i<3;i++) //設定三行
					flexTable.setWidget(i,count-1,null);
				line_temp=0;
				//--------------------------------------
				//第一頁必發生錯誤
				flexTable.setWidget(0,0,new Label(""+num[0]));
				flexTable.setWidget(1,0,new Label("\u3000"));
				flexTable.setWidget(2,0,new Label("\u3000"));
				//
				for(int i=1;i<count;i++) {
					if_correct = false;			//預設會引起分頁錯誤
					for(int j=0;j<3;j++) 
						if(flexTable.getText(j, line_temp).equals(""+num[i])){//判斷對這個參考不會造成錯誤
							if_correct = true;	//不會引起分頁錯誤
							for(int k=0;k<3;k++)//改變下一次被替換的分頁優先順序
								if(N[k]==j){
									for(int l=k;l>0;l--) 
										N[l]=N[l-1];
									N[0]=j;
								}
							flexTable.setWidget(2,i,new Label("\u3000"));
						}
					if(!if_correct){
							flexTable.setWidget(N[2],i,new Label(""+num[i]));
							flexTable.setWidget(N[1],i,new Label(""+flexTable.getText(N[1], line_temp)));
							flexTable.setWidget(N[0],i,new Label(""+flexTable.getText(N[0], line_temp)));
						
							//改變下一次分頁被替換的優先順序
							temp=N[2];			
							N[2]=N[1];
							N[1]=N[0];
							N[0]=temp;
							//紀錄有錯誤發生的此柱列
							line_temp=i;
					}
				}
			}
		});
		lru.setText("LRU");
		rp.add(lru, 128, 159);
		
		twice = new Button("New button");
		twice.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				int[] N={0 ,2, 1};			//分頁(行)被替換的優先順序，N[2]在下一次將第一個被替換
				int[] row={0,0,0};			//判斷此格有沒有第二次機會，預設為0
							
				int temp=0,line_temp=2;		//temp(N陣列的有用到的暫存器)，line_temp(上一次有錯誤發生的佇列)
				boolean if_correct;			//是否引起分頁錯誤
				//------------table設定-----------------
				flexTable.removeAllRows();
				flexTable.setBorderWidth(1);
				flexTable.setPixelSize(10, 10);
				for(int i=0;i<3;i++) 		//設定三行
					flexTable.setWidget(i,count-1,null);
				line_temp=0;
				//--------------------------------------第一頁~~~~~~~
				flexTable.setWidget(0,0,new Label(""+num[0]));	
				flexTable.setWidget(1,0,new Label("\u3000"));
				flexTable.setWidget(2,0,new Label("\u3000"));
				//
				for(int i=1;i<count;i++) {
					if_correct = false;			//預設會引起分頁錯誤
					for(int j=0;j<3;j++) 		//一頁有三格
						if(flexTable.getText(j, line_temp).equals(""+num[i])){//判斷對這個參考不會造成錯誤
							if_correct = true;	//不會引起分頁錯誤
							flexTable.setWidget(2,i,new Label("\u3000"));
							row[j]=1;			//遇到一樣的，參數位元改為一(有第二次機會了)
						}
					if(!if_correct){			//有錯誤時
							while(row[N[2]]==1){//判斷下一個將被替換的格子是否有第二次機會，有的話跳過，然後機會沒了
								row[N[2]]=0;
								
								temp=N[2];		//改變下一次被替換的優先順序
								N[2]=N[1];
								N[1]=N[0];
								N[0]=temp;
								
							}
							flexTable.setWidget(N[2],i,new Label(""+num[i]));
							flexTable.setWidget(N[1],i,new Label(""+flexTable.getText(N[1], line_temp)));
							flexTable.setWidget(N[0],i,new Label(""+flexTable.getText(N[0], line_temp)));
							
							//改變下一次分頁被替換的優先順序
							temp=N[2];			
							N[2]=N[1];
							N[1]=N[0];
							N[0]=temp;
							//紀錄有錯誤發生的此柱列
							line_temp=i;
					}
				}
			}
		});
		twice.setText("\u7B2C\u4E8C\u6B21\u6A5F\u6703");
		rp.add(twice, 259, 159);

		
		lblNewLabel = new Label("\u76EE\u524D\u5DF2\u8F38\u5165");
		rp.add(lblNewLabel, 10, 80);
		
		///////////////////////////////////
		flexTable = new FlexTable();
		rp.add(flexTable, 20, 200);
		flexTable.setSize("100px", "100px");
		///////////////////////////////////
		

		
	}
}
