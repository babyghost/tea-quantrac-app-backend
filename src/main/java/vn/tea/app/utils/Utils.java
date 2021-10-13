package vn.tea.app.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.Normalizer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Utils {
	public static String generateToken() {
		return DigestUtils.md5Hex(randomString(50));
	}

	public static String randomString(int length) {
		byte[] array = new byte[length]; // length is bounded by 7
		new Random().nextBytes(array);
		return new String(array, StandardCharsets.UTF_8);
	}

	public static void putSearchKey(HashMap<String, String> keys, String key, String type) {
		keys.put(key, type);
		keys.put(key.toLowerCase(), type);
		keys.put(key.toUpperCase(), type);
	}

	public static String readFromInputStream(InputStream inputStream) throws IOException {
		StringBuilder resultStringBuilder = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
			String line;
			while ((line = br.readLine()) != null) {
				resultStringBuilder.append(line).append("\n");
			}
		}
		return resultStringBuilder.toString();
	}

	public static long getNanos(Timestamp in) {
		if (in == null)
			return 0;
		else
			return in.getTime();
	}

	public static String readFromResource(Resource resource) {
		try (Reader reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8)) {
			String result = FileCopyUtils.copyToString(reader);
			log.info(result);
			return result;
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}

	// Split parameters from queryString
	public static Map<String, String> splitRequestParamsFromURL(String queryString) {
		String[] params = queryString.split("&");
		Map<String, String> listParams = new HashMap<>();

		for (String param : params) {
			String name = param.split("=")[0];
			String value = (param.split("=").length == 2) ? param.split("=")[1] : "";
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

	public static String getContentFromClassPath(String path) {
		try {
			ResourceLoader resourceLoader = new DefaultResourceLoader();
			Resource resource = resourceLoader.getResource(path);
			Reader reader = new InputStreamReader(resource.getInputStream(), "UTF-8");
			return FileCopyUtils.copyToString(reader);
		} catch (Exception e) {
			return "";
		}
	}
}
