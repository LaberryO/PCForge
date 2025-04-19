package org.kamjeon.pcforge.PCpart;

import java.util.Arrays;

public class PCpartUtils {
	public static void checkPCPart(String part) {
		String[] pcparts = getPCparts();
		if (Arrays.asList(pcparts).contains(part)) {
			return;
		} else {
			throw new IllegalArgumentException("존재하지 않는 부품입니다: " + part);
		}
	}
	
	public static String[] getPCparts() {
		return new String[] {
			"cpu", "ram", "gpu", "mboard", "psu", "comcase", "disk"
		};
	}
}
