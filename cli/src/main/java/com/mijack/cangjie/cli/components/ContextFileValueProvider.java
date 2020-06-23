package com.mijack.cangjie.cli.components;

import org.springframework.core.MethodParameter;
import org.springframework.shell.CompletionContext;
import org.springframework.shell.CompletionProposal;
import org.springframework.shell.standard.ValueProvider;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Mi&Jack
 */


@Component
public class ContextFileValueProvider implements ValueProvider {
    @Resource
    private CangjieContext cangjieContext;

    @Override
    public boolean supports(MethodParameter parameter, CompletionContext completionContext) {
        return true;
    }

    @Override
    public List<CompletionProposal> complete(MethodParameter parameter, CompletionContext completionContext, String[] hints) {

        String input = completionContext.currentWordUpToCursor();
        return Arrays.stream(Objects.requireNonNull(cangjieContext.getWorkspace().listFiles()))
                .filter(File::isDirectory)
                .filter(f -> f.getName().startsWith(input))
                .map(File::getName)
                .map(CompletionProposal::new)
                .collect(Collectors.toList())
                ;
    }

}
