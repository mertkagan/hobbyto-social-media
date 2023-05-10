package com.mertkagan.hobbyto.business.concretes;

import com.mertkagan.hobbyto.business.abstracts.StorageService;
import com.mertkagan.hobbyto.core.utilities.ImageUtils;
import com.mertkagan.hobbyto.dataAccess.abstracts.StorageRepository;
import com.mertkagan.hobbyto.entities.concretes.ImageData;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StorageManager implements StorageService {

    private StorageRepository storageRepository;
    @Override
    public String uploadImage(MultipartFile file) throws IOException {
        ImageData imageData = storageRepository.save(ImageData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtils.compressImage(file.getBytes())).build());
        if (imageData != null) {
            return "file uploaded successfully : " + file.getOriginalFilename();
        }
        return null;
    }

    @Override
    public byte[] downloadImage(String fileName) {
        Optional<ImageData> dbImageData = storageRepository.findByName(fileName);
        byte[] images=ImageUtils.decompressImage(dbImageData.get().getImageData());
        return images;
    }
}
