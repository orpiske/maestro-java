/*
 * Copyright 2018 Otavio Rodolfo Piske
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

package org.maestro.reports.common.organizer;

import org.maestro.client.exchange.support.PeerInfo;

/**
 * Provides an interface to organize the files in a report directory
 */
public interface Organizer<T> {

    /**
     * Organize the report directory
     * @param meta An object containing sufficient information for organizing the directory layout
     * @return The organized directory layout to use
     */
    String organize(final T meta);
}
