package com.brhm.httpmonitor;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Random;

public class Simulator {
	
	// simulating a file that is actively written to
	public static void main(String[] args) {
		OutputStream outStream = null;
		try {
			outStream = new FileOutputStream("access.log");
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outStream));
			Random rand = new Random();
			while(true) {
				int status = rand.nextInt(4)+1;
				writer.write("127.0.0.1 user-identifier frank [10/Oct/2000:13:55:36 -0700] \"GET /apache_pb.gif HTTP/1.0\" "+status+"00 2326");
				writer.newLine();				
				writer.flush();
				
				System.out.println("writing to file...");
				
				Thread.sleep(400);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			if(outStream != null)
				try {
					outStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

}
