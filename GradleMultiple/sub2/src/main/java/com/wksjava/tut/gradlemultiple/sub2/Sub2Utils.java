package com.wksjava.tut.gradlemultiple.sub2;

import org.apache.commons.lang3.LocaleUtils;
import org.apache.logging.log4j.Level;

public class Sub2Utils {
	public static void Say() {
		System.out.println("SUB2");
		LocaleUtils.availableLocaleList();
		String t = Level.CATEGORY;
	}
}
