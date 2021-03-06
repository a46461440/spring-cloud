package com.zxc.springcloud;

import com.zxc.springcloud.action.HelloWorldAction;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringCloudApplicationTests {

	private MockMvc mockMvc;

	@Before
	public void set() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(new HelloWorldAction()).build();
	}

	@Test
	public void contextLoads() throws Exception {
//		mockMvc.perform(MockMvcRequestBuilders.get("/hello").accept(MediaType.APPLICATION_JSON))
//				.andExpect(status().isOk()).andExpect(content().string(equalTo("hello world")));
	}

}
