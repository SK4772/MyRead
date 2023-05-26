package com.csk.csk_english;

import com.csk.csk_english.controller.EssayController;
import com.csk.csk_english.controller.UserController;
import com.csk.csk_english.controller.WordController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CskEnglishApplicationTests {

    @Autowired
    UserController userController;
    @Autowired
    EssayController controller;
    @Autowired
    WordController wordController;
    @Test
    void tryApi()  {



    }

}
