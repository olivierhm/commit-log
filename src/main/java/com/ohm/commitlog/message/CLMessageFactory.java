package com.ohm.commitlog.message;

import java.util.UUID;

import com.fasterxml.uuid.Generators;

public class CLMessageFactory<MessageData> implements ICLMessageFactory<MessageData> {

    // Each Commit Log message factory instance is dedicated to a given Commit ID (cid)
    private final String cid;

    public CLMessageFactory(String commitId) {
        this.cid = commitId;
    }

    // Factory method to create new messages
    public CLMessage<MessageData> createMessage(MessageData data) {
        UUID uuid = Generators.timeBasedGenerator().generate();
        return new CLMessage<MessageData>(this.cid, uuid, data);
    }

    // Retrieve the commit ID this message factory is for.
    public String getCommitId() {
        return this.cid;
    }
}


