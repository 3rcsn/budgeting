package org.ercsn.budgeting;

import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.ai.openai.OpenAiAudioTranscriptionModel;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@EnabledIfEnvironmentVariable(named = "OPENAI_API_KEY", matches = ".+")
public class OpenAITranscriptionModelIT {
    @Autowired
    OpenAiAudioTranscriptionModel openAiAudioTranscriptionModel;

    @ParameterizedTest
    @CsvSource({
            "recording-1.m4a, 80 reais",
            "recording-2.m4a, 100 reais",
    })
    public void should_containExcpectedKeywords_when_audioFilesAreProcessed(String fileName, String expectedKeyword) {
        var recording = new ClassPathResource("audio/" + fileName);

        var response = openAiAudioTranscriptionModel.call(recording);

        assertThat(response).contains(expectedKeyword);
        System.out.println(response);
    }
}
