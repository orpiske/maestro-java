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

package org.maestro.reports.controllers;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import org.maestro.common.HostTypes;
import org.maestro.common.exceptions.MaestroException;
import org.maestro.plotter.common.serializer.SingleData;
import org.maestro.plotter.rate.serializer.RateSerializer;
import org.maestro.reports.controllers.common.Response;
import org.maestro.reports.dto.Report;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

abstract class CommonRateReportController extends AbstractReportFileController {
    class RateResponse implements Response {
        @JsonProperty("Periods")
        private List<Date> periods = new LinkedList<>();

        @JsonProperty("Rate")
        private List<Long> rate = new LinkedList<>();

        public List<Date> getPeriods() {
            return periods;
        }

        public void setPeriods(List<Date> periods) {
            this.periods = periods;
        }

        public List<Long> getRate() {
            return rate;
        }

        public void setRate(List<Long> rate) {
            this.rate = rate;
        }
    }

    @NotNull
    protected File getReportFileForRole(final Report report, final String hostHole) {
        File reportFile;

        if (hostHole.equals(HostTypes.RECEIVER_HOST_TYPE)) {
            reportFile = getReportFile(report, "receiver.dat");
        }
        else {
            if (hostHole.equals(HostTypes.SENDER_HOST_TYPE)) {
                reportFile = getReportFile(report, "sender.dat");
            }
            else {
                throw new MaestroException("This host type does not support rate information");
            }
        }
        return reportFile;
    }


    protected SingleData<Long> processReport(final Report report, final String hostRole) throws IOException {
        File reportFile = getReportFileForRole(report, hostRole);

        RateSerializer rateSerializer = new RateSerializer();
        return rateSerializer.serialize(reportFile);
    }


}
