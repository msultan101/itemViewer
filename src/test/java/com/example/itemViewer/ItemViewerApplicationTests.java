package com.example.itemViewer;
import com.example.itemViewer.service.itemServices;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {  SpringRunner.class })
class ItemViewerApplicationTests {
	@Autowired
	itemServices instance;
	@Test
	public void testGetServiceAdd() {
		instance.getAdd(123,1);
	}

}
