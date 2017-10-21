package com.library;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.UUID;
import java.util.logging.Logger;

import javax.servlet.ServletContext;

import com.builder.AlertMessageBuilder;
import com.model.Response;

public class Library {
    public static byte[] getBytesFromStream(InputStream inputStream)throws IOException {
		Logger.getLogger("Library").warning("input stream"+inputStream);
		ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
		try{
		
		int nRead;
		byte[] data = new byte[1024];
		while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
			byteBuffer.write(data, 0, nRead);
			byteBuffer.flush();
		}
		
		inputStream.close();
		}catch(Exception e){
			Logger.getLogger("Library").warning("Exception: "+ e);
			
			
			
		}
		return byteBuffer.toByteArray();	
	}
   
   public static String getStackTraceAsString(Exception e){
		StringWriter writer = new StringWriter();
		PrintWriter printWriter = new PrintWriter(writer);
		e.printStackTrace(printWriter);
		return writer.toString();
	}
    public static Response getResponse( ServletContext context, String code ) {
		AlertMessageBuilder builder = AlertMessageBuilder.getInstance(context);
		builder = builder.setCode(code);
		return new Response(builder.build());
	}
    
    public static String getUniqueId(){
         UUID uniqueKey = UUID.randomUUID();
         
         return uniqueKey.toString();
    }
}
