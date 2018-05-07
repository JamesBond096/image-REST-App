package imageApp.Controller;


import imageApp.Service.ImageService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import imageApp.Exceptions.KeywordNotFoundException;

import javax.imageio.ImageIO;

import org.springframework.http.MediaType;

import java.io.ByteArrayOutputStream;

//this is used to get all requests from end-points involving images
@RestController
@RequestMapping(value = "/image")
public class ImageController {

    @Autowired
    private ImageService ImageService;

    //loads a given image to MAP and returns it's unique id
    @RequestMapping(method = RequestMethod.POST  )
    public String addImage(HttpServletRequest requestEntity) throws Exception {
        String id =ImageService.addImage(requestEntity.getInputStream().readAllBytes());
        return id;
    }
    //shows all images currently loaded
    @RequestMapping(method = RequestMethod.GET)
    public String getAllImages(){
        return ImageService.getAllImages().toString();
    }

    //deletes an image form app by given id
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void removeImageById(@PathVariable Integer id) throws Exception {
        if (ImageService.getImageById(id)==null) {
            throw new KeywordNotFoundException(id.toString() +" does not exist");
        }
        ImageService.removeImageById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getImageById(@PathVariable Integer id) throws Exception {
        if (ImageService.getImageById(id)==null) {
            throw new KeywordNotFoundException(id.toString() +" does not exist");
        }
        return  ImageService.fromBufferedImageToBytes(ImageService.getImageById(id));
    }


    @RequestMapping(value = "/{id}/size", method = RequestMethod.GET)
    public String getImageSizeById(@PathVariable Integer id){
        if (ImageService.getImageById(id)==null) {
            throw new KeywordNotFoundException(id.toString() +" does not exist");
        }
        JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.append("size", ImageService.getImageSizeById(id));
            jsonObj.append("height", ImageService.getImageHeigthById(id));
            jsonObj.append("width", ImageService.getImageWidthById(id));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObj.toString();
    }

    @RequestMapping(value = "/{id}/histogram", method = RequestMethod.GET)
    public String getImageHistogramById(@PathVariable Integer id){
        if (ImageService.getImageById(id)==null) {
            throw new KeywordNotFoundException(id.toString() +" does not exist");
        }
        JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.append("Histogram", ImageService.getImageHistogramById(id));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObj.toString();
    }

    @RequestMapping(value = "/{id}/crop", method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] cropImageById (
            @PathVariable Integer id,
            @RequestParam(value = "X") int X,
            @RequestParam(value = "Y") int Y,
            @RequestParam(value = "width") int width,
            @RequestParam(value = "height") int height

    )throws Exception{
        if (ImageService.getImageById(id)==null) {
            throw new KeywordNotFoundException(id.toString() +" does not exist");
        }
       return  ImageService.fromBufferedImageToBytes(ImageService.cropImageById(id,X,Y,width,height));
   }

}
