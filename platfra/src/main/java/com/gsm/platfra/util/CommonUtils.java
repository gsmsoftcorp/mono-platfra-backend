package com.gsm.platfra.util;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Random;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class CommonUtils {

    //정규식 숫자 체크.
    public static boolean isNumber(String str) {
        return str.matches("^[0-9]*$");
    }

    public static String getRandomString(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * Json to Object Convert
     * @param json
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public static HashMap<String, Object> convertJsonToObject(String json) throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<HashMap<String, Object>> typeReference = new TypeReference<HashMap<String, Object>>() { };
        HashMap<String, Object> object = objectMapper.readValue(json, typeReference);
        return object;
    }

    /**
     * Json to List Convert.
     * @param json
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public static List<?> convertJsonToList(String json) throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        //TypeReference<HashMap<String, Object>> typeReference = new TypeReference<HashMap<String, Object>>() { };
        List<?> list = objectMapper.readValue(json, List.class);
        return list;
    }

    public static MediaType contentType(String extension) {
        switch (extension) {
            case "txt":
                return MediaType.TEXT_PLAIN;
            case "png":
                return MediaType.IMAGE_PNG;
            case "jpg":
                return MediaType.IMAGE_JPEG;
            default:
                return MediaType.APPLICATION_OCTET_STREAM;
        }
    }

    public static String getGenerateCode(){

        Random random = new Random();

        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < 6 ; i ++){
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
}