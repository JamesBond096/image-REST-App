package imageApp.Service;

import imageApp.Dao.ImageDao;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collection;


//this is used to store methods on the images

@Service
public class ImageService {

    @Autowired
    private ImageDao ImageDao;

    public Collection<BufferedImage> getAllImages(){
        return ImageDao.getAllImages();
    }

    public String addImage(byte[] imageBytes){
        String id = ImageDao.addImage(createImageFromBytes(imageBytes));
        return id;
    }
    public BufferedImage getImageById(Integer id){
        return ImageDao.getImageById(id);
    }


    public void removeImageById(int id){
        ImageDao.removeImageById(id);
    }

    public long getImageSizeById(Integer id){
        return ImageDao.getImageSizeById(id);
    }
    public Integer getImageWidthById(Integer id){
        return ImageDao.getImageWidthById(id);
    }
    public Integer getImageHeigthById(Integer id){
        return ImageDao.getImageHeigthById(id);
    }
    public String getImageHistogramById(Integer id){
        return ImageDao.getImageHistogramById(id);
    }
    public BufferedImage cropImageById(Integer id,int X,int Y,int width,int height){
        return ImageDao.getImageById(id).getSubimage(X,Y,width,height);
    }

    public byte[] fromBufferedImageToBytes(BufferedImage img)throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(img, "jpg", baos);
        byte[] bytes = baos.toByteArray();
        return bytes;
    }
    private BufferedImage createImageFromBytes(byte[] imageData) {
        ByteArrayInputStream bais = new ByteArrayInputStream(imageData);
        try {
            return ImageIO.read(bais);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
