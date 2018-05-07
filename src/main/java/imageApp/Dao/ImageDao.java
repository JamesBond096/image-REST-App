package imageApp.Dao;

import imageApp.Entity.Histogram;
import org.springframework.stereotype.Repository;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

//this is used for all operations on stored data

@Repository
public class ImageDao {
    private static Integer id=0;
    private static Map<String, BufferedImage> Images;

    static{ Images = new HashMap<String,BufferedImage>();}
    public Collection<BufferedImage> getAllImages(){
        return this.Images.values();
    }
    public BufferedImage getImageById(Integer id){
        return this.Images.get(id.toString());
    }
    public String addImage(BufferedImage image){
        String uniqueId = id.toString();
        this.Images.put(uniqueId,image);
        id++;
        return uniqueId;
    }

    public void removeImageById(int id){
        this.Images.remove(id);
    }

    public long getImageSizeById(Integer id){
        DataBuffer dataBuffer = this.Images.get(id.toString()).getData().getDataBuffer();
        long sizeBytes = ((long) dataBuffer.getSize()) * 4l;
        return sizeBytes;
    }
    public Integer getImageWidthById(Integer id){
          return this.Images.get(id.toString()).getWidth();
    }
    public Integer getImageHeigthById(Integer id){
        return this.Images.get(id.toString()).getHeight();
    }
    public String getImageHistogramById(Integer id){
        Histogram histogram = new Histogram(this.Images.get(id.toString()));
        return histogram.toString();
    }


}
