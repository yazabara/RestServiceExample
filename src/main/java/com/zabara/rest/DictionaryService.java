package com.zabara.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import com.zabara.beans.*;
import com.zabara.dao.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Yaroslav_Zabara
 * Date: 23.01.13
 * Time: 19:21
 */
@Path("/dictionary")
//Это Resource Class
public class DictionaryService {

    private static Dictionary dictionary = DictionaryImpl.getInstance();

    @GET
    @Path("definition/{word}")
    //Resource method
    public Response getWordDefinition(@PathParam("word") String word){
        String definition = dictionary.getDefinitionByWord(word);
        if (definition == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(definition).build();
    }

    @DELETE
    @Path("delete/{word}")
    @Produces("text/plain")
    public Response deleteWord(@PathParam("word") String word){
        dictionary.removeWord(word);
        return Response.status(Response.Status.ACCEPTED).build();
    }

    @POST
    @Path("definition/{word}")
    public Response postWord(@PathParam("word") String word){
        String definition = dictionary.getDefinitionByWord(word);
        if (definition == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(definition).build();
    }

    @GET
    @Path("definition/{id:[0-9]+}")
    //Resource method
    @Produces("application/xml")
    public Response getWordById(@PathParam("id")long id){
        Word word = dictionary.getWordById(id);
        if (word == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(word).build();
    }

    @GET
    @Path("/list")
    public List<Word> getListWords(
            @QueryParam("word") String word,
            @QueryParam("definition") String definition,
            @QueryParam("orderBy") List<String> orderBy
    ){
        List<Word> words = null;
        try {
            words = dictionary.getWords(word,definition,orderBy);
        } catch (Exception ex) {
            return null;//Response.status(Response.Status.BAD_REQUEST).build();
        }
        if (words == null) {
            return null;//Response.status(Response.Status.NOT_FOUND).build();
        }
        return words;
        //return Response.ok(words).build();
    }

}
