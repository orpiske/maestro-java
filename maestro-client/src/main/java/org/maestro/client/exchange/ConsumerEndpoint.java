package org.maestro.client.exchange;

import java.util.function.BiConsumer;

public interface ConsumerEndpoint {
    String getClientId();

    boolean isConnected();

    void connect();
    void disconnect();
    void subscribe(String[] endpoints);
}
