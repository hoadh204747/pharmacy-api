package com.howie.pharmacy.pharmacy_store.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class CloudinaryService {

    private final Cloudinary cloudinary;

    public CloudinaryService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public List<String> uploadImages(List<MultipartFile> files, Transformation transformation)
            throws IOException {
        List<String> urls = new ArrayList<>();
        List<Transformation> eagerTransformationsList = Collections.singletonList(transformation);

        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                Map<String, Object> options = ObjectUtils.emptyMap(); // Khởi tạo options rỗng

                if (transformation != null) {
                    // Nếu có eager transformations được cung cấp, thêm chúng vào options
                    options = ObjectUtils.asMap("eager", eagerTransformationsList);
                }

                // Thực hiện upload và lấy URL gốc
                Map uploadResult = cloudinary.uploader().upload(file.getBytes(), options);
                List<Map<String, Object>> eagerResults = (List<Map<String, Object>>) uploadResult.get("eager");
                urls.add((String) eagerResults.get(0).get("url"));
            }
        }
        return urls; // Trả về danh sách các URL gốc
    }

    // Phương thức upload cơ bản không có transformation
    public Map uploadImage(MultipartFile file) throws IOException {
        return cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
    }
}