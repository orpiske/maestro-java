function percentileRender(data, type, full, meta) {
    if (full.envResourceRole == "sender") {
        return "N/A"
    }

    return  '<span> ' + ( Number(data).toFixed(2) / 1000) + ' ms</span>'
}

function rateRender(data, type, full, meta) {
    return  '<span> ' + data + ' msg/sec</span>'
}

function resultRender(data, type, full, meta) {
    if (data == "success") {
        return '<span class="pficon pficon-ok"></span> ' + data
    }
    else {
        return '<span class="pficon pficon-error-circle-o"></span> ' + data
    }
}

function simpleDateRender(data, type, full, meta) {
    var date = moment(data)

    return date.format('DD/MMM/Y HH:mm:ss z')
}


function renderRounded(data, type, full, meta) {
     return Number(data).toFixed(2);
}

function renderRoundedRate(data, type, full, meta) {
    return '<span> ' + renderRounded(data, type, full, meta) + ' msg/sec</span>'
}

function renderTestFull(data, type, full, meta) {
    return '<a href=\"view-test-report.html?test-id=' + full.testId + '&test-number=' + full.testNumber +
    '\"><span class=\"pficon pficon-cluster\"></span> View Test ' + full.testId +'</a>';
}

function renderReportIdFull(data, type, full, meta) {
    return '<a href=\"view-report.html?report-id=' + full.reportId + '&test-id=' + full.testId + '&test-number='
    + full.testNumber +'\"><span class=\"pficon pficon-container-node\"></span> View Node Report ' + data +'</a>';
}
function renderLink(data, type, full, meta) {
    return '<a href=\"' + full.link + '\"><span class=\"pficon pficon-save\"></span> Download</a>';
}