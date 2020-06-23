package com.mijack.cangjie.cli.config;

import org.jline.reader.LineReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.shell.jline.JLineShellAutoConfiguration;

/**
 * @author Mi&Jack
 */
@ComponentScan("com.mijack.cangjie.cli")
public class CangjieConfig extends JLineShellAutoConfiguration {

    @Bean
    @Override
    public LineReader lineReader() {
        LineReader lineReader = super.lineReader();
        // This allows completion on an empty buffer, rather than inserting a tab
        lineReader.unsetOpt(LineReader.Option.INSERT_TAB);
        return lineReader;
    }
}
