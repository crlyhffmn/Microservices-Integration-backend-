package com.revature.project3.services;

import com.revature.project3.entities.Reaction;
import com.revature.project3.repositories.ReactionsRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
@AutoConfigureMockMvc
class ReactionsServiceImplTest {

    Reaction testReaction;
    Reaction fullReaction;

    @BeforeEach
    void setUp() {
        testReaction = new Reaction();
        testReaction.setReaction(5);

        fullReaction = testReaction;
        fullReaction.setReactionId(10L);
    }

    @AfterEach
    void tearDown() {
        testReaction = null;
        fullReaction = null;
    }
    @Autowired
    private ReactionsServiceImpl reactionsService;

    @MockBean
    private ReactionsRepository repository;

    @Test
    public void whenReactionIsProvided_ThenReactionWithIdIsReturned(){
        Mockito.when(repository.save(testReaction)).thenReturn(fullReaction);

        assertSame(fullReaction, reactionsService.addReaction(testReaction));
    }

    @Test
    public void whenReactionIdIsProvided_ThenReactionIsReturned(){
        Mockito.when(repository.findById(10L)).thenReturn(java.util.Optional.ofNullable(fullReaction));

        assertSame(fullReaction, reactionsService.getReactionByReactionId(10L));
    }

    @Test
    public void whenReactionAndIdIsProvided_ThenReactionIsReturned(){
        Mockito.when(repository.findById(10L)).thenReturn(java.util.Optional.ofNullable(fullReaction));
        Mockito.when(repository.save(fullReaction)).thenReturn((fullReaction));

        assertSame(fullReaction, reactionsService.updateReaction(testReaction, 10L));
    }

    @Test
    public void whenReactionIdIsProvided_ThenReturnString(){
        Mockito.doNothing().when(repository).deleteById(10L);

        assertSame("Entry Successfully Deleted", reactionsService.deleteReaction(10L));
    }
}