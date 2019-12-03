/*
 *    Copyright 2019 Mi&Jack
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *
 */

package com.mijack.cangjie;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.core.env.StandardEnvironment;

/**
 * @author Mi&Jack
 */
@SpringBootApplication
public class CangjieApplication implements CommandLineRunner {
public static final Logger logger = LoggerFactory.getLogger(CangjieApplication.class);
    public static void main(String[] args) {
        SpringApplication application = new SpringApplicationBuilder(CangjieApplication.class)
                .environment(new StandardEnvironment())
                .application();
        application.run(args);
    }

    @Override
    public void run(String... args) {
        logger.info("run info: args = {}", args);
    }
}
