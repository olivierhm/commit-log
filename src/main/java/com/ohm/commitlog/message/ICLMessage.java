package com.ohm.commitlog.message;

public interface ICLMessage<MessageData> {
    public String getCommitId();
    public String getUniqueId();
    public MessageData getData();
}
