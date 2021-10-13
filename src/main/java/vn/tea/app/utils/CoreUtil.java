package vn.tea.app.utils;

import java.text.DateFormat;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public final class CoreUtil {
	private CoreUtil() {

	}

	public static String dateSQLToTimeVN(Date dateSQL) {
		try {
			DateFormat df2 = new SimpleDateFormat("HH:mm");
			return df2.format(dateSQL);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	public static String dateSQLToDateVN(Date dateSQL) {
		try {
			DateFormat df2 = new SimpleDateFormat("dd/MM/yyyy");
			return df2.format(dateSQL);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	public static Date stringDateVNToSQL(String vnDate) {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date dateSQL = new Date(dateFormat.parse(vnDate).getTime());
			// java.util.Date utilDate = dateFormat.parse(vnDate);
			return dateSQL;
		} catch (Exception es) {
		}
		return null;
	}

	public static Date stringDateTimeVNToSQL(String vnDateTime) {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
			Date dateSQL = new Date(dateFormat.parse(vnDateTime).getTime());
			// java.util.Date utilDate = dateFormat.parse(vnDate);
			return dateSQL;
		} catch (Exception es) {
		}
		return null;
	}

	public static int getYear() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		return year;
	}

	public static int getMonth() {
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH) + 1;
		return month;
	}

	public static String stripAccents(String s) {
		s = Normalizer.normalize(s, Normalizer.Form.NFD);
		s = s.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
		return s;
	}

}
