package bg.softuni.gamingstore.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertNotEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CloudinaryServiceTest {

    @Autowired
    private CloudinaryService cloudinaryService;

    @Test
    public void uploadingImageShouldWork() throws IOException {
        Path path = Paths.get("src/main/resources/static/assets/images/favicon.png");
        String name = "picture.png";
        String originalFileName = "picture.png";
        String contentType = "image/png";
        byte[] content = null;
        try{
            content = Files.readAllBytes(path);
        } catch (Exception ignored){

        }

        MultipartFile result = new MockMultipartFile(name,
                originalFileName, contentType, content);

        String s = this.cloudinaryService.uploadImage(result);

        assertNotEquals(null, s);
    }

    @Test(expected = RuntimeException.class)
    public void uploadingImageShouldFail() throws IOException {
        String name = "picture.png";
        String originalFileName = "picture.png";
        String contentType = "image/png";
        byte[] content = null;

        MultipartFile result = new MockMultipartFile(name,
                originalFileName, contentType, content);

        String s = this.cloudinaryService.uploadImage(result);

        assertNotEquals(null, s);
    }
}
