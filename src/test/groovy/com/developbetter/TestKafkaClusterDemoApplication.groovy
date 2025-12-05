package com.developbetter

import org.springframework.boot.SpringApplication

class TestKafkaClusterDemoApplication {

    static void main(String[] args) {
        SpringApplication.from(KafkaClusterDemoApplication::main)
                .with(TestcontainersConfiguration.class)
                .run(args)
    }
}
