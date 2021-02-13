package app.export;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Scanner;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

import app.controllers.CategorieDaoImpl;
import app.controllers.MarqueDaoImpl;
import app.controllers.ModeleDaoImpl;
import app.controllers.PieceDaoImpl;
import app.controllers.RefVehiculeImpl;
import app.controllers.ReferenceDaoImpl;
import app.controllers.TransactionsDaoImpl;
import app.controllers.VehiculeDaoImpl;
import app.model.Categorie;
import app.model.Marque;
import app.model.Modele;
import app.model.Piece;
import app.model.Reference;
import app.model.ReferenceVehicule;
import app.model.Transactions;
import app.model.Vehicule;

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

	public static void exec() {
		menu();
		Scanner sc = new Scanner(System.in);
		int i = sc.nextInt();

		switch (i) {
		case 1:
			transactions();
			break;
		case 2:
			modeles();
			break;
		case 3:
			categories();
			break;
		case 4:
			marques();
			break;
		case 5:
			pieces();
			break;
		case 6:
			references();
			break;
		case 7:
			vehicules();
			break;
		case 8:
			referencesvehicules();
			break;
		case 9:
			execAll();
			break;
		}
		sc.close();
	}

	public static void execAll() {
		transactions();
		modeles();
		categories();
		marques();
		pieces();
		references();
		vehicules();
		referencesvehicules();
	}

	public static void menu() {
		System.out.println("1 => transactions");
		System.out.println("2 => modeles");
		System.out.println("3 => categories");
		System.out.println("4 => marques");
		System.out.println("5 => pieces");
		System.out.println("6 => references");
		System.out.println("7 => vehicules");
		System.out.println("8 => references par vehicule");
		System.out.println("9 => Tout");
		System.out.print("\t>choix :");
	}

	public static void transactions() {
		// ************************** transactions *********************************
		TransactionsDaoImpl dao = new TransactionsDaoImpl();
		List<Transactions> liste = dao.listTransactions();
		HtmlExport.exportHtml(liste, "transactions", "Transaction", "transactions");
	}

	public static void modeles() {
		// ************************** modeles *********************************
		ModeleDaoImpl dao = new ModeleDaoImpl();
		List<Modele> liste = dao.listModeles();
		HtmlExport.exportHtml(liste, "modeles", "modele", "modeles");
	}

	public static void categories() {
		// ************************** categories *********************************
		CategorieDaoImpl dao = new CategorieDaoImpl();
		List<Categorie> liste = dao.listCategories();
		HtmlExport.exportHtml(liste, "categories", "categorie", "categories");
	}

	public static void marques() {
		// ************************** marques *********************************
		MarqueDaoImpl dao = new MarqueDaoImpl();
		List<Marque> liste = dao.listMarques();
		HtmlExport.exportHtml(liste, "marques", "marque", "marques");
	}

	public static void pieces() {
		// ************************** pieces *********************************
		PieceDaoImpl dao = new PieceDaoImpl();
		List<Piece> liste = dao.listPieces();
		HtmlExport.exportHtml(liste, "pieces", "piece", "pieces");
	}

	public static void references() {
		// ************************** references *********************************
		ReferenceDaoImpl dao = new ReferenceDaoImpl();
		List<Reference> liste = dao.listReference();
		HtmlExport.exportHtml(liste, "references", "reference", "references");
	}

	public static void vehicules() {
		// ************************** vehicules *********************************
		VehiculeDaoImpl dao = new VehiculeDaoImpl();
		List<Vehicule> liste = dao.listVehicule();
		HtmlExport.exportHtml(liste, "vehicules", "vehicule", "vehicules");
	}

	public static void referencesvehicules() {
		// ************************** references *********************************
		RefVehiculeImpl dao = new RefVehiculeImpl();
		List<ReferenceVehicule> liste = dao.listRefVehicule();
		HtmlExport.exportHtml(liste, "referencesvehicules", "reference vehicule", "referencesvehicules");
	}
}