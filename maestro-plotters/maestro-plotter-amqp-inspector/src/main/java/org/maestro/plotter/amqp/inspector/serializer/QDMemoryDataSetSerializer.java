/*
 * Copyright 2018 Otavio R. Piske <angusyoung@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.maestro.plotter.amqp.inspector.serializer;

import org.maestro.plotter.amqp.inspector.memory.QDMemoryDataSet;
import org.maestro.plotter.amqp.inspector.memory.QDMemoryReader;

public class QDMemoryDataSetSerializer extends MultiDataSetSerializer<QDMemoryDataSet, QDMemoryReader> {
    private static final String dataName = "qd-memory";

    public QDMemoryDataSetSerializer() {
        super(new QDMemoryReader(), dataName);
    }
}
