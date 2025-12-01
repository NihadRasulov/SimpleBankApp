package BankApp.service.image;

import BankApp.dto.PhotoResponseDto;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class ImageService {

    private final Cloudinary cloudinary;


    public PhotoResponseDto uploadPhoto(MultipartFile file, String folder) throws IOException {

        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }

        Map<?, ?> uploadResult = cloudinary.uploader().upload(
                file.getBytes(),
                ObjectUtils.asMap("folder", folder)
        );

        String url = uploadResult.get("secure_url").toString();
        String publicId = uploadResult.get("public_id").toString();

        return new PhotoResponseDto(url, publicId);
    }

    public boolean deletePhoto(String publicId) throws IOException {
        Map<?, ?> result = cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
        return "ok".equals(result.get("result")); // true if deleted successfully
    }

    // ---- Upload Multiple Photos ----
    public List<PhotoResponseDto> uploadPhotos(List<MultipartFile> files, String folder) throws IOException {
        return files.stream().map(file -> {
            try {
                return uploadPhoto(file, folder);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).toList();
    }

}
