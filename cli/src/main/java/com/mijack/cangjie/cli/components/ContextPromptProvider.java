package com.mijack.cangjie.cli.components;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Mi&Jack
 */
@Component
public class ContextPromptProvider implements PromptProvider {
    @Resource
    private CangjieContext cangjieContext;

    @Override
    public AttributedString getPrompt() {
        return new AttributedString(cangjieContext.getWorkspacePath4Shell()+":>", AttributedStyle.DEFAULT.foreground(AttributedStyle.YELLOW));
    }
}
