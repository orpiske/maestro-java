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

package org.maestro.reports.controllers;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.javalin.Context;
import org.maestro.common.HostTypes;
import org.maestro.common.exceptions.MaestroException;
import org.maestro.plotter.common.serializer.SingleData;
import org.maestro.plotter.rate.serializer.RateSerializer;
import org.maestro.reports.controllers.common.RateStatisticsResponse;
import org.maestro.reports.controllers.common.Response;
import org.maestro.reports.dao.ReportDao;
import org.maestro.reports.dto.Report;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class RateStatisticsReportController extends CommonRateReportController {
    private final ReportDao reportDao = new ReportDao();

    @Override
    public void handle(Context context) throws Exception {
        try {
            int id = Integer.parseInt(context.param("id"));

            Report report = reportDao.fetch(id);

            SingleData<Long> rateData = processReport(report, report.getTestHostRole());

            RateStatisticsResponse rateResponse = new RateStatisticsResponse();
            rateResponse.addStatistics(rateData.getStatistics());

            context.json(rateResponse);
        }
        catch (Throwable t) {
            context.status(500);
            context.result(String.format("Internal server error: %s", t.getMessage()));
        }
    }
}
