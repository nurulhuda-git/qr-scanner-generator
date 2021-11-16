package io.licht.qrthings.controllers;

import java.io.File;
import java.io.FileOutputStream;
// import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.licht.qrthings.helpers.ZXingHelper;

@RestController
@CrossOrigin("*")
public class FileUploadController {
   private static String QR_PATH = "./qrcode/";
   private static String IMG_PATH = "./data/";
   
   @RequestMapping(value = "/upload", method = RequestMethod.POST, 
      consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
   
   public String fileUpload(@RequestParam("file") MultipartFile file){
      try
      {
         String qr_filename = QR_PATH + new SimpleDateFormat("yyyy-mm-dd-hhmmss").format(new Date()) + "_qrcode.png";
         String img_filename = IMG_PATH + new SimpleDateFormat("yyyy-mm-dd-hhmmss").format(new Date()) + "_img.png";

         File convertFile = new File(img_filename);
         convertFile.createNewFile();
         FileOutputStream fout = new FileOutputStream(convertFile);
         fout.write(file.getBytes());
         fout.close();

         ZXingHelper.generateQRCodeImage(String.valueOf(System.currentTimeMillis()), 320, 320, qr_filename);

         return "File is upload successfully";
      } catch(Exception ex)
      {
         ex.printStackTrace();
         return "Failed!, cause : " + ex.getMessage(); 
      }

      
   }
}