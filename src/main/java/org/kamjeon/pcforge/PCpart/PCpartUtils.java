package org.kamjeon.pcforge.PCpart;

public class PCpartUtils {
	public static void checkPCPart(String part) {
		// part가 interface에서 정의한 상수에 포함되는지 확인
		switch (part) {
		case PCpartInterface.CPU:
		case PCpartInterface.GPU:
		case PCpartInterface.RAM:
		case PCpartInterface.COMCASE:
		case PCpartInterface.DISK:
		case PCpartInterface.MBOARD:
		case PCpartInterface.PSU:
			// 정상적인 부품일 경우, 아무런 동작도 하지 않음
			break;
		default:
			// 존재하지 않는 부품일 경우 예외 발생
			throw new IllegalArgumentException("존재하지 않는 부품입니다: " + part);
		}
	}
}
