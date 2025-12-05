package com.developbetter

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Import
import spock.lang.Specification

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class KafkaClusterDemoApplicationSpec extends Specification {

	@Autowired
	ApplicationContext context

	void "context loads"() {
		expect:
		context
	}
}
