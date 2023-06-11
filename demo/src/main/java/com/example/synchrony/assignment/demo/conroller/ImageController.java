package com.example.synchrony.assignment.demo.conroller;

import java.io.IOException;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.synchrony.assignment.demo.model.userImages;
import com.example.synchrony.assignment.demo.model.userProfile;
import com.example.synchrony.assignment.demo.repository.ImageRepository;
import com.example.synchrony.assignment.demo.service.imageService;

@RestController("/image")
public class ImageController {

	@Autowired
	private imageService imgsrvc;
	
	
	@PostMapping(value="/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	 public ResponseEntity<String> uploadJob(@RequestPart MultipartFile file  ) throws IOException{
		userImages images= new userImages();
		
	
			Map<String,String> map=imgsrvc.uploadimage(file);
			int status= Integer.parseInt(map.get("status"));
			if (status >= 200 && status < 300) {
				HttpHeaders header=new HttpHeaders();
				header.set("ref",map.get("link") );
			return ResponseEntity.status(status).headers(header).body("image uplaod");
			}
			else
			{
				return ResponseEntity.status(status).body("image uplaod failed");
			}

	    }
/*	@GetMapping(
            value = "getImage/{id}",
            produces = {MediaType.IMAGE_JPEG_VALUE,MediaType.IMAGE_GIF_VALUE,MediaType.IMAGE_PNG_VALUE}
    )
    public @ResponseBody byte[] getImageWithMediaType(@PathVariable(name = "id") String id) throws IOException {
        return imgsrvc.getImageWithMediaType(id);
    }*/
	
	
	
	@DeleteMapping(
            value = "getImage/{id}"    )
    public ResponseEntity  deleteImageWithMediaType(@PathVariable(name = "id") String id) throws IOException {
        int status= imgsrvc.deleteImagewithMediaTyp(id);
        return ResponseEntity.status(status).build();
    }
	
}
