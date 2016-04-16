package com.maincom;
/**
 * Description of the class
 * the class is to monitor any activity in E drive.
 *@author Amr Redaa 
 *@version 1.0
 *Date 04/11/2016 7:11 PM
 * 
 */


import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import static java.nio.file.StandardWatchEventKinds.*;

import java.io.IOException;
/*
 * @throws Exception
 * 
 */
public class Revesion {

	private Path p = null;
	private WatchService wS = null;
	
	private void init(){
	p = Paths.get("E:\\");
	try{
		wS = FileSystems.getDefault().newWatchService();
		p.register(wS,ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
	}catch(IOException e){
		System.out.println("Error"+e.getMessage());
	}			
		
	}
	/*
	 * @DoRound is method to loop for any change in the directory.
	 * 
	 * 
	 */
	private void doRounds(){
		WatchKey key = null;
		while(true){
			try{
			key= wS.take();
			for(WatchEvent<?> event :key.pollEvents()){
				Kind<?> kind = event.kind();
				System.out.println(event.context().toString() + kind);
			}
			}catch(InterruptedException e) {
				System.out.println(e.getMessage());
			}
			boolean reset = key.reset();
			if (!reset)
				break;
		}
		
	}
	
	public static void main(String[] args) {
		
Revesion x=new Revesion();
x.init();
x.doRounds();
System.out.println(""+x.p.getNameCount());
System.out.println(""+x.p.getFileName());
System.out.println(""+x.p.getRoot());
System.out.println(""+x.p.getParent());
	}

}
