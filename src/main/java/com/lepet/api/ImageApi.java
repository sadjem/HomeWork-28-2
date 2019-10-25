package com.lepet.api;

import com.lepet.dao.ImageDao;
import com.lepet.model.Image;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.ByteArrayInputStream;
import java.util.Base64;

@Path("/")
public class ImageApi {
    ImageDao imageDao = new ImageDao();

    @POST
    @Path("/addImage")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response addImage(@FormParam("imageInBase64") String imageInBase64,
                             @FormParam("aboutImage") String aboutImage){
        Image image = new Image(imageInBase64,aboutImage);
        return Response
                .status(Response.Status.OK)
                .entity("Image add with id: " + imageDao.addImage(image))
                .build();
    }
    @Path("/getImages")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getImages (){
        return Response
                .status(Response.Status.OK)
                .entity(imageDao.getAllImages().toString())
                .build();
    }
    @Path("/{id}")
    @GET
    @Produces("/image/png")
    public Response getImageById (@PathParam("id") int id){
        try {
            byte [] arr = Base64.getDecoder()
                    .decode(imageDao.getImageById(id).getImageInBase64());
            return Response
                    .ok(new ByteArrayInputStream(arr))
                    .build();
        }catch (Exception e){
            return  Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(e)
                    .build();
        }
    }
}
