package com.gprc;

import org.springframework.stereotype.Service;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import com.grpc.LogMessage;
import com.grpc.SendMessageServiceGrpc;

@Service
public class SendMessageServiceImpl {
  public void sendmsg(LogMessage message) throws InterruptedException {
    ManagedChannel channel;
    channel = ManagedChannelBuilder.forAddress("master-grpc", 9090)
                                   .usePlaintext()
                                   .build();
    SendMessageServiceGrpc.SendMessageServiceStub stub = SendMessageServiceGrpc.newStub(channel);
    stub.send(message, new LogMessageCallback());

    Thread.sleep(3000);
    channel.shutdownNow();
  }
}
