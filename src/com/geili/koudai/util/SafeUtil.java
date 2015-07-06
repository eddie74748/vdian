package com.geili.koudai.util;

import java.io.ByteArrayOutputStream;

import android.content.Context;

public class SafeUtil {
	private static char[] a = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G',
			'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
			'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g',
			'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
			'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', '+', '/' };

	private static byte[] b = new byte[] { -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1,
			-1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,
			14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1,
			-1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41,
			42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1 };

	public static native byte[] decryptPushData(Context arg1, byte[] arg2);

	public static native byte[] decryptRequestData(Context arg1, byte[] arg2,
			String arg3);

	public static native byte[] encryptRequestData(Context arg1, byte[] arg2,
			String arg3);

	public static native String readKey(Context arg1, String arg2);

	public static native int readSignature(Context arg1, String arg2);

	public static String Convert(byte[] arg8) {
		StringBuffer v1 = new StringBuffer();
		int v2 = arg8.length;
		int v0 = 0;
		while (v0 < v2) {
			int v3 = v0 + 1;
			int v4 = arg8[v0] & 255;
			if (v3 == v2) {
				v1.append(a[v4 >>> 2]);
				v1.append(a[(v4 & 3) << 4]);
				v1.append("==");
			} else {
				int v5 = v3 + 1;
				v3 = arg8[v3] & 255;
				if (v5 == v2) {
					v1.append(a[v4 >>> 2]);
					v1.append(a[(v4 & 3) << 4 | (v3 & 240) >>> 4]);
					v1.append(a[(v3 & 15) << 2]);
					v1.append("=");
				} else {
					v0 = v5 + 1;
					v5 = arg8[v5] & 255;
					v1.append(a[v4 >>> 2]);
					v1.append(a[(v4 & 3) << 4 | (v3 & 240) >>> 4]);
					v1.append(a[(v3 & 15) << 2 | (v5 & 192) >>> 6]);
					v1.append(a[v5 & 63]);
					continue;
				}
			}
			break;
		}

		return v1.toString();
	}

	public static byte[] ReConvert(byte[] arg8) {
        int v5;
        int v4;
        int v1;
        int v7 = 61;
        int v6 = -1;
        int v2 = arg8.length;
        ByteArrayOutputStream v3 = new ByteArrayOutputStream(v2);
        int v0 = 0;
        while(true) {
            if(v0 < v2) {
                while(true) {
                    v1 = v0 + 1;
                    v4 = b[arg8[v0]];
                    if(v1 < v2 && v4 == v6) {
                        v0 = v1;
                        continue;
                    }

                    break;
                }

                if(v4 == v6) {
                	return v3.toByteArray();
                }

                while(true) {
                    v0 = v1 + 1;
                    v5 = b[arg8[v1]];
                    if(v0 < v2 && v5 == v6) {
                        v1 = v0;
                        continue;
                    }

                    break;
                }

                if(v5 == v6) {
                	return v3.toByteArray();
                }

                v3.write(v4 << 2 | (v5 & 48) >>> 4);
                while(true) {
                    v1 = v0 + 1;
                    v0 = arg8[v0];
                    if(v0 == v7) {
                    	byte[] v0_1 = v3.toByteArray();
                        return v0_1;
                    }

                    v4 = b[v0];
                    if(v1 < v2 && v4 == v6) {
                        v0 = v1;
                        continue;
                    }

                    break;
                }

                if(v4 == v6) {
                	return v3.toByteArray();
                }

                v3.write((v5 & 15) << 4 | (v4 & 60) >>> 2);

                while(true) {
               
                    v0 = v1 + 1;
                    v1 = arg8[v1];
                    if(v1 == v7) {
                    	return v3.toByteArray();
                    }

                    v1 = b[v1];
                    if(v0 < v2 && v1 == v6) {
                        v1 = v0;
                        continue;
                    }

                    break;
                }

                if(v1 == v6) {
                	return v3.toByteArray();
                }

                v3.write(v1 | (v4 & 3) << 6);
                continue;
            }
            else {
            	return v3.toByteArray();
            }
        }

    }

}
