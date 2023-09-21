package com.alexg.alexginterface;

import com.alexg.alexgclientsdk.client.AlexgApiClient;
import com.alexg.alexgclientsdk.model.User;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

@SpringBootTest
class AlexgInterfaceApplicationTests {

    @Autowired(required = true)
    private AlexgApiClient alexgApiClient;

    @Test
    void testApiClient(){
        String res1 = alexgApiClient.getNameByGet("qishen");
        String res2 = alexgApiClient.getNameByPost("alexg");
        User user = new User();
        user.setUsername("高峥峻");
        String res3  = alexgApiClient.getNameByPostWithJson(user);
        System.out.println(res1);
        System.out.println(res2);
        System.out.println(res3);
    }


}
