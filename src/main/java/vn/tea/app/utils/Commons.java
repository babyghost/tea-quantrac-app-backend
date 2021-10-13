package vn.tea.app.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;


public class Commons {

	// Split parameters from queryString
	public static Map<String, Object> splitRequestParamsFromURL(String queryString) {
		String[] params = queryString.split("&");
		Map<String, Object> listParams = new HashMap<>();

		for (String param : params) {
			String name = param.split("=")[0];
			String value = (param.split("=").length == 2) ? param.split("=")[1] : null;
			listParams.put(name, value);
		}
		return listParams;
	}

	
	// Convert sortData with alias entity 
	public static String convertSortDataWithAlias(String sortString, String alias) {
		StringBuilder sortStringWithAlias = new StringBuilder();
		List<String> sortDataSplit = Arrays.asList(sortString.split(","));
		sortDataSplit.forEach(sortItem -> {
			sortStringWithAlias.append(alias);
			sortStringWithAlias.append(".");
			sortStringWithAlias.append(sortItem.trim());
			sortStringWithAlias.append(", ");
		});
		return sortStringWithAlias.toString().substring(0, sortStringWithAlias.toString().length() - 2);
	}

	public static String covertStringToURL(String str) {
		try {
			String temp = Normalizer.normalize(str, Normalizer.Form.NFD);
			Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
			return pattern.matcher(temp).replaceAll("").toLowerCase().replaceAll(" ", "-").replaceAll("đ", "d");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String currencyFormat(BigDecimal n) {
		try {
			DecimalFormat decimalFormat = new DecimalFormat("###,###");
			String result = decimalFormat.format(n);
			return result.replaceAll(",", ".");
		} catch (Exception e) {
			return "";
		}
	}
	
	public static <T> Predicate<T> distinctByKey(
		    Function<? super T, ?> keyExtractor) {
		   
		    Map<Object, Boolean> seen = new ConcurrentHashMap<>(); 
		    return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null; 
	}
	
	public static String dateFormat(String dateStr, String inputFormat, String ouputFormat) {
		//format
		//year: y
		//month: M
		//day: d
		String dateOutStr = "";
		try {
			SimpleDateFormat df_in = new SimpleDateFormat(inputFormat);
			SimpleDateFormat df_out = new SimpleDateFormat(ouputFormat);
			Date date = df_in.parse(dateStr);
			dateOutStr = df_out.format(date);
		} catch (Exception e) {
			return dateOutStr;
		}
		return dateOutStr;
	}
	
	public static void copyFile(File fileFrom, File fileTo) throws IOException {
		InputStream is = null;
	    OutputStream os = null;
	    try {
	        is = new FileInputStream(fileFrom);
	        os = new FileOutputStream(fileTo);
	        byte[] buffer = new byte[1024];
	        int length;
	        while ((length = is.read(buffer)) > 0) {
	            os.write(buffer, 0, length);
	        }
	    } catch (Exception e) {
			// TODO: handle exception
	    	e.printStackTrace();
		}finally {
	        is.close();
	        os.close();
	    }
    }
	
	public static int zeroIfNull(Integer bonus) {
	    return (bonus == null) ? 0 : bonus;
	}
	
	
	public static String getFileExtension(File file) {
	    String name = file.getName();
	    int lastIndexOf = name.lastIndexOf(".");
	    if (lastIndexOf == -1) {
	        return ""; // empty extension
	    }
	    return name.substring(lastIndexOf);
	}
}
