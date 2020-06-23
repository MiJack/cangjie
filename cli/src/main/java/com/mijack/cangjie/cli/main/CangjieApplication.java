package com.mijack.cangjie.cli.main;

import com.mijack.cangjie.cli.config.CangjieConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * @author Mi&Jack
 */
@SpringBootApplication
@Import(CangjieConfig.class)
public class CangjieApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(CangjieApplication.class, args);
    }

}