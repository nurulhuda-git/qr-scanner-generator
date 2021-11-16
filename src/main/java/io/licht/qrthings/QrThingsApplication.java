package io.licht.qrthings;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.licht.qrthings.helpers.ZXingHelper;

@SpringBootApplication
public class QrThingsApplication implements CommandLineRunner
{
	private static String PATH = "./qrcode/";

	public static void main(String[] args) {
		SpringApplication.run(QrThingsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String filename = PATH + new SimpleDateFormat("yyyy-mm-dd-hhmmss").format(new Date()) + "_qrcode.png";
		// String msg = "00001";
		String msg = String.valueOf(System.currentTimeMillis());
		char[] str = msg.toCharArray();
		StringBuffer sb = new StringBuffer();
		for(char c : str)
		{
			sb.append(Integer.toHexString(c));
		}
		ZXingHelper.generateQRCodeImage(sb.toString(), 320, 320, filename);
	}

}
