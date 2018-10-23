package zz.service;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Service("imageService")
public class ImageServiceImpl implements ImageService{

    static SimpleDateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
    static Random random = new Random();

    /**
     * 文件存储
     * @param sourceFile 原始文件名
     * @param targetPath 目标文件夹
     * @return
     * @throws IOException
     */
    @Override
    public String save(MultipartFile sourceFile, String targetPath) throws IOException {
        String autoFileName =  generateAutoFileName(sourceFile.getOriginalFilename());
        String targetFileName = targetPath + File.separator + autoFileName;
        // 生成图片的缩略图
        Thumbnails.of(sourceFile.getInputStream()).size(200,200).toFile(new File(targetFileName));
        return autoFileName;
    }

    /**
     * 动态生成文件名字
     * @param sourceFileFileName
     * @return
     */
    public String generateAutoFileName(String sourceFileFileName){
        return df.format(new Date()) + random.nextInt(10000) + "." + StringUtils.getFilenameExtension(sourceFileFileName);
    }
}
