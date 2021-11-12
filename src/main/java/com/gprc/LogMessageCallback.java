package com.gprc;

import com.grpc.LogMessageAck;
import io.grpc.stub.StreamObserver;

public class LogMessageCallback implements StreamObserver<LogMessageAck> {
  @Override
  public void onNext(LogMessageAck logMessageAck) {
// TODO add logic latch.countDown + check response
  }

  @Override
  public void onError(Throwable throwable) {
  }

  @Override
  public void onCompleted() {

  }
}
