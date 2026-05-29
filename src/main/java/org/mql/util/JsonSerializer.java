package org.mql.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonSerializer<T> {


    public String toJson(T dataObj){
        //
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            String json =  objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(dataObj);
            return json;
        }
        catch(JsonProcessingException e ){
            throw new RuntimeException(e);
        }
    }
}
