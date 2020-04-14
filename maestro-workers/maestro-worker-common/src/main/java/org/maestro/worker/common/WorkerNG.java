package org.maestro.worker.common;

import io.etcd.jetcd.ByteSequence;
import io.etcd.jetcd.Client;
import io.etcd.jetcd.Watch;
import io.etcd.jetcd.watch.WatchEvent;
import org.maestro.client.exchange.support.PeerInfo;

import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;

public class WorkerNG {
    private final PeerInfo peerInfo;
    private final String endpoints;

    public WorkerNG(PeerInfo peerInfo, final String endpoints) {
        this.peerInfo = peerInfo;
        this.endpoints = endpoints;
    }


    public void run() {
        CountDownLatch latch = new CountDownLatch(1);

        Watch.Listener listener = Watch.listener(
                response -> {
                    for (WatchEvent event : response.getEvents()) {
                        System.out.println("Received a event: " + event.getEventType());
                        System.out.println("Received data: " + Optional.ofNullable(event.getKeyValue().getValue()).map(bs -> bs.toString(StandardCharsets.UTF_8))
                                .orElse(""));
                    }

                    latch.countDown();
                }
        );

        ByteSequence key = ByteSequence.from("worker", StandardCharsets.UTF_8);

        try (Client client = Client.builder().endpoints(endpoints).build();
            Watch watch = client.getWatchClient();
            Watch.Watcher watcher = watch.watch(key, listener)) {
                latch.await();

        } catch (Throwable e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }


    }
}
