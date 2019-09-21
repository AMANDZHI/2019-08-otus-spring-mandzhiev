package amandzhi.springBoot.dao;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Question {
    private final String text;
    private final String validAnswer;
}