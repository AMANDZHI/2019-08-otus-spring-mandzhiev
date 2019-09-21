package amandzhi.springBoot.executor;

import amandzhi.springBoot.dao.Question;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import amandzhi.springBoot.service.IOServiceServiceImpl;
import amandzhi.springBoot.util.LocaleProps;
import amandzhi.springBoot.service.QuestionService;

import java.util.List;

@Service
@AllArgsConstructor
public class TestingAppImpl implements TestingApp {
    private final QuestionService service;
    private final IOServiceServiceImpl ioServiceService;
    private int result;
    private final String QUESTION = "question";
    private final String FINAL_TEXT = "finalText";
    private final LocaleProps localeProps;
    private final MessageSource messageSource;

    @Autowired
    public TestingAppImpl(QuestionService service, IOServiceServiceImpl ioServiceService, LocaleProps localeProps, MessageSource messageSource) {
        this.service = service;
        this.ioServiceService = ioServiceService;
        this.localeProps = localeProps;
        this.messageSource = messageSource;
    }

    public void start() {
        String questionName = messageSource.getMessage(QUESTION, null, localeProps.getLocale());
        List<Question> questionsAndAnswers = service.getQuestionsAndAnswers();
        ioServiceService.printString(questionName);
        String name = ioServiceService.readString();
        for (Question q: questionsAndAnswers) {
            ioServiceService.printString(q.getText());
            String answer = ioServiceService.readString();
            if (answer.equals(q.getValidAnswer())) {
                result++;
            }
        }
        String validText = messageSource.getMessage(FINAL_TEXT, null, localeProps.getLocale());
        System.out.println(name + validText + result);
    }
}