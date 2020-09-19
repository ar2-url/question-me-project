package com.team2.questionme;

import com.team2.questionme.model.Question;
import com.team2.questionme.model.User;
import com.team2.questionme.repository.QuestionRepository;
import com.team2.questionme.repository.UserRepository;
import com.team2.questionme.service.CategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QuestionMeApplicationTests {


    public static final String CATEGORY_1 = "KaT1";
    public static final String CATEGORY_2 = "KAt2";

    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CategoryServiceImpl categoryService;

    // Drop "question-me" schema before running test, then add query to create empty database,
    // otherwise test does not work.

    @BeforeEach
    void init() {
        questionRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void getAll() {
        // Given
        UUID rand = UUID.randomUUID();
        User adam = userRepository.save(new User("Adam"+rand, "secret", rand + "adam@ma.com", "ADA" + rand));
        Question question1 = new Question(adam, "Co to", CATEGORY_1);
        Question question2 = new Question(adam, "Co to 2", CATEGORY_2);

        questionRepository.save(question1);
        questionRepository.save(question2);
        // When
        List<String> categories = categoryService.getAll();
        // Then
        assertNotNull(categories); // sanity check
        assertEquals(2, questionRepository.findAll().size());
        assertArrayEquals(new String[]{CATEGORY_1.toLowerCase(),CATEGORY_2.toLowerCase()}, categories.toArray());
    }

}
