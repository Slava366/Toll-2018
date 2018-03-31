package controllers;

import jdev.dto.State;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class StateController {

    private static Logger LOG = LoggerFactory.getLogger(StateController.class);

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "/state", method = RequestMethod.POST)
    public void getState(@RequestParam(value = "stateInfo") String stateInfo) {
        // Выводим в лог информацию о состоянии
        LOG.info(stateInfo);
    }
}
