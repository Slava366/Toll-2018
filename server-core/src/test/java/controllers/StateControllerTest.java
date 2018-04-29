package controllers;

import jdev.dto.repo.StateRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class StateControllerTest {

    private String json = "{\"id\":0,\"latitude\":56.0,\"longitude\":74.0,\"autoId\":\"o567gfd\",\"time\":1521964156074,\"speed\":95.3,\"azimuth\":89.1}";

    @Mock
    StateRepository stateRepository;

    @InjectMocks
    StateController mockedController;

    @Test
    public void getState() {
        // Отправляем неверную строку
        ServerResponse response = mockedController.getState("State");
        assertEquals(false, response.isSuccess());
        // Отправляем корректную строку json состояния
        response = mockedController.getState(json);
        assertEquals(true, response.isSuccess());
    }
}