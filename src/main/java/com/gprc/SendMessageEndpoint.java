package com.gprc;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SendMessageEndpoint {
  SendMessageServiceImpl messageService;

  public SendMessageEndpoint(SendMessageServiceImpl messageService) {
    this.messageService = messageService;
  }

  @GetMapping("/send")
  public String send() {
    return messageService.send();
  }
}
