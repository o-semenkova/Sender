package com.gprc.sender;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import org.springframework.stereotype.Service;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import com.grpc.sender.SendMessageServiceGrpc;
import com.grpc.sender.SendMessageResponse;

@Service
public class SendMessageServiceImpl {
  public String send() {
    ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090)
                                                  .usePlaintext()
                                                  .build();
    SendMessageServiceGrpc.SendMessageServiceBlockingStub stub = SendMessageServiceGrpc.newBlockingStub(channel);
    int counter = 1;

    ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    AtomicBoolean done = new AtomicBoolean();
    scheduler.schedule(() -> done.set(true), 10, TimeUnit.SECONDS);
    SendMessageResponse sendResponse = null;
    while (!done.get()) {
      sendResponse = stub.send(com.grpc.sender.SendMessageRequest.newBuilder()
                                                                 .setId(counter)
                                                                 .setText("Message " + counter)
                                                                 .build());
      counter++;
    }
    scheduler.shutdownNow();
    channel.shutdownNow();

    return sendResponse.getId();
  }
}
