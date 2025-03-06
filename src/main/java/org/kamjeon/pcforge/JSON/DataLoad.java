
  package org.kamjeon.pcforge.JSON;
  
  import org.springframework.boot.CommandLineRunner; import
  org.springframework.stereotype.Component;
  
  import lombok.RequiredArgsConstructor;
  
  @RequiredArgsConstructor
  
  @Component public class DataLoad implements CommandLineRunner { private final
  DataLoader loader;
  
  
  @Override public void run(String... args) throws Exception {
  loader.allDataLoder(); }
  
  }
 