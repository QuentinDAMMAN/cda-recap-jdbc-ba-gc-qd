package app.menu.saisie;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Ihm {
	public static final Ihm IHM = new Ihm();
	public static final Logger ERRORLOGGER = LoggerFactory.getLogger("DEV");
	private static final Logger CLIENTLOGGER = LoggerFactory.getLogger("CLIENT");

	private Scanner sc;

	private Ihm() {
		this.sc = new Scanner(System.in);
	}

	public int inputInt() {
		int input = 0;
		boolean error = Boolean.TRUE;
		do {
			System.out.print("$ ");
			try {
				input = this.sc.nextInt();
				error = Boolean.FALSE;
			} catch (NumberFormatException | InputMismatchException e) {
				this.sc.nextLine();
				afficherClient("Mauvaise Saisie, Veuillez saisir un nombre");
				ERRORLOGGER.error("Erreur de saisie", e);
			}
		} while (error);
		return input;

	}

	public String inputString(boolean noSpace) {
		String str = "";
		System.out.print("$ ");
		str = sc.next();
		str += sc.nextLine();
		return str;
	}


	public double inputDouble() {
		double input = 0;
		boolean error = Boolean.TRUE;
		do {
			System.out.print("$ ");
			try {
				input = this.sc.nextDouble();
				error = Boolean.FALSE;
			} catch (NumberFormatException | InputMismatchException e) {
				this.sc.nextLine();
				afficherClient("Mauvaise Saisie, Veuillez saisir un nombre");
				ERRORLOGGER.error("Erreur de saisie", e);
			}
		} while (error);

		return input;
	}

	public static void afficherClient(String str) {
		CLIENTLOGGER.info(str);
	}

	public String inputString() {
		return inputString(false);
	}

	public void afficherMenu(String str) {
		str = str.substring(6);
		Ihm.afficherClient("--- Menu " + str + " ---");
		Ihm.afficherClient("  0 - Pour revenir au Menu");
		Ihm.afficherClient("  1 - Ajouter " + str);
		Ihm.afficherClient("  2 - Supprimer " + str);
		Ihm.afficherClient("  3 - Mettre à jour " + str);
		Ihm.afficherClient("  4 - Voir " + str);
		Ihm.afficherClient("  5 - Voir liste " + str);
	}

	public <T> String selectionChamp(Class<?> clazz) {
		Field[] fields = clazz.getDeclaredFields();
		String fieldNames = Arrays.asList(fields).stream().skip(1).map(e -> e.getName())
				.collect(Collectors.joining(", "));
		Ihm.afficherClient("Que souhaitez vous modifier : " + fieldNames);
		String fieldName = IHM.inputString();
		if (!Arrays.asList(fields).stream().skip(1).anyMatch(e -> e.getName().equals(fieldName))) {
			Ihm.afficherClient("Ce champ n'existe pas");
			return null;
		}
		return fieldName;
	}

	public Date inputDate() {
		String input = "";
		Date date = null;
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		boolean error = Boolean.TRUE;
		do {
			System.out.print("$ ");
			input = this.sc.next();
			try {
				date = formatter.parse(input);
				error = Boolean.FALSE;
			} catch (ParseException e) {
				this.sc.nextLine();
				afficherClient("Mauvaise Saisie, Veuillez saisir une date valide (yyyy-mm-dd)");
				ERRORLOGGER.error("Erreur de saisie ", e);
			}
		} while (error);
		System.out.println("IHM INPUT INT " + input);
		return date;
	}

}
