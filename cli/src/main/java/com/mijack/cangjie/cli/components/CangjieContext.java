package com.mijack.cangjie.cli.components;

import lombok.Getter;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.apache.commons.lang3.Streams;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * @author Mi&Jack
 */
@Component
public class CangjieContext implements InitializingBean {
    @Getter
    private File workspace;
    @Getter
    private List<File> files = new ArrayList<>();
    @Resource
    private Environment environment;
    public static final String USER_HOME_KEY = "user.home";
    public static final String USER_DIR_KEY = "user.dir";

    public String getWorkspacePath4Shell() {
        return workspace.getAbsolutePath().replace(Objects.requireNonNull(environment.getProperty(USER_HOME_KEY)), "~");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        workspace = new File(Objects.requireNonNull(environment.getProperty(USER_DIR_KEY)));
    }

    public void switchPath(String path) {
        File newWorkspace = buildFile(workspace.getAbsolutePath(), path);
        if (newWorkspace.exists() && newWorkspace.isDirectory()) {
            workspace = newWorkspace;
            return;
        }
        throw new IllegalStateException("The folder（" +
                newWorkspace.getAbsolutePath() + "） don't exit or isn't a folder");
    }

    private File buildFile(String absolutePath, String path) {
        String result = FilenameUtils.concat(absolutePath, path);
        return new File(result);
    }

    public void addFile(String filePath) {
        WildcardFileFilter fileFilter = new WildcardFileFilter(filePath);

        Iterator<File> fileIterator = FileUtils.iterateFiles(getWorkspace(), fileFilter, TrueFileFilter.INSTANCE);
        while (fileIterator.hasNext()) {
            File next = fileIterator.next();
            if (next.isFile()) {
                files.add(next);
            }
        }

    }

    public List<File> listContextFiles() {
        return files;
    }
}
