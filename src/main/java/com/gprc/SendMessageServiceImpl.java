package com.gprc;

import java.util.Random;

import org.springframework.stereotype.Service;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import com.grpc.SendMessageServiceGrpc;
import com.grpc.LogMessageAck;

@Service
public class SendMessageServiceImpl {
  public String send() {
    ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090)
                                                  .usePlaintext()
                                                  .build();
    SendMessageServiceGrpc.SendMessageServiceBlockingStub stub = SendMessageServiceGrpc.newBlockingStub(channel);
    Random random = new Random();
    int id = random.nextInt();
    LogMessageAck sendResponse = stub.send(com.grpc.LogMessage.newBuilder()
                                                                 .setId(id)
                                                                 .setText("Message " + id)
                                                                 .build());
    channel.shutdownNow();
    return "ID " + sendResponse.getId() + " " + sendResponse.getStatus();
  }
}
