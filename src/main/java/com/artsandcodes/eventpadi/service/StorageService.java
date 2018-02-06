package com.artsandcodes.eventpadi.service;

import com.swifta.omnibranches.utility.UtilityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by prestige on 1/24/2018.
 */
@Service
public class StorageService {

    @Autowired
    private UtilityService utilityService;
    private final Logger log = LoggerFactory.getLogger(StorageService.class);

    private String os = System.getProperty("os.name").toLowerCase();
    private String workingDirectory = System.getProperty("user.home");
    private String separator = File.separator;
    private String uploadSubDirectory = "uploads";

    public String store(MultipartFile file) throws IOException {

        File directory =  null;
        String filename = null;
        String uploadPath = "";

        //if its a linux machine
        if(os.indexOf("lin") > -1){
            directory = new File("/var/www/html/" + uploadSubDirectory);
            filename = this.getUploadFileName(file);
            uploadPath = uploadSubDirectory + separator + filename;
        }
        else{
            directory = new File(workingDirectory + separator + uploadSubDirectory);
            filename = this.getUploadFileName(file);
            uploadPath = uploadSubDirectory + separator + filename;
        }

        if(!directory.isDirectory()){
            directory.mkdir();
        }


        log.info(os);
        log.info(filename);
        log.info(workingDirectory);

        Files.copy(file.getInputStream(), Paths.get(directory + separator + filename));
        return uploadPath;
    }

    public String getUploadFileName(MultipartFile file){

        String filename = file.getName();
        String[] filePaths = file.getOriginalFilename().split("\\.");
        log.info(filePaths.length + "");
        String ext = filePaths[filePaths.length - 1];

        return this.utilityService.getCurrentTimeStamp().getTime() + "_" + filename + "." + ext;
    }

}
