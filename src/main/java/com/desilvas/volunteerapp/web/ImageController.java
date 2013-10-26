package com.desilvas.volunteerapp.web;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.desilvas.volunteerapp.service.BusinessFacade;
import com.desilvas.volunteerapp.service.BusinessResponse;
import com.mysql.jdbc.PacketTooBigException;


/**
 * Handles requests for the application home page.
 */
@Controller
public class ImageController {
	
	private static final Logger log = LoggerFactory.getLogger(ImageController.class);
	
	@Autowired
	ServletContext sc;
	
	@Resource
	BusinessFacade facade;
	
		
	
	@RequestMapping(value="/loadImage", method = RequestMethod.GET)
	public @ResponseBody byte[] loadImage(HttpServletRequest request, HttpServletResponse resp) {

		String userId = request.getParameter("userId");
	
		resp.setContentType("image/png");
		
		Integer userid = null;
		
		if(userId!=null){
		
			if("currentuser".equals(userId.trim())){
				userid=facade.getLoggedInUserId();
			}else{
				userid = Integer.valueOf(userId);
			}
			
			return facade.getImage(userid);
		}
		
		
		return null;
		
		
		 
	}
	
	

	@RequestMapping(value="/uploadImage", method = RequestMethod.POST)
	public @ResponseBody String uploadImage(@RequestParam MultipartFile image, HttpServletRequest req, HttpServletResponse resp) throws IOException {

		
		
		BusinessResponse response = facade.uploadImage(image.getBytes(),false);
		
		
				

		return "ok";
        

        
		
		 
	}



	@RequestMapping(value="/cropImage", method = RequestMethod.POST)
	public @ResponseBody String cropImage(HttpServletRequest req) throws IOException {

		String rtop = req.getParameter("top");
		String rleft = req.getParameter("left");
		String rwidth = req.getParameter("width");
		String rheight = req.getParameter("height");
		
		System.out.println(rtop + "--" + rleft + "--" + rwidth + "--" + rheight);
		
	    int top = Math.round(Double.valueOf(rtop).floatValue());
	    int left = Math.round(Double.valueOf(rleft).floatValue());
	    int width = Math.round(Double.valueOf(rwidth).floatValue());
	    int height = Math.round(Double.valueOf(rheight).floatValue());
	    
	    byte[] ui = facade.getImage(facade.getLoggedInUserId());
	    
	    InputStream in = new ByteArrayInputStream(ui);
	    BufferedImage outImage = ImageIO.read(in);
	    BufferedImage cropped = outImage.getSubimage(left, top, width, height);
	    
	    ByteArrayOutputStream croppedOut = new ByteArrayOutputStream();
	    ImageIO.write(cropped, "png", croppedOut);

	    
	    facade.uploadImage(croppedOut.toByteArray(),true);
        
	    return "cropped";
        
		
		 
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
}

