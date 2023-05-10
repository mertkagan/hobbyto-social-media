package com.mertkagan.hobbyto.conrollers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@RestController
@RequestMapping("/image")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class ImageController {


    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            Path path = Paths
                    .get("C:\\Users\\mertk\\OneDrive\\Masaüstü\\hobby-social\\hobbyto\\client\\public\\upload\\" + fileName);

            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);


            return ResponseEntity.ok().body(fileName);
        } catch (IOException ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Dosya yüklenirken bir hata oluştu: " + ex.getMessage());
        }
    }
}
