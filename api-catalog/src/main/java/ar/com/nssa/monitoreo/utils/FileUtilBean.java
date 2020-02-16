package ar.com.nssa.monitoreo.utils;

import java.io.File;
import java.nio.file.Files;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

@Component
@Scope(value = "application")
public class FileUtilBean {
	
	public static String read(String filename) throws Exception {
		File file = ResourceUtils.getFile(filename);
		//Read File Content
		return  new String(Files.readAllBytes(file.toPath()));
	}

}
