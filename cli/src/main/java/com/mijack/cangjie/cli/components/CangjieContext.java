package com.mijack.cangjie.cli.components;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.util.Objects;

/**
 * @author Mi&Jack
 */
@Component
public class CangjieContext implements InitializingBean {
    @Getter
    private File workspace;
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
        if (Objects.equals(path, ".")) {
            return;
        }
        if (Objects.equals(path, "..")) {
            workspace = workspace.getParentFile();
            return;
        }
        if (path.indexOf("~") > 0) {
            return;
        }
        if (path.indexOf("~") == 0) {
            workspace = new File(path.replace("~", Objects.requireNonNull(environment.getProperty(USER_DIR_KEY))));
            return;
        }
        workspace = new File(workspace, path);

    }
}
