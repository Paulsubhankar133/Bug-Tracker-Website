import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.bug.project.email.controller.EmailController;
import com.example.bug.project.email.entity.Mail;
import com.example.bug.project.email.service.IMailService;

@ExtendWith(MockitoExtension.class)
class EmailControllerTest {

	@InjectMocks
	private EmailController emailController;

	@Mock
	private IMailService iMailService;

	@Test
	void testSendMail() {
		Mail mail = new Mail();
		doNothing().when(iMailService).sendEmail(mail);
		emailController.sendMail(mail);
		verify(iMailService, times(1)).sendEmail(mail);
	}
}
