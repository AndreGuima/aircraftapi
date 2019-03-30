package com.zerog.aircraftapi.test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.StreamUtils;

public class TestUtil {

	private static final String CHARACTER = "a";
	private static final String JSON_BASE_PATH = "json/";

	public static String getTodayAsString() {
		String pattern = "MM/dd/yyyy HH:mm:ss";
		DateFormat df = new SimpleDateFormat(pattern);
		Date today = Calendar.getInstance().getTime();
		String todayAsString = df.format(today);
		return todayAsString;
	}

	public static String createStringWithLength(int length) {
		StringBuilder builder = new StringBuilder();

		for (int index = 0; index < length; index++) {
			builder.append(CHARACTER);
		}

		return builder.toString();
	}

	public static String fromPath(final String filepath) throws IOException {
		final Resource jsonFileResource = new ClassPathResource(JSON_BASE_PATH + filepath + ".json");
		return StreamUtils.copyToString(jsonFileResource.getInputStream(), Charset.defaultCharset());
	}
}