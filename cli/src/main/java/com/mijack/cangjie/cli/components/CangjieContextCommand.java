package com.mijack.cangjie.cli.components;

import lombok.SneakyThrows;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import javax.annotation.Resource;
import java.io.File;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Mi&Jack
 */
@ShellComponent()
public class CangjieContextCommand {
    @Resource
    private CangjieContext cangjieContext;

    @ShellMethod(value = "ls the file in the context folder", key = "ls")
    public String ls() {
        return Arrays.stream(Objects.requireNonNull(cangjieContext.getWorkspace().listFiles()))
                .map(File::getName).collect(Collectors.joining("\n"));
    }

    @ShellMethod(value = "Show the context folder", key = "pwd")
    public File pwd() {
        return cangjieContext.getWorkspace();
    }

    @SneakyThrows
    @ShellMethod(value = "change the context folder", key = "cd")
    public String cd(String path) {
        try {
            cangjieContext.switchPath(path);
            return "cd the path:" + Paths.get(cangjieContext.getWorkspace().getAbsolutePath());
        }catch (Exception e){
            return e.getLocalizedMessage();
        }}

}
