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
 *
 */

package org.maestro.reports.controllers.common;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

public class TestLatenciesResponse implements Response {
//    @JsonProperty("Percentiles")
//    private Set<String> percentiles = new LinkedHashSet<>();

    @JsonProperty("Service Time")
    private final Map<String, List<Double>> serviceTime = new HashMap<>();

    @JsonProperty("Response Time")
    private final Map<String, List<Double>> responseTime = new HashMap<>();

//    public Set<String> getPercentiles() {
//        return percentiles;
//    }

    public Map<String, List<Double>> getServiceTime() {
        return serviceTime;
    }

    public Map<String, List<Double>> getResponseTime() {
        return responseTime;
    }

    public static String categoryName(int testId, int testNumber, String name) {
        return String.format("%d/%d %s", testId, testNumber, name);
    }
}