package com.project.receiverrest.controller


import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class ContextTest extends Specification {

    def "when context is loaded then all expected beans are created"() {
        expect: "the WebController is created"

    }
}
