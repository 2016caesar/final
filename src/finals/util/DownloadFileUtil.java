package finals.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;


public class DownloadFileUtil {
    static Logger logger = Logger.getLogger(DownloadFileUtil.class.getName());
    FileInputStream fileIn = null;

    public static boolean downloadFile(HttpServletRequest request,
                                       HttpServletResponse response,
                                       String fileName,
                                       String uploadDir) throws Exception {
        boolean result = true;
        BufferedOutputStream bos = null;
        FileInputStream fileIn = null;

        String userAgent = request.getHeader("User-Agent");

        try {
            if (fileName != null && fileName.length() > 0) {

                File file = new File(uploadDir + fileName);

                if (file.exists()) {

                	int fileLength = (int)file.length();
                    logger.info("file.getName()==="+file.getName());

                	if (userAgent.indexOf("MSIE 5.5") != -1) {

                    	response.setContentType("doesn/matter");
                        response.setHeader("Content-Disposition",   "filename=" + fileName + ";");
                    } else {

                    	response.setContentType("application/octet-stream");
                    	response.setHeader("Content-Disposition",   "attachment; filename=\"" +     new String(file.getName().getBytes("MS949"),    "ISO8859_1") + "\"");
                    	//jeus서버에는 euc-kr사용
                    	//response.setHeader("Content-Disposition",   "attachment; filename=\"" +     new String(file.getName().getBytes(),    "euc-kr") + "\"");
                    }

                    response.setContentLength(fileLength);

                    fileIn = new FileInputStream(file);
                    byte[] b = new byte[fileLength];

                    bos = new BufferedOutputStream(response.getOutputStream());
                    int bytesRead = 0;

                    while ((bytesRead = fileIn.read(b, 0, fileLength)) != -1) {
                        bos.write(b, 0, bytesRead);
                    }


                } else {
                    throw new FileNotFoundException();
                }
            }
        } catch (FileNotFoundException fnfe) {

        	throw new FileNotFoundException("FileNotFound( file name = " +  fileName + " )");

        } finally {
            try {
                if (fileIn != null)
                    fileIn.close();
                if (bos != null)
                    bos.close();
            } catch (java.lang.Exception e2) {
                logger.error("Can not complete closing input/out Stream.");
            }
        }

        return result;
    }
}
