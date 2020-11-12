package cuit.hotel.controller.common;

import cn.hutool.core.io.FileUtil;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

@Controller
public class FileController {

    @GetMapping("/hotel_imgs/{img_name}")
    public ResponseEntity<byte[]> getHotelImg(@PathVariable String img_name, HttpServletRequest req, HttpServletResponse resp) throws Exception{
        ApplicationHome h = new ApplicationHome(getClass());
        File jarF = h.getSource();
        String basePath = jarF.getParentFile().toString();
        String path = basePath+"/imgs/hotel_imgs/"+img_name;
        byte[] imageContent = FileUtil.readBytes(new File(path));
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(imageContent, headers, HttpStatus.OK);
    }

    @GetMapping("/room_type_imgs/{img_name}")
    public ResponseEntity<byte[]> getRoomTypeImg(@PathVariable String img_name, HttpServletRequest req, HttpServletResponse resp) throws Exception{
        ApplicationHome h = new ApplicationHome(getClass());
        File jarF = h.getSource();
        String basePath = jarF.getParentFile().toString();
        String path = basePath+"/imgs/room_type_imgs/"+img_name;
        byte[] imageContent = FileUtil.readBytes(new File(path));
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(imageContent, headers, HttpStatus.OK);
    }
}
