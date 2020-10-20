package com.ohm.commitlog.message;

import java.util.UUID;


public class CLMessage<MessageData> implements ICLMessage<MessageData> {
    private final String cid;
    private final UUID uuid;
    private final MessageData data;


	public String getCommitId() {
        return this.cid;
    }

    public String getUniqueId() {
        return this.uuid.toString();
    }
    
    public MessageData getData() {
        return this.data;
    }

    @Override
    public String toString() {
        return "CLMessage: {" + "CID=" + cid + ", UUID=" + uuid + ", msg=" + data.toString() + "}";
    }

    /**
     * @param commitId Commit Log ID
     * @param uuid Unique identifier
     * @param data message payload
     */
    public CLMessage(String commitId, UUID uuid, MessageData data) {
        this.cid = commitId;
        this.uuid = uuid;
        this.data = data;
    }

}
