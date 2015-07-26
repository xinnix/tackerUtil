package network;

import tackerUtil.MsgEventHandler;

public class HeartBeat extends Thread{

	NetworkAdapter na;
	final long timeInterval = 10000;
	
	
	@Override
	public void run() {
		while (true){
			MsgEventHandler.sHeartBeat();
			
			try {  
                Thread.sleep(timeInterval);  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }  
		}
		
		
	}
	

}
