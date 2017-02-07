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
				//////////////////�M�Ū��/////////////////
				flexTable.removeAllRows();
				////////////////////////////////////////
			}
		});
		clean.setText("\u6E05\u9664");
		rp.add(clean, 285, 20);		
		
		fifo = new Button("New button");
		fifo.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				int[] N={0, 2, 1};	//����(��)�Q�������u�����ǡAN[2]�b�U�@���N�Ĥ@�ӳQ����
				int temp,line_temp=2;		//temp(N�}�C�����Ψ쪺�Ȧs��)�Aline_temp(�W�@�������~�o�ͪ���C)
				boolean if_correct;			//�O�_�ް_�������~
				//------------table�]�w-----------------
				flexTable.removeAllRows();
				flexTable.setBorderWidth(1);
				flexTable.setPixelSize(10, 10);
				for(int i=0;i<3;i++) //�]�w�T��
					flexTable.setWidget(i,count-1,null);
				line_temp=0;
				//--------------------------------------
				//�Ĥ@�����o�Ϳ��~
				flexTable.setWidget(0,0,new Label(""+num[0]));
				flexTable.setWidget(1,0,new Label("\u3000"));
				flexTable.setWidget(2,0,new Label("\u3000"));
				//
				for(int i=1;i<count;i++) {
					if_correct = false;			//�w�]�|�ް_�������~
					for(int j=0;j<3;j++) 
						if(flexTable.getText(j, line_temp).equals(""+num[i])){//�P�_��o�ӰѦҤ��|�y�����~
							if_correct = true;	//���|�ް_�������~
							flexTable.setWidget(2,i,new Label("\u3000"));
						}
					if(!if_correct){
							flexTable.setWidget(N[2],i,new Label(""+num[i]));
							flexTable.setWidget(N[1],i,new Label(""+flexTable.getText(N[1], line_temp)));
							flexTable.setWidget(N[0],i,new Label(""+flexTable.getText(N[0], line_temp)));
						
							//���ܤU�@�������Q�������u������
							temp=N[2];			
							N[2]=N[1];
							N[1]=N[0];
							N[0]=temp;
							//���������~�o�ͪ�����C
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
				
				int[] N={0, 2, 1};	//����(��)�Q�������u�����ǡAN[2]�b�U�@���N�Ĥ@�ӳQ����
				int temp,line_temp=2;		//temp(N�}�C�����Ψ쪺�Ȧs��)�Aline_temp(�W�@�������~�o�ͪ���C)
				boolean if_correct;			//�O�_�ް_�������~
				
				//------------table�]�w-----------------
				flexTable.removeAllRows();
				flexTable.setBorderWidth(1);
				flexTable.setPixelSize(10, 10);
				for(int i=0;i<3;i++) //�]�w�T��
					flexTable.setWidget(i,count-1,null);
				line_temp=0;
				//--------------------------------------
				//�Ĥ@�����o�Ϳ��~
				flexTable.setWidget(0,0,new Label(""+num[0]));
				flexTable.setWidget(1,0,new Label("\u3000"));
				flexTable.setWidget(2,0,new Label("\u3000"));
				//
				for(int i=1;i<count;i++) {
					if_correct = false;			//�w�]�|�ް_�������~
					for(int j=0;j<3;j++) 
						if(flexTable.getText(j, line_temp).equals(""+num[i])){//�P�_��o�ӰѦҤ��|�y�����~
							if_correct = true;	//���|�ް_�������~
							for(int k=0;k<3;k++)//���ܤU�@���Q�����������u������
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
						
							//���ܤU�@�������Q�������u������
							temp=N[2];			
							N[2]=N[1];
							N[1]=N[0];
							N[0]=temp;
							//���������~�o�ͪ����W�C
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
				int[] N={0 ,2, 1};			//����(��)�Q�������u�����ǡAN[2]�b�U�@���N�Ĥ@�ӳQ����
				int[] row={0,0,0};			//�P�_���榳�S���ĤG�����|�A�w�]��0
							
				int temp=0,line_temp=2;		//temp(N�}�C�����Ψ쪺�Ȧs��)�Aline_temp(�W�@�������~�o�ͪ���C)
				boolean if_correct;			//�O�_�ް_�������~
				//------------table�]�w-----------------
				flexTable.removeAllRows();
				flexTable.setBorderWidth(1);
				flexTable.setPixelSize(10, 10);
				for(int i=0;i<3;i++) 		//�]�w�T��
					flexTable.setWidget(i,count-1,null);
				line_temp=0;
				//--------------------------------------�Ĥ@��~~~~~~~
				flexTable.setWidget(0,0,new Label(""+num[0]));	
				flexTable.setWidget(1,0,new Label("\u3000"));
				flexTable.setWidget(2,0,new Label("\u3000"));
				//
				for(int i=1;i<count;i++) {
					if_correct = false;			//�w�]�|�ް_�������~
					for(int j=0;j<3;j++) 		//�@�����T��
						if(flexTable.getText(j, line_temp).equals(""+num[i])){//�P�_��o�ӰѦҤ��|�y�����~
							if_correct = true;	//���|�ް_�������~
							flexTable.setWidget(2,i,new Label("\u3000"));
							row[j]=1;			//�J��@�˪��A�ѼƦ줸�אּ�@(���ĤG�����|�F)
						}
					if(!if_correct){			//�����~��
							while(row[N[2]]==1){//�P�_�U�@�ӱN�Q��������l�O�_���ĤG�����|�A�����ܸ��L�A�M����|�S�F
								row[N[2]]=0;
								
								temp=N[2];		//���ܤU�@���Q�������u������
								N[2]=N[1];
								N[1]=N[0];
								N[0]=temp;
								
							}
							flexTable.setWidget(N[2],i,new Label(""+num[i]));
							flexTable.setWidget(N[1],i,new Label(""+flexTable.getText(N[1], line_temp)));
							flexTable.setWidget(N[0],i,new Label(""+flexTable.getText(N[0], line_temp)));
							
							//���ܤU�@�������Q�������u������
							temp=N[2];			
							N[2]=N[1];
							N[1]=N[0];
							N[0]=temp;
							//���������~�o�ͪ����W�C
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
