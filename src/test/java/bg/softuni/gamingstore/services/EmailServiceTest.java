package bg.softuni.gamingstore.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EmailServiceTest {

    @Autowired
    private EmailService emailService;

    @Test
    public void  emailSendingShouldReturnTrue(){
        String from = "test@test.com";
        boolean b = emailService.sendMail(from);
        assertTrue(b);
    }

    @Test(expected = NullPointerException.class)
    public void  emailSendingShouldFail(){
        String from = null;
        boolean b = emailService.sendMail(from);
        assertTrue(b);
    }

}
