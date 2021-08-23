package com.udacity.jwdnd.course1.cloudstorage.services;


import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class FilesService {


    private final FileMapper fileMapper;

    public FilesService(FileMapper fileMapper)
    {
        this.fileMapper = fileMapper;
    }

    public int AddFile(MultipartFile uploadedFile, Integer userId)  {

        try {
            File file = new File();
            file.setFileName(uploadedFile.getOriginalFilename());
            file.setContentType(uploadedFile.getContentType());
            file.setFileSize(uploadedFile.getSize() + "");
            file.setFileData(uploadedFile.getBytes());
            file.setUserId(userId);
            return this.fileMapper.insert(file);
        }catch(IOException ex)
        {
            ex.printStackTrace();
            return 0;
        }
    }

    public List<File> getAllFiles(Integer userId)
    {
        return this.fileMapper.selectAll(userId);
    }

    public int deleteFile(Integer fileId, Integer userId)
    {
        return this.fileMapper.delete(fileId,userId);
    }

    public File getFile(Integer fileId, Integer userId)
    {
        return this.fileMapper.getFile(fileId,userId);
    }

}
