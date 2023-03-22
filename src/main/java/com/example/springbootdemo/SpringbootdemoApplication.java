package com.example.springbootdemo;

import com.example.springbootdemo.security.UserCredentials;
import com.example.springbootdemo.security.UserCredentialsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.ZoneId;
import java.util.Locale;
import java.util.TimeZone;

@SpringBootApplication
public class SpringbootdemoApplication {

    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        SpringApplication.run(SpringbootdemoApplication.class, args);
    }

//    @Bean
//    CommandLineRunner runOnStartUp(UserCredentialsRepository repo, PasswordEncoder passwordEncoder) {
//        return new CommandLineRunner() {
//            @Override
//            public void run(String... args) throws Exception {
//                //Place code here that should run after spring has started
//                var user = repo.findByName("admin");
//                if (user == null) {
//                    var u = new UserCredentials();
//                    u.setName("admin");
//                    u.setPassword(passwordEncoder.encode("admin"));
//                    repo.save(u);
//                }
//            }
//        };
//    }

}
