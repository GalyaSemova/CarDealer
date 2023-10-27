package com.example.mobilele.init;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBinit implements CommandLineRunner {

    private final String defaultAdminPass;

    public DBinit(@Value("${mobilele.default.admin.pass}") String defaultAdminPass) {
        this.defaultAdminPass = defaultAdminPass;
    }

    @Override
    public void run(String... args) throws Exception {

//        TODO

    }
}
