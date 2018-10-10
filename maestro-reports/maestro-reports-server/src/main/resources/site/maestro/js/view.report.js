$(document).ready(function () {
    var reportId = getUrlVars()["report-id"];
    var testId = getUrlVars()["test-id"];
    var testNumber = getUrlVars()["test-number"];

    var url = $('[data-datatables]').attr('data-api') + '/report/' + reportId + '/properties';

    console.log("Loading data from " + url)
    maestroDataTable('[data-datatables]', url)
})

function groupedBarGraphServiceTime(response, element, groups, yLabel) {
    var chartData = response.data

    var c3ChartDefaults = $().c3ChartDefaults();
    var lineChartConfig = c3ChartDefaults.getDefaultLineConfig();
    lineChartConfig.bindto = element;

    lineChartConfig.data = {
        x: 'Percentiles',
        json: chartData,
    };

    lineChartConfig.zoom = {
        enabled: true,
        type: 'drag',
    };

    lineChartConfig.legend = {
        position: 'right'
    };

    lineChartConfig.axis = {
        x: {
            label: {
                text: 'Percentiles',
            },
            tick: {
                rotate: 90,
                multiline: false,
                // Uses https://github.com/d3/d3-format
                format: d3.format(".4f")
            }
        },
        y: {
            label: {
                text: 'Milliseconds',
            }
        }
    };
    lineChartConfig.point = {
        show: false
    }

    var lineChart = c3.generate(lineChartConfig);
}

function setPercentileTable(statistics, type) {
    if (statistics != null) {
        $("#perTotalCount" + type).text(statistics.latencyTotalCount);
        $("#maxLatency" + type).text(statistics.latencyMaxValue);
        $("#99999percentile" + type).text(statistics.latency99999th);
        $("#9999percentile" + type).text(statistics.latency9999th);
        $("#999percentile" + type).text(statistics.latency999th);
        $("#99percentile" + type).text(statistics.latency99th);
        $("#95percentile" + type).text(statistics.latency95th);
        $("#90percentile" + type).text(statistics.latency90th);
        $("#50percentile" + type).text(statistics.latency50th);
    }
}

function fillPercentileInformation() {
    var reportId = getUrlVars()["report-id"];

    var latDistributionUrl = $('[graphs]').attr('graph-api') + reportId;
    axios.get(latDistributionUrl).then(function (response) {
        var element = '#bar-chart-3';
        var groups = [];
        var yLabel = 'Milliseconds';

        groupedBarGraphServiceTime(response, element, groups, yLabel)

    })
    .catch(function (error) {
        console.log(error);
    });

//
    var latPropertiesUrl = $('[lat-properties]').attr('lat-prop-api') + reportId;
    axios.get(latPropertiesUrl).then(function (response) {
        setPercentileTable(response.data.ServiceTimeStatistics[0], "Service")
        setPercentileTable(response.data.ResponseTimeStatistics[0], "Response")
    })
    .catch(function (error) {
        console.log(error);
    });
}

$(document).ready(function () {
    fillPercentileInformation()
})