<!doctype html>
<html>
<head>
<asset:stylesheet src="bootstrap.css"/>
<asset:javascript src="bootstrap.js"/>
<asset:javascript src="jquery-2.2.0.min.js"/>

<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<body>
	<ul class="nav nav-tabs">
		<li class="active"><a data-toggle="tab" href="#table">Table</a></li>
		<li><a data-toggle="tab" href="#chart">Chart</a></li>
	</ul>
	<div class="tab-content">
    	<div id="table" class="tab-pane fade in active">
    		<table class="table">
				<thead>
					<tr>
						<th>Date</th>
						<th>USD Rate</th>
						<th>EUR Rate</th>
					</tr>
				</thead>
				<tbody>
				    <g:each var="rate" in="${rates}">
				        <tr>
				        	<td>${rate.date}</td>
				        	<td style='background-color: ${rate.usdRate == maxUsdRate ? 'red' : ( rate.usdRate == minUsdRate ? 'blue' : 'none' )  } ;'>${rate.usdRate?.rate}</td>
				        	<td style='background-color: ${rate.eurRate == maxEurRate ? 'red' : ( rate.eurRate == minEurRate ? 'blue' : 'none' )  } ;'>${rate.eurRate?.rate}</td>
				        </tr>
				    </g:each>
				</tbody>
		    </table>
    	</div>
		<div id="chart" class="tab-pane fade">
			<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"/>
			<g:javascript>
Highcharts.chart('container', {
    chart: {
        type: 'spline'
    },
    title: {
        text: 'Dynamic currency rates'
    },
    xAxis: {
        type: 'datetime',
        dateTimeLabelFormats: { // don't display the dummy year
            month: '%e. %b',
            year: '%b'
        },
        title: {
            text: 'Date'
        }
    },
    yAxis: {
        title: {
            text: 'RUB'
        },
        min: 0
    },
    tooltip: {
        headerFormat: '<b>{series.name}</b><br>',
        pointFormat: '{point.x:%e. %b}: {point.y:.2f} m'
    },

    plotOptions: {
        spline: {
            marker: {
                enabled: true
            }
        }
    },

    series: [{
        name: 'USD Rates',
        data: ${usdSeries}
    },{
        name: 'EUR Rates',
        data: ${eurSeries}
    }]
});
</g:javascript>
		</div>
	</div>
	
    
</body>
</html>
