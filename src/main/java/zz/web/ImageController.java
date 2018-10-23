package zz.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import zz.service.ImageService;
import zz.util.WebResult;
import zz.util.WebResultCodeType;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * 图像上传控制器
 */
@Controller
public class ImageController {
    @Autowired
    private ImageService imageService;

    @ResponseBody
    @RequestMapping(value = "/images/heads",method = RequestMethod.POST)
    public WebResult<String> uploadHeadImage(MultipartFile file, HttpServletRequest request,String oldFileName){
        String realpath = request.getServletContext().getRealPath("/static/images/face");
        try {
            File oldFile = new File(realpath+ File.separator+oldFileName);
            if(oldFile.exists()){
                oldFile.delete();
            }
            String targetFileName = this.imageService.save(file,realpath);
            return new WebResult<>(StringUtils.getFilename(targetFileName));
        } catch (Exception e) {
            e.printStackTrace();
            return new WebResult<>(WebResultCodeType.ERROR,e.getMessage(),"");
        }
    }

    public ImageService getImageService() {
        return imageService;
    }

    public void setImageService(ImageService imageService) {
        this.imageService = imageService;
    }
}
