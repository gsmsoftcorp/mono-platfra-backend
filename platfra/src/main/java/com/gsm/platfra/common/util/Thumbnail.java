package com.gsm.platfra.common.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.IOException;


public  class Thumbnail {

	public static String ThumbnailImage(String filePath, String orgFile, String thubFile, int width) throws IOException{

		String resultFile = orgFile;

		//원본이미지
		String saveFile = filePath + File.separator + orgFile;

		//썸네일 파일
		String thumnailFile = filePath + File.separator + thubFile;

		//원본이미지 파일을 읽어들인다.
		Image srcImg = ImageIO.read(new File(saveFile));

		//이미지 사이즈 계산
		int imx = srcImg.getWidth(null);
		int imy = srcImg.getHeight(null);
		int height = width * imy / imx;

		Image imgTarget = srcImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		int pixels[] = new int[width * height];
		PixelGrabber pg = new PixelGrabber(imgTarget, 0, 0, width, height, pixels, 0, width);

		try {

			pg.grabPixels();
			resultFile = thubFile;

		} catch (InterruptedException e) {
			//오류가 나면 원본파일명을 보내준다.
			resultFile = orgFile;
			//throw new IOException(e.getMessage());
		}

		BufferedImage thumImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		thumImg.setRGB(0, 0, width, height, pixels, 0, width);

		//썸네일 이미지 파일을 작성한다.
		File thumFile = new File(thumnailFile);
		ImageIO.write(thumImg, "jpg", thumFile);

		return resultFile;

	}
}
