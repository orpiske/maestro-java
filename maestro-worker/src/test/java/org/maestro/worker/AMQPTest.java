/*
 *  Copyright 2017 Otavio R. Piske <angusyoung@gmail.com>
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.maestro.worker;

import net.orpiske.jms.provider.activemq.ActiveMqProvider;
import net.orpiske.jms.test.annotations.Provider;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.maestro.client.Maestro;
import org.maestro.worker.tests.support.annotations.MaestroPeer;
import org.maestro.worker.tests.support.annotations.ReceivingPeer;
import org.maestro.worker.tests.support.annotations.SendingPeer;
import org.maestro.worker.tests.support.runner.AMQPBrokerConfiguration;
import org.maestro.worker.tests.support.runner.MiniPeer;
import org.maestro.worker.tests.support.runner.WorkerTestRunner;

@Ignore
@SuppressWarnings("unused")
@RunWith(WorkerTestRunner.class)
@Provider(
        value = ActiveMqProvider.class,
        configuration = AMQPBrokerConfiguration.class)
public class AMQPTest extends AbstractProtocolTest {

    @ReceivingPeer
    private MiniPeer miniReceivingPeer;

    @SendingPeer
    private MiniPeer miniSendingPeer;

    @MaestroPeer
    private Maestro maestro;

    @Before
    public void setUp() throws Exception {
        setupMaestroConnectionProperties();

        miniSendingPeer.start();
        miniReceivingPeer.start();
        System.out.println("Mini peers have started");
    }

    @After
    public void tearDown() {
       stopWorkers(maestro);

        miniSendingPeer.stop();
        miniReceivingPeer.stop();
    }

    @Test(timeout = 300000)
    public void testFixedCountTest() throws Exception {
        testFixedCountTest(maestro, "amqp://localhost:5672/unit.test.queue");
    }
}
