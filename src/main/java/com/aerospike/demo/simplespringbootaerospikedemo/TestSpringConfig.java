package com.aerospike.demo.simplespringbootaerospikedemo;

import com.aerospike.demo.simplespringbootaerospikedemo.configuration.AerospikeConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.aerospike.core.AerospikeTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.stream.Stream;

public class TestSpringConfig {
  public static void main(String[] args) throws IOException {
    System.out.println(ClassLoader.getSystemResourceAsStream("application.properties").available());

    Properties props = new Properties();
    InputStream resourceAsStream =  ClassLoader.getSystemResourceAsStream("application.properties");
    if (resourceAsStream != null) {
      props.load(resourceAsStream);
    }
    if(props != null) {
      System.setProperties(props);
    }
    try {
      ApplicationContext ctx = new AnnotationConfigApplicationContext(AerospikeConfiguration.class);
      AerospikeTemplate template = ctx.getBean(AerospikeTemplate.class);
    } catch (Exception e) {
      StackTraceElement[] st = e.getStackTrace();
      if(st != null) {
        System.out.println("st.length = " + st.length);
      }
      Stream.of(st).forEach(System.out::println);
    } catch (ExceptionInInitializerError e) {
      StackTraceElement[] st = e.getStackTrace();
      if(st != null) {
        System.out.println("st.length = " + st.length);
      }
      Stream.of(st).forEach(System.out::println);
    }

  }
}
