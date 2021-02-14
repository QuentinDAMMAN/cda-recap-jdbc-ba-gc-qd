package app.export;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

public class HtmlExport {


	public static <E> void exportHtml(List<E> liste, String Template, String Titre, String File) {
		
		String sourceDirectory = "/resources/templates/";
//		String outputDirectory = "/export/";

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
//			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(outputDirectory + File+".html"))));
			writer = new FileWriter(new File(File + ".html"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Velocity.mergeTemplate(sourceDirectory + Template + ".vm", "UTF-8", context, writer);
//		Velocity.mergeTemplate(Template + ".vm", "UTF-8", context, writer);
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
		System.out.println("Generated File > " + File + ".html");
//		System.out.println("Generated File > " + File + ".html into "+outputDirectory);
	};

//	public static void exec() {
//		menu();
//		Scanner sc = new Scanner(System.in);
//		int i = sc.nextInt();
//
//		switch (i) {
//		case 1:
//			transactions();
//			break;
//		case 2:
//			modeles();
//			break;
//		case 3:
//			categories();
//			break;
//		case 4:
//			marques();
//			break;
//		case 5:
//			pieces();
//			break;
//		case 6:
//			references();
//			break;
//		case 7:
//			vehicules();
//			break;
//		case 8:
//			referencesvehicules();
//			break;
//		case 9:
//			execAll();
//			break;
//		}
//		sc.close();
//	}
//
//	public static void execAll() {
//		transactions();
//		modeles();
//		categories();
//		marques();
//		pieces();
//		references();
//		vehicules();
//		referencesvehicules();
//	}
//
//	public static void menu() {
//		System.out.println("1 => transactions");
//		System.out.println("2 => modeles");
//		System.out.println("3 => categories");
//		System.out.println("4 => marques");
//		System.out.println("5 => pieces");
//		System.out.println("6 => references");
//		System.out.println("7 => vehicules");
//		System.out.println("8 => references par vehicule");
//		System.out.println("9 => Tout");
//		System.out.print("\t>choix :");
//	}


}