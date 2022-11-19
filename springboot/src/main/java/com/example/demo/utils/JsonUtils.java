package com.example.demo.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.text.SimpleDateFormat;

public class JsonUtils
{

  public static String getJson(Object object)
  {
    ObjectMapper mapper = new ObjectMapper();
    String str = null;
    try {
      str = mapper.writeValueAsString(object);
      return str;
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    return null;
  }
  public static String getJson(Object object,String dateFormat)
  {
    ObjectMapper mapper = new ObjectMapper();
    mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,false);
    SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
    mapper.setDateFormat(sdf);
    String str = null;
    try {
      str = mapper.writeValueAsString(object);
      return str;
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    return null;
  }
}
