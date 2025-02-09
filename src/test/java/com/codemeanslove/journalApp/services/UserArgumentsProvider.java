package com.codemeanslove.journalApp.services;

import com.codemeanslove.journalApp.entity.User;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class UserArgumentsProvider implements ArgumentsProvider {// Arguments s h ok

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        return Stream.of(
                Arguments.of(User.builder().userName("prem").password("prem").build()),//false bcoz already h
                Arguments.of(User.builder().userName("abcd").password("").build())//without pass ke  bhi save ho rha h
        );                      //esko shi nhi kiya h but kr lege future me
    }          // ^-- yha pr constructor ka use bhi kr sakte the user ko create krne k liye
}
