package com.xqSupport.common.Utils;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.UUID;

/**
 * Created by xu on 2017/8/3.
 */
public class FileUtils {

    //辅导班课程图片
    public static String PATH_TUCLASS_COURCE_PIC = "tuclass/cource";

    /**
     * @param file
     * @param path              String path = request.getSession().getServletContext().getRealPath("/") + releatedPath + "/";
     * @param convertedfileName convertFilerName(file.getOriginalFilename());
     * @return
     */
    public static boolean saveFile(MultipartFile file, String path, String convertedfileName) {
        // 判断文件是否为空
        if (!file.isEmpty()) {
            try {
                // 文件保存路径

                // 转存文件
                File targetFile = new File(path + convertedfileName);
                if (!targetFile.exists()) {
                    targetFile.mkdirs();
                }
                file.transferTo(targetFile);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static String convertFilerName(String uploadfileRealName) {
        String[] strings = uploadfileRealName.split("\\.");
        if (strings != null && strings.length == 2) {
            return (UUID.randomUUID() + "." + strings[1]).replaceAll("-", "");
        }
//        if (uploadfileRealName==null||uploadfileRealName.length()==0 ){
//            return UUID.randomUUID()+"";
//        }
        return (UUID.randomUUID() + "").replaceAll("=", "");
    }

    /**
     * 常用
     *
     * @param request
     * @param file
     * @param releatedPath : upload \ upload/bookitemImg
     * @return 存储文件的后缀路径
     */
    public static String saveFile(HttpServletRequest request, MultipartFile file, String releatedPath) {
        String path = request.getSession().getServletContext().getRealPath("/") + releatedPath + "/";
        String convertFilerName = convertFilerName(file.getOriginalFilename());
        if (saveFile(file, path, convertFilerName)) {
            return "/" + releatedPath + "/" + convertFilerName;
        }
        return null;
    }

}
