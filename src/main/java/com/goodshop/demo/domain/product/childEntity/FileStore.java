package com.goodshop.demo.domain.product.childEntity;


import com.goodshop.demo.domain.product.UploadFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//저장되는 파일이름 생성


@Component
public class FileStore {

     @Value("${file.dir}")
    private String fileDir;


    public String getFullPath(String filename){
        return fileDir + filename;
    }

    public UploadFile storeFile(MultipartFile multipartFile) throws IOException {
        if(multipartFile.isEmpty()){
            return null;
        }

        String originalFilename = multipartFile.getOriginalFilename();
        String storeFileName = createStoreFileName(originalFilename);
        multipartFile.transferTo(new File(getFullPath(storeFileName)));
        return new UploadFile(originalFilename, storeFileName);
    }

    //여러개의 이미지
    public List<UploadFile> storeFiles(List<MultipartFile> multipartFiles) throws IOException{
        List<UploadFile> storeFileResult = new ArrayList<>();
        for(MultipartFile multipartFile : multipartFiles){
            if(!multipartFile.isEmpty()){
                storeFileResult.add(storeFile(multipartFile));
            }
        }
        return storeFileResult;
    }


    //서버에서 저장하는 유일한 파일 이름을 만들어준다.
    private String createStoreFileName(String originalFilename) {
        //저장되는 확장자명
        String ext = extractExt(originalFilename);
        //서버에 저장하는 파일명
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext;
    }

    //확장자를 추출하여 준다.
    private String extractExt(String originalFilename) {
        //image.png
        int pos = originalFilename.lastIndexOf(".");    //원본파일의 . 위치를 가져옴
        return originalFilename.substring(pos + 1);   //. 다음 확장자를 뽑아옴
    }


}
