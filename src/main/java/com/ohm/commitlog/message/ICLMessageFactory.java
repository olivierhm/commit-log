package com.ohm.commitlog.message;

public interface ICLMessageFactory<MessageData> {
    /**
     * createMessage creates a new message with the passed in data and a unique ID
     * Used on the client side.
     * 
     * @param data The message payload of type MessageData
     * @return the new message of type ICLMessage<MessageData>
     */
    public ICLMessage<MessageData> createMessage(MessageData data);
}


