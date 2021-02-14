package app.export;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.time.LocalDate;
import java.util.List;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

public class HtmlExport {
	public static <E> void exportHtml(List<E> liste, String Template, String Titre, String File) {
		String sourceDirectory = "/resources/templates/";
		String outputDirectory = "C:/Temp/Export/HTML/";
		String filef = File + "_" + LocalDate.now() + ".html";
		String templatet = sourceDirectory + Template + ".vm";
		String fileName = outputDirectory + filef;
		File file = new File(fileName);
		try {
			file.getParentFile().mkdir();
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		VelocityEngine velocityEngine = new VelocityEngine();
		velocityEngine.setProperty("resource.loader", "file");
		velocityEngine.setProperty("file.resource.loader.class",
				"org.apache.velocity.runtime.resource.loader.FileResourceLoader");
		velocityEngine.setProperty("file.resource.loader.path", sourceDirectory);
		velocityEngine.setProperty("file.resource.loader.cache", true);
		velocityEngine.setProperty("file.resource.loader.modificationCheckInterval", "2");
		velocityEngine.init();
		VelocityContext context = new VelocityContext();
		context.put("title", Titre);
		context.put("list", liste);
		Writer writer = null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Velocity.mergeTemplate(templatet, "UTF-8", context, writer);
		try {
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Generated File > " + filef + "\t into \t" + outputDirectory);
	};
}